/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import databank.MyConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexander
 */
public class TestConnection {
    
    public TestConnection() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void TestConnection()
    {
        MyConnection con = null;
        try {
            con = MyConnection.class.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(con);
        assertNotNull(con.GetConnection());
    }
}