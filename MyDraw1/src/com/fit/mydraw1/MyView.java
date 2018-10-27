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
		//���ƺ�ɫ����
		canvas.drawColor(Color.BLACK);
		//��������
		Paint paint=new Paint();
		paint.setColor(Color.RED);
		
		//���ƾ���
		canvas.drawRect(10,10, 110, 110, paint);
		canvas.drawText("�����ַ�����",10, 130, paint);
		//����һ������ 
		paint.setColor(Color.GREEN);
		canvas.drawRect(10, 300, 200, 490, paint);
		RectF rf1=new RectF(10, 300, 200, 490);
		//˳ʱ�뻭��
		paint.setColor(Color.RED);
		canvas.drawArc(rf1, 0, 90, true, paint);
		//����
		paint.setColor(Color.WHITE);
		canvas.drawLine(300, 300, 400, 450, paint);
		//����һ������
		RectF rf2=new RectF(150, 130, 250, 230);
		//��Բ
		canvas.drawOval(rf2, paint);
	}
}
