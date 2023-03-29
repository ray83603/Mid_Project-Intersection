package com.Project.java;

public class PaperLight {
	public static final int light_red = 0;		//燈顏色常量-紅色
	public static final int light_green = 1;	//燈顏色常量-綠色
	public static final int light_black = 2;	//燈顏色常量-停黑色
	public static final int light_go = 3;	//燈顏色常量-走色
	
	String light_img[] = {"Lpp/Red.png", "Lpp/Green.png", "Lpp/stop2.png", "Lpp/ucango.png"};		//圖片路徑
	int x, y;									//燈在面板的位置
	int status;									//燈的三種狀態
	
	public PaperLight(int x, int y, int status) {
		this.x = x;
		this.y = y;
		this.status = status;
	}
	
	public String getphoto() {					//得到紅綠燈圖片
		return this.light_img[status];
	}	//getphoto結束
	
	public int get_status() {					//對外得到燈的狀態
		return this.status;
	}
}
