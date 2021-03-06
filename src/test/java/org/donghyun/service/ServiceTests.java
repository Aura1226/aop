package org.donghyun.service;

import org.donghyun.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ServiceTests {

	@Autowired
	SampleService service;
	

	
	@Test
	public void show() {
		log.info(service.getClass().getName());
		
		service.doA();
//		
//		service.doB();
//		log.info(service.doA());
//		log.info(service.doB());
//		log.info(service.doC());
	}
}
