/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Competentie;

import DAO.CompetentiesDAO;
import domein.Competentie;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class Changecompetentie extends JPanel 
{
    private JLabel lblOudeNaam,lblNieuweNaam,lbluitleg;
    private JTextField txtNieuweNaam;
    private JButton btnWijzigen;
    private JComboBox cbxOudeNaam;
    
    
    public Changecompetentie()
    {
        super();
        setLayout(new MigLayout());
        addComponents();
    }

    private void addComponents() 
    {
        
        lblOudeNaam = new JLabel();
       
        lblNieuweNaam = new JLabel();
        txtNieuweNaam = new JTextField();
        btnWijzigen = new JButton();
        
        cbxOudeNaam = new JComboBox();
        FillComboBox();
        lbluitleg = new JLabel();
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        lbluitleg.setText("Wijzigen van Competentie:");
        lblOudeNaam.setText("oud naam:");
        lblOudeNaam.setFont(new Font("verdana", Font.BOLD, 12));
        lblNieuweNaam.setText("nieuwe naam:");
        lblNieuweNaam.setFont(new Font("verdana", Font.BOLD, 12));
        txtNieuweNaam.setPreferredSize(new Dimension(200, 25));
        btnWijzigen.setText("wijzig");
        btnWijzigen.setFont(new Font("verdana", Font.BOLD, 12));
        add(lbluitleg,"wrap");
        add(lblOudeNaam,"split");
        add(cbxOudeNaam,"wrap");
        add(lblNieuweNaam,"split");
        add(txtNieuweNaam,"wrap");
        add(btnWijzigen);
        
        btnWijzigen.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) 
            {   try {
                    buttonaction(e);
                } catch (SQLException ex) {System.out.println(ex.getMessage());}
            }
        });}

        private void buttonaction(ActionEvent e) throws SQLException 
        {
           CompetentiesDAO od = new CompetentiesDAO();
           try
           {
            if( txtNieuweNaam.getText().equals(""))
              JOptionPane.showMessageDialog(this, "één van de invoer velden is leeg!","Error",JOptionPane.ERROR_MESSAGE);  
            else
            {
               String oud = (String ) cbxOudeNaam.getSelectedItem();
               JOptionPane.showMessageDialog(this, "Record changed succs!","Succes",JOptionPane.INFORMATION_MESSAGE);
               od.changeCompetentie(oud, txtNieuweNaam.getText());
               FillComboBox();
               txtNieuweNaam.setText("");
            }
           }
          catch(NumberFormatException ex)
          {
             JOptionPane.showMessageDialog(this, "gelieve een getal integeven","Error",JOptionPane.ERROR_MESSAGE);
             txtNieuweNaam.setText("");
          }
        }  

    public void FillComboBox() 
    {
        CompetentiesDAO cdao = new CompetentiesDAO();
        cbxOudeNaam.removeAllItems();
        try {
        for(Competentie p : cdao.getAllcompetenties())
        {
            cbxOudeNaam.addItem(p.getNaam());
        }
        }catch(Exception e)
        {
            System.out.println("FillComboBox mislukt!");
        }
    }
}
