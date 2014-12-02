/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import databank.MyConnection;
import domein.Opleidingsonderdeel;
import java.sql.*;
import java.util.*;

/**
 *
 * @author alexander
 */
public class OpleidingsondereelDAO 
{
    public Statement statement = null;
    public Connection dbconnect = null;
    
    public List<Opleidingsonderdeel> getAllOpleidingsonderdelen() throws SQLException
    {
        List<Opleidingsonderdeel> OpleidingsonderdeelLijst = new ArrayList();
        try
        {
            MyConnection mc = new MyConnection();
            dbconnect = mc.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "select *from projectjava.opleidingsonderdeel";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);
            while(ResultaatSet.next())
            {
                Integer ID = ResultaatSet.getInt("id");
                String Naam = ResultaatSet.getString("naam");
                Integer OpleidingsID = ResultaatSet.getInt("opleidingsid");

                Opleidingsonderdeel Opleidingsonderdeel = new Opleidingsonderdeel(ID,Naam,OpleidingsID);
                OpleidingsonderdeelLijst.add(Opleidingsonderdeel);
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
      return OpleidingsonderdeelLijst;
    }//Einde getAllOpleidingsonderdelen
    
    public  void insertopleidingsonderdeel(String Name) throws SQLException
    {
        String InsertSQLStatement = "INSERT INTO projectjava.opleidingsonderdeel "
                                    + "(naam) " + "VALUES"
                                    + "('"+Name+"')";
        try 
        {
            MyConnection MijConnectie = new   MyConnection();
            dbconnect=MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(InsertSQLStatement);
            System.out.println("Record is inserted into opleidingsonderdeel table!");
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
    }//Einde insertOpleidingsOnderdeel
    
    public  void changeopleidingsonderdeel(int id,String naam) throws SQLException
    {
        String UpdateSQLStatement = "UPDATE projectjava.opleidingsonderdeel SET "
                                    + "naam=('"+naam+"') where id=('"+id+"')";
        try 
        {
            MyConnection MijConnectie = new   MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement(); 
            // execute insert SQL stetement
            statement.executeUpdate(UpdateSQLStatement);
            System.out.println("Record is changed into opleidingonderdeel table!");
 
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
    }//Einde changeOpleidingsOnderdeel
    
    public void deleteOpleidingsondereel(int id) throws SQLException
    {
        String DeleteSQLStatement = "DELETE FROM projectjava.opleidingsonderdeel WHERE id=('"+id+"')";         
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect=MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(DeleteSQLStatement);
            System.out.println("Record is Deleted!");
        } 
        catch (SQLException e) 
            {System.out.println(e.getMessage()); } 
        finally 
        {
            if (statement != null)
                statement.close();
            if (dbconnect != null) 
                dbconnect.close();
       }
   }//Einde deleteOpleidingsOnderdeel
    
    public  void KoppelIDMetOpleidingsID(int id,int opleidingsid) throws SQLException
    {
        String UpdateSQLStatement = "UPDATE projectjava.opleidingsonderdeel SET "
                                    + "opleidingsid=('"+opleidingsid+"') where id=('"+id+"')";
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie .GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(UpdateSQLStatement);
            System.out.println("opleiding id is add to table!");
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
    }//Einde addOpleidingsIdID
}//Einde Class