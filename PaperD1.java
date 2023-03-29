package com.Project.java;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaperD1 extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
private Timer timer;
private int viewW, viewH;
private JButton go, stop, redo, savePic ;
private BufferedImage trash, scooter, police, car;
	
//	JButton go = new JButton("Go!");
//	JButton stop = new JButton("Stop!");

	PaperLightControl lightControl1 = new PaperLightControl(light_N_left, light_N_right, "NS", 'N', 10);
	PaperLightControl lightControl2 = new PaperLightControl(light_S_left, light_S_right, "NS", 'S', 10);
	PaperLightControl lightControl3 = new PaperLightControl(light_E_left, light_E_right, "WE", 'E', 20);
	PaperLightControl lightControl4 = new PaperLightControl(light_W_left, light_W_right, "WE", 'W', 20);
	
		Thread f1 = new Thread(lightControl1);
		Thread f2 = new Thread(lightControl2);
		Thread f3 = new Thread(lightControl3);
		Thread f4 = new Thread(lightControl4);
	
	//建立燈
	//北(燈所設定的x,y座標，於下方200-50裏面)

	static PaperLight light_N_left = new PaperLight(0, 0, PaperLight.light_red);
	static PaperLight light_N_right = new PaperLight(0, 50, PaperLight.light_black);
//	static PaperLight light_N_black = new PaperLight(50, 0, PaperLight.light_black);
//	static PaperLight light_N_go = new PaperLight(0, 0, PaperLight.light_go);	
	//南
	static PaperLight light_S_left = new PaperLight(0, 0, PaperLight.light_red);
	static PaperLight light_S_right = new PaperLight(0, 50, PaperLight.light_black);
//	static PaperLight light_S_black = new PaperLight(100, 0, PaperLight.light_black);
//	static PaperLight light_S_go = new PaperLight(50, 0, PaperLight.light_go);
	//西
	static PaperLight light_E_left = new PaperLight(0, 0, PaperLight.light_red);
	static PaperLight light_E_right = new PaperLight(50, 0, PaperLight.light_black);
//	static PaperLight light_E_black = new PaperLight(0, 50, PaperLight.light_black);
//	static PaperLight light_E_go = new PaperLight(0, 0, PaperLight.light_go);
	//東
	static PaperLight light_W_left = new PaperLight(0, 0, PaperLight.light_red);
	static PaperLight light_W_right = new PaperLight(50, 0, PaperLight.light_black);
