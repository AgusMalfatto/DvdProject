package Productos;
public class Cd extends Disc
{
	private String artist;
	private String songs;
	
	public Cd(String title, String gender, String comment, int stock, String artist, String songs) 
	{
		super(title, gender, comment, stock);
		this.artist = artist;
		this.songs = songs;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSongs() {
		return songs;
	}

	public void setSongs(String songs) {
		this.songs = songs;
	}
	
	
}
