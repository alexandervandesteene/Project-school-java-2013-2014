/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import databank.MyConnection;
import domein.Partims;
import domein.Student;
import domein.Student_Indicator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin
 */
public class PuntenTabelDAO {
    
    public Statement statement = null;
    public Connection dbconnect = null;
    
    public List<String> GetColumnNames(Partims p)
    {
        List<String> colNames = new ArrayList();
        
        try
        {
            colNames.add("Student");
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "SELECT ind_id \n" +
                                        "FROM projectjava.partim_indicator\n" +
                                        "WHERE part_id =" + p.getId().toString() + "\n" +
                                        "ORDER BY ind_id";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);
            
            while(ResultaatSet.next())
            {
                String ind_id = ResultaatSet.getString("ind_id");

                colNames.add("Indicator " + ind_id + " score");
            }
            colNames.add("Gemiddelde score");
        }
        catch(SQLException ex) 
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
        return colNames;
    }
    
    public List<Student> GetStudents(Partims p)
    {
        List<Student> studentlist = new ArrayList();
        
        try
        {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "SELECT stud_id, naam \n" +
                                        "FROM projectjava.student_partim\n" +
                                        "JOIN projectjava.student ON student.id = student_partim.stud_id\n" +
                                        "WHERE part_id =" + p.getId() +
                                        "\nORDER BY stud_id";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);
            
            while(ResultaatSet.next())
            {
                Student stud = new Student(ResultaatSet.getInt("stud_id"), ResultaatSet.getString("naam"));
                studentlist.add(stud);
            }
        }
        catch(SQLException ex) 
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
        return studentlist;
    }
    public Object[][] GetColumnData(Partims p)
    {
        
        List<Student> studentList = GetStudents(p);
        
        int rowCount = studentList.size();
        int indColCount = GetColumnNames(p).size();
        Object [][] data = new Object[rowCount][indColCount];
        for (int i = 0; i < rowCount; i++) 
        {
            List<Student_Indicator> scoreList;
            float gemiddelde = 0;
            data [i][0] = studentList.get(i);
            scoreList = GetIndicatorScores(p, studentList.get(i));
            for (int j = 1; j < indColCount-1; j++) 
            {
                data [i][j] = scoreList.get(j-1); 
                gemiddelde += scoreList.get(j-1).getScore();
            }
            gemiddelde /= (indColCount-2);
            data[i][indColCount-1] = gemiddelde;
            //gemiddelde testen op null-waarden 
        }
        return data;
    }
    public List<Student_Indicator> GetIndicatorScores(Partims p, Student s)
    {
        List<Student_Indicator> scoreList = new ArrayList();
        
        Student_IndicatorDAO SIDAO = new Student_IndicatorDAO();
        
        try
        {
            scoreList=SIDAO.getStudInd(p, s);
        }
        catch(SQLException ex) 
            {System.out.println(ex.getMessage());}
        
        return scoreList;
    }
}