//	static PaperLight light_W_black = new PaperLight(0, 100, PaperLight.light_black);
//	static PaperLight light_W_go = new PaperLight(0, 50, PaperLight.light_go);

	PaperD1() {
		
		go = new JButton("Go!");
		stop = new JButton("Stop!");
//		redo = new JButton("Redo");
//		savePic = new JButton("SavePic");
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		go.addActionListener(this);
//		go.setBounds(50, 50, 50, 50);
		this.add(go);
		stop.addActionListener(this);
//		stop.setBounds(150, 50, 50, 50);
		this.add(stop);
		
		lightControl1.setBounds(190, 100, 50, 150);
		lightControl2.setBounds(560, 550, 50, 150);
		lightControl3.setBounds(560, 200, 150, 50);
		lightControl4.setBounds(90, 550, 150, 50);
		
		this.add(lightControl1);
		this.add(lightControl2);
		this.add(lightControl3);
		this.add(lightControl4);
		
//		
		setLayout(new BorderLayout());
		JPanel top = new JPanel(new FlowLayout());
		add(top, BorderLayout.NORTH);											//按鈕放在上面
		top.add(go);top.add(stop);				//top.add(redo);top.add(savePic);				//創建按鈕
		
		
		
		timer = new Timer();
		
//		try {
//			trash = ImageIO.read(new File("Lpp/Trash_car.png"));
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		
//		try {
//			scooter = ImageIO.read(new File("Lpp/Scooter.png"));
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		
//		try {
//			police = ImageIO.read(new File("Lpp/Police_car.png"));
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//		
//		try {
//			car = ImageIO.read(new File("Lpp/Car.png"));
//		} catch (IOException e) {
//			System.out.println(e);
//		}
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == go) {
			
			PaperLightControl.Run = true;
			f1.start();
			f2.start();
			f3.start();
			f4.start();
		}
		else if(e.getSource() == stop) {
			PaperLightControl.Run = false;
		}
	}
	
	
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		viewW = getWidth(); viewH = getHeight();
		Graphics2D g2d = (Graphics2D)g;											//把g2d轉型成graphics
		g2d.setColor(Color.darkGray);											//邊線顏色
		g2d.setStroke(new BasicStroke(6));										//邊線粗細
		g2d.drawLine(250, 0, 250, 260);g2d.drawLine(0, 260, 250, 260);			//左上2線
		g2d.drawLine(550, 0, 550, 260);g2d.drawLine(550, 260, 800, 260);		//右上2線
		g2d.drawLine(0, 540, 250, 540);g2d.drawLine(250, 540, 250, 800);		//左下2線
		g2d.drawLine(550, 540, 800, 540);g2d.drawLine(550, 540, 550, 800);		//右下2線
		
		g2d.setColor(Color.yellow);												//雙黃線顏色
		g2d.setStroke(new BasicStroke(3));										//雙黃線粗細
		g2d.drawLine(395, 0, 395, 220);g2d.drawLine(405, 0, 405, 220);			//上雙黃線
		g2d.drawLine(0, 395, 210, 395);g2d.drawLine(0, 405, 210, 405);			//左雙黃線
		g2d.drawLine(395, 580, 395, 800);g2d.drawLine(405, 580, 405, 800);		//下雙黃線
		g2d.drawLine(590, 395, 800, 395);g2d.drawLine(590, 405, 800, 405);		//右雙黃線
		
		g2d.setColor(Color.white);												//停止線顏色
		g2d.setStroke(new BasicStroke(8));										//停止線粗細
		g2d.drawLine(257, 220, 403, 220);g2d.drawLine(397, 580, 543, 580);		//上下停止線
		g2d.drawLine(210, 397, 210, 533);g2d.drawLine(590, 267, 590, 403);		//左右停止線
		
		g2d.setColor(Color.white);												//斑馬線顏色
		g2d.setStroke(new BasicStroke(10));
		g2d.drawLine(275, 234, 275, 258);g2d.drawLine(300, 234, 300, 258);g2d.drawLine(325, 234, 325, 258);
		g2d.drawLine(350, 234, 350, 258);g2d.drawLine(375, 234, 375, 258);g2d.drawLine(400, 234, 400, 258);
		g2d.drawLine(425, 234, 425, 258);g2d.drawLine(450, 234, 450, 258);g2d.drawLine(475, 234, 475, 258);
		g2d.drawLine(500, 234, 500, 258);g2d.drawLine(525, 234, 525, 258);		//上斑馬
		g2d.drawLine(275, 542, 275, 566);g2d.drawLine(300, 542, 300, 566);g2d.drawLine(325, 542, 325, 566);
		g2d.drawLine(350, 542, 350, 566);g2d.drawLine(375, 542, 375, 566);g2d.drawLine(400, 542, 400, 566);
		g2d.drawLine(425, 542, 425, 566);g2d.drawLine(450, 542, 450, 566);g2d.drawLine(475, 542, 475, 566);
		g2d.drawLine(500, 542, 500, 566);g2d.drawLine(525, 542, 525, 566);		//下斑馬
		g2d.drawLine(224, 288, 248, 288);g2d.drawLine(224, 316, 248, 316);g2d.drawLine(224, 344, 248, 344);
		g2d.drawLine(224, 372, 248, 372);g2d.drawLine(224, 400, 248, 400);g2d.drawLine(224, 428, 248, 428);
		g2d.drawLine(224, 456, 248, 456);g2d.drawLine(224, 484, 248, 484);g2d.drawLine(224, 512, 248, 512);		//左斑馬
		g2d.drawLine(552, 288, 576, 288);g2d.drawLine(552, 316, 576, 316);g2d.drawLine(552, 344, 576, 344);
		g2d.drawLine(552, 372, 576, 372);g2d.drawLine(552, 400, 576, 400);g2d.drawLine(552, 428, 576, 428);
		g2d.drawLine(552, 456, 576, 456);g2d.drawLine(552, 484, 576, 484);g2d.drawLine(552, 512, 576, 512);		//右斑馬
		
		g2d.setStroke(new BasicStroke(1));
		g2d.drawLine(299, 0, 299, 40);g2d.drawLine(299, 60, 299, 100);g2d.drawLine(299, 120, 299, 160);g2d.drawLine(299, 180, 299, 220);		//上虛線-1
		g2d.drawLine(347, 0, 347, 40);g2d.drawLine(347, 60, 347, 100);g2d.drawLine(347, 120, 347, 160);g2d.drawLine(347, 180, 347, 220);		//上虛線-2
		g2d.drawLine(454, 0, 454, 40);g2d.drawLine(454, 60, 454, 100);g2d.drawLine(454, 120, 454, 160);g2d.drawLine(454, 180, 454, 220);		//上虛線-3
		g2d.drawLine(502, 0, 502, 40);g2d.drawLine(502, 60, 502, 100);g2d.drawLine(502, 120, 502, 160);g2d.drawLine(502, 180, 502, 220);		//上虛線-4
		g2d.drawLine(0, 472, 30, 472);g2d.drawLine(50, 472, 90, 472);g2d.drawLine(110, 472, 150, 472);g2d.drawLine(170, 472, 210, 472);			//左虛線-1
		g2d.drawLine(0, 327, 30, 327);g2d.drawLine(50, 327, 90, 327);g2d.drawLine(110, 327, 150, 327);g2d.drawLine(170, 327, 210, 327);			//左虛線-2
		g2d.drawLine(454, 580, 454, 620);g2d.drawLine(454, 640, 454, 680);g2d.drawLine(454, 700, 454, 740);g2d.drawLine(454, 760, 454, 800);		//下虛線-3
		g2d.drawLine(502, 580, 502, 620);g2d.drawLine(502, 640, 502, 680);g2d.drawLine(502, 700, 502, 740);g2d.drawLine(502, 760, 502, 800);		//下虛線-4
		g2d.drawLine(299, 580, 299, 620);g2d.drawLine(299, 640, 299, 680);g2d.drawLine(299, 700, 299, 740);g2d.drawLine(299, 760, 299, 800);		//下虛線-1
		g2d.drawLine(347, 580, 347, 620);g2d.drawLine(347, 640, 347, 680);g2d.drawLine(347, 700, 347, 740);g2d.drawLine(347, 760, 347, 800);		//下虛線-2
		g2d.drawLine(590, 327, 630, 327);g2d.drawLine(650, 327, 690, 327);g2d.drawLine(710, 327, 750, 327);g2d.drawLine(770, 327, 800, 327);			//右虛線-1
		g2d.drawLine(590, 472, 630, 472);g2d.drawLine(650, 472, 690, 472);g2d.drawLine(710, 472, 750, 472);g2d.drawLine(770, 472, 800, 472);			//右虛線-2
		
		
		for(int j = 0 ; j<PaperV1.list_all.size(); j++) {
			if(PaperV1.list_all.get(j).road == "S2W" || PaperV1.list_all.get(j).road == "S2N" || PaperV1.list_all.get(j).road == "S2E" ||PaperV1.list_all.get(j).road == "N2W" ||
					PaperV1.list_all.get(j).road == "N2E" ||PaperV1.list_all.get(j).road == "N2S") {
				//判斷南北是否左轉
				if(PaperV1.list_all.get(j).turn_right == false && PaperV1.list_all.get(j).turn_left == false) {		//直走
					g2d.drawImage(getToolkit().getImage(PaperV1.list_all.get(j).car_img), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1, PaperCar.car_width, PaperCar.car_high, this);
				}else if(PaperV1.list_all.get(j).turn_left == true){
//					g2d.rotate(Math.toRadians(-90), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1);
					g2d.drawImage(getToolkit().getImage(PaperV1.list_all.get(j).car_img), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1, PaperCar.car_width, PaperCar.car_high, this);
//					g2d.rotate(Math.toRadians(90), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1);
				}
				//判斷南北是否右轉
				if(PaperV1.list_all.get(j).turn_right == false && PaperV1.list_all.get(j).turn_left == false) {		//直走
					g2d.drawImage(getToolkit().getImage(PaperV1.list_all.get(j).car_img), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1, PaperCar.car_width, PaperCar.car_high, this);
				}else if(PaperV1.list_all.get(j).turn_right == true){
//					g2d.rotate(Math.toRadians(-90), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1);
					g2d.drawImage(getToolkit().getImage(PaperV1.list_all.get(j).car_img), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1, PaperCar.car_width, PaperCar.car_high, this);
//					g2d.rotate(Math.toRadians(90), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1);
				}
			}
			else {
				//判斷東西是否左轉
				if(PaperV1.list_all.get(j).turn_right == false && PaperV1.list_all.get(j).turn_left == false) {		//直走
					g2d.drawImage(getToolkit().getImage(PaperV1.list_all.get(j).car_img), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1, PaperCar.car_width, PaperCar.car_high, this);
				}else if(PaperV1.list_all.get(j).turn_left == true){
//					g2d.rotate(Math.toRadians(-90), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1);
					g2d.drawImage(getToolkit().getImage(PaperV1.list_all.get(j).car_img), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1, PaperCar.car_width, PaperCar.car_high, this);
//					g2d.rotate(Math.toRadians(90), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1);
				}
//				//判斷東西是否右轉
//				if(PaperV1.list_all.get(j).turn_right == false && PaperV1.list_all.get(j).turn_left == false) {		//直走
//					g2d.drawImage(getToolkit().getImage(PaperV1.list_all.get(j).car_img), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1, PaperCar.car_width, PaperCar.car_high, this);}
//				}else if(PaperV1.list_all.get(j).turn_right == true){
//					g2d.rotate(Math.toRadians(90), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1);
//					g2d.drawImage(getToolkit().getImage(PaperV1.list_all.get(j).car_img), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1, PaperCar.car_width, PaperCar.car_high, this);
//					g2d.rotate(Math.toRadians(-90), PaperV1.list_all.get(j).x1, PaperV1.list_all.get(j).y1);
//				}
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
	
	
