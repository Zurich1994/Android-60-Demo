package com.android.myball;

public class BallThread extends Thread {

	Movable father;
	//线程执行标志位
	boolean flag=false;
	//休眠时间
	int sleepSpan=40;
	//求下落的加速度
	float g=200;
	//纪录当前时间
	double current;
	public BallThread(Movable father) {
		super();
		this.father = father;
		this.flag = true;
	}
	
	@Override
	public void run() {
		while(flag){
			// 获取当前时间，单位为纳秒
			current=System.nanoTime();
			//处理水平方向上的运动时间
			double timeSpanX=(double)((current-father.timeX)/1000/1000/1000);
			//获取水平方向走过的位移
			father.x=(int)(father.startX+father.v_x*timeSpanX);
		//判断球是否已经移出挡板
			if(father.bFall){
				double timeSpanY=(double)((current-father.timeY)/1000/1000/1000);
				father.y=(int)(father.startY+father.startVY*timeSpanY+timeSpanY*timeSpanY*g/2);
				//竖直方向速度
				father.v_y=(float)(father.startVY+g*timeSpanY);
				
				//判断小球是否到达最高点
				if(father.startVY<0&&Math.abs(father.v_y)<=BallView.UP_ZERO){
					father.timeY=System.nanoTime();
					father.v_y=0;
					father.startVX=0;
					father.startY=father.y;
				}
				//判断小球是否撞地
				if(father.y+father.r*2>=BallView.GROUND_LINE&&father.v_y>0){
					father.v_x=father.v_x*(1-father.impactFactor);
					if(Math.abs(father.v_y)<BallView.DOWN_ZERO){
						this.flag=false;
					}else{
						father.startX=father.x;
						father.timeX=System.nanoTime();
						father.startY=father.y;
						father.timeY=System.nanoTime();
						father.startVY=father.v_y;
					}
				}
			}
			//判断球是否移出了挡板
			else if(father.x+father.r/2>=BallView.WOOD_EDGE){
				father.timeY=System.nanoTime();
				father.bFall=true;
				try {
					Thread.sleep(sleepSpan);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}
}
