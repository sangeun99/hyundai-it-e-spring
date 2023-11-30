package net.developia.spring01.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.developia.spring01.article.dto.ArticleDTO;
import net.developia.spring01.article.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleInsert {
	
	@Autowired
	ArticleService service;
	

	@GetMapping("/insert")
	protected String insert(){
		return "article/insert";
	}
	
	@PostMapping("/insert")
	public ModelAndView insertAction(@ModelAttribute ArticleDTO articleDTO) { // 알아서 넘어온 값을 넣어줌

		try {
			// 성공 시 게시물 리스트로 넘어가면 됨 (forward 아닌 페이지 이동)
			service.insertArticle(articleDTO);
			return new ModelAndView("redirect:list");
		}
		catch (Exception e) {
			// 실패 시 alert으로 알려줌
			ModelAndView mav = new ModelAndView("/WEB-INF/article/result.jsp");
			mav.addObject("msg","게시물 입력 실패\\n 입력 폼으로 되돌아갑니다.");
			mav.addObject("url", "javascript:history.back();");
			return mav;
		}
	}
}
