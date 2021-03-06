/**
 * 
 */
package com.gopawpaw.kynb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 2011-11-26
 * @author Jason
 */
public class IDNumberChecker {
	/*********************************** 身份证验证开始 ****************************************/	
	/**
	 * 身份证号码验证 
	 * 1、号码的结构
	 * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
	 * 八位数字出生日期码，三位数字顺序码和一位数字校验码。
	 * 2、地址码(前六位数） 
	 * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 
	 * 3、出生日期码（第七位至十四位）
	 * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。 
	 * 4、顺序码（第十五位至十七位）
	 * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，
	 * 顺序码的奇数分配给男性，偶数分配给女性。 
	 * 5、校验码（第十八位数）
	 * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
	 * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
	 * 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0
	 * X 9 8 7 6 5 4 3 2
	 */

	/**
	 * 功能：身份证的有效验证
	 * @param IDStr 身份证号
	 * @return 有效：返回"" 无效：返回String信息
	 * @throws ParseException
	 */
	public static String IDCardValidate(String IDStr) throws ParseException {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
			"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
			"9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// ================ 号码的长度18位 或15位================
		//2012-03-30 卢向琪 修改
		if (IDStr.length() == 18 || IDStr.length() == 15) {
		} else {
			errorInfo = "身份证号码长度应该为18位或15位。";
			return errorInfo;			
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
				|| (gc.getTime().getTime() - s.parse(
						strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
			errorInfo = "身份证生日不在有效范围。";
			return errorInfo;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "身份证月份无效";
			return errorInfo;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "身份证日期无效";
			return errorInfo;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable<String, String> h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.equals(IDStr) == false) {
				errorInfo = "身份证无效，不是合法的身份证号码";
				return errorInfo;
			}
		} else {
			return "";
		}
		// =====================(end)=====================
		return "";
	}

	/**
	 * 身份证是合法
	 */
	public static final int IDCARD_IS_OK = 0;
	
	/**
	 * 身份证是15位 合法
	 */
	public static final int IDCARD_IS_OK_15 = 1;
	
	/**
	 *  身份证为null
	 */
	public static final int IDCARD_IS_NULL = 2;
	
	/**
	 *  身份证生日无效
	 */
	public static final int IDCARD_BITHDAY_FALSE = 3;
	
	/**
	 *  身份证生日不在有效范围
	 */
	public static final int IDCARD_BITHDAY_FALSE2 = 4;
	
	/**
	 *  身份证月份无效
	 */
	public static final int IDCARD_BITHDAY_FALSE_MONTH = 5;
	
	/**
	 *  身份证日期无效
	 */
	public static final int IDCARD_BITHDAY_FALSE_DATE = 6;
	
	/**
	 *  身份证地区编码错误
	 */
	public static final int IDCARD_ADDRESS_FALSE = 7;
	
	/**
	 *  身份证无效，不是合法的身份证号码
	 */
	public static final int IDCARD_CHECK_FALSE = 8;
	
	/**
	 *  身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。
	 */
	public static final int IDCARD_CHECK_FALSE_NUMBER = 9;
	
	/**
	 *  身份证号位数应该为15位或18位号码
	 */
	public static final int IDCARD_CHECK_FALSE_LENGTH= 10;
	
	/**
	 * 获取身份证错误信息
	 * @version 2012-4-3
	 * @author Jason
	 * @param
	 * @return String
	 */
	public static String getIDCardErrorInfo(int errorCode){
		
		String errorInfo = "";
		switch(errorCode){
		case IDCARD_IS_OK:
			errorInfo = "身份证是合法";
			break;
		case IDCARD_IS_OK_15:
			errorInfo = "身份证是15位 合法";
			break;
		case IDCARD_IS_NULL:
			errorInfo = "身份证号码为null";
			break;
		case IDCARD_BITHDAY_FALSE:
			errorInfo = "身份证生日无效";
			break;
		case IDCARD_BITHDAY_FALSE2:
			errorInfo = "身份证生日不在有效范围";
			break;
		case IDCARD_BITHDAY_FALSE_MONTH:
			errorInfo = "身份证月份无效";
			break;
		case IDCARD_BITHDAY_FALSE_DATE:
			errorInfo = "身份证日期无效";
			break;
		case IDCARD_ADDRESS_FALSE:
			errorInfo = "身份证地区编码错误";
			break;
		case IDCARD_CHECK_FALSE:
			errorInfo = "身份证无效，不是合法的身份证号码";
			break;
		case IDCARD_CHECK_FALSE_NUMBER:
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			break;
		case IDCARD_CHECK_FALSE_LENGTH:
			errorInfo = "身份证号位数应该为15位或18位号码";
			break;
		}
		
		return errorInfo;
	}
	/**
	 * 判断是否为15位身份证号码
	 * @version 2012-3-27
	 * @author Jason
	 * @param
	 * @return boolean
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public static int checkIDCard(String IDStr) throws NumberFormatException, ParseException{
		String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		String Ai = "";
		
		if (IDStr == null) {
			return IDCARD_IS_NULL;
		}
		
		// ================ 数字 除最后以为都为数字 ================
		boolean flag15 = false;
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			flag15 = true;
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		} else {
			return IDCARD_CHECK_FALSE_LENGTH;
		}
		if (isNumeric(Ai) == false) {
			//"身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return IDCARD_CHECK_FALSE_NUMBER;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			//"身份证生日无效。";
			return IDCARD_BITHDAY_FALSE;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
				|| (gc.getTime().getTime() - s.parse(
						strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
			//"身份证生日不在有效范围。";
			return IDCARD_BITHDAY_FALSE2;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			//"身份证月份无效";
			return IDCARD_BITHDAY_FALSE_MONTH;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			//"身份证日期无效";
			return IDCARD_BITHDAY_FALSE_DATE;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable<String, String> h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			//"身份证地区编码错误。";
			return IDCARD_ADDRESS_FALSE;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.equals(IDStr) == false) {
				//"身份证无效，不是合法的身份证号码";
				return IDCARD_CHECK_FALSE;
			}
		} else {
			if(flag15){
				return IDCARD_IS_OK_15;
			}else{
				
				return IDCARD_IS_OK;
			}
		}
		// =====================(end)=====================
		if(flag15){
			return IDCARD_IS_OK_15;
		}else{
			return IDCARD_IS_OK;
		}
	}
	
	/**
	 * 将15位身份证号码转换为18位身份证号码
	 * @version 2012-3-27
	 * @author Jason
	 * @param
	 * @return String
	 */
	public static String SwitchIDCard15To18(String IDStr){
		
		String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		
		if(IDStr == null || IDStr.length() != 15){
			return null;
		}
		
		String Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;
		
		return Ai;
	}

	/**
	 * 将18位身份证号码转换为15位身份证号码
	 * @version 2012-4-3
	 * @author Jason
	 * @param
	 * @return String 转换后的值，失败则返回错误信息
	 */
	public static String SwitchIDCard18To15(String IDStr){
		try {
			int check = checkIDCard(IDStr);
			if(check == IDCARD_IS_OK_15){
				return IDStr;
			}else if(check == IDCARD_IS_OK){
				String s1 = IDStr.substring(0, 6);
				String s2 = IDStr.substring(8, 17);
				return s1 + s2;
			}else{
				return getIDCardErrorInfo(check);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	/**
	 * 功能：设置地区编码
	 * @return Hashtable 对象
	 */
	private static Hashtable<String, String> GetAreaCode() {
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	/**
	 * 功能：判断字符串是否为数字
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：判断字符串是否为日期格式
	 * @param str
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// String IDCardNum="210102820826411";
		// String IDCardNum="210102198208264114";
		String IDCardNum = "500113860624521";
		
		System.out.println(IDNumberChecker.checkIDCard(IDCardNum));
		System.out.println(IDNumberChecker.SwitchIDCard15To18(IDCardNum));
		System.out.println(IDNumberChecker.SwitchIDCard18To15("500113198606245211"));
		// System.out.println(cc.isDate("1996-02-29"));
	}
/*********************************** 身份证验证结束 ****************************************/

	
	
}
