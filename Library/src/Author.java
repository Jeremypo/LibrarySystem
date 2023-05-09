public class Author {
	
	private int authorID;
	private String name;
	private String nationality;
	private String subject;
	
	public Author(int authorID, String name, String nationality, String subject) {
		super();
		this.authorID = authorID;
		this.name = name;
		this.nationality = nationality;
		this.subject = subject;
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
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