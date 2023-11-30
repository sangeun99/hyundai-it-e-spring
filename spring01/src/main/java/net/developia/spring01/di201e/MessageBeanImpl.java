package net.developia.spring01.di201e;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("di201e/app.properties")
public class MessageBeanImpl implements MessageBean {
	@Value("${name}") private String name;
	@Value("${greeting}") private String greeting;
	
	@Autowired
	@Qualifier("fileout")
	private FileOutputter fileOutputter;
	
	public void sayHello() {
		System.out.println(name + "님, " + greeting);
		try {
			fileOutputter.outputter(name + "님, " + greeting);
		} catch (IOException e) {
			e.getMessage();
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
