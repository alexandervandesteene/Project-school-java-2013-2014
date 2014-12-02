/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Competentie;

import DAO.CompetentiesDAO;
import domein.Competentie;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class Deletecompetentie extends JPanel
{
    private JLabel lblCompetentie,lbluitleg;
    
    private JButton btnVerwijderen;
    public JComboBox cbxCompetentie;
    
    public Deletecompetentie()
    {
        super();
        setLayout(new MigLayout());
        
        addComponents();
      
        
        
    }

    private void addComponents() 
    {
        cbxCompetentie = new JComboBox();
        FillComboBox();
        
        lblCompetentie = new JLabel();
        btnVerwijderen = new JButton();
      lbluitleg = new JLabel();
        
        lbluitleg.setText("Verwijderen van Competentie:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        lblCompetentie.setText("Competentie:");
        lblCompetentie.setFont(new Font("verdana", Font.BOLD, 12));
        btnVerwijderen.setText("Verwijderen");
        btnVerwijderen.setFont(new Font("verdana", Font.BOLD, 12));
        add(lbluitleg,"wrap");
        add(lblCompetentie,"split");
        add(cbxCompetentie,"wrap");
        add(btnVerwijderen);
        
        btnVerwijderen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttenaction(e);
                } catch (SQLException ex) {System.out.println(ex.getMessage());}
            }
        });}
   
    private void buttenaction(ActionEvent e) throws SQLException 
    {
         CompetentiesDAO od = new CompetentiesDAO();
         String text = (String) cbxCompetentie.getSelectedItem();
         od.deleteCompetentie(text);
         JOptionPane.showMessageDialog(this, "Record is succesvol deleted","Succes",JOptionPane.INFORMATION_MESSAGE);
         FillComboBox();
     }
           
    public void FillComboBox() 
    {
        CompetentiesDAO cdao = new CompetentiesDAO();
        cbxCompetentie.removeAllItems();
        try 
        {
            for(Competentie p : cdao.getAllcompetenties())
                cbxCompetentie.addItem(p.getNaam());
        }
        catch(Exception e) {System.out.println("FillComboBox mislukt!");}
    }
}
