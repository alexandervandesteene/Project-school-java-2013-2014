/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import databank.MyConnection;
import domein.Partims;
import java.sql.*;
import java.util.*;

/**
 *
 * @author alexander
 */
public class PartimsDAO 
{
    public Statement statement = null;
    public Connection dbconnect = null;
    
    public List<Partims> getAllPartims() throws SQLException
    {
        List<Partims> PartimLijst = new ArrayList();
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "select *from projectjava.partims";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);
            
            while(ResultaatSet.next())
            {
                Integer ID = ResultaatSet.getInt("id");
                String Naam = ResultaatSet.getString("naam");
                Integer OpleidingsonderdeelID = ResultaatSet.getInt("opleidingsonderdeel");

                Partims partim = new Partims(ID,Naam,OpleidingsonderdeelID);
                PartimLijst.add(partim);
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
         return PartimLijst;
      }//Einde hetAllPartims
    
    public  void insertPartims(String Name) throws SQLException 
    {
        String InsertSQLStatement = "INSERT INTO projectjava.partims "
                                + "(naam) " + "VALUES"
                                + "('"+Name+"')";
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie .GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(InsertSQLStatement);
            System.out.println("Record is inserted into partims table!");
         }
        catch (SQLException e) 
            { System.out.println(e.getMessage()); } 
        finally 
        {
            if (statement != null) 
                statement.close();
            if (dbconnect != null)
                dbconnect.close();
        }
    }//Einde insertPartims
    
    public  void changePartims(int id,String naam) throws SQLException
    {
        String UpdateSQLStatement = "UPDATE projectjava.partims SET "
                                    + "naam=('"+naam+"') where id=('"+id+"')";
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
           statement.executeUpdate(UpdateSQLStatement);
           System.out.println("Record is changed into partims table!");
        } 
        catch (SQLException e) 
            { System.out.println(e.getMessage());} 
        finally 
        {
            if (statement != null) 
                statement.close();
            if (dbconnect != null)
                dbconnect.close();
        }
    }//Einde changePartims
     
    public void deletePartims(int id) throws SQLException
    {
        String DeleteSQLStatement = "DELETE FROM projectjava.partims WHERE id=('"+id+"')";
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(DeleteSQLStatement);
            System.out.println("Record is Deleted!");
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
    }//Einde deletePartims
    
    public  void KoppelOpleidingsonderdeelAanPartim(int id,int opleidingonderdeelid) throws SQLException
    {
        String UpdateSQLStatment = "UPDATE projectjava.partims SET "
                                    + "opleidingsonderdeel=('"+opleidingonderdeelid+"') where id=('"+id+"')";
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(UpdateSQLStatment);
            System.out.println("opleidingssonderdeel id is add to table!");
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
    }//Einde addopleidingsonderdeel
}//Einde class