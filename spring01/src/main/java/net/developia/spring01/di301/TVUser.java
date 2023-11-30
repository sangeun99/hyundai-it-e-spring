package net.developia.spring01.di301;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext("net.developia.spring01.di301");
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
