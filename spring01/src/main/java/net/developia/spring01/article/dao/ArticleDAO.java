package net.developia.spring01.article.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.developia.spring01.article.dto.ArticleDTO;

@Repository
public class ArticleDAO {	
	public ArticleDAO() {
		try {
			Class.forName("net.sf.log4jdbc.DriverSpy");			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:log4jdbc:oracle:thin:@localhost:1521/xepdb1", "ACE", "ACE");
	}

	public void insertArticle(ArticleDTO articleDTO) throws SQLException {
		String sql = "insert into article(no, title, name, content, password)" +
					"values(seq_article.nextval, ?, ?, ?, ?)";
		
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, articleDTO.getTitle());
			pstmt.setString(2, articleDTO.getName());
			pstmt.setString(3, articleDTO.getContent());
			pstmt.setString(4, articleDTO.getPassword());
			pstmt.executeQuery();
		}
	}

	public List<ArticleDTO> getArticleList() throws SQLException {
		String sql =
				"SELECT no,title,name,regdate,readcount " +
				"FROM   article " +
				"ORDER BY no DESC ";
		
		List<ArticleDTO> list = new ArrayList<>();
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getLong("no"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				list.add(dto);
			}
			return list;
		}
	}
	
	public ArticleDTO getArticleDetail(int no) throws SQLException {
		String sql = "SELECT no,title,content,name,regdate,readcount " +
				"FROM   article " +
				"WHERE 	no=?";
		ArticleDTO detail = new ArticleDTO();
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, no);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					detail.setNo(rs.getLong("no"));
					detail.setTitle(rs.getString("title"));
					detail.setName(rs.getString("name"));
					detail.setContent(rs.getString("content"));
					detail.setRegdate(rs.getDate("regdate"));
					detail.setReadcount(rs.getInt("readcount"));
				}				
			}
		}
		return detail;
	}
}
