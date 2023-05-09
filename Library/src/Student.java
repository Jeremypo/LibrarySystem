
public class Student {

	private int broncoID;
	private String studentName;
	private int courseID;
	private String studentEmail;
	
	public Student(int broncoID, String studentName, int courseID, String studentEmail) {
		super();
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
	
	
	
}