package com.fit.mydrawview;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	Button dong,stop;
	MyView myView;
	 boolean isStart;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LinearLayout root=(LinearLayout) findViewById(R.id.root);
        myView=new MyView(this);
        root.addView(myView);
        
		dong=(Button) findViewById(R.id.dong);
		stop=(Button) findViewById(R.id.stop);
		dong.setOnClickListener(this);
		stop.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
    	if(v==dong){
    		isStart=true;
    			myView.myDraw();
    			myView.invalidate();
    			
    			
    			
    	}
    	if(v==stop){
    		isStart=false;
    	}
    }
    
    
   public class MyView extends View{

	   Bitmap bm;
	   Matrix matrix;
	  
	   
	public MyView(Context context) {
		super(context);
		
		 bm=BitmapFactory.decodeResource(getResources(), R.drawable.gg);
		 matrix=new Matrix();
		 /*���ȣ�������Ϊ100*100������scale�Ĳ����Ǳ�������һ��Ҫע�⣬���ֱ����100/  
		bmp.getWidth()�Ļ�����õ�0����Ϊ��������������Ա���������һ����float�͵ģ�ֱ����100f�ͺá�*/ 
		matrix.setScale(0.5f,0.5f);
		//��ת
//		matrix.setRotate(30, 100, 100);
		//ƽ�Ƶ���100��100����   
		matrix.postTranslate(100,100);
		 //��бx��y�ᣬ�ԣ�100��100��Ϊ���ġ�   
		matrix.postSkew(0.2f, 0.2f,100,100);
		
		
	}
	   @Override
	protected void onDraw(Canvas canvas) {
		   canvas.drawBitmap(bm, matrix, null);
		   Paint p=new Paint();
		   p.setColor(Color.RED);
		   p.setTextSize(20);
   			String familyName="����_GB2312";
   			Typeface font=Typeface.create(familyName, Typeface.ITALIC);
   			p.setTypeface(font);
   			canvas.drawText("������è��", 200, 200, p); 
	   }
	
	   public void myDraw(){
						  final Animation mRotateAnimation=new RotateAnimation(0.0f,360.0f,   
									Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
					        	mRotateAnimation.setDuration(2000);
						 MyView.this.setAnimation(mRotateAnimation);
						 postInvalidate();
							if(isStart){
								mRotateAnimation.setAnimationListener(new AnimationListener() {
									
									@Override
									public void onAnimationStart(Animation animation) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void onAnimationRepeat(Animation animation) {
										// TODO Auto-generated method stub
									}
									
									@Override
									public void onAnimationEnd(Animation animation) {
										// TODO Auto-generated method stub
										mRotateAnimation.start();
									}
								});
//								myDraw();
							}else{}
	   }
//	   private Bitmap mBitmap; 
//	   private Bitmap mBitmap2; 
//	   private Bitmap mBitmap3;       
//	   private Shader mShader;
//    	
//    	private void drawIntoBitmap(Bitmap bm){
//    		float x=bm.getWidth();
//    		float y=bm.getHeight();
//    		//����һ�����ƶ�����Ļ���
//    		Canvas c=new Canvas(bm);
//    		Paint p=new Paint();
//    		//��Ҫ����ӭ��������Ե ��Ч���Ͽ� ����false�Ļ���ë��
//    		p.setAntiAlias(true);
//    		//��ɫ
//    		p.setAlpha(0x555555);
//    		c.drawCircle(x/2, 2/y,x/2, p);
//    		//��ɫ
//    		p.setAlpha(0x0000ff);
//    		p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//    		//������������������
//    		p.setTextSize(20);
//    		String familyName="����";
//    		Typeface font=Typeface.create(familyName, Typeface.ITALIC);
//    		p.setTypeface(font);
//    		p.setTextAlign(Paint.Align.CENTER);
//    		Paint.FontMetrics fm=p.getFontMetrics();
//    		c.drawText("����һ���¶����ˣ�", x/2, 2/y, p);
//    	}
//    	
//		public MyView(Context context) {
//			super(context);
//			setFocusable(true);
//			//�õ�ͼ��ļ��ַ���
//			InputStream is = context.getResources().openRawResource(R.drawable.gg);
//			mBitmap=BitmapFactory.decodeStream(is);
//			//��һ��ͼ���ȡɫֵ��Ϊ�µ�ͼ��һ���ǻ��ʵ���ɫ��ͬ����
//			Paint p=new Paint();
//			p.setColor(Color.BLUE);
//			mBitmap2=mBitmap.extractAlpha();
//			mBitmap3=Bitmap.createBitmap(200, 200,Bitmap.Config.ALPHA_8);
//			drawIntoBitmap(mBitmap3);
//			//һ�ֽ���Ч��
//			mShader=new LinearGradient(0,0, 150, 70, new int[]{Color.RED,Color.GREEN,Color.BLUE}, null,Shader.TileMode.MIRROR);
//			
//		}
//    	
//		
//		@Override
//		protected void onDraw(Canvas canvas) {
//			canvas.drawColor(Color.LTGRAY);
//			Paint p=new Paint();
//			float y=10;
//			p.setColor(Color.BLUE);
//			canvas.drawBitmap(mBitmap, 10,y, p);
//			y+=mBitmap.getHeight()+10;
//			canvas.drawBitmap(mBitmap2, 10, y, p);
//			y+=mBitmap2.getHeight()+10;
//			p.setShader(mShader);
//			canvas.drawBitmap(mBitmap3, 10, y, p);
//		}
    }
}