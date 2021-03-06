package com.gopawpaw.kynb.module.print;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.gopawpaw.frame.GlobalParameter;
import com.gopawpaw.kynb.GlobalUI;
import com.gopawpaw.kynb.module.BaseModuleFrame;
import com.gopawpaw.kynb.utils.StringConstant;

public class PrintFrame extends BaseModuleFrame {
	private static final long serialVersionUID = 6612665480808401495L;

	private LicenseTable spnLT;
	private QueryPanel pnlQuery;
	
	private JPanel pnlOptBtn;
	private JButton btnPrintOne;
	//private JButton btnPrintList;
	
	public PrintFrame() {
		if(!GlobalParameter.isAuthModuls){
			//非法授权
			JOptionPane.showConfirmDialog(null, StringConstant.isNotAuthMsg,
					"系统提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		setTitle("证件打印");
		spnLT = new LicenseTable();
		pnlQuery = new QueryPanel(this);
		
		pnlOptBtn = new JPanel();
		btnPrintOne = new JButton("打印");
		//btnPrintList = new JButton("打印列表");
		
		btnPrintOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LicenseDto licenseDto = spnLT.getSelectLicenseDto();
				if(licenseDto != null) {
					if(licenseDto.getPrintFlag() == 1) {
						Toolkit.getDefaultToolkit().beep();
						int rv = JOptionPane.showConfirmDialog(PrintFrame.this,
								"该记录标记为已打印，是否重新打印？", "打印提示！", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.WARNING_MESSAGE);
						if (rv == 0) {
							//创建打印预备按钮
							new PrintReviseDialog(PrintFrame.this, licenseDto);
						}						
					} else {
						//创建打印预备按钮
						new PrintReviseDialog(PrintFrame.this, licenseDto);
					}
				} else {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "请选择相应记录，再进行打印操作！", "操作提示！",
							JOptionPane.WARNING_MESSAGE);
				}
				
				
/*				License.readInitFile();
				License license = spnLT.getSelectLicense();
				if(license != null) {
					new PreviewDialog(PrintFrame.this, license);
				}*/
			}
		});
		
		pnlOptBtn.add(btnPrintOne);
		//pnlOptBtn.add(btnPrintList);
		
		setLayout(new BorderLayout());
		add(pnlQuery, BorderLayout.NORTH);
		add(pnlOptBtn, BorderLayout.EAST);
		add(spnLT, BorderLayout.CENTER);
		setSize(800, 600);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		com.gopawpaw.frame.GlobalParameter.isAuthModuls = true;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GlobalUI.initUI();
				PrintFrame thisClass = new PrintFrame();
				thisClass.showWithFrame();
			}
		});
	}
	
	public LicenseTable getSpnLT() {
		return spnLT;
	}
	
	public QueryPanel getQP() {
		return pnlQuery;
	}
}
