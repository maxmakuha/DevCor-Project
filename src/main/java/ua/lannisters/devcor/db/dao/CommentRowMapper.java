package ua.lannisters.devcor.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ua.lannisters.devcor.db.model.Comment;

@Component
public class CommentRowMapper implements RowMapper<Comment> {

	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new Comment(rs.getString("COMMENT"), rs.getDate("CREATION_DATE"), rs.getInt("COMMENT_ID"));
	}
	

}
