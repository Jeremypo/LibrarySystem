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
import java.sql.SQLException;

import jdbc.ConnectionFactory;

@Entity
@Table(name = "loan")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_number")
	private int loanNumber;

	@Column(name = "student_id")
	private int studentID;

	@Column(name = "book_id")
	private int bookID;

	@Column(name = "start_date")
	private String startDate;
	//2023-05-10

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "estimated_price")
	private double estimatedPrice;

	@Column(name = "final_price")
	private double finalPrice;

	/*
	private Date loanDueDate;
	private Date loanDate;


	private Receipt receipt;
	private Book book;
	private Documentary documentary;
	private Student student;


*/
	/*
	private double dailyPrice;
	private String studentName;
	private String returnDate;
	private long estDays;*/
	
	public static void main(String[] args) {
		createLoan(1, 2, "1", "2");
		createLoan(3, 4, "5", "6");
	}
	
	public static void createLoan(int studentID, int bookID, String startDate, String endDate) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Loan.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			Loan loan = new Loan(studentID, bookID, startDate, endDate);
			loan.calculateEstimatedPrice();
			
			

			session.beginTransaction();

			session.save(loan);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			factory.close();
		}
	}
	
	public Loan() {

	}

	// constructor for when the loan is with a book
	public Loan(int studentID, int bookID, String startDate, String endDate) throws ClassNotFoundException {
		this.studentID = studentID;
		this.bookID = bookID;
		this.startDate = startDate;
		this.endDate = endDate;

		// Insert the loan data into a database table using JDBC
		/*try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO loans (loan_number, loan_due_date, loan_date, daily_price, estimated_price, student_name, student_id) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
			statement.setInt(1, loanNumber);
			statement.setDate(2, new java.sql.Date(loanDueDate.getTime()));
			statement.setDate(3, new java.sql.Date(loanDate.getTime()));
			statement.setDouble(4, dailyPrice);
			statement.setDouble(5, estimatedPrice);
			statement.setString(6, studentName);
			statement.setInt(7, studentID);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Insert the loan data into a database table using Hibernate
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(this);
		transaction.commit();
		session.close();
		sessionFactory.close();*/
	}

	// function for when the user returns the loan
	// checks if it was returned within the allowed time, and applies late fees when
	/* applicable
	public void returnLoan(String returnDate) {
		this.returnDate = returnDate;
		long loanDateInMs = loanDate.getTime();
		long returnDateInMs = returnDate.getTime();
		long time = Math.abs(returnDateInMs - loanDateInMs);
		long realDays = TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS);
		if (realDays > estDays) {
			finalPrice = (estDays * dailyPrice) + (realDays * 0.10);
		} else {
			finalPrice = (estDays * dailyPrice);
		}
	}
	*/
	// function to calculate the estimated price based on the loan date and the due
	// date
	public void calculateEstimatedPrice() {
		long estDays;
		double dailyPrice = 3.00;
		//long loanDateInMs = loanDate.getTime();
		//long dueDateInMs = loanDueDate.getTime();
		//long time = Math.abs(dueDateInMs - loanDateInMs);
		//long days = TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS);
		estDays = 7;
		estimatedPrice = (estDays * dailyPrice);
		
		//testing
		finalPrice = estimatedPrice;
	}

	/*
	public void generateReceipt() {
		this.receipt = new Receipt(this);
	}*/

	// getters and setters
	public double getEstimatedPrice() {
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

	/*
	public Date getLoanDueDate() {
		return loanDueDate;
	}

	public void setLoanDueDate(Date loanDueDate) {
		this.loanDueDate = loanDueDate;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	
	public Receipt getreceipt() {
		return receipt;
	}*/

	/*
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Documentary getDocumentary() {
		return documentary;
	}

	public void setDocumentary(Documentary documentary) {
		this.documentary = documentary;
	}

	public long getEstDays() {
		return estDays;
	}

	public void setEstDays(long estDays) {
		this.estDays = estDays;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
*/
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int id) {
		this.studentID = id;
	}
	
	public int getBookId() {
		return bookID;
	}
	
	public void setBookId(int bookID) {
		this.bookID = bookID;
	}
	



}