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
@Table(name = "director")
public class Director {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int directorID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "nationality")
	private String nationality;
	
	@Column(name = "subject")
	private String subject;
	
	
	// constructors
	public Director() {
		super();
	}
	
	public Director(int authorID, String name, String nationality, String subject) {
		super();
		this.directorID = authorID;
		this.name = name;
		this.nationality = nationality;
		this.subject = subject;
	}
	
	
	// method to create a director object in the database
	public void createDirector() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		connection.setAutoCommit(false);
		
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO director(id,"
						+"name, nationality, subject) VALUES(?,?,?,?)");
		stmt.setInt(1,  this.getDirectorID());
		stmt.setString(2,  this.getName());
		stmt.setString(3, this.getNationality());
		stmt.setString(4,  this.getSubject());
		
		stmt.executeUpdate();
	
		connection.commit();
	}
	
	
	//search director
    public Director searchDirector(int dirID){
        Director dir = new Director();
        
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Director.class).
                buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            dir = session.get(Director.class, dirID);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            factory.close();
        }
        
        if(dir == null)
            System.out.println("director not found");
        else
            System.out.println("Found: " + dir.getDirectorID());
        
        return dir;
    }

	
	// getters and setters
	public int getDirectorID() {
		return directorID;
	}

	public void setDirectorID(int directorID) {
		this.directorID = directorID;
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