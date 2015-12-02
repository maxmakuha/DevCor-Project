package lannisters.devcor.util;


public class SimpleOrder {

	private int orderId;
	private String description;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;




	public SimpleOrder(int orderId,  String description,
			 int day, int month, int year, int hour, int minute) {
		this.orderId = orderId;
		this.description = description;
		this.day = day;
		this.month = month;
        this.year = year;
        this.setHour(hour);
        this.setMinute(minute);
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	}
