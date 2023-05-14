package hibernate.old;

/*
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.*;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionFactory;

@Entity
@Table(name = "loan")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_number")
	private int loanNumber;

	@Column(name = "start_date")
	private String startDate;
	//2023-05-10

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "estimated_price")
	private double estimatedPrice;

	@Column(name = "final_price")
	private double finalPrice;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="loan_book_id")
	private Book book;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="loan_doc_id")
	private Documentary doc;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="loan_student_id")
	private Student student;
	
	
	//constructors
	public Loan() {}
	
	public Loan(int loanNumber, String startDate, String endDate) {
		this.loanNumber = loanNumber;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Loan(int loanNumber, String startDate) {
		this.loanNumber = loanNumber;
		this.startDate = startDate;
	}
	
	
	// method to create a book loan object in the database
	public void createBookLoan(Book book, Student student) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		connection.setAutoCommit(false);
		
		this.setBook(book);
		this.setStudent(student);
		
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO loan(loan_number,"
				+ "start_date, loan_book_id, loan_student_id) VALUES(?,?,?,?)");
		stmt.setInt(1,  this.getLoanNumber());
		stmt.setString(2, this.getStartDate());
		stmt.setInt(3,  book.getCode());
		stmt.setInt(4,  student.getBroncoID());
		
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
	
	
	// method to create a documentary loan in the database
	public void createDocLoan(Documentary doc, Student student) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		connection.setAutoCommit(false);
		
		this.setDoc(doc);
		this.setStudent(student);
		
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO loan(loan_number,"
				+ "start_date, loan_doc_id, loan_student_id) VALUES(?,?,?,?)");
		stmt.setInt(1,  this.getLoanNumber());
		stmt.setString(2, this.getStartDate());
		stmt.setInt(3,  doc.getCode());
		stmt.setInt(4,  student.getBroncoID());
		
		stmt.executeUpdate();
		
		stmt = connection.prepareStatement("SELECT * FROM documentary order by code desc");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			doc.setCode(rs.getInt("code"));
		}
		
		stmt = connection.prepareStatement("SELECT * FROM student order by bronco_id desc");
		rs = stmt.executeQuery();
		if(rs.next()) {
			student.setBroncoID(rs.getInt("bronco_id"));
		}
		
		connection.commit();
	}

	// getters and setters and other commented out methods
	
	
	// constructor for when the loan is with a book
	//public Loan() {
	//}

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
		sessionFactory.close();
	

	// function for when the user returns the loan
	// checks if it was returned within the allowed time, and applies late fees when
	 applicable
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
	
	// function to calculate the estimated price based on the loan date and the due
	// date
	public void calculateEstimatedPrice(String type) {
		if(type.equals("Book")) {
			
		}
		else {
			
		}
		
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


	
	public void generateReceipt() {
		this.receipt = new Receipt(this);
	}

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Documentary getDoc() {
		return doc;
	}

	public void setDoc(Documentary doc) {
		this.doc = doc;
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


}*/