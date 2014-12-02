/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import databank.MyConnection;
import domein.Competentie;
import domein.Partims;
import domein.Student;
import domein.Student_Indicator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin
 */
public class Student_IndicatorDAO {

    public Statement statement = null;
    public PreparedStatement prestatement = null;
    public Connection dbconnect = null;

    public List<Student_Indicator> getStudInd(Partims p, Student s) throws SQLException {
        List<Student_Indicator> studentIndicatorScoreLijst = new ArrayList();
        try {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String SelectSQLStatement = "SELECT si.stud_id, si.ind_id, si.score\n"
                    + "FROM projectjava.partim_indicator pai\n"
                    + "JOIN projectjava.student_indicator si ON pai.ind_id = si.ind_id\n"
                    + "WHERE pai.part_id = " + p.getId() + " AND si.stud_id = " + s.getID() + "\n"
                    + "ORDER BY ind_id";
            ResultSet ResultaatSet = statement.executeQuery(SelectSQLStatement);

            while (ResultaatSet.next()) {
                int stud_id = ResultaatSet.getInt("stud_id");
                int ind_id = ResultaatSet.getInt("ind_id");
                int score = ResultaatSet.getInt("score");

                Student_Indicator SI = new Student_Indicator(stud_id, ind_id, score);
                studentIndicatorScoreLijst.add(SI);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbconnect != null) {
                    dbconnect.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return studentIndicatorScoreLijst;
    }

    public void UpdateStudent_Indicator(Student_Indicator si, int score) {
        try {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            statement = dbconnect.createStatement();
            String UpdateSQLStatement = "Update projectjava.Student_indicator\n"
                    + "SET score = " + score + "\n"
                    + "WHERE stud_id = " + si.getStudID() + " AND ind_id = " + si.getIndID() + "\n";
            //statement.executeQuery(UpdateSQLStatement);
            statement.executeUpdate(UpdateSQLStatement);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbconnect != null) {
                    dbconnect.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public List<Student_Indicator> opvullenProcesbar(int id) {
        List<Student_Indicator> scorelijst = new ArrayList();
        try {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            prestatement = dbconnect.prepareStatement("select score,ind_id from projectjava.Student_indicator where stud_id=?");
            prestatement.setInt(1, id);
            //statement.executeQuery(UpdateSQLStatement);
            ResultSet Resultaatset = prestatement.executeQuery();

            while (Resultaatset.next()) {
                int indid = Resultaatset.getInt("ind_id");
                int score = Resultaatset.getInt("score");


                Student_Indicator SI = new Student_Indicator(score, indid);
                scorelijst.add(SI);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbconnect != null) {
                    dbconnect.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return scorelijst;
    }

    public int getaantalindicatorid(int id) {
        int x = 0;
        try {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            prestatement = dbconnect.prepareStatement("select count(ind_id) from projectjava.student_indicator where stud_id=?");
            prestatement.setInt(1, id);
            //statement.executeQuery(UpdateSQLStatement);
            ResultSet rs = prestatement.executeQuery();
            rs.next();
            x = rs.getInt(1);







        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbconnect != null) {
                    dbconnect.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return x;
    }

    public List<Student_Indicator> opvullenProcesbarDeelcomp(int id, int deelid) {
        List<Student_Indicator> scorelijst = new ArrayList();
        try {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            prestatement = dbconnect.prepareStatement("select score,ind_id from projectjava.Student_indicator where stud_id=? and ind_id=?");
            prestatement.setInt(1, id);
            prestatement.setInt(2, id);
            //statement.executeQuery(UpdateSQLStatement);
            ResultSet Resultaatset = prestatement.executeQuery();

            while (Resultaatset.next()) {

                int score = Resultaatset.getInt("score");


                Student_Indicator SI = new Student_Indicator(score);
                scorelijst.add(SI);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbconnect != null) {
                    dbconnect.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return scorelijst;
    }

    public int getaantaldeelindicatorid(int id,int deelid) {
        int x = 0;
        try {
            MyConnection MijConnectie = new MyConnection();
            dbconnect = MijConnectie.GetConnection();
            prestatement = dbconnect.prepareStatement("select count(ind_id) from projectjava.student_indicator where stud_id=? and ind_id =?");
            prestatement.setInt(1, id);
            prestatement.setInt(2, deelid);
            //statement.executeQuery(UpdateSQLStatement);
            ResultSet rs = prestatement.executeQuery();
            rs.next();
            x = rs.getInt(1);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbconnect != null) {
                    dbconnect.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return x;
    }
}