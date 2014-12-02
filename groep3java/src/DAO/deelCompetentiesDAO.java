/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import databank.MyConnection;
import java.sql.*;
import java.util.*;
import domein.*;

/**
 *
 * @author alexander
 */
public class deelCompetentiesDAO
{
    public Statement statement = null;
    public Connection dbconnect = null;
    public PreparedStatement prestatement = null;
    
    public List<DeelCompetenties> getAlldeelCompetentie() throws SQLException
    {
        List<DeelCompetenties> deelCompetentieLijst = new ArrayList();
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "select *from projectjava.deelcompetentie";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);
            
            while(ResultaatSet.next())
            {
                Integer ID = ResultaatSet.getInt("id");
                String Naam = ResultaatSet.getString("naam");
                Integer compid = ResultaatSet.getInt("Comp_id");
                DeelCompetenties deelCompetentie = new DeelCompetenties(ID,Naam,compid);
                deelCompetentieLijst.add(deelCompetentie);
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
        return deelCompetentieLijst;
      }//Einde getAllDeelCompetenties
    
    public void insertdeelCompetentie(String Name,int x) throws SQLException 
    {
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            
            prestatement = dbconnect.prepareStatement("INSERT INTO projectjava.deelcompetentie (naam,comp_id) values(?,?)");
            prestatement.setString(1, Name);
            prestatement.setInt(2, x);
            // execute insert SQL stetement
            prestatement.executeUpdate();
            System.out.println("Record is inserted into competentie table!");        
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
    }//Einde insertDeelCompetentie
    
    public  void changedeelCompetentie(String oud,String naam,int id) throws SQLException
    {
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie .GetConnection();
            prestatement = dbconnect.prepareStatement("UPDATE projectjava.deelcompetentie SET naam=? ,comp_id=?  where naam=?");
            prestatement.setString(1, naam);
            prestatement.setString(3, oud);
            prestatement.setInt(2, id);
            // execute insert SQL stetement
            prestatement.executeUpdate();
            System.out.println("Record is changed into competentie table!");
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
    }//Einde changeDeelCompetentie
    
    public void deletedeelCompetentie(String id) throws SQLException
    {
        
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            prestatement = dbconnect.prepareStatement("DELETE FROM projectjava.deelcompetentie WHERE naam=?");
            prestatement.setString(1, id);
            // execute insert SQL stetement
            prestatement.executeUpdate();
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
   }//Einde deleteDeelCompetentie
     public List<DeelCompetenties> getDeelcompPercomp(int id) throws SQLException
    {
        List<DeelCompetenties> deelCompLijst = new ArrayList();
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "select *from projectjava.deelcompetentie where comp_id=('"+id+"')";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);
            
            while(ResultaatSet.next())
            {
                Integer ID = ResultaatSet.getInt("id");
                String Naam = ResultaatSet.getString("naam");
                Integer compid = ResultaatSet.getInt("comp_id");
                
                DeelCompetenties deelCompetenties = new DeelCompetenties(ID, Naam,compid);
                deelCompLijst.add(deelCompetenties);
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
        return deelCompLijst;
      }//Einde getIndicatorenPerDeelcomp
}//Einde Class
