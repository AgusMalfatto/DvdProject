package Connection;
import java.sql.*;
import java.util.logging.Level; 
import java.util.logging.Logger;

import javax.swing.JOptionPane; 

public class Connect {
    Statement sqlSt; // runs sql
    // String useSQL = new String("use prueba");
    String output;
    ResultSet result; // holds the output from sql
    String sql;
    private String dbURL = "jdbc:mysql://localhost:3306/blockbuster";
    private Connection dbConnect;
    

    public void setConnection() throws Exception {
        System.out.println("Welcome to 'El Marcianito'");
        try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         dbConnect = DriverManager.getConnection(dbURL, "root", "Racing.2010");
         //dbConnect = DriverManager.getConnection(dbURL, "root", "Racing.2010");
         sqlSt = dbConnect.createStatement(); // allows SQL to be executed
         /*result = sqlSt.executeQuery(sql);

         while(result.next()){
             output = result.getString("Nombre") + " " + result.getString("Nota");
             System.out.println(output);
         }*/

         sqlSt.close();

        }catch(ClassNotFoundException ex){ //The jar doesn't load rigth
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Class not found, check the jar");
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL is badd!! " + ex.getMessage());
        }
    }
/* 
    public ResultSet getDataTable(String table){
        sql = "Select * from " + table + ";";
        try{
            sqlSt = dbConnect.createStatement(); // allows SQL to be executed
            result = sqlSt.executeQuery(sql);
            return result;
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could not connect to db");
        }
        return null;
    }

    public void delelteBD(String table, int id) throws SQLException{
        sql = "Delete from " + table + "where Id = '" + id + "'";
        try{
            sqlSt = dbConnect.createStatement(); // allows SQL to be executed
            result = sqlSt.executeQuery(sql);
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could not connect to db");
        }
    }*/
     
    private void executeConsult(String sql) throws SQLException {
        try{
            dbConnect = DriverManager.getConnection(dbURL, "root", "Racing.2010");
            sqlSt = dbConnect.createStatement(); // allows SQL to be executed
            sqlSt.executeUpdate(sql);
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could not connect to db");
            JOptionPane.showMessageDialog(null, "Something is wrong, please check the data.");
        }
    }
    
    private ResultSet executeGetter(String sql) throws SQLException{
        try{
            dbConnect = DriverManager.getConnection(dbURL, "root", "Racing.2010");
            sqlSt = dbConnect.createStatement(); // allows SQL to be executed
            result = sqlSt.executeQuery(sql);
            return result;
        }catch(SQLException ex){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could not connect to db");
            JOptionPane.showMessageDialog(null, "Something is wrong, please check the data.");
        }
        return null;
    }

    public ResultSet getDataTable(String table){
        sql = "Select * from " + table + " order by Id;";
        try {
            result = executeGetter(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet getDisc(String table, int id) {
        sql = "Select * from " + table + " where Id = " + id;
        try {
            result = executeGetter(sql);
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public void delelteDB(String table, int id){
        sql = "Delete from " + table + " where Id = " + id;
        try {
            executeConsult(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void addDBDvd(String table, String title, String creator, String genre, String count, String comment, int stock){
        sql = "insert into " + table + " (Title, Director, Gender, Time, Comment, Stock) values ('" + 
            title + "', '" + creator + "', '" + genre  + "', '" + count + "', '" + comment + "', " + stock + ");";
        try {
            executeConsult(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Something is wrong, please check the data.");
            e.printStackTrace();
        }
    }

    public void addDBCd(String table, String title, String creator, String genre, String count, String comment, int stock){
        sql = "insert into " + table + " (Title, Artist, Gender, Songs, Comment, Stock) values ('" + 
            title + "', '" + creator + "', '" + genre  + "', '" + count + "', '" + comment + "', " + stock + ");";
        try {
            executeConsult(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Something is wrong, please check the data.");
            e.printStackTrace();
        }
    }
     
    public void modifyDBDvd(String title, String creator, String genre, String count, String comment, int stock, int id){
        sql = "update dvd set Title = '" + title + "', Director = '" + creator + "', Gender = '" + genre + "', Time = '" + count +
            "', Comment = '" + comment + "', Stock = '" + stock + "' where id = " + id + ";";
        try {
            executeConsult(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void modifyDBCd(String title, String creator, String genre, String count, String comment, int stock, int id){
        sql = "update cd set Title = '" + title + "' , Artist = '" + creator + "', Gender = '" + genre + "', Songs = '" + count +
            "', Comment = '" + comment + "', Stock = '" + stock + "' where Id = " + id + ";";
        try {
            executeConsult(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public ResultSet searchTitle(String table, String title) {
        sql = "Select * from " + table + " where Title LIKE '%" + title + "%' order by Title;";
        try {
            result = executeGetter(sql);
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public ResultSet searchCreator(String table, String column, String creator) {
        sql = "Select * from " + table + " where " + column + " LIKE '%" + creator + "%' order by Id;";
        try {
            result = executeGetter(sql);
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getDataTableOrderBy(String table, String column) {
        sql = "Select * from " + table + " order by " + column + ";";
        try {
            result = executeGetter(sql);
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    
}
