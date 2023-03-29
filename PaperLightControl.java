package com.Project.java;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PaperLightControl extends Canvas implements Runnable{
	public static boolean Run = true;		//控制燈的線程運行
	private static final int light_width = 50;
	private static final int light_height = 50;
	private static final int repaint_time = 500;
	
	private int time;			//燈的時間
	private String Lampgroup_choice;		//燈組的選擇
	private char light_choice;				//燈的選擇
	private PaperLight light_left, light_right; //左燈（紅），右燈（綠)，黑燈黑
	
	//傳入一組燈，按順序，對每組燈進行相應控制
	public PaperLightControl(PaperLight light_left, PaperLight light_right,
			String Lampgroup_choice, char light_choice, int time) {
		this.light_left = light_left;
		this.light_right = light_right;
//		this.light_black = light_black;
//		this.light_ucango = light_ucango;
		this.light_choice = light_choice;
		this.Lampgroup_choice = Lampgroup_choice;
		this.time = time;
	}//light control結束
	
	public void paint_NS_Light_control(Graphics2D G) {			//畫南北燈
		G.drawImage(getToolkit().getImage(light_left.getphoto()), light_left.x, light_left.y, light_width, light_height, this);
		G.drawImage(getToolkit().getImage(light_right.getphoto()), light_right.x, light_right.y, light_width, light_height, this);
//		G.drawImage(getToolkit().getImage(light_black.getphoto()), light_black.x, light_black.y, light_width, light_height, this);
//		G.drawImage(getToolkit().getImage(light_ucango.getphoto()), light_ucango.x, light_ucango.y, light_width, light_height, this);
		G.setColor(Color.black);
	}


	public void paint_WE_Light_control(Graphics2D G) {			//畫東西燈
		G.drawImage(getToolkit().getImage(light_left.getphoto()), light_left.x, light_left.y, light_width, light_height, this);
		G.drawImage(getToolkit().getImage(light_right.getphoto()), light_right.x, light_right.y, light_width, light_height, this);
//		G.drawImage(getToolkit().getImage(light_black.getphoto()), light_black.x, light_black.y, light_width, light_height, this);
//		G.drawImage(getToolkit().getImage(light_ucango.getphoto()), light_ucango.x, light_ucango.y, light_width, light_height, this);
		G.setColor(Color.black);
	}
	
	public void paint(Graphics g) {
		Graphics2D G = (Graphics2D) g;
		switch(this.light_choice) {
		case 'N' :
			paint_NS_Light_control(G);			//調用南北燈的方法
			G.fillRect(light_left.x, light_left.y+104, light_width,light_height);		//畫矩形
			G.setColor(Color.red);
			G.setFont(new Font("Calibri", Font.BOLD, 35));							//字體,字型大小
			G.drawString(time+" ", light_left.x, light_left.y+140);					//倒數在紅燈的x,y邊
			break;
		case 'S' :
			paint_NS_Light_control(G);			//調用南北燈的方法
			G.fillRect(light_left.x, light_left.y+104, light_width,light_height);		//畫矩形
			G.setColor(Color.red);
			G.setFont(new Font("Calibri", Font.BOLD, 35));							//字體,字型大小
			G.drawString(time+" ", light_left.x, light_left.y+140);					//倒數在紅燈的x,y邊
			break;
		case 'E' :
			paint_WE_Light_control(G);			//調用東西燈的方法
			G.fillRect(light_left.x+104, light_left.y, light_width,light_height);		//畫矩形
			G.setColor(Color.red);
			G.setFont(new Font("Calibri", Font.BOLD, 35));							//字體,字型大小
			G.drawString(time+" ", light_left.x+104, light_left.y+40);					//倒數在紅燈的x,y邊
			break;
		case 'W' :
			paint_WE_Light_control(G);			//調用東西燈的方法
			G.fillRect(light_left.x+104, light_left.y, light_width,light_height);		//畫矩形
			G.setColor(Color.red);
			G.setFont(new Font("Calibri", Font.BOLD, 35));							//字體,字型大小
			G.drawString(time+" ", light_left.x+104, light_left.y+40);					//倒數在紅燈的x,y邊
			break;
		}	
	}
	@Override
	public void run() {
		String light_choice1 = "light_left";
		String light_choice2 = "stop";
		while(Run) {
			switch(Lampgroup_choice) {
			case "NS" :
				switch(light_choice1) {
				case "light_left" :
				{
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(this.time > 0) {
						light_left.status = PaperLight.light_red;	//開始時亮紅燈
						light_right.status = PaperLight.light_black;
					}
					if(time == 0) {
						this.time = 10;
						light_left.status = PaperLight.light_go;		//倒數到0的時候換亮綠燈
						light_right.status = PaperLight.light_green;	
						light_choice1 = "light_right";
					}	
				}
					break;
				case "light_right" :
				{
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(this.time > 0) {
						light_left.status = PaperLight.light_go;	//開始時亮綠燈
						light_right.status = PaperLight.light_green;
					}
					if(time == 0) {
						this.time = 10;
						light_left.status = PaperLight.light_red;	//倒數到0的時候換亮紅燈
						light_right.status = PaperLight.light_black;	
						light_choice1 = "stop";
					}	
				}
				break;
				case "stop" :
				{
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(this.time == 0) {
						time = 10;
						light_left.status = PaperLight.light_red;	//開始時亮紅燈
						light_right.status = PaperLight.light_black;
						light_choice1 = "light_left";
					}	
				}
					break;
			}
				break;
			case "WE" :
				switch(light_choice2) {
				case "light_left" :
				{
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(this.time > 0) {
						light_left.status = PaperLight.light_red;	//開始時亮紅燈
						light_right.status = PaperLight.light_black;
					}
					if(time == 0) {
						this.time = 10;
						light_left.status = PaperLight.light_go;
						light_right.status = PaperLight.light_green;	//開始時亮綠燈
						light_choice2 = "light_right";
					}	
				}
					break;
				case "light_right" :
				{
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(time > 0) {
						light_left.status = PaperLight.light_go;	//開始時亮紅燈
						light_right.status = PaperLight.light_green;
					}
					if(time == 0) {
						this.time = 10;
						light_left.status = PaperLight.light_red;
						light_right.status = PaperLight.light_black;	//開始時亮綠燈
						light_choice2 = "stop";
					}	
				}
				break;
				case "stop" :
				{
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(this.time == 0) {
						time = 10;
						light_left.status = PaperLight.light_red;	//開始時亮紅燈
						light_right.status = PaperLight.light_black;
						light_choice2 = "light_left";
					}
				}
				break;
				}
				break;
			}	
		}
	}
}