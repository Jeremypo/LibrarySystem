package hibernate.old;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jdbc.ConnectionFactory;

public class ListStudents {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		String type = "hibernate";
		
		if (type.equals("jdbc")) {
			
			try {
				
				Connection conection = ConnectionFactory.getConnection();
				final PreparedStatement stmt = conection.prepareStatement("SELECT * FROM student");
				
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
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
				
				session.beginTransaction();
				
				List<Student> students = session.createQuery("from Student").getResultList();
				
				for (Student student: students) {
					System.out.println(student);
				}
				
				students = session.createQuery("from Student s where s.firstName='john'").getResultList();
				
				for (Student student: students) {
					System.out.println(student);
				}

				Student myUser = new Student("mary", "Z", "mary@cpp.edu");
				String hql = "from Student s where s.firstName=:firstName and s.lastName=:lastName";
				students = session.createQuery(hql)
						.setProperties(myUser)
						.getResultList();
						       
				for (Student student: students) {
					System.out.println(student);
				}
				
				session.getTransaction().commit();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				factory.close();
			}
			
		}	
		
	}
	
}
