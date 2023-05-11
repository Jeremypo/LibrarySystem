package hibernate;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_cpp")
public class Student {

	@Id
	@Column(name="bronco_id")
	private int broncoID;
	
	@Column(name="student_name")
	private String studentName;
	
	@Column(name="course_id")
	private int courseID;
	
	@Column(name="student_email")
	private String studentEmail;
	
	public Student() {};
	
	public Student(int broncoID, String studentName, int courseID, String studentEmail) {
		this.broncoID = broncoID;
		this.studentName = studentName;
		this.courseID = courseID;
		this.studentEmail = studentEmail;
	}

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

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	@Override
	public String toString() {
		return "Student: " + broncoID + ", " + studentName + ", " + courseID + ", " + studentEmail;
	}
	
}