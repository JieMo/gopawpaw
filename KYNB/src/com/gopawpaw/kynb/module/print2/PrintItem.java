package com.gopawpaw.kynb.module.print2;
/**     
 * @author 卢向琪 luxiangqi521@gmail.com    
 * @version 1.0     
 */

public class PrintItem {
	
	public static final String SYSDATE_FLAG = "系统时间";
	
	private String id;
	private String code;
	private String name;
	private int mapIndex;
	private String defValue;
	private int x;
	private int y;
	private boolean enAble;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMapIndex() {
		return mapIndex;
	}
	public void setMapIndex(int mapIndex) {
		this.mapIndex = mapIndex;
	}
	public String getDefValue() {
		return defValue;
	}
	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isEnAble() {
		return enAble;
	}
	public void setEnAble(boolean enAble) {
		this.enAble = enAble;
	}
}
