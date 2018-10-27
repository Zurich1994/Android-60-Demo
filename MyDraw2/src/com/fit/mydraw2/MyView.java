package com.fit.mydraw2;

import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {

	Paint paint;
	Bitmap myBitmap;
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initBitmap();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//打开抗锯齿
		paint.setAntiAlias(true);
		
		paint.setColor(Color.WHITE);
		paint.setTextSize(15);
		 Matrix matrix = new Matrix();
         int mWidth=myBitmap.getWidth();
         int mHeight=myBitmap.getHeight();
         float scaleWidth=(float)200/mWidth;
         float scaleHeight=(float)200/mHeight;
         matrix.postScale(scaleWidth, scaleHeight);
         Bitmap newBitmap=Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
         canvas.drawBitmap(newBitmap, 170, 250, paint);
         canvas.drawText("图片的大小"+newBitmap.getWidth()+newBitmap.getHeight(),180, 480, paint);
	}
	public void initBitmap(){
		paint=new Paint();
		myBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.gg);
	}
	
}
