package com.gopawpaw.kynb.module.datascan2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.gopawpaw.frame.dev.common.GppJarRunableInterface;
import com.gopawpaw.kynb.GlobalUI;
import com.gopawpaw.kynb.bean.OtherData;
import com.gopawpaw.kynb.common.ExcelFileFilter;
import com.gopawpaw.kynb.common.PoiOperatXls;
import com.gopawpaw.kynb.common.Progress;
import com.gopawpaw.kynb.module.BaseModuleFrame;

public class DataScanFrame2 extends BaseModuleFrame implements
		GppJarRunableInterface {
	private static final long serialVersionUID = 3688309249432143888L;
	
	private static final String[] dbtColumnNames = {OtherData.ACOL,
		OtherData.BCOL, OtherData.CCOL, OtherData.DCOL,
		OtherData.ECOL, OtherData.FCOL, OtherData.GCOL,
		OtherData.HCOL, OtherData.ICOL, OtherData.JCOL };
	
	private static List<ScanItem> scanItemList = new ArrayList<ScanItem>();
	
	private static DataScanFrame2 thisClass;
	private static ExcelDataTablePane excelDataTablePane = null;
	private static OptBtnsPanel optBtnsPanel = null;
	// private static List<ScanItem> siList = new ArrayList<ScanItem>();
	private static Map<String, Integer> scanMap = null;

	// �ļ�ѡ��ؼ�
	private static final JFileChooser fileChooser = new JFileChooser();

	/**
	 * ��̬����� 1��Ϊɨ�����б�����ʼֵ 2��Ϊ�ļ�ѡ��Ի��������ļ�������
	 */
	static {
		//���ó�ʼĬ��ɨ����
		scanMap = new HashMap<String, Integer>();
		scanMap.put(OtherData.ACOL, 0);
		
		//------------------------------------------------
		for(int i = 0; i < dbtColumnNames.length; i++ ) {
			ScanItem si = new ScanItem();
			si.setCheck(true);
			si.setDbtColumnName(dbtColumnNames[i]);
			si.setDbtExample("");
			si.setExcelColumnName("");
			scanItemList.add(si);
		}		

		// �����ļ�������
		fileChooser.setFileFilter(new ExcelFileFilter());
	}

	public DataScanFrame2() {
		excelDataTablePane = new ExcelDataTablePane();
		optBtnsPanel = new OptBtnsPanel();
		optBtnsPanel.setSize(new Dimension(100, 600));
		optBtnsPanel.setPreferredSize(new Dimension(100, 600));
		setLayout(new BorderLayout());
		add(excelDataTablePane, BorderLayout.CENTER);
		add(optBtnsPanel, BorderLayout.EAST);
		setSize(900, 600);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GlobalUI.initUI();
				// IdnoUpgrade thisClass = new IdnoUpgrade();
				thisClass = new DataScanFrame2();
				// thisClass.setVisible(true);
				thisClass.setVisible(true);
				optBtnsPanel.setMainFrame(thisClass);
			}
		});
	}

	@Override
	public boolean runJar(String[] args) {
		DataScanFrame2.main(args);
		return false;
	}

	public static DataScanFrame2 getThisClass() {
		return thisClass;
	}

	public static void setThisClass(DataScanFrame2 thisClass) {
		DataScanFrame2.thisClass = thisClass;
	}

	public ExcelDataTablePane getExcelDataTablePane() {
		return excelDataTablePane;
	}

	public void setExcelDataTablePane(ExcelDataTablePane excelDataTablePane) {
		DataScanFrame2.excelDataTablePane = excelDataTablePane;
	}

	public OptBtnsPanel getOptBtnsPanel() {
		return optBtnsPanel;
	}

	public void setOptBtnsPanel(OptBtnsPanel optBtnsPanel) {
		DataScanFrame2.optBtnsPanel = optBtnsPanel;
	}

	public JFileChooser getFilechooser() {
		return fileChooser;
	}

	public Map<String, Integer> getScanMap() {
		return scanMap;
	}

	/**
	 * ִ�е���Excel�ļ�
	 * 
	 * @param file
	 */
	public void executImportExcel(File file) {
		if (file == null)
			return;
		ImportExcelProgree iep = new ImportExcelProgree(file);
		iep.getProgressBar().setString("���ڼ������ݣ������ĵȴ���������");
		iep.getProgressBar().setIndeterminate(true);
		iep.start();
	}

	/**
	 * ִ�е���Excel�ļ�
	 * 
	 * @param file
	 */
	public void executlScanning() {
		ScanningProgress sp = new ScanningProgress();
		//sp.getProgressBar().setString("����ɨ�����ݣ������ĵȴ���������");
		sp.getProgressBar().setIndeterminate(false);
		sp.start();
	}

	/**
	 * ִ�е���Excel�ļ�
	 * 
	 * @param file
	 */
	public void executExportExcel(File file) {
		if (file == null)
			return;
		ExportExcelProgress eep = new ExportExcelProgress(file);
		eep.getProgressBar().setString("���ڵ������ݣ������ĵȴ���������");
		eep.getProgressBar().setIndeterminate(true);
		eep.start();
	}

	/**
	 * ����Excel�߳��࣬��ʾ������
	 * 
	 * @author lxq
	 * 
	 */
	class ImportExcelProgree extends Progress {
		private File file = null;

		public ImportExcelProgree(File file) {
			super(DataScanFrame2.this);
			this.file = file;
		}

		@Override
		public void execut() {
			Object[][] excelData = PoiOperatXls.readXlsRTA(file);
			getExcelDataTablePane().refreshTableByOriginalData(excelData);
		}
	}

	/**
	 * ɨ�������߳��࣬��ʾ������
	 * 
	 * @author lxq
	 * 
	 */
	class ScanningProgress extends Progress {
		public ScanningProgress() {
			super(DataScanFrame2.this);
		}

		public void execut() {
			ScanningListener sl = new ScanningListener() {
				@Override
				public void onScanningPre(int size) {
					getProgressBar().setMaximum(size);
				}

				@Override
				public void onScanningProgress(int n) {
					getProgressBar().setString("");
					getProgressBar().setValue(n);
					getProgressBar().setString("���ȣ�"+ n + "/"+getProgressBar().getMaximum());
				}
			};
			
			DataScanning ds = new DataScanning();
			// ��ñ���ԭʼ���ݣ���ɨ������
			Object[][] sranResult = ds.scanning(scanMap,
					getExcelDataTablePane().getOriginalData(), sl);
			// ˢ�±���
			getExcelDataTablePane().refreshTable(sranResult);
		}
	}

	/**
	 * ����Excel�߳��࣬��ʾ������
	 * 
	 * @author lxq
	 * 
	 */
	class ExportExcelProgress extends Progress {
		private String message = "";
		private File file = null;

		public ExportExcelProgress(File file) {
			super(DataScanFrame2.this);
			this.file = file;
		}

		public void execut() {
			// ��ȡ�ļ�����ı�������
			Object[][] excelData = getExcelDataTablePane().getHaveTitleData();

			// �����ļ�
			boolean rv = PoiOperatXls.writeXls(excelData, file.getPath());

			Toolkit.getDefaultToolkit().beep();
			if (rv) {
				message = "�ɹ������ļ���" + file.getPath();
				JOptionPane.showMessageDialog(null, message, "�����ļ���ʾ��",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				message = "�޷������ļ���" + file.getPath();
				JOptionPane.showMessageDialog(null, message, "�����ļ���ʾ��",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void setDialogLocaltion(JDialog dialog) {
		JFrame owner = thisClass;
		int left = (owner.getWidth() - dialog.getWidth()) / 2 + owner.getX();
		int top = (owner.getHeight() - dialog.getHeight()) / 2 + owner.getY();
		dialog.setLocation(left, top);
	}

	public static String[] getDbtcolumnnames() {
		return dbtColumnNames;
	}
	
	public static List<ScanItem> getScanItemList() {
		return scanItemList;
	}

	public static void setScanItemList(List<ScanItem> scanItemList) {
		DataScanFrame2.scanItemList = scanItemList;
	}
}