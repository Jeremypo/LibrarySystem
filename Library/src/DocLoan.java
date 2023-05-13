package hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
@Table(name = "loan")
public class DocLoan {
	
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
	@JoinColumn(name="loan_doc_id")
	private Documentary doc;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="loan_student_id")
	private Student student;
	
	
	//constructors
	public DocLoan() {
		super();
	}
	
	public DocLoan(int loanNumber, String startDate, String dueDate) {
		this.loanNumber = loanNumber;
		this.startDate = startDate;
		this.dueDate = dueDate;
	}
	

	
	// method to create a documentary loan in the database
	public void createDocLoan(Documentary doc, Student student) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		connection.setAutoCommit(false);
		
		this.setDoc(doc);
		this.setStudent(student);
		
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO loan(loan_number,"
				+ "start_date, due_date, loan_doc_id, loan_student_id) VALUES(?,?,?,?,?)");
		stmt.setInt(1,  this.getLoanNumber());
		stmt.setString(2, this.getStartDate());
		stmt.setString(3, this.getDueDate());
		stmt.setInt(4,  doc.getCode());
		stmt.setInt(5,  student.getBroncoID());
		
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
	
	
	
	//search loan
    public DocLoan searchDocLoan(int loanNum){
        DocLoan loan = new DocLoan();
        
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(DocLoan.class).
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Director.class).
                addAnnotatedClass(Documentary.class).
                buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            loan = session.get(DocLoan.class, loanNum);
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

	// getters and setters and other commented out methods
	
    // return loan
    public void returnDocLoan(int loanNumber, String returnDate) {
    	SessionFactory factory = new Configuration().
    			configure("hibernate.cfg.xml").
    			addAnnotatedClass(DocLoan.class).
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Director.class).
                addAnnotatedClass(Documentary.class).
    			buildSessionFactory();
    	
    	Session session = factory.getCurrentSession();
    	
    	session.beginTransaction();
    	DocLoan retDL = session.get(DocLoan.class, loanNumber);
    	retDL.setReturnDate(returnDate);
    	
    	session.getTransaction().commit();
    }
    
	// function to calculate the estimated price based on the loan date and the due
	// date
	public void calculateEstimatedPrice() {
		long estDays;
		double dailyPrice = doc.getDailyPrice();
		estDays = 7;
		estimatedPrice = (estDays * dailyPrice);
	}

	public void calculateFinalPrice() {
		
		LocalDate due = LocalDate.parse(startDate);
		LocalDate current = LocalDate.now();
		
		if(current.isAfter(due)) {
			finalPrice = ChronoUnit.DAYS.between(due, current) * doc.getDailyPrice();
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
	
	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
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

}
