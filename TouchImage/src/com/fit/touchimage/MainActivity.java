package com.fit.touchimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {
	/** Called when the activity is first created. */

	   /** Called when the activity is first created. */
	 
	 //�Ŵ���С
	 Matrix matrix=new Matrix();
	 Matrix savedMatrix=new Matrix();
	 
	 PointF start=new PointF();
	 PointF mid=new PointF();
	 float oldDist;
	 
	 private ImageView myImageView;
	 
	 //ģʽ
	 static final int NONE=0;
	 static final int DRAG=1;
	 static final int ZOOM=2;
	 int mode=NONE;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        myImageView=(ImageView) findViewById(R.id.myImage);
	        Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.xiaoxiong);
	        Matrix matrix = new Matrix();
            int mWidth=bit.getWidth();
            int mHeight=bit.getHeight();
            float scaleWidth=(float)320/mWidth;
            float scaleHeight=(float)480/mHeight;
            Log.i("scale", scaleWidth+"++++++++++++"+scaleHeight);
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap newBitmap=Bitmap.createBitmap(bit, 0, 0, bit.getWidth(), bit.getHeight(), matrix, true);
	        myImageView.setImageBitmap(newBitmap);
	        myImageView.setOnTouchListener(this);
	        
	    }

	 @Override
	 public boolean onTouch(View v, MotionEvent event) {
	  ImageView myImageView=(ImageView) v;
	  switch(event.getAction()&MotionEvent.ACTION_MASK){
	   //��������ģʽ 
	  case MotionEvent.ACTION_DOWN:
	   matrix.set(myImageView.getImageMatrix());
	   savedMatrix.set(matrix);
	   start.set(event.getX(),event.getY());
	   mode=DRAG;
	  break;
	  case MotionEvent.ACTION_UP:
	  case MotionEvent.ACTION_POINTER_UP:
	   mode=NONE;
	   break;
	  
	  //���ö�㴥��ģʽ
	  case MotionEvent.ACTION_POINTER_DOWN:
	   oldDist=spacing(event);
	   if(oldDist>10f){
	    savedMatrix.set(matrix);
	    midPoint(mid, event);
	    mode=ZOOM;
	   }
	   break;
	   //��ΪDRAGģʽ�������ƶ�ͼƬ
	  case MotionEvent.ACTION_MOVE:
	   if(mode==DRAG){
	    matrix.set(savedMatrix);
	    matrix.postTranslate(event.getX()-start.x,event.getY()-start.y);
	   }
	   //��ΪZOOMģʽ��������������
	   else if(mode==ZOOM){
	    float newDist=spacing(event);
	    if(newDist>10f){
	     matrix.set(savedMatrix);
	     float scale=newDist/oldDist;
	     //����˶�ű�����ͼƬ���е�λ��
	     matrix.postScale(scale,scale, mid.x,mid.y);
	    }
	   }
	   break;
	  } 
	  myImageView.setImageMatrix(matrix);
	  return true;
	 }
	 //�����ƶ�����
	 private float spacing(MotionEvent event){
	  float x=event.getX(0)-event.getX(1);
	  float y=event.getY(0)-event.getY(1);
	  return FloatMath.sqrt(x*x+y*y);
	 }
	 //�����е�λ��
	 private void midPoint(PointF point,MotionEvent event){
	  float x=event.getX(0)+event.getX(1);
	  float y=event.getY(0)+event.getY(1);
	  point.set(x/2,y/2);
	 }


}