/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import java.sql.*;

/**
 *
 * @author alexander
 */
public class MyConnection 
{
    private Connection connect = null;
    public Connection GetConnection()
    {
        System.out.println("------------- connection testing----------");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
            {/*System.out.println("waar is MySQl Driver?");*/}

        try
        {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
        }
        catch(SQLException e)
            {System.out.println("connection faild!");}
        
        if(getConnect() != null)
            System.out.println("er is connectie met de db");
        else
            System.out.println("geen connectie met db");
           
        return getConnect();
    } //Einde getConnection

    /**
     * @return the connect
     */
    public Connection getConnect() {
        return connect;
    }
}//Einde Class