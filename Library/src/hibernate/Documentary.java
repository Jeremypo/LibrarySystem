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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

@Entity
@Table(name="documentary")
public class Documentary extends Item{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private int code;
	
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
	
	@Column(name="release_date")
	private String releaseDate;
	
	@Column(name="length")
	private int length;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="doc_director_id")
	private Director director;
	
	
	// constructors
	public Documentary() {
		super();
	}
	
	public Documentary(int code, String title, String desc, String releaseDate, int length, String location, double dailyPrice, boolean status){
		this.code = code;
		this.title = title;
		this.desc = desc;
		this.releaseDate = releaseDate;
		this.length = length;
		this.location = location;
		this.dailyPrice = dailyPrice;
		this.status = status;
	}
	
	
	//method to create a documentary object in the database
	public void createDocumentary(Director director) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionFactory.getConnection();
		connection.setAutoCommit(false);
		
		this.setDirector(director);
		
		
		// create documentary record
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO documentary(code, title, description,"
				+"release_date, length, location, daily_price, status, doc_director_id) VALUES(?,?,?,?,?,?,?,?,?)");
		
		stmt.setInt(1,  this.getCode());
		stmt.setString(2, this.getTitle());
		stmt.setString(3, this.getDesc());
		stmt.setString(4,  this.getReleaseDate());
		stmt.setInt(5, this.getLength());
		stmt.setString(6, this.getLocation());
		stmt.setDouble(7,  this.getDailyPrice());
		stmt.setBoolean(8, this.isStatus());
		stmt.setInt(9, director.getDirectorID());
		
		stmt.executeUpdate();
		
		stmt = connection.prepareStatement("SELECT * FROM director order by id desc");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			director.setDirectorID(rs.getInt("id"));
		}
		
		connection.commit();
	}
	
	
	
	//search doc
    public Documentary searchDocumentary(int docCode){
        Documentary doc = new Documentary();
        
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Documentary.class).
                addAnnotatedClass(Director.class).
                buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            doc = session.get(Documentary.class, docCode);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            factory.close();
        }
        
        if(doc == null)
            System.out.println("documentary not found");
        else
            System.out.println("Found: " + doc.getCode());
        
        return doc;
    }


	public void setDirector(Director director) {
		this.director = director;
	}
	
	public Director getDirector() {
		return director;
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



	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}

	/*
	public void addAuthor(Author newAuth) {
		int curNumAuth = this.authors.size();
		this.authors.add(newAuth);
		if(this.authors.size() > curNumAuth)
		{
			System.out.println("Successfully added author");
		}
	}
	*/
	
	
	
}