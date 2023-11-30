package net.developia.spring01.article.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import net.developia.spring01.article.dao.ArticleDAO;
import net.developia.spring01.article.dto.ArticleDTO;

@Log
@Service
public class ArticleService {
	// 관리자가 비즈니스 로직을 수행하며 로그, 메세지를 찍는 역할
	private ArticleDAO dao;
	
	public ArticleService(ArticleDAO dao) {
		this.dao = dao;
	}

	public void insertArticle(ArticleDTO articleDTO) throws SQLException {
		try {
			dao.insertArticle(articleDTO);
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	public List<ArticleDTO> getArticleList() throws SQLException {
		try {
			return dao.getArticleList();
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return null;
	}
	
	public ArticleDTO getArticleDetail(int no) throws SQLException {
		try {
			ArticleDTO dto = dao.getArticleDetail(no);
			if (dto == null) throw new RuntimeException(no + "번 게시물이 존재하지 않습니다.");
			return dto;
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return null;
	}
}