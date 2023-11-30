package net.developia.spring01.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import net.developia.spring01.article.dto.ArticleDTO;
import net.developia.spring01.article.service.ArticleService;

@Controller
public class ArticleDetail{
	@Autowired
	ArticleService service;
	
	public ArticleDetail(ArticleService service) {
		this.service = service;
	}
	
	@RequestMapping(value="/article/detail")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("no");
		try {
			ArticleDTO detail = service.getArticleDetail(Integer.parseInt(id));
			return new ModelAndView("article/detail", "dto", detail);
		}
		catch (Exception e) {
			ModelAndView mav = new ModelAndView("/WEB-INF/article/result.jsp");
			mav.addObject("msg","게시물 리스트 출력 실패");
			mav.addObject("url", "../");
			return mav;			
		}
	}
}
