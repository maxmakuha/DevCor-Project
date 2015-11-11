package lannisters.devcor.service;

import java.util.List;
import lannisters.devcor.dao.CommentsDAO;
import lannisters.devcor.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsDAO commentsDao;

	public List<Comment> getAllComments() {
		return commentsDao.getAllComments();
	}

	public Comment getCommentById(int commentId) {
		return commentsDao.getCommentById(commentId);
	}

	public void addComment(Comment comment) {
		commentsDao.addComment(comment);
	}

	public void deleteComment(int commentId) {
		commentsDao.deleteComment(commentId);
	}
}