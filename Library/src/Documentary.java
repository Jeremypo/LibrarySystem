import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Documentary extends Item{
	
	private List<Director> directors = new ArrayList<Director>();
	private double length;
	private Date releaseDate;
	
	public Documentary(int code, String title, String description, String location, double dailyPrice, boolean status,
			List<Director> directors, double length, Date releaseDate) {
		super(code, title, description, location, dailyPrice, status);
		this.directors = directors;
		this.length = length;
		this.releaseDate = releaseDate;
	}
	
	public void addDirector(Director director) {
		this.directors.add(director);
	}

	public List<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
}