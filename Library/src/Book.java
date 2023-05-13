package hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

@Entity
@Table(name="book")
public class Book extends Item{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private int code;
	
	@Column(name="publisher")
	private String publisher;
	
	@Column(name="publish_date")
	private String publishDate;
	
	@Column(name="page_count")
	private int pageCount;
	
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
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="book_author_id")
	private Author author;
	

	// constructors
	
	public Book() {
		super();
	}
	
	
	public Book(int code, String title, String desc, String publisher, String publishDate, int pageCount, String location, double dailyPrice, boolean status) {
		this.code = code;
		this.title = title;
		this.desc = desc;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.pageCount = pageCount;
		this.location = location;
		this.dailyPrice = dailyPrice;
		this.status = status;
	}

	// method to create a book object in the database table
	public void createBook(Author author) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		
		connection.setAutoCommit(false);
		
		this.setAuthor(author);
		
		// create book record
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO book(code, title, description,"
				+"publisher, publish_date, page_count, location, daily_price, status,"
				+ "book_author_id) VALUES(?,?,?,?,?,?,?,?,?,?)");
		
		stmt.setInt(1,  this.getCode());
		stmt.setString(2, this.getTitle());
		stmt.setString(3, this.getDesc());
		stmt.setString(4, this.getPublisher());
		stmt.setString(5,  this.getPublishDate());
		stmt.setInt(6,  this.getPageCount());
		stmt.setString(7, this.getLocation());
		stmt.setDouble(8,  this.getDailyPrice());
		stmt.setBoolean(9, this.isStatus());
		stmt.setInt(10,  author.getAuthorID());
		
		stmt.executeUpdate();
		
		stmt = connection.prepareStatement("SELECT * FROM author order by id desc");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			author.setAuthorID(rs.getInt("id"));
		}
		
		connection.commit();
	}
	
	
	
	//search book
    public Book searchBook(int bookCode){
        Book book = new Book();
        
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Book.class).
                addAnnotatedClass(Author.class).
                buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            book = session.get(Book.class, bookCode);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            factory.close();
        }
        
        if(book == null)
            System.out.println("book not found");
        else
            System.out.println("Found: " + book.getCode());
        
        return book;
    }
	
	
	// getters and setters
	public void changePublisher(String publisher) {
		this.publisher = publisher;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
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
	
}
