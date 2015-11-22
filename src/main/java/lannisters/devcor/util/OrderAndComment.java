package lannisters.devcor.util;

import lannisters.devcor.entity.Comment;
import lannisters.devcor.entity.Order;

public class OrderAndComment{
	
	private Order order;
	private Comment comment;
	
	public OrderAndComment() {
		this.order = null;
		this.comment = null;
	}
	
	public OrderAndComment(Order order, Comment comment) {
		this.order = order;
		this.comment = comment;
	}

	public Order getOrder() {
		return order;
	}
	
	public Comment getComment() {
		return comment;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	
}
