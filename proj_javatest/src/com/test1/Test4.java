package com.test1;

import com.fit.inter.MyInterface;


public class Test4 implements MyInterface  {

	
	@Override
	public void getNumber() {
		int m=(int)(Math.random()*10);
		System.out.println("随机数是:"+m);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("这是我第四次测试ant！");
		MyInterface in=new Test4();
		in.getNumber();
		
		
	}

	

}
