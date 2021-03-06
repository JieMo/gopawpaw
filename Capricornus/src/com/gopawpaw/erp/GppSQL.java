/**
 * 
 */
package com.gopawpaw.erp;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.gopawpaw.dev.common.GppCommonDBAccess;

/**
 * @version 2009-10-7
 * @author 李锦华
 */
public class GppSQL {
	private GppCommonDBAccess commonsql = null;
	private static String driveName = null;
	private static String connectName = null;
	private static String user = null;
	private static String password = null;

	/**
	 * 
	 */
	public GppSQL() {
		initialize();
	}

	/**
	 * 初始化连接
	 */
	private void initialize() {
		if (driveName == null || connectName == null) {
			initDrive();
		}

		commonsql = new GppCommonDBAccess();
		commonsql.setDriveName(driveName);
		commonsql.setConnectName(connectName);
		if (commonsql.connect(user, password)) {
//			 System.out.println("connect seccessed!");
		} else {
			System.err.println("connect failed!");

			JOptionPane.showMessageDialog(null, "数据库连接失败！请检查数据库连接", "错误",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 初始化数据库驱动连接信息
	 */
	private void initDrive() {

		connectName = GlobalParameter.getDatabaseInfo().getConnectName();
		driveName = GlobalParameter.getDatabaseInfo().getDriveName();
		user = GlobalParameter.getDatabaseInfo().getUser();
		password = GlobalParameter.getDatabaseInfo().getPassword();

	}

	/**
	 * 执行不返回结果的SQL
	 * 
	 * @param strsql
	 * @return 结果：成功（true） 失败（false）
	 */
	public boolean executesql(String strsql) {
		return commonsql.executesql(strsql);
	}

	/**
	 * 执行查询返回结果的SQL
	 * 
	 * @param strsql
	 * @return 结果集
	 */
	public ResultSet query(String strsql) {
		commonsql.query(strsql);
		return commonsql.getResultLists();
	}

	/**
	 * 关闭数据库操作对象
	 * 
	 * @return 结果：成功（true） 失败（false）
	 */
	public boolean close() {
		return commonsql.close();
	}

}
