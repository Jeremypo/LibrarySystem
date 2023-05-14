package hibernate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

@Entity
@Table(name="student")
public class Student {

	@Id
	@Column(name="bronco_id")
	private int broncoID;
	
	@Column(name="student_name")
	private String studentName;
	
	@Column(name="course")
	private String course;
	
	@Column(name="student_email")
	private String studentEmail;
	
	
	//constructors
	public Student() {
		super();
	}
	
	public Student(int broncoID, String studentName, String studentEmail, String course) {
		this.broncoID = broncoID;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.course = course;
	}
	
	// method to create a student object in the database
	public void createStudent() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		
		connection.setAutoCommit(false);
		
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO student(bronco_id,"
				+ "student_name, student_email, course) VALUES(?,?,?,?)");
		
		stmt.setInt(1, this.getBroncoID());
		stmt.setString(2,  this.getStudentName());
		stmt.setString(3,  this.getStudentEmail());
		stmt.setString(4, this.getCourse());
		stmt.executeUpdate();
		
		stmt = connection.prepareStatement("SELECT * FROM student order by bronco_id desc");
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			this.setBroncoID(rs.getInt("bronco_id"));
		}
		
		connection.commit();
	}
	
	
	
	//search student
    public Student searchStudent(int broncoID){
        Student stud = new Student();
        
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            stud = session.get(Student.class, broncoID);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            factory.close();
        }
        
        if(stud == null)
            System.out.println("student not found");
        else
            System.out.println("Found: " + stud.getBroncoID());
        
        return stud;
    }

	
	// getters and setters
	public int getBroncoID() {
		return broncoID;
	}

	public void setBroncoID(int broncoID) {
		this.broncoID = broncoID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	@Override
	public String toString() {
		return "Student: " + broncoID + ", " + studentName + ", " + course + ", " + studentEmail;
	}
	
}








/*
package hibernate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	public Student() {
		
	}
	
	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Student: " + firstName + " ," + lastName + ", " + email;
	}

}

*/
