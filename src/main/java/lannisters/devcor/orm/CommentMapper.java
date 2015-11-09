package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Comment;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Maxim
 * @version 1.0
 *
 */

public class CommentMapper implements RowMapper<Comment> {

	/**
	 * Creates Comment and fills fields with database values recieved from
	 * ResultSet.
	 * 
	 * @param rs
	 *            ResultSet - table which has all data recieved by query.
	 * @param rowNum
	 *            number of ResultSet rows.
	 * @return Comment with fields: commentId, comment, orderId, creationDate.
	 */
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setCommentId(rs.getInt("note_id"));
		comment.setComment(rs.getString("note"));
		comment.setOrderId(rs.getInt("request_id"));
		comment.setCreationDate(rs.getDate("creation_date"));
		return comment;
	}
}