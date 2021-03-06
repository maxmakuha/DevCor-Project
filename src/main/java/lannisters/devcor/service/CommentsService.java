package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.entity.Comment;

public interface CommentsService {

	public List<Comment> getAllComments();

	public Comment getCommentById(int commentId);

	public void addComment(Comment comment) throws SQLException;

	public void updateComment(Comment comment) throws SQLException;
	
	public void deleteComment(int commentId) throws SQLException;

	public List<Comment> getAllCommentsOfOrder(int orderId);
}