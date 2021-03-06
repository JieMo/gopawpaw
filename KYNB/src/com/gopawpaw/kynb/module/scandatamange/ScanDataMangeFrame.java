package com.gopawpaw.kynb.module.scandatamange;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.gopawpaw.frame.GlobalParameter;
import com.gopawpaw.kynb.GlobalUI;
import com.gopawpaw.kynb.bean.OtherData;
import com.gopawpaw.kynb.common.*;
import com.gopawpaw.kynb.module.BaseModuleFrame;
import com.gopawpaw.kynb.utils.StringConstant;

public class ScanDataMangeFrame extends BaseModuleFrame {

	private static final long serialVersionUID = -1184641427476379004L;

	private static QueryPanel pnlQuery;
	private TableScrollPane spnTable;
	private static OptBtnsPanel optBtnsPanel;
	private ProgressBarPanel pnlProgressBar;
	
	// 文件选择控件
	private static final JFileChooser fileChooser = new JFileChooser();

	/**
	 * 静态代码段 1、为扫描项列表赋初始值 2、为文件选择对话框设置文件过滤器
	 */
	static {
		// 设置文件过滤器
		fileChooser.setFileFilter(new ExcelFileFilter());
	}
	
	public ScanDataMangeFrame() {
		
		if(!GlobalParameter.isAuthModuls){
			//非法授权
			JOptionPane.showConfirmDialog(null, StringConstant.isNotAuthMsg,
					"系统提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		setTitle("自定数据库管理");

		// 创建线形边框
		LineBorder lineBorder = (LineBorder)BorderFactory.createLineBorder(Color.black);
		//创建数据库数据面板的边框
		TitledBorder qbBorder = BorderFactory.createTitledBorder(lineBorder, "查询条件");
		TitledBorder dlBorder = BorderFactory.createTitledBorder(lineBorder, "数据列表");
		
		pnlQuery = new QueryPanel(this);
		pnlQuery.setBorder(qbBorder);
		spnTable = new TableScrollPane();
		spnTable.setBorder(dlBorder);
		pnlProgressBar =  new ProgressBarPanel();
		optBtnsPanel = new OptBtnsPanel(this);
		optBtnsPanel.setSize(new Dimension(70, 600));
		optBtnsPanel.setPreferredSize(new Dimension(70, 600));
		
		setLayout(new BorderLayout());
		add(pnlQuery, BorderLayout.NORTH);
		add(spnTable, BorderLayout.CENTER);
		add(optBtnsPanel, BorderLayout.EAST);
		add(pnlProgressBar, BorderLayout.SOUTH);
		setSize(900, 600);
		
		//--------------------------------------------
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GlobalUI.initUI();
				ScanDataMangeFrame thisClass = new ScanDataMangeFrame();
				thisClass.showWithFrame();
			}
		});
	}

	/**
	 * 执行导入Excel文件
	 * 
	 * @param file
	 */
	public void executImportExcel(File file) {
		if (file == null)
			return;
		ImportExcelProgree iep = new ImportExcelProgree(pnlProgressBar, file);
		iep.start();
	}
	
	public void executExportExcel(File file) {
		if (file == null)
			return;
		ExportExcelProgress eep = new ExportExcelProgress(pnlProgressBar, file);
		eep.start();
	}
	
	public JFileChooser getFilechooser() {
		return fileChooser;
	}
	
	public TableScrollPane getSpnTable() {
		return spnTable;
	}
	
	public QueryPanel getPnlQuery() {
		return pnlQuery;
	}

	/**
	 * 导入Excel线程类，显示进度条
	 * 
	 * @author lxq
	 * 
	 */
	class ImportExcelProgree extends Progress {
		private File file = null;

		public ImportExcelProgree(ProgressBarPanel pnlProgressBar, File file) {
			super(pnlProgressBar);
			this.file = file;
		}

		@Override
		public void execut() {
			Object[][] excelData = PoiOperatXls.readXlsRTA(file, super.getListener());
			
			if(excelData[0].length > 10) {
				JOptionPane.showMessageDialog(null, "导入Excel表格列数不能大于10！", "导入Excel提示！",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			Object[][] tableData = new Object[excelData.length - 1][];
			List<OtherData> list = new ArrayList<OtherData>();
			OtherData odata = null;
			for(int i = 1; i < excelData.length ; i++) {
				tableData[i - 1] = excelData[i];
				odata = new OtherData();
				odata.setAcol("");
				odata.setBcol("");
				odata.setCcol("");
				odata.setDcol("");
				odata.setEcol("");
				odata.setFcol("");
				odata.setGcol("");
				odata.setHcol("");
				odata.setIcol("");
				odata.setJcol("");
				
				for(int j = 0; j < excelData[i].length; j++) {
					switch (j) {
					case 0:
						odata.setAcol((String)excelData[i][j]);
						break;
					case 1:
						odata.setBcol((String)excelData[i][j]);
						break;
					case 2:
						odata.setCcol((String)excelData[i][j]);
						break;
					case 3:
						odata.setDcol((String)excelData[i][j]);
						break;
					case 4:
						odata.setEcol((String)excelData[i][j]);
						break;
					case 5:
						odata.setFcol((String)excelData[i][j]);
						break;
					case 6:
						odata.setGcol((String)excelData[i][j]);
						break;
					case 7:
						odata.setHcol((String)excelData[i][j]);
						break;
					case 8:
						odata.setIcol((String)excelData[i][j]);
						break;
					case 9:
						odata.setJcol((String)excelData[i][j]);
						break;
					default:
						break;
					}
				}
				list.add(odata);
			}
			DataOpertor dot = new DataOpertor();
			if(dot.batSave(list, super.getListener())) {
				dot = new DataOpertor();
				spnTable.refreshTable(dot.findListAll());
				JOptionPane.showMessageDialog(null, "导入数据成功！", "导入Excel提示！",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "导入数据失败！", "导入Excel提示！",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
/*		private  OtherData createODByArray(String[] array) {
			return null;
		}*/
	}
	
	/**
	 * 导出Excel线程类，显示进度条
	 * 
	 * @author lxq
	 * 
	 */
	class ExportExcelProgress extends Progress {
		private String message = "";
		private File file = null;

		public ExportExcelProgress(ProgressBarPanel pbp, File file) {
			super(pbp);
			this.file = file;
		}

		public void execut() {
			// 获取文件保存的表格数据
			@SuppressWarnings("static-access")
			Object[] columnNaems = spnTable.getColumnNames();
			Object[][] data = spnTable.getData();

			// 保存文件
			boolean rv = PoiOperatXls.writeXls(
					data,
					columnNaems, 
					file.getPath(),
					super.getListener());

			Toolkit.getDefaultToolkit().beep();
			if (rv) {
				message = "成功保存文件：" + file.getPath();
				JOptionPane.showMessageDialog(null, message, "保存文件提示！",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				message = "无法保存文件：" + file.getPath();
				JOptionPane.showMessageDialog(null, message, "保存文件提示！",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
