package com.Project.java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;



public class PaperV1 extends JFrame {
	private JFrame jframe;
	
	private JMenuItem red, yellow, green, blue, back,close;	
//	private PaperD1 paperD1;
	static PaperD1 paperD1 = new PaperD1();		//原為下方new一個而已，但為了要將paperD1也匯入car所以改成這樣
	
	static List<PaperCar>list_all = new ArrayList<PaperCar>();
	static List<PaperCar>list_S2W = new ArrayList<PaperCar>();
	static List<PaperCar>list_S2E = new ArrayList<PaperCar>();
	static List<PaperCar>list_S2N = new ArrayList<PaperCar>();
	static List<PaperCar>list_N2W = new ArrayList<PaperCar>();
	static List<PaperCar>list_N2E = new ArrayList<PaperCar>();
	static List<PaperCar>list_N2S = new ArrayList<PaperCar>();
	static List<PaperCar>list_W2N = new ArrayList<PaperCar>();
//	static List<PaperCar>list_W2S = new ArrayList<PaperCar>();
	static List<PaperCar>list_W2E = new ArrayList<PaperCar>();
//	static List<PaperCar>list_E2N = new ArrayList<PaperCar>();
	static List<PaperCar>list_E2S = new ArrayList<PaperCar>();
	static List<PaperCar>list_E2W = new ArrayList<PaperCar>();
	
	
	public PaperV1() {
		super("危險的十字路口");
		
		
		
		JMenuBar jmb = new JMenuBar();											//新增一個bar
		setJMenuBar(jmb);														//
		JMenu jfile = new JMenu("Change Board Color(C)");						//新增下拉選單,命名
		jfile.setMnemonic(KeyEvent.VK_C);										//可利用按快捷鍵C
																//
		JMenu setting = new JMenu("Color");										//新增一個下拉後延伸功能(子選單)
		setting.add(red = new JMenuItem("Red"));								//設定顏色
		
		red.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paperD1.setBackground(Color.red);
			}
		});
		setting.add(yellow = new JMenuItem("Yellow"));
		yellow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paperD1.setBackground(Color.yellow);
			}
		});
		setting.add(green = new JMenuItem("Green"));
		green.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paperD1.setBackground(Color.green);
			}
		});
		setting.add(blue = new JMenuItem("Blue"));
		blue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paperD1.setBackground(Color.blue);
			}
		});
		jfile.add(setting);														//jfile(下拉選單)加入 setting功能
		
		jfile.add(back = new JMenuItem("Back"));
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paperD1.setBackground(Color.gray);
				
			}
		});
		
		jfile.addSeparator();
		jfile.add(close = new JMenuItem("Close", KeyEvent.VK_X));
		jmb.add(jfile);												//jmb(Bar)加入jfile(下拉選單,含setting)功能




//		JMenu cfile = new JMenu("Change Cars(B)");
//		cfile.setMnemonic(KeyEvent.VK_B);
//		JCheckBoxMenuItem check;
//		check = new JCheckBoxMenuItem("Change");
////		check.addActionListener(this);
//		cfile.add(check);
//		ButtonGroup bg = new ButtonGroup();
//		air = new JRadioButtonMenuItem("Airplane", null);
//		
//		cfile.add(air);
//		bg.add(air);
//		jmb.add(cfile);
		
//		paperD1 = new PaperD1();					//call paperD1的功能來用
		add(paperD1, BorderLayout.CENTER);
		paperD1.setBackground(Color.gray);
		setContentPane(paperD1);
		setSize(800, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		}
	
	public static void main(String[] args) {
		new PaperV1();
		for(int i = 0; i<100 ; i++) {
			PaperCar paperCar = new PaperCar();
			new Thread(paperCar).start();
		}
	}
}
