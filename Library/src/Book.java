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
@Table(name="book")
public class Book extends Item{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private int code;
	
	@Column(name="author")
	private String authors;
	
	@Column(name="publisher")
	private String publisher;
	
	@Column(name="publish_date")
	private String publishDate;
	
	@Column(name="page_number")
	private int pageNumber;
	
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
	
	public Book() {
		super();
	}
	
	
	public Book(int code, String title, String description, String authors, String publisher, String publishDate, int pageNumber, String location, double dailyPrice, boolean status) {
		super(code, title, description, location, dailyPrice, status);
		this.authors = authors;
		this.pageNumber = pageNumber;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.title = title;
		this.desc = description;
		this.location = location;
		this.dailyPrice = dailyPrice;
		this.status = status;
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
	
	public void changePublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
}
