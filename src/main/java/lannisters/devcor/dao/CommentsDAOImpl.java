package lannisters.devcor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lannisters.devcor.entity.Comment;
import lannisters.devcor.orm.CommentMapper;

@Repository
public class CommentsDAOImpl implements CommentsDAO {

	private static final String SQL_SELECT_ALL_COMMENTS = "SELECT note.note_id, note.note, note.request_Id, note.creation_date FROM note";
	private static final String SQL_SELECT_COMMENT_BY_ID = "SELECT note.note_id, note.note, note.request_Id, note.creation_date FROM note WHERE note.note_id=?";
	private static final String SQL_INSERT_COMMENT = "INSERT INTO note(note, request_id, creation_date) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE_COMMENT = "UPDATE note SET note = ?, request_id = ?, creation_date = ? WHERE note_id=?";
	private static final String SQL_DELETE_COMMENT = "DELETE note WHERE note_id=?";
	private static final String SQL_SELECT_ALL_COMMENTS_OF_ORDER = "SELECT note.note_id, note.note, note.request_id, note.creation_date FROM note WHERE note.request_id = ? ORDER BY note.creation_date DESC";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Comment> getAllComments() {
		return jdbcTemplate.query(SQL_SELECT_ALL_COMMENTS, new CommentMapper());
	}

	@Override
	public Comment getCommentById(int commentId) {
		return jdbcTemplate.queryForObject(SQL_SELECT_COMMENT_BY_ID, new CommentMapper(), commentId);
	}

	@Override
	public void addComment(Comment comment) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_INSERT_COMMENT);
			ps.setString(1, comment.getComment());
			ps.setInt(2, comment.getOrderId());
			ps.setTimestamp(3, comment.getCreationDate());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void updateComment(Comment comment) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_UPDATE_COMMENT);
			ps.setString(1, comment.getComment());
			ps.setInt(2, comment.getOrderId());
			ps.setTimestamp(3, comment.getCreationDate());
			ps.setInt(4, comment.getCommentId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public void deleteComment(int commentId) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SQL_DELETE_COMMENT);
			ps.setInt(1, commentId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	@Override
	public List<Comment> getAllCommentsOfOrder(int orderId) throws SQLException {
		return jdbcTemplate.query(SQL_SELECT_ALL_COMMENTS_OF_ORDER, new CommentMapper(), orderId);
	}
}