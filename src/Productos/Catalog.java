package Productos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Connection.Connect;

public class Catalog 
{
	private ArrayList<Dvd> dataBaseDvd;
	private ArrayList<Cd> dataBaseCd;
    private Connect con;
    private ResultSet result;
    
    public Catalog()
    { 
		dataBaseDvd = new ArrayList<Dvd>();
		dataBaseCd = new ArrayList<Cd>();
        //Dvd dvd = new Dvd("Star Wars", "George Lucas", "Ciencia Ficción", "135", "Excelente", 1);
        con = new Connect();
        //setIntoDvd(dvd);
    }
    
    public ArrayList<Dvd> getDbDvd()
    {
    	return dataBaseDvd;
    }
    
    public void setIntoDvd(Dvd dvd)
    {
    	con.addDBDvd("dvd", dvd.getTitle(), dvd.getDirector(), dvd.getGender(), dvd.getTime(), dvd.getComment(), dvd.getStock());
    }
    
	
	public int indexOfDvd(Dvd dvd) 
	{
		for(int i = 0; i < getSizeDvd(); i++)
		{
			Dvd myDvd = dataBaseDvd.get(i);
			if(myDvd.getTitle().equals(dvd.getTitle()) && myDvd.getDirector().equals(dvd.getDirector()) && myDvd.getGender().equals(dvd.getGender()))
			{
				return i;
			}
		}
		return -1;
	}

    public boolean inCatalog(Dvd dvd)
    {
    	return true;
    }

    public int getSizeDvd(){return dataBaseDvd.size();}

    public void deleteFilm(String table, int id)
    {
    	try {
            con.delelteDB(table, id);
        } catch (Exception e) {
        };
    }

    public Dvd getDVD(String table, int id)
    {
    	result = con.getDisc(table, id);
        
        try {
            if(result.next()) {
                try {
                    String title = result.getString("Title");
                    String director = result.getString("Director");
                    String genre = result.getString("Gender");
                    String time = result.getString("Time");
                    String comment = result.getString("Comment");
                    int stock = Integer.parseInt(result.getString("Stock"));

                    Dvd dvd = new Dvd(title, director, genre, time, comment, stock);
                    return dvd;
                } catch (NumberFormatException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    
    public void insertIntoCd(Cd newCd) 
	{
		con.addDBCd("cd", newCd.getTitle(), newCd.getArtist(), newCd.getGender(), newCd.getSongs(), newCd.getComment(), newCd.getStock());
		
	}

	public ArrayList<Cd> getDbCd() {
		return dataBaseCd;
	}
}
