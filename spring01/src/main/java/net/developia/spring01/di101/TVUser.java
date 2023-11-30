package net.developia.spring01.di101;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		ApplicationContext context = 
				new GenericXmlApplicationContext(TVUser.class, "beaninit.xml");	// 현재 클래스 위치에서 설정 파일을 찾아옴	 -> 먼저 인스턴스 생성
		System.out.println("==========");
		TV tv = (TV) context.getBean("tv"); // 생성된 인스턴스를 받아오기만 함
		tv.powerOn();
		tv.channelUp();
		tv.channelUp();
		tv.soundUp();
		tv.soundUp();
		tv.soundDown();
		tv.powerOff();
//		TV tv2 = (TV) context.getBean("tv");
//		tv2.powerOn();
	}
}
