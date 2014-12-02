/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.deelCompetentie;

import DAO.*;
import domein.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class DeleteDeelCompetentie extends JPanel
{
    private JLabel lblDeelCompetentie,lbluitleg;
    private JComboBox cbxDeelcompetentie;
    private JButton btnVerwijderen;
    
    public DeleteDeelCompetentie()
    {
        super();
        setLayout(new MigLayout());
        addComponents();
    }

    private void addComponents() 
    {
        btnVerwijderen = new JButton();
        lblDeelCompetentie = new JLabel();
        cbxDeelcompetentie = new JComboBox();
        fillcombobox();
        lbluitleg = new JLabel();
        
        lbluitleg.setText("Verwijderen van Deelcompetentie:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        lblDeelCompetentie.setText("deelCompetentie:");
        lblDeelCompetentie.setFont(new Font("verdana", Font.BOLD, 12));
        btnVerwijderen.setText("Verwijderen");
        btnVerwijderen.setFont(new Font("verdana", Font.BOLD, 12));
        
        add(lbluitleg,"wrap");
        add(lblDeelCompetentie,"split");
        add(cbxDeelcompetentie,"wrap");
        add(btnVerwijderen);
        
        btnVerwijderen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {buttenaction(e);}
                catch (SQLException ex) {System.out.println(ex.getMessage());}
            }
        });
    }
   
    private void buttenaction(ActionEvent e) throws SQLException 
    {
        deelCompetentiesDAO od = new deelCompetentiesDAO();
        String text = (String ) cbxDeelcompetentie.getSelectedItem();
        od.deletedeelCompetentie(text);
        JOptionPane.showMessageDialog(this, "Record is succesvol deleted","Succes",JOptionPane.INFORMATION_MESSAGE);
        fillcombobox(); 
     }
        
    public void fillcombobox()
    {
        deelCompetentiesDAO cdao = new deelCompetentiesDAO();
        cbxDeelcompetentie.removeAllItems();
        try 
        {
            for(DeelCompetenties p : cdao.getAlldeelCompetentie())
                cbxDeelcompetentie.addItem(p.getNaam());
        }
        catch(Exception e){System.out.println("FillComboBox mislukt!");}
     }
}
