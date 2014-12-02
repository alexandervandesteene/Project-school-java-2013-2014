/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.studenten;

import DAO.StudentenDAO;
import domein.Studenten;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class StudentenIndicatorenPanel extends JPanel {

    private JComboBox jcb;
    private JProgressBar jpb;
    private JLabel jl, lbluitleg;
    private int id;
    StudentenDAO sd = new StudentenDAO();
    labelprogresbarPanel lpp = new labelprogresbarPanel();

    public StudentenIndicatorenPanel() {

        super();
        this.setSize(500, 300);
        setLayout(new MigLayout());

        addComponents();
    }

    private void addComponents() {
        jcb = new JComboBox();
        fillComboBox();
        lbluitleg = new JLabel();
       

        lbluitleg.setText("Score per Indicator van student:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        add(lbluitleg, "wrap");
        add(jcb, "wrap");
        add(lpp);


       jcb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
               
                
                verwijderenComponenten();
                
                try {
                    lpp.fillprogres(getstudentid());
                } catch (SQLException ex) {
                    Logger.getLogger(StudentenIndicatorenPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                                  
                validate();
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

 

    private int getstudentid() {
        try {
            String text = (String) jcb.getSelectedItem();
            for (Studenten p : sd.getAllStudenten()) {
                if (p.getNaam() == null ? text == null : p.getNaam().equals(text)) {
                    id = p.getId();
                    System.out.println(id);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentenIndicatorenPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    private void verwijderenComponenten()
    {
        Component[] allComponents =lpp.getComponents(); 
                for(Component component : allComponents){
                        
                         lpp.remove(component);
                        }
    }

    
}
