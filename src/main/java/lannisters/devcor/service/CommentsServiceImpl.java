package lannisters.devcor.service;

import java.sql.SQLException;
import java.util.List;

import lannisters.devcor.dao.CommentsDAO;
import lannisters.devcor.entity.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsDAO commentsDao;

	@Override
	public List<Comment> getAllComments() {
		return commentsDao.getAllComments();
	}

	@Override
	public Comment getCommentById(int commentId) {
		return commentsDao.getCommentById(commentId);
	}

	@Override
	public void addComment(Comment comment) throws SQLException {
		commentsDao.addComment(comment);
	}

	@Override
	public void updateComment(Comment comment) throws SQLException {
		commentsDao.updateComment(comment);
	}

	@Override
	public void deleteComment(int commentId) throws SQLException {
		commentsDao.deleteComment(commentId);
	}

	@Override
	public List<Comment> getAllCommentsOfOrder(int orderId) throws SQLException {
		return commentsDao.getAllCommentsOfOrder(orderId);
	}
}