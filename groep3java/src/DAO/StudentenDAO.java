/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import databank.MyConnection;
import domein.Studenten;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class StudentenDAO {

    public Statement statement = null;
    public Connection dbconnect = null;

    public List<Studenten> getAllStudenten() throws SQLException {
        List<Studenten> mylist = new ArrayList();
        try {
            MyConnection mc = new MyConnection();
            dbconnect = mc.GetConnection();
            statement = dbconnect.createStatement();

            String selectTableSql = "select * from projectjava.student";
            ResultSet ra = statement.executeQuery(selectTableSql);

            while (ra.next()) {
                Integer aId = ra.getInt("id");
                String aName = ra.getString("naam");

                Studenten o = new Studenten(aId, aName);
                mylist.add(o);
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
        return mylist;
    }
}