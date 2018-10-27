package com.android.myball;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {
 
	BallView bv;
	SurfaceHolder surfaceHolder;
	//�߳�ִ�б�־λ
	boolean flag=false;
	int sleepSpan=30;
	//��¼��ʼʱ�䣬�ñ������ڼ���֡����
	long start=System.nanoTime();
	//��¼֡�����ñ������ڼ���֡����
	int count=0;
	public DrawThread(BallView bv, SurfaceHolder surfaceHolder) {
		super();
		this.bv = bv;
		this.surfaceHolder = surfaceHolder;
		this.flag=true;
	}
	
	@Override
	public void run() {
		Canvas canvas=null;
	while(flag){
		//��ȡBallView�Ļ���
		try{
		canvas=surfaceHolder.lockCanvas(null);
		synchronized (surfaceHolder) {
			bv.doDraw(canvas);
		}
	}
	catch(Exception e){
		e.printStackTrace();
		}
	finally{
		surfaceHolder.unlockCanvasAndPost(canvas);
	}
	}
	this.count++;
	if(count==20){
		count=0;
		long tempStamp=System.nanoTime();
		long span=tempStamp-start;
		start=tempStamp;
		double fps=Math.round(1000000000.0/span*20)/100.0;
		bv.fps="FPS:"+fps;
	}
	try {
		Thread.sleep(sleepSpan);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
	
}
