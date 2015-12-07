package lannisters.devcor.entity;

import java.sql.Timestamp;

public class Comment {

	private int commentId;
	private String comment;
	private Order order;
	private Timestamp creationDate;
	private Player author;

	public Comment() {
		order = new Order();
		author = new Player();
	}

	public Comment(int commentId, String comment, Order order, Timestamp creationDate, Player author) {
		this.commentId = commentId;
		this.comment = comment;
		this.order = order;
		this.creationDate = creationDate;
		this.author = author;
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
	
	public Player getAuthorObj(){
		return this.author;
	}
	
	public void setAuthorObj(Player author){
		if(this.author == null){
			this.author = new Player();
		}
		this.author = author;
		System.out.println("--" + this);
	}
	
	public int getAuthorId(){
		return author.getPlayerId();
	}
	
	public void setAuthorId(int authorId){
		if(this.author == null){
			this.author = new Player();
		}
		this.author.setPlayerId(authorId);
	}
	
	public String getAuthorFirstName(){
		return this.author.getFirstName();
	}
	
	public void setAuthorFirstName(String authorFirstName){
		if(this.author == null){
			this.author = new Player();
		}
		this.author.setFirstName(authorFirstName);
	}
	
	public String getAuthorLastName(){
		return this.author.getLastName();
	}
	
	public void setAuthorLastName(String authorLastName){
		if(this.author == null){
			this.author = new Player();
		}
		this.author.setLastName(authorLastName);
	}
	
	public String getAuthorFullName(){
		return this.getAuthorFirstName() + " " + this.getAuthorLastName();
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", comment=" + comment
				+ ", order=" + order + ", creationDate=" + creationDate + ", author=" + author+"]";
	}
}