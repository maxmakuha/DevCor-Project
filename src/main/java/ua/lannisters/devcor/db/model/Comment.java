package ua.lannisters.devcor.db.model;

import java.util.Date;

public class Comment {

	private String comment;
	private Date creationDate;

	public Comment(String comment, Date creationDate) {
		this.comment = comment;
		this.creationDate = creationDate;

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

	@Override
	public String toString() {
		return "Comment: " + comment + ", creationDate = " + creationDate;
	}

}
