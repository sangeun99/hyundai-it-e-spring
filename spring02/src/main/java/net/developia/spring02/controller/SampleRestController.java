package net.developia.spring02.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;
import net.developia.spring02.domain.SampleDTO;

@RestController
@RequestMapping("/restful")
@Log
public class SampleRestController {
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() { 
		// @ResponseBody: 리턴하고자 하는 데이터가 json이나 txt로 바뀜 -> 뷰를 거치지 않고 프론트로 바로 쏘게 됨
		// @RequestBody: 
		log.info("/ex06....");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
}
