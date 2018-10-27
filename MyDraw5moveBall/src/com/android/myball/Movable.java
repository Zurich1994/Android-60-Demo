package com.android.myball;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Movable {

	//���������ƶ������Movable��
	
	//��ʼ��X��Y������
	int startX=0;
	int startY=0;
	//ʵʱX��Y����
	int x;
	int y;
	//��ʼ��ˮƽ����ֱ������ٶ�
	float startVX=0;
	float startVY=0;
	//ʵʱˮƽ����ֱ������ٶ�
	float v_x=0f;
	float v_y=0f;
	//���ƶ�����İ뾶
	int r;
	//X��Y�����ϵ��˶�ʱ��
	double timeX;
	double timeY;
	//���ƶ�����ͼƬ
	Bitmap bitmap;
	//����С���ƶ�
	BallThread bt;
	//С���Ƿ��Ѿ���ľ��������
	boolean bFall=false;
	//С��ײ�غ��ٶȵ���ʧϵ��
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







