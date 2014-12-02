/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import databank.MyConnection;
import domein.Opleidingen;
import java.sql.*;
import java.util.*;

/**
 *
 * @author alexander
 */
public class OpleidingsDAO 
{
    public Statement statement = null;
    public Connection dbconnect = null;
  
    public List<Opleidingen> getAllOpleidingen() throws SQLException
    {
        List<Opleidingen> OpleidingLijst = new ArrayList();
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "select *from projectjava.opleidingen";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);
            
            while(ResultaatSet.next())
            {
                Integer ID = ResultaatSet.getInt("id");
                String Naam = ResultaatSet.getString("naam");
                Opleidingen Opleiding = new Opleidingen(ID,Naam);
                OpleidingLijst.add(Opleiding);
            }
        }  
        catch (SQLException ex) 
            {System.out.println(ex.getMessage());}
        finally
        {
            try
            {
                if(statement != null)
                    statement.close();  
                if(dbconnect != null)
                    dbconnect.close();
            }
            catch (SQLException ex) 
               { System.out.println(ex.getMessage());}
        }
        return OpleidingLijst;
      }//Einde getAllOpleidingen
    
    public  void insertOpleiding(String Name) throws SQLException 
    {
        String insertTableSQL = "INSERT INTO projectjava.opleidingen"
                                + "(naam) " + "VALUES"
                                + "('"+Name+"')";
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie .GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(insertTableSQL);
            System.out.println("Record is inserted into opleidingen table!");
        } 
        catch (SQLException e) 
            {System.out.println(e.getMessage());}
        finally 
        {
            if (statement != null) 
                statement.close();
            if (dbconnect != null) 
                dbconnect.close();
        }
    }//Einde insertOpleiding
    
    public  void changeopleidingen(int id,String naam) throws SQLException
    {
        String UpdateSQLStatement = "UPDATE projectjava.opleidingen SET "
                                    + "naam=('"+naam+"') where id=('"+id+"')";
        try
        {
            MyConnection MijnConnectie = new MyConnection();
            dbconnect = MijnConnectie.GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(UpdateSQLStatement);
            System.out.println("Record is changed into opleidingen table!");
        }
        catch (SQLException e) 
            {System.out.println(e.getMessage());}
        finally 
        {
            if (statement != null) 
                statement.close();
            if (dbconnect != null)
                dbconnect.close();
        }
    }//Einde changeOpleidingen
    
    public void deleteOpleidingen(int id) throws SQLException
    {
        String deleteTableSQL = "DELETE FROM projectjava.opleidingen WHERE id=('"+id+"')";             
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(deleteTableSQL);
            System.out.println("opleiding is Deleted!");
        } 
        catch (SQLException e) 
            {System.out.println(e.getMessage());} 
        finally 
        {
            if (statement != null)
                statement.close();
            if (dbconnect != null) 
                dbconnect.close();
       }
   
    }//Einde deleteOpleidingen
}//Einde class
