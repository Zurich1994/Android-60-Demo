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

	//С��ˮƽ�ٶȵ����ֵ
	public static final int V_MAX=35;
	//С����ֱ�ٶȵ����ֵ
	public static final int V_MIN=15;
	//ľ�����ֱ�Ե��x����
	public static final int WOOD_EDGE=60;
	//��Ϸ�д������y���꣬С�����䵽�˻ᵯ��
	public static final int GROUND_LINE=450;
	//С�����������У�����ٶȴ�СС��ԓֵ����Ϊ0
	public static final int UP_ZERO=30;
	//С����ײ�����������ٶȴ�СС��ԓֵ����Ϊ0��
	public static final int DOWN_ZERO=60;
	//������ɫ��״��С��ͼƬ����
	Bitmap [] bitmapArray=new Bitmap[6];
	//����ͼƬ����
	Bitmap bmpBack;
	//ľ��ͼƬ����
	Bitmap bmpWood;    
	//������ʾ֡�����ʵ��ַ�������ʼʱΪ����FPS:N/A��
	String fps="FPS:N/A";
	//С����Ŀ
	int ballNumber=8;
	//С���������
	ArrayList<Movable> alMovable=new ArrayList<Movable>();
	
	DrawThread dt;
	
	public BallView(Context context) {
		super(context);
		getHolder().addCallback(this);
		initBitmaps(getResources());
		initMovables();
		dt=new DrawThread(this, getHolder());
	}
//��ʼ��ͼƬ
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
	//��ʼ��С��
	public void initMovables(){
		Random r=new Random();
		for(int i=0;i<ballNumber;i++){
			int index=r.nextInt(32);
			Bitmap tempBitmap=null;
			if(i<ballNumber/2){
				//����ǳ�ʼ��ǰһ��С�򣬾ʹӴ����������һ��
				tempBitmap=bitmapArray[3+index%3];
			}else{
				tempBitmap=bitmapArray[index%3];
			}
			Movable m=new Movable(0,70-tempBitmap.getHeight(), tempBitmap.getWidth()/2, tempBitmap);
			//���½���Movable������ӵ�ArrayList�б���
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
