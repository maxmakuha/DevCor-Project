package ua.lannisters.devcor.db.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ua.lannisters.devcor.db.model.Comment;

@Repository("commentDao")
public class CommentDaoImpl implements CommentDao {

	private static final String CREATE_SQL = "INSERT INTO COMMENTS(COMMENT, CREATION_DATE, COMMENT_ID) "
			+ "VALUES (COMMENTS_SEQ.NEXTVAL, :comment, :creationDate)";

	private static final String GET_ALL_SQL = "SELECT COMMENT, CREATION_DATE, COMMENT_ID " + "FROM COMMENTS";

	private static final String GET_SQL = "SELECT COMMENT_ID, COMMENT, CREATION_DATE "
			+ "FROM COMMENTS WHERE COMMENT_ID = :commentId";
	
	private static final String UPDATE_SQL = "UPDATE COMMENTS SET COMMENT = :comment, CREATION_DATE = :creationDate " + "WHERE COMMENT_ID = :commentId";

	private static final String DELETE_SQL = "DELETE COMMENTS WHERE COMMENT_ID = :commentId";
	private NamedParameterJdbcOperations jdbcTemplate;

	private CommentRowMapper commentRowMapper;

	public void createComment(Comment comment) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("comment", comment.getComment())
				.addValue("creationDate", comment.getCreationDate());
		jdbcTemplate.update(CREATE_SQL, params);

	}

	public List<Comment> getComments() {
		return jdbcTemplate.query(GET_ALL_SQL, new HashMap<String, Object>(), commentRowMapper);
		
	}

	public Comment getComment(int commentId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("commentId", commentId);
		List<Comment> comments = jdbcTemplate.query(GET_SQL, params, commentRowMapper);
		
		return comments.isEmpty() ? null : comments.get(0);

	}

	public void updateComment(Comment comment) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("comment", comment.getComment())
				.addValue("creationDate", comment.getCreationDate())
				.addValue("commentId", comment.getCommentId());
				
		
		jdbcTemplate.update(UPDATE_SQL, params);

	}

	public void deleteComment(int commentId) {
		jdbcTemplate.update(DELETE_SQL, new MapSqlParameterSource("commentId", commentId));
	}

}
