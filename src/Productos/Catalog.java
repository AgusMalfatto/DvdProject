package Productos;
import java.util.ArrayList;
import java.util.List;

public class Catalog 
{
	private ArrayList<Dvd> dataBaseDvd;
	private ArrayList<Cd> dataBaseCd;
    
    public Catalog()
    { 
		dataBaseDvd = new ArrayList<Dvd>();
		dataBaseCd = new ArrayList<Cd>();
        Dvd dvd = new Dvd("Star Wars", "George Lucas", "Ciencia Ficción", "135", "Excelente", 1);
        setIntoDvd(dvd);
    }
    
    public ArrayList<Dvd> getDbDvd()
    {
    	return dataBaseDvd;
    }
    
    public void setIntoDvd(Dvd dvd)
    {
    	dataBaseDvd.add(dvd);
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
    
    public void modificarDVD()
    {
    	
    }

    public int getSizeDvd(){return dataBaseDvd.size();}

    public void deleteFilm(int index)
    {
    	dataBaseDvd.remove(index);
    }

    public void list()
    {
    	
    }

    private void showTitleList()
    {
    	
    }
    
    private void showTitleListSorted(String [] names)
    {

    }
    
    private void showTitleTime(int time)
    {

    }
    
    private void showTitleDirector(String name)
    {

    }
    
    public void showDataBase()
    {
        
    }

    public void showCatalog()
    {

    }

    public void MenuModificar(Dvd film)
    {
    	
    }

    public Dvd getDVD(int id)
    {
    	for(Dvd dvd: dataBaseDvd){
    		if(dvd.getId() == id){
    			return dvd;
    		}
    	}
    	return null;
    }
    
    public void insertIntoCd(Cd newCd) 
	{
		dataBaseCd.add(newCd);
		
	}

	public ArrayList<Cd> getDbCd() {
		return dataBaseCd;
	}
}
