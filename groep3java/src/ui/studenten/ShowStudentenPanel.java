/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.studenten;

import DAO.Student_IndicatorDAO;
import DAO.StudentenDAO;
import domein.Student_Indicator;
import domein.Studenten;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class ShowStudentenPanel extends JPanel {

    private JComboBox jcb;
    private JProgressBar jpb;
    private JLabel lbluitleg,lblverklaring;

    public ShowStudentenPanel() throws SQLException {
        super();
        this.setSize(500, 300);
        setLayout(new MigLayout());
    
        addComponents();
    }

    private void addComponents() throws SQLException {
        
        lbluitleg = new JLabel();
        lblverklaring = new JLabel();
        lblverklaring.setText("De student is reeds geslaagd voor:");
        lblverklaring.setFont(new Font("verdana", Font.BOLD, 12));
        lbluitleg.setText("Algemeen overzicht progressie student");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        add(lbluitleg,"wrap");
        jcb = new JComboBox();
        fillComboBox();
        
        jpb = new JProgressBar(0, 100);
        jpb.setValue(fillprogres());
        jpb.setStringPainted(true);
        
        add(jcb,"wrap");
        add(lblverklaring,"wrap");
        add(jpb);
        jcb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                jpb.setValue(0);
                try {
                jpb.setValue(fillprogres());
                } catch (SQLException ex) {
                    Logger.getLogger(ShowStudentenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void fillComboBox() {
        StudentenDAO cdao = new StudentenDAO();
        jcb.removeAllItems();
        try {
            for (Studenten p : cdao.getAllStudenten()) {
                jcb.addItem(p.getNaam());
            }
        } catch (Exception e) {
            System.out.println("FillComboBox mislukt!");
        }
    }

    private int fillprogres() throws SQLException {
        StudentenDAO sd = new StudentenDAO();
        Student_IndicatorDAO stid = new Student_IndicatorDAO();
        ArrayList<Student_Indicator> l = new ArrayList<>();
        
        int aantalvakken;
        int x = 0;
        int idstudent = 0;
        double toevoegwaarde;
        
        
        String text = (String) jcb.getSelectedItem();
        for (Studenten p : sd.getAllStudenten()) 
        {
            if (p.getNaam() == null ? text == null : p.getNaam().equals(text)) 
            {
                idstudent = p.getId();
            }
        }
        aantalvakken = stid.getaantalindicatorid(idstudent);
        System.out.println(aantalvakken + "aantal vakken");
        toevoegwaarde = 100/aantalvakken;
        System.out.println(toevoegwaarde + "toegevoede waarde");
   
        
        
        for(Object em:(ArrayList)stid.opvullenProcesbar(idstudent))
        {
           l.add((Student_Indicator)em);
        }
        if(l.size() >0)
        {       
        for(int i =0;i< l.size();i++)
        {
            Student_Indicator hashmap = l.get(i);
            int score = hashmap.getScore();
            if(score > 10)
            {
                System.out.println(score + " punten per vak");
                x += toevoegwaarde;
                
                
            }
        }
        }
        System.out.println(x + "totaal toevoegen");
        return x;
        
    }
}
