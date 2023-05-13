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

import jdbc.ConnectionFactory;


@Entity
@Table(name="author")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int authorID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "nationality")
	private String nationality;
	
	@Column(name = "subject")
	private String subject;
	
	// constructors
	
	public Author() {
		super();
	}
	
	public Author(int authorID, String name, String nationality, String subject) {
		super();
		this.authorID = authorID;
		this.name = name;
		this.nationality = nationality;
		this.subject = subject;
	}
	
	// method to create an author object in the database
	public void createAuthor() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		connection.setAutoCommit(false);
		
		// create author record
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO author(id,"
				+"name, nationality, subject) VALUES(?,?,?,?)");
		stmt.setInt(1,  this.getAuthorID());
		stmt.setString(2,  this.getName());
		stmt.setString(3, this.getNationality());
		stmt.setString(4,  this.getSubject());
		
		stmt.executeUpdate();
		
		connection.commit();		
	}
	
	
	//search author
    public Author searchAuthor(int authID){
        Author auth = new Author();
        
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Author.class).
                buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            auth = session.get(Author.class, authID);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            factory.close();
        }
        
        if(auth == null)
            System.out.println("author not found");
        else
            System.out.println("Found: " + auth.getAuthorID());
        
        return auth;
    }
	
	
	// getters and setters

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}

