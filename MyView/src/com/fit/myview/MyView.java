package com.fit.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.WindowManager;

public class MyView extends View {

	
	static final int ANGEL_MAX=50;
	//�ƶ��Ĳ���
	static final int SPEED=4;
	//��Ϸ��������
	static final int SCREEN_WIDTH=480;
	static final int SCREEN_HEIGHT=320;
	//������
	static final int LEFT=2;
	static final int RIGHT=0;
	static final int UP=3;
	static final int DOWN=1;
	//�����ſ��Ƕ����ֵ
	int angle=30;
	//�Ƕȱ仯ֵ
	int angleChange=3;
	int radius=20;
	//��ǰλ��
	int centerX=radius;
	int centerY=radius;
	//��¼����Ƭ��ʱ��
	long timeStamp=System.currentTimeMillis();
	int currPhoto;
	int direction=RIGHT;
	
	Bitmap bmpMan;
	
	Bitmap [] bmpPhotos;
	int [] imgIds={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
	
	public MyView(Context context) {
		super(context);
		bmpMan=BitmapFactory.decodeResource(getResources(), R.drawable.mand);
		
		bmpPhotos=new Bitmap[imgIds.length];
		for(int i=0;i<bmpPhotos.length;i++){
			bmpPhotos[i]=BitmapFactory.decodeResource(getResources(), imgIds[i]);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		//��������
		Paint paint=new Paint();
		//���Ʊ���
		canvas.drawColor(Color.BLACK);
		//����Ӣ��
		canvas.drawBitmap(bmpMan, centerX-radius,centerY-radius, null);
		paint.setColor(Color.BLACK);
		//���ÿ����
		paint.setAntiAlias(true);
		//����
		RectF oval=new RectF(centerX-radius-1, centerY-radius-2, centerX-radius-1+2*radius+2, centerY-radius-2+2*radius+4);
		
		canvas.drawArc(oval, 360-angle+90*direction, 2*angle, true, paint);
		//���ʱ��������5��
		if(System.currentTimeMillis()-timeStamp>5000){
			timeStamp=System.currentTimeMillis();
			//������Ƭ
			currPhoto=(currPhoto+1)%bmpPhotos.length;
		}
		//������Ƭ 
		canvas.drawBitmap(bmpPhotos[currPhoto],80, 40, null);
		super.onDraw(canvas);
	}

}
