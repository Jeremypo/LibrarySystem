package hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="documentary")
public class Documentary extends Item{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private int code;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String desc;
	
	@Column(name="location")
	private String location;
	
	@Column(name="daily_price")
	private double dailyPrice;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="director")
	private String director;
	
	@Column(name="release_date")
	private String releaseDate;
	
	@Column(name="length")
	private int length;
	
	
	public Documentary() {
		super();
	}
	
	
	public Documentary(int code, String title, String description, String director, int length, String releaseDate, String location, double dailyPrice, boolean status) {
		super(code, title, description, location, dailyPrice, status);
		this.director = director;
		this.length = length;
		this.releaseDate = releaseDate;
		this.title = title;
		this.desc = description;
		this.location = location;
		this.dailyPrice = dailyPrice;
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


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
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


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}

	/*
	public void addAuthor(Author newAuth) {
		int curNumAuth = this.authors.size();
		this.authors.add(newAuth);
		if(this.authors.size() > curNumAuth)
		{
			System.out.println("Successfully added author");
		}
	}
	*/
	
	
	
}