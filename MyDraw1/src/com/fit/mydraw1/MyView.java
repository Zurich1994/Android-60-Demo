package com.fit.mydraw1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//绘制黑色背景
		canvas.drawColor(Color.BLACK);
		//创建画笔
		Paint paint=new Paint();
		paint.setColor(Color.RED);
		
		//绘制矩形
		canvas.drawRect(10,10, 110, 110, paint);
		canvas.drawText("这是字符串！",10, 130, paint);
		//定义一个矩形 
		paint.setColor(Color.GREEN);
		canvas.drawRect(10, 300, 200, 490, paint);
		RectF rf1=new RectF(10, 300, 200, 490);
		//顺时针画弧
		paint.setColor(Color.RED);
		canvas.drawArc(rf1, 0, 90, true, paint);
		//画线
		paint.setColor(Color.WHITE);
		canvas.drawLine(300, 300, 400, 450, paint);
		//定义一个矩形
		RectF rf2=new RectF(150, 130, 250, 230);
		//画圆
		canvas.drawOval(rf2, paint);
	}
}
