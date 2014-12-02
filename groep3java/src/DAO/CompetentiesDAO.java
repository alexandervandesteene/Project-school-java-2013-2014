/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import databank.MyConnection;
import domein.Competentie;
import java.sql.*;
import java.util.*;

/**
 *
 * @author alexander
 */
public class CompetentiesDAO 
{
    public Statement statement = null;
    public PreparedStatement prestatement = null;
    public Connection dbconnect = null;
    
    public List<Competentie> getAllcompetenties() throws SQLException
    {
        List<Competentie> CompetentieLijst = new ArrayList();
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            
         
            String selectTableSql = "select *from projectjava.competentie";
            ResultSet ResultaatSet = statement.executeQuery(selectTableSql);
            
            while(ResultaatSet.next())
            {
                Integer ID = ResultaatSet.getInt("id");
                String Naam = ResultaatSet.getString("naam");
                String Omschrijving = ResultaatSet.getString("Omschrijving");
                Competentie Comp = new Competentie(ID,Naam,Omschrijving);
                CompetentieLijst.add(Comp);
            }
        }  
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
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
        return CompetentieLijst;
    } //Einde getAllcompetenties
    
    public  void insertCompetentie(String naam,String omschrijving) throws SQLException 
    {
       
        try 
        {
             MyConnection MijConnection =new MyConnection();
             dbconnect=MijConnection.GetConnection();
             //statement = dbconnect.createStatement();
             prestatement = dbconnect.prepareStatement("INSERT INTO projectjava.competentie(naam,Omschrijving) values(?,?)");
             prestatement.setString(1, naam);
             prestatement.setString(2, omschrijving);
             
             prestatement.executeUpdate();
             System.out.println("Record is inserted into competentie table!");
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
    }//Einde insertCompetentie
    
    public  void changeCompetentie(String oud,String naam) throws SQLException
    {
        
        try 
        {
                MyConnection MijConnectie = new MyConnection();
                dbconnect = MijConnectie.GetConnection();
               
                prestatement = dbconnect.prepareStatement("UPDATE projectjava.competentie SET naam =?  where naam=?");
                prestatement.setString(2, oud);
                prestatement.setString(1, naam);
              
                
                // execute insert SQL stetement
                prestatement.executeUpdate();
                System.out.println("Record is changed into competentie table!");
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
    } //Einde changeCompetentie
    
    public void deleteCompetentie(String naam) throws SQLException
    {
                    
        try 
        {
            MyConnection MijConnectie = new   MyConnection();
            dbconnect = MijConnectie.GetConnection();
            
            prestatement = dbconnect.prepareStatement("DELETE FROM projectjava.competentie WHERE naam=?");
            prestatement.setString(1, naam);
            prestatement.executeUpdate();
            
            // execute insert SQL stetement
            
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
   } //Einde deleteCompetentie  
}//Einde class
