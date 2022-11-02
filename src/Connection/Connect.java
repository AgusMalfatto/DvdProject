package Connection;
import java.sql.*;
import java.util.logging.Level; 
import java.util.logging.Logger; 

public class Connect {
    Statement sqlSt; // runs sql
    // String useSQL = new String("use prueba");
    String output;
    ResultSet result; // holds the output from sql
     String sql = "select * from alumnos;";

    public void setConnection() throws Exception {
        System.out.println("Welcome to 'El Marcianito'");
        try{
         Class.forName("com.mysql.jdbc.Driver");
         String dbURL = "jdbc:mysql://localhost:3306/prueba";
         Connection dbConnect = DriverManager.getConnection(dbURL, "root", "Racing.2010");
         sqlSt = dbConnect.createStatement(); // allows SQL to be executed
         result = sqlSt.executeQuery(sql);

         while(result.next()){
             output = result.getString("Nombre") + " " + result.getString("Nota");
             System.out.println(output);
         }

         sqlSt.close();

        }catch(ClassNotFoundException ex){ //The jar doesn't load rigth
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not found, check the jar");
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL is badd!! " + ex.getMessage());
        }
    }
     
    
}
