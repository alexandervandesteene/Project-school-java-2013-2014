/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.studenten;

import DAO.IndicatorenDAO;
import DAO.Student_IndicatorDAO;
import domein.Indicatoren;
import domein.Student_Indicator;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class labelprogresbarPanel extends JPanel 
{
    private int teller;
    private JLabel jl;
    private JProgressBar jpb;
    
    public labelprogresbarPanel()
    {   
        
        jl = new JLabel();
        jpb = new JProgressBar();
        setLayout(new MigLayout());
        
      
    }
    
       public void fillprogres(int id) throws SQLException {


        Student_IndicatorDAO stid = new Student_IndicatorDAO();
        ArrayList<Student_Indicator> l = new ArrayList<>();

        for (Object em : (ArrayList) stid.opvullenProcesbar(id)) {
            l.add((Student_Indicator) em);

        }
        if (l.size() > 0) {
            for (int i = 0; i < l.size(); i++) {
                Student_Indicator hashmap = l.get(i);
                teller = l.size();
                System.out.println("teller:" + teller);
                jl = new JLabel();
                jl.setText(gettitle(hashmap.getIndID()));

                add(jl, "split");
                jpb = new JProgressBar(0, 20);
                jpb.setValue(hashmap.getScore());
                jpb.setStringPainted(true);



                add(jpb, "wrap");


            }
        }
    }
       private String gettitle(int id) throws SQLException {
        IndicatorenDAO sd = new IndicatorenDAO();
        String text = null;
        for (Indicatoren p : sd.getAllIndicatoren()) {
            if (p.getId() == id) {
                text = p.getNaam();
                System.out.println(id);
                System.out.println(text);
            }
        }

        return text;

    }
}
