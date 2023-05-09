public class Director {

	private int directorID;
	private String name;
	private String nationality;
	private String subject;
	
	public Director(int authorID, String name, String nationality, String subject) {
		super();
		this.directorID = authorID;
		this.name = name;
		this.nationality = nationality;
		this.subject = subject;
	}

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