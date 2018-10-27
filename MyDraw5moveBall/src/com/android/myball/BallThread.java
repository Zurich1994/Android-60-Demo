package com.android.myball;

public class BallThread extends Thread {

	Movable father;
	//�߳�ִ�б�־λ
	boolean flag=false;
	//����ʱ��
	int sleepSpan=40;
	//������ļ��ٶ�
	float g=200;
	//��¼��ǰʱ��
	double current;
	public BallThread(Movable father) {
		super();
		this.father = father;
		this.flag = true;
	}
	
	@Override
	public void run() {
		while(flag){
			// ��ȡ��ǰʱ�䣬��λΪ����
			current=System.nanoTime();
			//����ˮƽ�����ϵ��˶�ʱ��
			double timeSpanX=(double)((current-father.timeX)/1000/1000/1000);
			//��ȡˮƽ�����߹���λ��
			father.x=(int)(father.startX+father.v_x*timeSpanX);
		//�ж����Ƿ��Ѿ��Ƴ�����
			if(father.bFall){
				double timeSpanY=(double)((current-father.timeY)/1000/1000/1000);
				father.y=(int)(father.startY+father.startVY*timeSpanY+timeSpanY*timeSpanY*g/2);
				//��ֱ�����ٶ�
				father.v_y=(float)(father.startVY+g*timeSpanY);
				
				//�ж�С���Ƿ񵽴���ߵ�
				if(father.startVY<0&&Math.abs(father.v_y)<=BallView.UP_ZERO){
					father.timeY=System.nanoTime();
					father.v_y=0;
					father.startVX=0;
					father.startY=father.y;
				}
				//�ж�С���Ƿ�ײ��
				if(father.y+father.r*2>=BallView.GROUND_LINE&&father.v_y>0){
					father.v_x=father.v_x*(1-father.impactFactor);
					if(Math.abs(father.v_y)<BallView.DOWN_ZERO){
						this.flag=false;
					}else{
						father.startX=father.x;
						father.timeX=System.nanoTime();
						father.startY=father.y;
						father.timeY=System.nanoTime();
						father.startVY=father.v_y;
					}
				}
			}
			//�ж����Ƿ��Ƴ��˵���
			else if(father.x+father.r/2>=BallView.WOOD_EDGE){
				father.timeY=System.nanoTime();
				father.bFall=true;
				try {
					Thread.sleep(sleepSpan);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		}
	}
}
