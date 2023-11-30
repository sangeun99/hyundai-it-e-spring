package net.developia.spring01.di101e;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageBeanTest {
	public static void main(String[] args) {
		ApplicationContext context = 
				new GenericXmlApplicationContext(MessageBeanTest.class, "beaninit.xml"); // 어플리케이션 컨텍스트 생성
		MessageBean mb = (MessageBean) context.getBean("messageBean");
		mb.sayHello();
	}
}
