package hibernate;

public class Item {
	
	private int code;
	private String title;
	private String description;
	private String location;
	private double dailyPrice;
	private boolean status;
	
	public Item() {};
	
	public Item(int code, String title, String description, String location, double dailyPrice, boolean status) {
		super();
		this.code = code;
		this.title = title;
		this.description = description;
		this.location = location;
		this.dailyPrice = dailyPrice;
		this.status = status;
	}
	
	
	public boolean checkAvailability() {
		if(this.status == false)
		{
			System.out.println("This item is NOT currently available");
		}
		else
		{
			System.out.println("This item is available");
		}
		return this.status;
	}
	
	public void setAvailability(boolean status) {
		this.status = status;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public double getDailyPrice() {
		return dailyPrice;
	}


	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	
	
	
	
	
}