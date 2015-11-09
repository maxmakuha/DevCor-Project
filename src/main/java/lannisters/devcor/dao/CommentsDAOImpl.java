package lannisters.devcor.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import lannisters.devcor.entity.Comment;
import lannisters.devcor.orm.CommentMapper;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

@Repository
public class CommentsDAOImpl implements CommentsDAO {

	private static final String SQL_SELECT_ALL_COMMENTS = "SELECT note.note_id, note.note, note.request_Id, note.creation_date FROM note";
	private static final String SQL_SELECT_COMMENT_BY_ID = "SELECT note.note_id, note.note, note.request_Id, note.creation_date FROM note WHERE note.note_id=?";
	private static final String SQL_INSERT_COMMENT = "INSERT INTO note(note, request_id, creation_date) VALUES (?, ?, ?)";
	private static final String SQL_DELETE_COMMENT = "DELETE note WHERE note_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Comment> getAllComments() {
		return jdbcTemplate.query(SQL_SELECT_ALL_COMMENTS, new CommentMapper());
	}

	public Comment getCommentById(int commentId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_COMMENT_BY_ID,
				new CommentMapper(), commentId);
	}

	public void addComment(Comment comment) {
		jdbcTemplate.update(SQL_INSERT_COMMENT, comment);
	}

	public void deleteComment(int commentId) {
		jdbcTemplate.update(SQL_DELETE_COMMENT, commentId);
	}
}