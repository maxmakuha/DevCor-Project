package lannisters.devcor.entity;

import java.util.Date;

public class Comment {

	private int commentId;
	private String comment;
	private Order order;
	private Date creationDate;

	public Comment() {
	}

	public Comment(int commentId, String comment, Order order, Date creationDate) {
		this.commentId = commentId;
		this.comment = comment;
		this.order = order;
		this.creationDate = creationDate;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getOrderId() {
		return order.getOrderId();
	}

	public void setOrderId(int orderId) {
		if (this.order == null)
			this.order = new Order();
		this.order.setOrderId(orderId);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", comment=" + comment
				+ ", order=" + order + ", creationDate=" + creationDate + "]";
	}
}