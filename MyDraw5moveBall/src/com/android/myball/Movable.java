package com.android.myball;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Movable {

	//此类代表可移动物体的Movable类
	
	//初始化X、Y的坐标
	int startX=0;
	int startY=0;
	//实时X、Y坐标
	int x;
	int y;
	//初始化水平、竖直方向的速度
	float startVX=0;
	float startVY=0;
	//实时水平、竖直方向的速度
	float v_x=0f;
	float v_y=0f;
	//可移动物体的半径
	int r;
	//X、Y方向上的运动时间
	double timeX;
	double timeY;
	//可移动物体图片
	Bitmap bitmap;
	//负责小球移动
	BallThread bt;
	//小球是否已经从木板上下落
	boolean bFall=false;
	//小球撞地后速度的损失系数
	float impactFactor=0.25f;
	public Movable(int x, int y, int r, Bitmap bitmap) {
		super();
		this.startX=x;
		this.x = x;
		this.startY=y;
		this.y = y;
		this.r = r;
		this.bitmap = bitmap;
		timeX=System.nanoTime();
		this.v_x=BallView.V_MIN+(int)((BallView.V_MAX-BallView.V_MIN)*Math.random());
		bt=new BallThread(this);
		bt.start();
	}
	
	public void drawSelf(Canvas canvas){
		canvas.drawBitmap(this.bitmap, x, y, null);
	}
}







