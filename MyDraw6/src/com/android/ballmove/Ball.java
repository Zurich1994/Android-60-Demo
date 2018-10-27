package com.android.ballmove;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {
	/**  
	18.          * ��ĸ�  
	19.          */  
	         public static final int HEIGHT = 93;   
	         /**  
	22.          * ��Ŀ�  
	23.          */  
	         public static final int WIDTH = 93;   
	         private static final int STEPLENGTH = 10;//ÿ���˶��ļ��   
	         private static final float REDUCEPERCENTAGE =  0.35F;//�ݼ�ϵ��   
	         private int stepReduce ;//ÿ�η����˶������̵ľ���   
	           
	         private float runX ;//���λ��   
	         private float runY ;//���λ��   
         private BallSurfaceView bsv ;   
	         private boolean upDirection = false;//if true,up direction,or is down direction   
	         private float maxHeight ;//��ǰ�˶���ߵĸ߶�   
	         private Paint paint ;   
	           
	         Bitmap ballBitmap ;//���ͼƬ   
	         SportActivity sa ;   
	         public Ball(float initX , float initY , BallSurfaceView bsv){   
	                   this.runX  = initX;   
	                   this.runY = initY ;   
	                   maxHeight = initY;   
	                   this.bsv = bsv;   
	                   ballBitmap = BitmapFactory.decodeResource(bsv.getResources(), R.drawable.ball);//����ͼƬ   
	                   paint = new Paint();   
	                   sa = bsv.sportActivity;   
	         }   
	           
	         public void onDraw(Canvas canvas) {   
	                   int c = paint.getColor();//������ɫ��֮��ԭΪ֮ǰ��ɫ   
	                   boundaryTest();   
	                   if(canvas != null) canvas.drawBitmap(ballBitmap,runX,runY,paint);   
	                   paint.setColor(c);   
	                   move();   
	        }   
         /**  
	56.          * �˶�  
	57.          */  
	         private void move() {   
	                   if(maxHeight >= (sa.screenHeight - HEIGHT)) {   
	                            return;   
	                  }   
                   if(upDirection){//����   
	                            runY = runY + STEPLENGTH ;   
	                   }else{   
	                            runY = runY - STEPLENGTH ;   
	                   }   
	         }   
	    
	         /**  
	70.          * �߽��⣬ʹ�򲻻�ɳ��߽�  
	71.          */  
	         private void boundaryTest(){   
	    
	                   if(runY > sa.screenHeight - HEIGHT){//�����˶���ͷ   
	                          upDirection = !upDirection;//�����÷�   
	                            runY = sa.screenHeight - HEIGHT;   
	                            stepReduce = (int) (maxHeight * REDUCEPERCENTAGE);   
	                            maxHeight = maxHeight + stepReduce ;//���߶ȵݼ�   
	                              
	                  }   
	                   if(runY < maxHeight ){//�����˶���ͷ   
                            upDirection = !upDirection;//�����÷�   
	                           if(maxHeight >= (sa.screenHeight - HEIGHT)) return;   
	                            runY = maxHeight ;   
	                              
                   }   
         }   
	}  
