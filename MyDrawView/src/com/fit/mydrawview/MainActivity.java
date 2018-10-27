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
		 /*首先，将缩放为100*100。这里scale的参数是比例。有一点要注意，如果直接用100/  
		bmp.getWidth()的话，会得到0，因为是整型相除，所以必须其中有一个是float型的，直接用100f就好。*/ 
		matrix.setScale(0.5f,0.5f);
		//旋转
//		matrix.setRotate(30, 100, 100);
		//平移到（100，100）处   
		matrix.postTranslate(100,100);
		 //倾斜x和y轴，以（100，100）为中心。   
		matrix.postSkew(0.2f, 0.2f,100,100);
		
		
	}
	   @Override
	protected void onDraw(Canvas canvas) {
		   canvas.drawBitmap(bm, matrix, null);
		   Paint p=new Paint();
		   p.setColor(Color.RED);
		   p.setTextSize(20);
   			String familyName="楷体_GB2312";
   			Typeface font=Typeface.create(familyName, Typeface.ITALIC);
   			p.setTypeface(font);
   			canvas.drawText("功夫熊猫！", 200, 200, p); 
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
//    		//建立一个有制定对象的画布
//    		Canvas c=new Canvas(bm);
//    		Paint p=new Paint();
//    		//主要爱是迎来消除边缘 从效果上看 好像false的话有毛边
//    		p.setAntiAlias(true);
//    		//灰色
//    		p.setAlpha(0x555555);
//    		c.drawCircle(x/2, 2/y,x/2, p);
//    		//蓝色
//    		p.setAlpha(0x0000ff);
//    		p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//    		//下面是字体的相关设置
//    		p.setTextSize(20);
//    		String familyName="宋体";
//    		Typeface font=Typeface.create(familyName, Typeface.ITALIC);
//    		p.setTypeface(font);
//    		p.setTextAlign(Paint.Align.CENTER);
//    		Paint.FontMetrics fm=p.getFontMetrics();
//    		c.drawText("我是一个孤独的人！", x/2, 2/y, p);
//    	}
//    	
//		public MyView(Context context) {
//			super(context);
//			setFocusable(true);
//			//得到图像的几种方法
//			InputStream is = context.getResources().openRawResource(R.drawable.gg);
//			mBitmap=BitmapFactory.decodeStream(is);
//			//从一副图像抽取色值变为新的图像，一般是画笔的颜色，同下面
//			Paint p=new Paint();
//			p.setColor(Color.BLUE);
//			mBitmap2=mBitmap.extractAlpha();
//			mBitmap3=Bitmap.createBitmap(200, 200,Bitmap.Config.ALPHA_8);
//			drawIntoBitmap(mBitmap3);
//			//一种渐变效果
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