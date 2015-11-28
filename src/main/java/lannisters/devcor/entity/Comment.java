package lannisters.devcor.entity;

import java.sql.Timestamp;

public class Comment {

	private int commentId;
	private String comment;
	private Order order;
	private Timestamp creationDate;

	public Comment() {
		order = new Order();
	}

	public Comment(int commentId, String comment, Order order, Timestamp creationDate) {
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

	public Order getOrderObj() {
		return this.order;
	}

	public int getOrderId() {
		return order.getOrderId();
	}

	public void setOrderId(int orderId) {
		if (this.order == null)
			this.order = new Order();
		this.order.setOrderId(orderId);
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", comment=" + comment
				+ ", order=" + order + ", creationDate=" + creationDate + "]";
	}
}