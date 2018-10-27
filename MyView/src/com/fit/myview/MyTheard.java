package com.fit.myview;

public class MyTheard extends Thread {

	int sleepSpan=30;
	MyView myView;
	public MyTheard(MyView myView) {
		super();
		this.myView = myView;
	}
	
	@Override
	public void run() {
		while(true){
			myView.angle=myView.angle+myView.angleChange;
			if(myView.angle>MyView.ANGEL_MAX){
				myView.angleChange=-3;
			}else if(myView.angle<0){
				myView.angleChange=+3;
			}
			switch(myView.direction){
			case MyView.RIGHT:
				myView.centerX=myView.centerX+MyView.SPEED;
				break;
			case MyView.UP:
				myView.centerY=myView.centerY-MyView.SPEED;
				break;
			case MyView.LEFT:
				myView.centerX=myView.centerX-MyView.SPEED;
				break;
			case MyView.DOWN:
				myView.centerY=myView.centerY+MyView.SPEED;
				break;
			}
			//�����±߽�
			if(myView.centerY+myView.radius>MyView.SCREEN_HEIGHT){
				myView.centerY=myView.centerY-MyView.SPEED;
				myView.direction=MyView.LEFT;
			}
			//�����ϱ߽�
			if(myView.centerY-myView.radius<0){
				myView.centerY=myView.centerY+MyView.SPEED;
				myView.direction=MyView.RIGHT;
			}
			//������߽�
			if(myView.centerX-myView.radius<0){
				myView.centerX=myView.radius;
				myView.direction=MyView.UP;
			}
			//�����ұ߽�
			if(myView.centerX+myView.radius>MyView.SCREEN_WIDTH){
				myView.centerX=myView.centerX-MyView.SPEED;
				myView.direction=MyView.DOWN;
			}
			myView.postInvalidate();
			try {
				Thread.sleep(sleepSpan);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
