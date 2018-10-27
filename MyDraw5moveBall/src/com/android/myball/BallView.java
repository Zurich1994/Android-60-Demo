package com.android.myball;

import java.util.ArrayList;
import java.util.Random;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BallView extends SurfaceView implements SurfaceHolder.Callback {

	//小球水平速度的最大值
	public static final int V_MAX=35;
	//小球竖直速度的最大值
	public static final int V_MIN=15;
	//木板有又边缘的x坐标
	public static final int WOOD_EDGE=60;
	//游戏中代表地面y坐标，小球下落到此会弹起
	public static final int GROUND_LINE=450;
	//小球上升过程中，如果速度大小小于該值就算为0
	public static final int UP_ZERO=30;
	//小球在撞击地面后，如果速度大小小于該值就算为0；
	public static final int DOWN_ZERO=60;
	//各种颜色形状的小球图片引用
	Bitmap [] bitmapArray=new Bitmap[6];
	//背景图片对象
	Bitmap bmpBack;
	//木板图片对象
	Bitmap bmpWood;    
	//用于显示帧苏速率的字符串，初始时为：“FPS:N/A”
	String fps="FPS:N/A";
	//小球数目
	int ballNumber=8;
	//小球对象数组
	ArrayList<Movable> alMovable=new ArrayList<Movable>();
	
	DrawThread dt;
	
	public BallView(Context context) {
		super(context);
		getHolder().addCallback(this);
		initBitmaps(getResources());
		initMovables();
		dt=new DrawThread(this, getHolder());
	}
//初始化图片
	public void initBitmaps(Resources r){
		bitmapArray[0]=BitmapFactory.decodeResource(r, R.drawable.black_ball);
		bitmapArray[1]=BitmapFactory.decodeResource(r, R.drawable.blue_ball);
		bitmapArray[2]=BitmapFactory.decodeResource(r, R.drawable.red_ball);
		bitmapArray[3]=BitmapFactory.decodeResource(r, R.drawable.small_black_ball);
		bitmapArray[4]=BitmapFactory.decodeResource(r, R.drawable.small_blue_ball);
		bitmapArray[5]=BitmapFactory.decodeResource(r, R.drawable.small_red_ball);
		
		bmpBack=BitmapFactory.decodeResource(r, R.drawable.photo);
		
		bmpWood=BitmapFactory.decodeResource(r, R.drawable.wood);
	}
	//初始化小球
	public void initMovables(){
		Random r=new Random();
		for(int i=0;i<ballNumber;i++){
			int index=r.nextInt(32);
			Bitmap tempBitmap=null;
			if(i<ballNumber/2){
				//如果是初始化前一半小球，就从大球中随机找一个
				tempBitmap=bitmapArray[3+index%3];
			}else{
				tempBitmap=bitmapArray[index%3];
			}
			Movable m=new Movable(0,70-tempBitmap.getHeight(), tempBitmap.getWidth()/2, tempBitmap);
			//将新建的Movable对象添加到ArrayList列表中
			alMovable.add(m);
		}
	}
	
	public void doDraw(Canvas canvas) {
		canvas.drawBitmap(bmpBack, 0, 0, null);
		canvas.drawBitmap(bmpWood, 0, 60, null);
		for(Movable m:alMovable){
			m.drawSelf(canvas);
		}
		Paint p=new Paint();
		p.setColor(Color.BLUE);
		p.setTextSize(18);
		p.setAntiAlias(true);
		canvas.drawText(fps, 30,30, p);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if(!dt.isAlive()){
			dt.start();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		dt.flag=false;
		dt=null;
	}

}
