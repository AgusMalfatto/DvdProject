package Productos;

public class Disc 
{
	private String title;
	private String gender;
	private String comment;
	private int stock;
	private static int auxId = 1000;
	private int id;
	
	public Disc(String title, String gender, String comment, int stock) {
		super();
		id = auxId++;
		this.title = title;
		this.gender = gender;
		this.comment = comment;
		this.stock = stock;
	}
	
	public int getId() {return id;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String getGender() {return gender;}

	public void setGender(String gender) {this.gender = gender;}
	
	public String getComment() {return comment;}

	public void setComment(String comment) {this.comment = comment;}

	public int getStock() {return stock;}

	public void setStock(int stock) {this.stock = stock;}
	

}
