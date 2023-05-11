package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Staff {
	public static void main(String[] args) {
		Student stu1 = new Student(Integer.parseInt("016197285"), "Jeremy Po", 33658, "jpo@cpp.edu");
		createStudent(stu1);
		//deleteStudent(Integer.parseInt("026197285"));
		//deleteStudent(Integer.parseInt("016197285"));
		Student stu2 = searchStudent(13);
		stu2 = searchStudent(16197285);
		
		updateStudent(16197285, "Jer", 33658, "jpo@cpp.edu");
	}
	
	//create student
	public static void createStudent(Student student) {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
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
	
	//search student
	public static Student searchStudent(int broncoID){
		Student student = new Student();
		
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			student = session.get(Student.class, broncoID);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			factory.close();
		}
		
		if(student == null)
			System.out.println("student not found");
		else
			System.out.println("Found: " + student.getBroncoID());
		
		return student;
	}
	
	//[drop down] ----- [text input field]
	
	//update student
	public static void updateStudent(int broncoID, String studentName, int courseID, String studentEmail) {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Student student = session.get(Student.class, broncoID);
			
			student.setStudentName(studentName);
			student.setCourseID(courseID);
			student.setStudentEmail(studentEmail);
			
			//session.createQuery("update Student set first_name='mary'").executeUpdate();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			factory.close();
		}
	}
	
	//delete student
	public static void deleteStudent(int broncoID) {
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			Student student = session.get(Student.class, broncoID);
			//session.createQuery("delete from Student where id=2").executeUpdate();
			session.delete(student);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			factory.close();
		}
	}
	
	public static void createBook(int code, String title, String description, String authors, String publisher, String publishDate, int pageNumber, String location, double dailyPrice, boolean status) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Book.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			Book book = new Book( code,  title,  description,  authors,  publisher,  publishDate,  pageNumber,  location,  dailyPrice,  status);
			
			session.beginTransaction();

			session.save(book);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			factory.close();
		}
	}
}
