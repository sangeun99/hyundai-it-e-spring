package net.developia.spring02.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.java.Log;
import net.developia.spring02.domain.SampleDTO;
import net.developia.spring02.domain.SampleDTOList;

@Controller
@RequestMapping("/sample/*")
@Log
public class SampleController {
	@RequestMapping("") // DELETE /basic 실행될 경우 여기로 매핑됨
	public void basic() {
		log.info("basic.....");
	}
	
	@RequestMapping(value="/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get......");
	}
	
	@GetMapping(value="/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get .......");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name:" + name);
		log.info("age:" + age);
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids: " + ids);
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids")String[] ids) {
		log.info("array ids: " + Arrays.toString(ids));
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		return "ex02Bean";
	}
	
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