package lannisters.devcor.dao;

import java.util.List;
import lannisters.devcor.entity.Comment;

public interface CommentsDAO {

	public List<Comment> getAllComments();

	public Comment getCommentById(int commentId);

	public void addComment(Comment comment);

	public void deleteComment(int commentId);
}
