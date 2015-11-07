package ua.lannisters.devcor.db.dao;

import java.util.List;

import ua.lannisters.devcor.db.model.Comment;

public interface CommentDao {

	public void createComment(Comment comment);

	public List<Comment> getComments();

	public Comment getComment(int commentId);

	public void updateComment(Comment comment);

	public void deleteComment(int commentId);

}
