package hibernate.old;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class DeleteStudent {

	public static void main(String[] args) {
		
		String type = "hibernate";
		
		if (type.equals("jdbc")) {		
			
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				
				PreparedStatement stmt = conection.prepareStatement("SELECT * FROM student WHERE "
						+ "id=?");
				stmt.setInt(1, 1);
				
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
				
					conection.setAutoCommit(false);
					
					Student student = new Student(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
					student.setId(rs.getInt("id"));
			        System.out.println(student);
					
					stmt = conection.prepareStatement("Delete from Student WHERE id = ?");		
					
					stmt.setInt(1, student.getId());	
					
					stmt.executeUpdate();
					
					conection.commit();
					//conection.rollback();
					
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
				
				session.beginTransaction();
				
				Student student = session.get(Student.class, 2);
				
				//session.createQuery("delete from Student where id=2").executeUpdate();
	
				session.delete(student);
				
				session.getTransaction().commit();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
		
		}	
			
	}
	
}
