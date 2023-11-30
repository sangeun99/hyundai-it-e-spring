package net.developia.spring02.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:**/*-context.xml") // bean context 파일 이름은 관례적으로 *-context
@Log4j
public class SampleTests {
	@Autowired
	private Restaurant restaurant;
	
	@Test
	public void testExist() {
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("-------------");
		log.info(restaurant.getChef());
	}
	
	@Test
	public void testUsrDTO() throws  Exception {
       UserDTO userDTO = new UserDTO();
       userDTO.setUsr_id("next");
       userDTO.setUsr_name("신해철");
       userDTO.setUsr_pw("1234");
       log.info(userDTO.toString());
       assertNotNull(userDTO);
    }
	
	@Test(timeout = 2000)
    public void create_usrDTO() {
        UserDTO userDTO = new UserDTO();
        try {
//         Thread.sleep(3000);
        	Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
        assertNotNull(userDTO); // 값을 눈으로 확인하기보다 assert를 이용하는 편이 좋음
        log.info("생성완료~!");
    }

    @Test
    //@Disabled
    @Ignore
    public void create_usrDTO_2() {
       log.info("일단 실행하지 않음");
    }

    //@BeforeAll
    @BeforeClass
    public static void beforeClass() {
       log.info("가장 처음 1번 실행");
    }

    //@AfterAll
    @AfterClass
    public static void afterClass() {
       log.info("가장 마지막 1번 실행");
    }

    //@BeforeEach //주석은 JUnit5
    @Before
    public void before() {
       log.info("각 메소드 테스트 전 실행");
    }

    //@AfterEach
    @After
    public void after() {
       log.info("각 메소드 테스트 후 실행");
    }
}
