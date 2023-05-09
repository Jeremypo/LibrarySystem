package LibraryManagementSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book extends Item{

	private List<Author> authors = new ArrayList<Author>();
	private int pageNumber;
	private String publisher;
	private Date publishDate;
	
	public Book(int code, String title, String description, String location, double dailyPrice, boolean status,
			List<Author> authors, int pageNumber, String publisher, Date publishDate) {
		super(code, title, description, location, dailyPrice, status);
		this.authors = authors;
		this.pageNumber = pageNumber;
		this.publisher = publisher;
		this.publishDate = publishDate;
	}

	public void addAuthor(Author newAuth) {
		int curNumAuth = this.authors.size();
		this.authors.add(newAuth);
		if(this.authors.size() > curNumAuth)
		{
			System.out.println("Successfully added author");
		}
	}
	
	public void changePublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
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

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
}