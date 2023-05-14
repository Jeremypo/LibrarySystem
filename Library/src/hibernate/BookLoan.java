package hibernate;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jdbc.ConnectionFactory;

@Entity
@Table(name = "loan")
public class BookLoan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_number")
	private int loanNumber;

	@Column(name = "start_date")
	private String startDate;
	//2023-05-10

	@Column(name = "due_date")
	private String dueDate;
	
	@Column(name= "return_date")
	private String returnDate;

	@Column(name = "estimated_price")
	private double estimatedPrice;

	@Column(name = "final_price")
	private double finalPrice;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="loan_book_id")
	private Book book;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="loan_student_id")
	private Student student;
	
	
	//constructors
	public BookLoan() {
		super();
	}
	
	public BookLoan(int loanNumber, String startDate, String dueDate) {
		this.loanNumber = loanNumber;
		this.startDate = startDate;
		this.dueDate = dueDate;
	}
	
	
	// method to create a book loan object in the database
	public void createBookLoan(Book book, Student student) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		connection.setAutoCommit(false);
		
		this.setBook(book);
		this.setStudent(student);
		
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO loan(loan_number,"
				+ "start_date, due_date, loan_book_id, loan_student_id) VALUES(?,?,?,?,?)");
		stmt.setInt(1,  this.getLoanNumber());
		stmt.setString(2, this.getStartDate());
		stmt.setString(3, this.getDueDate());
		stmt.setInt(4,  book.getCode());
		stmt.setInt(5,  student.getBroncoID());
		
		stmt.executeUpdate();
		
		stmt = connection.prepareStatement("SELECT * FROM book order by code desc");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			book.setCode(rs.getInt("code"));
		}
		
		stmt = connection.prepareStatement("SELECT * FROM student order by bronco_id desc");
		rs = stmt.executeQuery();
		if(rs.next()) {
			student.setBroncoID(rs.getInt("bronco_id"));
		}
		connection.commit();
	}
	
	
	//search loan
    public BookLoan searchBookLoan(int loanNum){
        BookLoan loan = new BookLoan();
        
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(BookLoan.class).
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Author.class).
                addAnnotatedClass(Book.class).
                buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            loan = session.get(BookLoan.class, loanNum);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            factory.close();
        }
        
        if(loan == null)
            System.out.println("loan not found");
        else
            System.out.println("Found: " + loan.getLoanNumber());
        
        return loan;
    }
    
    
    // return loan
    public void returnBookLoan(int loanNumber, String returnDate) {
    	SessionFactory factory = new Configuration().
    			configure("hibernate.cfg.xml").
    			addAnnotatedClass(BookLoan.class).
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Author.class).
                addAnnotatedClass(Book.class).
    			buildSessionFactory();
    	
    	Session session = factory.getCurrentSession();
    	
    	session.beginTransaction();
    	BookLoan retBL = session.get(BookLoan.class, loanNumber);
    	retBL.setReturnDate(returnDate);
    	
    	session.getTransaction().commit();
    }

	// function to calculate the estimated price based on the loan date and the due
	// date
	public void calculateEstimatedPrice() {
		long estDays;
		double dailyPrice = book.getDailyPrice();
		estDays = 7;
		estimatedPrice = (estDays * dailyPrice);
	}
	
	public void calculateFinalPrice() {
		
		LocalDate due = LocalDate.parse(startDate);
		LocalDate current = LocalDate.now();
		
		if(current.isAfter(due)) {
			finalPrice = ChronoUnit.DAYS.between(due, current) * book.getDailyPrice();
		}
		else {
			finalPrice = 0.00;
		}
	}

	/*
	public void generateReceipt() {
		this.receipt = new Receipt(this);
	}*/

	// getters and setters
	public double getEstimatedPrice() {
		calculateEstimatedPrice();
		return estimatedPrice;
	}

	public double getFinalPrice() {
		return finalPrice;
	}
	


	public int getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(int loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setEstimatedPrice(double estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}



	



}