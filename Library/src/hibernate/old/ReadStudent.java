package hibernate.old;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class ReadStudent {

	public static void main(String[] args) {
		
		String type = "hibernate";
		
		if (type.equals("jdbc")) {
			
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				final PreparedStatement stmt = conection.prepareStatement("SELECT * FROM student WHERE "
						+ "id=?");
				stmt.setInt(1, 1);
				
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					Student student = new Student(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
					student.setId(rs.getInt("id"));
			        System.out.println(student);
			    }				
				
				
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
				
				//Student student = new Student("john", "11111", "john@cpp.edu");
				
				session.beginTransaction();
				
				//session.save(student);
				
				//Student student = session.get(Student.class, student.getId());
				Student student = session.get(Student.class, 1);
				
				System.out.println(student);
				
				session.getTransaction().commit();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
			
		}	
		
	}
	
}
