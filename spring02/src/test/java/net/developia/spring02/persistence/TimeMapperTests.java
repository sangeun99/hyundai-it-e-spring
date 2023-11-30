package net.developia.spring02.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;
import net.developia.spring02.mapper.TimeMapper;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration("file:**/*-context.xml")
@Log4j
public class TimeMapperTests {
	@Autowired
	private TimeMapper timeMapper;
	
	@Test
	public void testConnection() {
		log.info(timeMapper.getClass().getName()); // timeMapper 구현체를 얘가 만들고 timeMapper가 들어감
		log.info(timeMapper.getTime());
		log.info(timeMapper.getTime2());
	}
}
