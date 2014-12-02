/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import databank.MyConnection;
import domein.Indicatoren;
import java.sql.*;
import java.util.*;

/**
 *
 * @author alexander
 */
public class IndicatorenDAO 
{
    public Statement statement = null;
    public PreparedStatement prestatement = null;
    public Connection dbconnect = null;
    
    public List<Indicatoren> getAllIndicatoren() throws SQLException
    {
        List<Indicatoren> IndicatorenLijst = new ArrayList();
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
        
            String SelectSQLStatement = "select *from projectjava.indicatoren";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);

            while (ResultaatSet.next()) 
            {
                Integer ID = ResultaatSet.getInt("id");
                String Naam = ResultaatSet.getString("naam");
                Integer deelcompid = ResultaatSet.getInt("deelcomp_id");
                Integer PartimID = ResultaatSet.getInt("part_id");
                Indicatoren Indicator = new Indicatoren(ID, Naam,deelcompid,PartimID);
                IndicatorenLijst.add(Indicator);
            }
        } 
        catch (SQLException ex) 
            {System.out.println(ex.getMessage());}
        finally 
        {
            try 
            {
                if (statement != null)
                    statement.close();
                if (dbconnect != null)
                    dbconnect.close();
            } 
            catch (SQLException ex) 
                {System.out.println(ex.getMessage());}
        }
        return IndicatorenLijst;
    }//Einde getAllIndicatoren
    
    public  void insertIndicator(String Name,int x,int y) throws SQLException 
    {
        try 
        {
            MyConnection mc=new   MyConnection();
            dbconnect=mc.GetConnection();
            prestatement =dbconnect.prepareStatement("INSERT INTO projectjava.indicatoren(naam,deelcomp_id,part_id) values(?,?,?) ");
            prestatement.setString(1, Name);
            prestatement.setInt(2, x);
            prestatement.setInt(3, y);
            // execute insert SQL stetement
            prestatement.executeUpdate();
            System.out.println("Record is inserted into indicator table!");
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
 
    }//Einde insertIndicator
    
    public  void changeIndicator(String oud,String naam,int x, int y) throws SQLException
    {
        
       
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect=MijConnectie.GetConnection();
            prestatement = dbconnect.prepareStatement("UPDATE projectjava.indicatoren SET naam =?,deelcomp_id=?,part_id=?  where naam=?");
            prestatement.setString(1, naam);
            prestatement.setInt(2, x);
            prestatement.setInt(3, y);
            prestatement.setString(4, oud);
            // execute insert SQL stetement
            prestatement.executeUpdate();
            System.out.println("Record is changed into indicator table!");
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
    }//Einde changeIndicatoren
    
    public void deleteIndicator(String naam) throws SQLException
    {
                     
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            prestatement = dbconnect.prepareStatement("DELETE FROM projectjava.indicatoren WHERE naam=?");
            prestatement.setString(1, naam);
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
   }//Einde DeleteIndicatoren
   
    public void addPartimsID(int id,int partimsid) throws SQLException
    {
        String UpdateSQLStatementPartims = "UPDATE projectjava.indicatoren SET "
                                           + "part_id=('"+partimsid+"') where id=('"+id+"')";
        try 
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            // execute insert SQL stetement
            statement.executeUpdate(UpdateSQLStatementPartims);
            System.out.println("partim id is add to table!");
 
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
    }//Einde addPartimsID
    
    public List<Indicatoren> getIndicatorenPerPartims(int id) throws SQLException
    {
        List<Indicatoren> IndicatorenLijst = new ArrayList();
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "select *from projectjava.indicatoren where part_id=('"+id+"')";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);
            
            while(ResultaatSet.next())
            {
                Integer ID = ResultaatSet.getInt("id");
                String Naam = ResultaatSet.getString("naam");
                Integer deelcompid = ResultaatSet.getInt("deelcomp_id");
                Integer PartimID = ResultaatSet.getInt("part_id");
                Indicatoren Indicator = new Indicatoren(ID, Naam,deelcompid,PartimID);
                IndicatorenLijst.add(Indicator);
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
        return IndicatorenLijst;
      }//Einde getIndicatorenPerPartims
      public List<Indicatoren> getIndicatorenPerDeelcomp(int id) throws SQLException
    {
        List<Indicatoren> IndicatorenLijst = new ArrayList();
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "select *from projectjava.indicatoren where deelcomp_id=('"+id+"')";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);
            
            while(ResultaatSet.next())
            {
                Integer ID = ResultaatSet.getInt("id");
                String Naam = ResultaatSet.getString("naam");
                Integer deelcompid = ResultaatSet.getInt("deelcomp_id");
                Integer PartimID = ResultaatSet.getInt("part_id");
                Indicatoren Indicator = new Indicatoren(ID, Naam,deelcompid,PartimID);
                IndicatorenLijst.add(Indicator);
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
        return IndicatorenLijst;
      }//Einde getIndicatorenPerDeelcomp
}//Einde class
