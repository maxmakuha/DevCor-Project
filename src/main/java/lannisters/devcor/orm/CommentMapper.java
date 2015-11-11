package lannisters.devcor.orm;

import java.sql.ResultSet;
import java.sql.SQLException;
import lannisters.devcor.entity.Comment;
import org.springframework.jdbc.core.RowMapper;

public class CommentMapper implements RowMapper<Comment> {

	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setCommentId(rs.getInt("note_id"));
		comment.setComment(rs.getString("note"));
		comment.setOrderId(rs.getInt("request_id"));
		comment.setCreationDate(rs.getDate("creation_date"));
		return comment;
	}
}