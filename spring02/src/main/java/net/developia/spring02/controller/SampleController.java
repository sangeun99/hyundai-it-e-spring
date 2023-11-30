package net.developia.spring02.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.java.Log;
import net.developia.spring02.domain.SampleDTO;
import net.developia.spring02.domain.SampleDTOList;
import net.developia.spring02.domain.TodoDTO;

@Controller
@RequestMapping("/sample/*")
@Log
@PropertySource("classpath:sample.properties")
public class SampleController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dataFormat, false));
	}
	
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
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex03";
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
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("/ex07.......");
		
		// { "name": "홍길동"}
		String msg = "{\"name\": \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<> (msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/ex07b")
	public ResponseEntity<SampleDTO> ex07b() {
		log.info("/ex07b........");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(25);
		dto.setName("홍길동");
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type","application/json;charset=UTF-8");
		
		return new ResponseEntity<> (dto, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public String exUpload() {
		log.info("/exUpload........");
		return "sample/exUpload";
	}
	
	@Value("${uploadPath}") String uploadPath;
	@PostMapping("/exUpload")
	public String exUploadPost(ArrayList<MultipartFile> files) {
		log.info("/exUploadPost........");
		
		files.forEach(file -> {
			log.info("--------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		
			try {
				if (file==null) throw new IOException();
				File filename = new File(uploadPath, file.getOriginalFilename());
				file.transferTo(filename);			
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return "sample/exUpload";
	}
}
