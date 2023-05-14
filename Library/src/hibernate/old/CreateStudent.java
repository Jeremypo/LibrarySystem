package hibernate.old;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class CreateStudent {

	public static void main(String[] args) {
		
		String type = "hibernate";
		
		if (type.equals("jdbc")) {
		
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				
				conection.setAutoCommit(false);
				
				Student student = new Student("mary", "Z", "mary@cpp.edu");
				System.out.println(student);
				
				final PreparedStatement stmt = conection.prepareStatement("INSERT INTO student(first_name,"
						+ "last_name, email) VALUES(?,?,?)");		
				
				stmt.setString(1, student.getFirstName());
				stmt.setString(2, student.getLastName());	
				stmt.setString(3, student.getEmail());
				
				stmt.executeUpdate();
				
				conection.commit();
				//conection.rollback();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		} else if (type.equals("hibernate")) { 
		
			SessionFactory factory = new Configuration().
					                 configure("hibernate.cfg.xml").
					                 addAnnotatedClass(Student.class).
					                 buildSessionFactory();
			
			Session session = factory.getCurrentSession();
			
			try {
				
				
				Student student = new Student("john", "R", "john@cpp.edu");
				
				session.beginTransaction();
				
				System.out.println(student);
				session.save(student);
				
				
				
				session.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
			
  	    }	
		
		System.out.println("Finished!");
		
	}
	
}
