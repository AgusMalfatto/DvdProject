package Productos;
public class Dvd extends Disc
{
	private String director;
	private String time;
	
	public Dvd(String title, String director, String gender, String time, String comment, int stock) 
	{
		super(title, gender, comment, stock);
		this.director = director;
		this.time = time;
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
