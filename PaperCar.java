package com.Project.java;

public class PaperCar implements Runnable{
	int x1, y1;	//車的起始位置
	int x2, y2;	//車的終點位置
	int speed;	//車的速度
	int random_R;	//隨機路
	int random_S;	//隨機速度
	int safety_line; 	//停止線
	boolean turn_left;	//初始為False
	boolean turn_right;
	private boolean isAlive = true;
	String car_img;		//車自身圖片
	String road;		//路名的選擇
	int rotation_angle;		//轉彎車輛的旋轉角度
	
	static final int car_width = 45;		//車寬
	static final int car_high = 45;			//車高
	private static int car_U = 0;			//上
	private static int car_D = 1;			//下
	private static int car_L = 2;			//左
	private static int car_R = 3;			//右
	private static final int time = 100;	//小車刷新時間
	static int car_speed[] = {5, 10, 15};
	//車輛圖片路徑群組
	private static String img[][] = {{"Lpp/Ambulance.png" , "Lpp/truck.png"}, 
			{"Lpp/Car.png" , "Lpp/Police_car.png"}, {"Lpp/mcqueen.png" , "Lpp/Trash_car.png"},
			{"Lpp/Scooter.png" , "Lpp/Fire_car.png"}};
	//路名群組
	private static String car_on_road[] = {"S2W", "S2E", "S2N", "N2E", "N2W", "N2S", "W2N", "W2E", "E2S", "E2W"};
	
	
	public PaperCar() {
		create_car();		//調用生成車屬性的方法
	}
	public void create_car() {		//生成車屬性的方法＋車輛圖片＋車輛起始、終點位置
		//每隔5*Car.time毫秒生成一台車
		try {Thread.sleep((int)(15*PaperCar.time));} catch(Throwable e) {e.printStackTrace();}
		//生成隨機數
		this.random_R = (int)(Math.random()*10);
		this.random_S = (int)(Math.random()*3);
		this.road = PaperCar.car_on_road[this.random_R];		//車所在道路的選擇
		this.speed = PaperCar.car_speed[this.random_S];			//車速度選擇
		PaperV1.list_all.add(this);	//使用集合來操作全部車輛
		switch(this.road) {
		case "S2W" :	//南左轉西
			this.car_img = img[PaperCar.car_U][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 408; y1 = 780; x2 = -50; y2 = 380;
			this.safety_line = 580;		//行駛到停止線y1=580
			PaperV1.list_S2W.add(this);
			break;
		case "S2E" :	//南右轉東
			this.car_img = img[PaperCar.car_U][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 505; y1 = 780; x2 = 850; y2 = 500;
			this.safety_line = 580;		//行駛到停止線y1=580
			PaperV1.list_S2E.add(this);
			break;
		case "S2N" :	//南直走北
			this.car_img = img[PaperCar.car_U][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 457; y1 = 780; x2 = 457; y2 = -50;
			this.safety_line = 580;		//行駛到停止線y1=580
			PaperV1.list_S2N.add(this);
			break;
		case "N2E" :	//北左轉東
			this.car_img = img[PaperCar.car_D][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 350; y1 = 20; x2 = 850; y2 = 500;
			this.safety_line = 220;		//行駛到停止線y1=220
			PaperV1.list_N2E.add(this);
			break;
		case "N2W" :	//北右轉西
			this.car_img = img[PaperCar.car_D][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 253; y1 = 20; x2 = -50; y2 = 300;
			this.safety_line = 220;		//行駛到停止線y1=220
			PaperV1.list_N2W.add(this);
			break;
		case "N2S" :	//北直走南
			this.car_img = img[PaperCar.car_D][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 302; y1 = 20; x2 = 302; y2 = 850;
			this.safety_line = 220;		//行駛到停止線y1=220
			PaperV1.list_N2S.add(this);
			break;
		case "W2N" :	//西左轉北
			this.car_img = img[PaperCar.car_R][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 20; y1 = 415; x2 = 500; y2 = -50;
			this.safety_line = 210;		//行駛到停止線x1=210
			PaperV1.list_W2N.add(this);
			break;
//		case "W2S" :	//西右轉南
//			this.car_img = img[PaperCar.car_R][(int)(Math.random()*2)];	//出隨機一台車
//			x1 = 0; y1 = 430; x2 = 300; y2 = 850;
//			this.safety_line = 210;		//行駛到停止線x1=210
//			PaperV1.list_W2S.add(this);
//			break;
		case "W2E" :	//西直走東
			this.car_img = img[PaperCar.car_R][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 20; y1 = 487; x2 = 850; y2 = 487;
			this.safety_line = 210;		//行駛到停止線x1=210
			PaperV1.list_W2E.add(this);
			break;
		case "E2S" :	//東左轉南
			this.car_img = img[PaperCar.car_L][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 780; y1 = 337; x2 = -50; y2 = 850;
			this.safety_line = 590;		//行駛到停止線x1=590
			PaperV1.list_E2S.add(this);
			break;
//		case "E2N" :	//東右轉北
//			this.car_img = img[PaperCar.car_L][(int)(Math.random()*2)];	//出隨機一台車
//			x1 = 680; y1 = 250; x2 = 500; y2 = -50;
//			this.safety_line = 590;		//行駛到停止線x1=590
//			PaperV1.list_E2N.add(this);
//			break;
		case "E2W" :	//東直走西
			this.car_img = img[PaperCar.car_L][(int)(Math.random()*2)];	//出隨機一台車
			x1 = 780; y1 = 270; x2 = -50; y2 = 270;
			this.safety_line = 590;		//行駛到停止線x1=590
			PaperV1.list_E2W.add(this);
			break;
		}
	}
	@Override
	public void run() {
		while (this.isAlive) {
			int original_V = this.speed;		//利用變量保存初始速度
			int myself;							//定義變量保存this(自己)在集合中的位子
			boolean isrun;						//定義一個boolean來作為是否判斷燈的狀態
			
			switch(this.road) {
			case "S2W" :			//南左轉西
				while(this.x1 >= this.x2) {
					try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
					PaperV1.paperD1.repaint();
					if(this.y1 >= this.safety_line-10) {
						isrun = true;
						this.y1-= this.speed;
						this.turn_left = false;			//判斷左轉
						while(isrun) {					//到停止線時停止
							if((PaperD1.light_S_left.get_status() == PaperLight.light_red) && (this.y1 <= this.safety_line + 10 && this.y1>this.safety_line)) {
							this.speed = 0;
						}
							if(PaperD1.light_S_right.get_status() == PaperLight.light_green) {
								this.speed = 5;
							}
							
							if(this.y1 <= this.safety_line) {
								this.speed = original_V;
							}
							isrun = false;
						}
						for(int i = 0 ; i<PaperV1.list_S2W.size() ; i++) {		//車距
							if((PaperV1.list_S2W.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_S2W.get(i).hashCode())){
								myself = i;	//找到自己集合中的位子
								if((PaperV1.list_S2W.get(myself-1).y1 >= this.safety_line-100) && (PaperV1.list_S2W.get(myself).y1-(PaperV1.list_S2W.get(myself-1).y1 + PaperCar.car_high) <=15)){
									PaperV1.list_S2W.get(myself).speed = PaperV1.list_S2W.get(myself - 1).speed;
									PaperV1.list_S2W.get(myself).y1 = PaperV1.list_S2W.get(myself - 1).y1 + car_high + 10;
								}
								else if(PaperV1.list_S2W.get(myself).y1 < this.safety_line - 100) {
									PaperV1.list_S2W.get(myself).speed = PaperV1.list_S2W.get(myself - 1).speed;
									PaperV1.list_S2W.get(myself - 1).speed+= 10;
									
								}
							}
						}
					}
					else {
						this.turn_left = true;
						if(this.y1 > this.safety_line - 240) {			//轉到指定位子 以及其速度
							this.y1-=this.speed;
						}else if(this.y1 <= this.safety_line-240) {		//到指定位子後車子轉向
//							this.rotation_angle = 90;
						}
						this.x1-=this.speed;						//轉彎後速度
						if(this.x1 < this.x2) {						//超過終點座標remove
							PaperV1.list_S2W.remove(this);
							PaperV1.list_all.remove(this);
							isAlive = false;
						}
					}
				}
			break;
			case "S2N" :		//南直走北
				while(this.x1 >= this.x2) {
					try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
					PaperV1.paperD1.repaint();
					isrun = true;
					this.y1-= this.speed;
						while(isrun) {					//到停止線時停止
							if((PaperD1.light_S_left.get_status() == PaperLight.light_red) && (this.y1 <= this.safety_line + 10 && this.y1>this.safety_line)) {
							this.speed = 0;
						}
							if(PaperD1.light_S_right.get_status() == PaperLight.light_green) {
								this.speed = 5;
							}
							
							if(this.y1 <= this.safety_line) {
								this.speed = original_V;
							}
							isrun = false;
						}
						for(int i = 0 ; i<PaperV1.list_S2N.size() ; i++) {		//車距
							if((PaperV1.list_S2N.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_S2N.get(i).hashCode())){
								myself = i;	//找到自己集合中的位子
								if((this.y1 >= this.y2) && (PaperV1.list_S2N.get(myself).y1-(PaperV1.list_S2N.get(myself-1).y1 + PaperCar.car_high) <=15)) {
									PaperV1.list_S2N.get(myself).speed = PaperV1.list_S2N.get(myself - 1).speed;
									PaperV1.list_S2N.get(myself).y1 = PaperV1.list_S2N.get(myself - 1).y1 + car_high + 10;
								}
							}
						}
						if(this.y1 < this.y2) {						//超過終點座標remove
							PaperV1.list_S2N.remove(this);
							PaperV1.list_all.remove(this);
							isAlive = false;
						}
				}
			break;
			case "S2E" :		//南右轉東
			while(this.x1 <= this.x2) {
				try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
				PaperV1.paperD1.repaint();
				if(this.y1 >= this.safety_line-10) {
					isrun = true;
					this.y1-= this.speed;
					this.turn_right = false;			//判斷右轉
					while(isrun) {					//到停止線時停止
						if((PaperD1.light_S_left.get_status() == PaperLight.light_red) && (this.y1 <= this.safety_line + 10 && this.y1>this.safety_line)) {
						this.speed = 0;
					}
						if(PaperD1.light_S_right.get_status() == PaperLight.light_green) {
							this.speed = 5;
						}
						
						if(this.y1 <= this.safety_line) {
							this.speed = original_V;
						}
						isrun = false;
					}
					for(int i = 0 ; i<PaperV1.list_S2E.size() ; i++) {
						if((PaperV1.list_S2E.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_S2E.get(i).hashCode())){
							myself = i;	//找到自己集合中的位子
							if((PaperV1.list_S2E.get(myself-1).y1 >= this.safety_line-100) && (PaperV1.list_S2E.get(myself).y1-(PaperV1.list_S2E.get(myself-1).y1 + PaperCar.car_high) <=15)){
								PaperV1.list_S2E.get(myself).speed = PaperV1.list_S2E.get(myself - 1).speed;
								PaperV1.list_S2E.get(myself).y1 = PaperV1.list_S2E.get(myself - 1).y1 + car_high + 10;
							}
							else if(PaperV1.list_S2E.get(myself).y1 < this.safety_line - 100) {
								PaperV1.list_S2E.get(myself).speed = PaperV1.list_S2E.get(myself - 1).speed;
								PaperV1.list_S2E.get(myself - 1).speed+= 10;
								
							}
						}
					}
				}
				else {
					this.turn_right = true;
					if(this.y1 > this.safety_line - 100) {			//轉到指定位子 以及其速度
						this.y1-=this.speed;
					}
					else if(this.y1 <= this.safety_line - 100){
						this.x1+=this.speed;this.y1 = 480;
					}
									
					if(this.x1 >= this.x2) {						//超過終點座標remove
						PaperV1.list_S2E.remove(this);
						PaperV1.list_all.remove(this);
						isAlive = false;
					}
				}
			}
			break;
			case "N2E" :		//北左轉東
				while(this.x1 <= this.x2) {
					try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
					PaperV1.paperD1.repaint();
					if(this.y1 <= this.safety_line) {
						isrun = true;
						this.y1+= this.speed;
						this.turn_left = false;			//判斷左轉
						while(isrun) {					//到停止線時停止
							if((PaperD1.light_N_left.get_status() == PaperLight.light_red) && (this.y1 < this.safety_line && this.y1>=this.safety_line-80)) {
							this.speed = 0;
						}
							if(PaperD1.light_N_right.get_status() == PaperLight.light_green) {
								this.speed = 5;
							}
							
							if(this.y1 >= this.safety_line+50) {
								this.speed = original_V;
							}
							isrun = false;
						}
						for(int i = 0 ; i<PaperV1.list_N2E.size() ; i++) {
							if((PaperV1.list_N2E.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_N2E.get(i).hashCode())){
								myself = i;	//找到自己集合中的位子
								if((PaperV1.list_N2E.get(myself-1).y1 <= this.safety_line+200) && (PaperV1.list_N2E.get(myself-1).y1-(PaperV1.list_N2E.get(myself).y1 + PaperCar.car_high) <=15)){
									PaperV1.list_N2E.get(myself).speed = PaperV1.list_N2E.get(myself - 1).speed;
									PaperV1.list_N2E.get(myself).y1 = PaperV1.list_N2E.get(myself - 1).y1 - (car_high + 10);
								}
								else if(PaperV1.list_N2E.get(myself).y1 > this.safety_line + 100) {
									PaperV1.list_N2E.get(myself).speed = PaperV1.list_N2E.get(myself - 1).speed;
									PaperV1.list_N2E.get(myself - 1).speed+= 10;
									
								}
							}
						}
					}
					else {
						this.turn_left = true;
						if(this.y1 <= this.safety_line + 200) {			//轉到指定位子 以及其速度
							this.y1+=this.speed;
						}else if(this.y1 >= this.safety_line + 200) {		//到指定位子後車子轉向
//							this.rotation_angle = 90;
						}
						this.x1+=this.speed;						//轉彎後速度
						if(this.x1 >= this.x2) {						//超過終點座標remove
							PaperV1.list_N2E.remove(this);
							PaperV1.list_all.remove(this);
							isAlive = false;
						}
					}
				}
			break;
			case "N2S" :		//北直走南
				while(this.y1 <= this.y2) {
					try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
					PaperV1.paperD1.repaint();
						isrun = true;
						this.y1+= this.speed;
						while(isrun) {					//到停止線時停止
							if((PaperD1.light_N_left.get_status() == PaperLight.light_red) && (this.y1 < this.safety_line && this.y1>=this.safety_line-80)) {
							this.speed = 0;
						}
							if(PaperD1.light_N_right.get_status() == PaperLight.light_green) {
								this.speed = 5;
							}
							
							if(this.y1 >= this.safety_line) {
								this.speed = original_V;
							}
							isrun = false;
						}
						for(int i = 0 ; i<PaperV1.list_N2S.size() ; i++) {
							if((PaperV1.list_N2S.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_N2S.get(i).hashCode())){
								myself = i;	//找到自己集合中的位子
								if((this.y1 <= this.y2) && (PaperV1.list_N2S.get(myself-1).y1-(PaperV1.list_N2S.get(myself).y1 + PaperCar.car_high) <=15)){
									PaperV1.list_N2S.get(myself).speed = PaperV1.list_N2S.get(myself - 1).speed;
									PaperV1.list_N2S.get(myself).y1 = PaperV1.list_N2S.get(myself - 1).y1 - (car_high + 10);
								}		
							}
						}
						if(this.y1 >= this.y2) {						//超過終點座標remove
							PaperV1.list_N2S.remove(this);
							PaperV1.list_all.remove(this);
							isAlive = false;
						}
				}
			break;
			case "N2W" :		//北右轉西
				while(this.x1 >= this.x2) {
					try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
					PaperV1.paperD1.repaint();
					if(this.y1 <= this.safety_line) {
						isrun = true;
						this.y1+= this.speed;
						this.turn_right = false;			//判斷右轉
						while(isrun) {					//到停止線時停止
							if((PaperD1.light_N_left.get_status() == PaperLight.light_red) && (this.y1 < this.safety_line && this.y1>=this.safety_line-80)) {
							this.speed = 0;
						}
							if(PaperD1.light_N_right.get_status() == PaperLight.light_green) {
								this.speed = 5;
							}
							
							if(this.y1 >= this.safety_line) {
								this.speed = original_V;
							}
							isrun = false;
						}
						for(int i = 0 ; i<PaperV1.list_N2W.size() ; i++) {		//車距
							if((PaperV1.list_N2W.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_N2W.get(i).hashCode())){
								myself = i;	//找到自己集合中的位子
								if((PaperV1.list_N2W.get(myself-1).y1 <= this.safety_line+200) && (PaperV1.list_N2W.get(myself-1).y1-(PaperV1.list_N2W.get(myself).y1 + PaperCar.car_high) <=15)){
									PaperV1.list_N2W.get(myself).speed = PaperV1.list_N2W.get(myself - 1).speed;
									PaperV1.list_N2W.get(myself).y1 = PaperV1.list_N2W.get(myself - 1).y1 - (car_high + 10);
								}
								else if(PaperV1.list_N2W.get(myself).y1 > this.safety_line + 100) {
									PaperV1.list_N2W.get(myself).speed = PaperV1.list_N2W.get(myself - 1).speed;
									PaperV1.list_N2W.get(myself - 1).speed+= 10;
									
								}
							}
						}
					}
					else {
						this.turn_right = true;
						if(this.y1 <= this.safety_line + 30) {			//轉到指定位子 以及其速度
							this.y1+=this.speed;
						}else if(this.y1 >= this.safety_line + 30) {		//到指定位子後車子轉向
							this.x1-=this.speed;this.y1=260;
						}
						if(this.x1 <= this.x2) {						//超過終點座標remove
							PaperV1.list_N2W.remove(this);
							PaperV1.list_all.remove(this);
							isAlive = false;
						}
					}
				}
			break;
			case "W2N" :			//西左轉北
				while(this.y1 >= this.y2) {
					try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
					PaperV1.paperD1.repaint();
					if(this.x1 <= this.safety_line-10) {
						isrun = true;
						this.x1+= this.speed;
						this.turn_left = false;			//判斷左轉
						while(isrun) {					//到停止線時停止
							if((PaperD1.light_W_left.get_status() == PaperLight.light_red) && (this.x1 >= this.safety_line - 80 && this.x1 < this.safety_line)) {
							this.speed = 0;
						}
							if(PaperD1.light_W_right.get_status() == PaperLight.light_green) {
								this.speed = 5;
							}
							
							if(this.x1 > this.safety_line) {
								this.speed = original_V;
							}
							isrun = false;
						}
						for(int i = 0 ; i<PaperV1.list_W2N.size() ; i++) {		//車距
							if((PaperV1.list_W2N.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_W2N.get(i).hashCode())){
								myself = i;	//找到自己集合中的位子
								if((PaperV1.list_W2N.get(myself-1).x1 <= this.safety_line + 200) && (PaperV1.list_W2N.get(myself-1).x1-(PaperV1.list_W2N.get(myself).x1 + PaperCar.car_width) <=15)){
									PaperV1.list_W2N.get(myself).speed = PaperV1.list_W2N.get(myself - 1).speed;
									PaperV1.list_W2N.get(myself).x1 = PaperV1.list_W2N.get(myself - 1).x1 - car_width - 20;
								}
								else if(PaperV1.list_W2N.get(myself).x1 > this.safety_line + 200) {
									PaperV1.list_W2N.get(myself).speed = PaperV1.list_W2N.get(myself - 1).speed;
									PaperV1.list_W2N.get(myself - 1).speed+= 10;
									
								}
							}
						}
					}
					else {
						this.turn_left = true;
						if(this.x1 < this.safety_line + 250) {			//轉到指定位子 以及其速度
							this.x1+=this.speed;
						}else if(this.x1 >= this.safety_line + 250) {		//到指定位子後車子轉向
						}
						this.y1-=this.speed;						//轉彎後速度
						if(this.y1 < this.y2) {						//超過終點座標remove
							PaperV1.list_W2N.remove(this);
							PaperV1.list_all.remove(this);
							isAlive = false;
						}
					}
				}
			break;
			case "W2E" :			//西直走東
				while(this.y1 <= this.y2) {
					try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
					PaperV1.paperD1.repaint();
						isrun = true;
						this.x1+= this.speed;
						while(isrun) {					//到停止線時停止
							if((PaperD1.light_W_left.get_status() == PaperLight.light_red) && (this.x1 >= this.safety_line - 80 && this.x1 < this.safety_line)) {
							this.speed = 0;
						}
							if(PaperD1.light_W_right.get_status() == PaperLight.light_green) {
								this.speed = 5;
							}
							
							if(this.x1 > this.safety_line) {
								this.speed = original_V;
							}
							isrun = false;
						}
						for(int i = 0 ; i<PaperV1.list_W2E.size() ; i++) {		//車距
							if((PaperV1.list_W2E.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_W2E.get(i).hashCode())){
								myself = i;	//找到自己集合中的位子
								if((this.x1 <= this.x2) && (PaperV1.list_W2E.get(myself-1).x1-(PaperV1.list_W2E.get(myself).x1 + PaperCar.car_width) <=15)){
									PaperV1.list_W2E.get(myself).speed = PaperV1.list_W2E.get(myself - 1).speed;
									PaperV1.list_W2E.get(myself).x1 = PaperV1.list_W2E.get(myself - 1).x1 - car_width - 20;
								}
							}
						}
						if(this.y1 > this.y2) {						//超過終點座標remove
							PaperV1.list_W2E.remove(this);
							PaperV1.list_all.remove(this);
							isAlive = false;
						}
					}
			break;
			case "E2S" :			//東左轉南
				while(this.y1 <= this.y2) {
					try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
					PaperV1.paperD1.repaint();
					if(this.x1 >= this.safety_line-10) {
						isrun = true;
						this.x1-= this.speed;
						this.turn_left = false;			//判斷左轉
						while(isrun) {					//到停止線時停止
							if((PaperD1.light_E_left.get_status() == PaperLight.light_red) && (this.x1 <= this.safety_line + 10 && this.x1>this.safety_line)) {
							this.speed = 0;
						}
							if(PaperD1.light_E_right.get_status() == PaperLight.light_green) {
								this.speed = 5;
							}
							
							if(this.x1 <= this.safety_line) {
								this.speed = original_V;
							}
							isrun = false;
						}
						for(int i = 0 ; i<PaperV1.list_E2S.size() ; i++) {		//車距
							if((PaperV1.list_E2S.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_E2S.get(i).hashCode())){
								myself = i;	//找到自己集合中的位子
								if((PaperV1.list_E2S.get(myself-1).x1 >= this.safety_line - 100) && (PaperV1.list_E2S.get(myself).x1-(PaperV1.list_E2S.get(myself-1).x1 + PaperCar.car_width) <=15)){
									PaperV1.list_E2S.get(myself).speed = PaperV1.list_E2S.get(myself - 1).speed;
									PaperV1.list_E2S.get(myself).x1 = PaperV1.list_E2S.get(myself - 1).x1 + car_width + 10;
								}
								else if(PaperV1.list_E2S.get(myself).x1 < this.safety_line - 200) {
									PaperV1.list_E2S.get(myself).speed = PaperV1.list_E2S.get(myself - 1).speed;
									PaperV1.list_E2S.get(myself - 1).speed+= 10;
									
								}
							}
						}
					}
					else {
						this.turn_left = true;
						if(this.x1 > this.safety_line -300) {			//轉到指定位子 以及其速度
							this.x1-=this.speed;
						}else if(this.x1 <= this.safety_line - 300) {		//到指定位子後車子轉向
//							this.rotation_angle = 90;
						}
						this.y1+=this.speed;						//轉彎後速度
						if(this.y1 > this.y2) {						//超過終點座標remove
							PaperV1.list_E2S.remove(this);
							PaperV1.list_all.remove(this);
							isAlive = false;
						}
					}
				}
			break;
			case "E2W" :			//東直走西
				while(this.x1 >= this.x2) {
					try {Thread.sleep(PaperCar.time);}catch(InterruptedException e) {e.printStackTrace();}
					PaperV1.paperD1.repaint();
						isrun = true;
						this.x1-= this.speed;
						while(isrun) {					//到停止線時停止
							if((PaperD1.light_E_left.get_status() == PaperLight.light_red) && (this.x1 <= this.safety_line + 10 && this.x1>this.safety_line)) {
							this.speed = 0;
						}
							if(PaperD1.light_E_right.get_status() == PaperLight.light_green) {
								this.speed = 5;
							}
							
							if(this.x1 < this.safety_line) {
								this.speed = original_V;
							}
							isrun = false;
						}
						for(int i = 0 ; i<PaperV1.list_E2W.size() ; i++) {		//車距
							if((PaperV1.list_E2W.size() >= 2) && (i != 0) && (this.hashCode() == PaperV1.list_E2W.get(i).hashCode())){
								myself = i;	//找到自己集合中的位子
								if((this.x1 >= this.x2) && (PaperV1.list_E2W.get(myself).x1-(PaperV1.list_E2W.get(myself-1).x1 + PaperCar.car_high) <=15)){
									PaperV1.list_E2W.get(myself).speed = PaperV1.list_E2W.get(myself - 1).speed;
									PaperV1.list_E2W.get(myself).x1 = PaperV1.list_E2W.get(myself - 1).x1 + car_high + 10;
								}
							}
						}
						if(this.x1 < this.x2) {						//超過終點座標remove
							PaperV1.list_E2W.remove(this);
							PaperV1.list_all.remove(this);
							isAlive = false;
						}
					}
			break;
			}
		}
	}
}
