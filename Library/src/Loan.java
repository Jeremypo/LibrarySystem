package LibraryManagementSystem;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Loan {

	private int loanNumber;
	private Date loanDueDate;
	private Date loanDate;
	private Date returnDate;
	private double dailyPrice;
	private double estimatedPrice;
	private double finalPrice;
	private Receipt reciept;
	private Book book;
	private Documentary documentary;
	private Student student;
	private String studentName;
	private int studentID;
	private long estDays;
	
	// constructor for when the loan is with a book
	public Loan(Book book, Student student, int loanNumber, Date loanDate, Date loanDueDate) {
		super();
		this.book = book;
		this.student = student;
		this.loanNumber = loanNumber;
		this.dailyPrice = book.getDailyPrice();
		this.loanDate = loanDate;
		this.loanDueDate = loanDueDate;
		this.studentName = student.getStudentName();
		this.studentID = student.getBroncoID();
		this.estimatedPrice = getEstimatedPrice();
	}
	
	// constructor for when the loan is with a documentary
	public Loan(Documentary documentary, Date loanDate, Date loanDueDate) {
		super();
		this.documentary = documentary;
		this.dailyPrice = book.getDailyPrice();
		this.loanDate = loanDate;
		this.loanDueDate = loanDueDate;
		this.estimatedPrice = getEstimatedPrice();
	}
	
	// function for when the user returns the loan
	// checks if it was returned within the allowed time, and applies late fees when applicable
	public void returnLoan(Date returnDate) {
		this.returnDate = returnDate;
		long loanDateInMs = loanDate.getTime();
		long returnDateInMs = returnDate.getTime();
		long time = Math.abs(returnDateInMs - loanDateInMs);
		long realDays = TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS);
		if(realDays > estDays)
		{
			finalPrice = (estDays * dailyPrice) + (realDays * 0.10);
		}
		else
		{
			finalPrice = (estDays * dailyPrice);
		}
	}
	
	// function to calculate the estimated price based on the loan date and the due date
	public void calculateEstimatedPrice() {
		long loanDateInMs = loanDate.getTime();
		long dueDateInMs = loanDueDate.getTime();
		long time = Math.abs(dueDateInMs - loanDateInMs);
		long days = TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS);
		estDays = days;
		estimatedPrice = (estDays * dailyPrice);
	}
	
	public void generateReciept() {
		this.reciept = new Receipt(this);
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

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public Receipt getReciept() {
		return reciept;
	}

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
	
	public int getStudentID() {
		return studentID;
	}
	
	public void setStudentID(int id) {
		this.studentID = id;
	}
	
}
