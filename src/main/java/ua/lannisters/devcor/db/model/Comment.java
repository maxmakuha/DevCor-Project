package ua.lannisters.devcor.db.model;

import java.util.Date;

public class Comment {

	private String comment;
	private Date creationDate;
	private int commentId;

	public Comment(String comment, Date creationDate, int commentId) {
		this.comment = comment;
		this.creationDate = creationDate;
		this.commentId = commentId;

	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	@Override
	public String toString() {
		return "Comment: " + comment + ", creationDate = " + creationDate;
	}

}
