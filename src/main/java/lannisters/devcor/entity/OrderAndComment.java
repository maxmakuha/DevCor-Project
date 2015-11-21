package lannisters.devcor.entity;

public class OrderAndComment {
	
	private Order order;
	private Comment comment;
	
	public OrderAndComment() {
		this.order = new Order();
		this.comment = new Comment();
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

	@Override
	public String toString() {
		return "OrderAndComment [order=" + order + ", comment=" + comment + "]";
	}
}
