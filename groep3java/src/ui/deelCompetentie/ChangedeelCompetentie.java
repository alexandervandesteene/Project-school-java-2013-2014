/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.deelCompetentie;

import DAO.*;
import domein.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class ChangedeelCompetentie extends JPanel
{
    private JLabel lblOudeNaam,lblNieuweNaam,lblCompetentie,lbluitleg;
    private JTextField txtNieuweNaam;
    private JButton btnWijzigen;
    private JComboBox cbxOudeNaam,cbxCompetentie;
    
    public ChangedeelCompetentie()
    {
        super();
        setLayout(new MigLayout());
        addComponents();
    }

    private void addComponents() 
    {
        cbxOudeNaam = new JComboBox();
        fillCbxOudeNaam();
        
        cbxCompetentie = new JComboBox();
        filCbxCompetentie();
        
        lblOudeNaam = new JLabel();
        lblNieuweNaam = new JLabel();
        txtNieuweNaam = new JTextField();
        btnWijzigen = new JButton();
        lblCompetentie = new JLabel();
        lbluitleg = new JLabel();
        
        lbluitleg.setText("Wijzigen van Deelcompetentie:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        
        lblOudeNaam.setText("oude naam:");
        lblOudeNaam.setFont(new Font("verdana", Font.BOLD, 12));
        lblNieuweNaam.setText("nieuwe naam:");
        lblNieuweNaam.setFont(new Font("verdana", Font.BOLD, 12));
        txtNieuweNaam.setPreferredSize(new Dimension(200, 25));
        btnWijzigen.setText("wijzig");
        btnWijzigen.setFont(new Font("verdana", Font.BOLD, 12));
        lblCompetentie.setText("Competentie:");
        lblCompetentie.setFont(new Font("verdana", Font.BOLD, 12));
        
        add(lbluitleg,"wrap");
        add(lblOudeNaam,"split");
        add(cbxOudeNaam,"wrap");
        add(lblNieuweNaam,"split");
        add(txtNieuweNaam,"wrap");
        add(lblCompetentie,"split");
        add(cbxCompetentie,"wrap");
        add(btnWijzigen);
        
        btnWijzigen.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) 
           {
               try {buttonaction(e);}
               catch (SQLException ex) {System.out.println(ex.getMessage());}
           }
        });}

        private void buttonaction(ActionEvent e) throws SQLException 
        {
           deelCompetentiesDAO od = new deelCompetentiesDAO();
           CompetentiesDAO cod = new CompetentiesDAO();
           try
           {
                if(txtNieuweNaam.getText().equals(""))
                    JOptionPane.showMessageDialog(this, "één van de invoer velden is leeg!","Error",JOptionPane.ERROR_MESSAGE);  
                else
                {
                    int x = 0;
                    String text = (String) cbxOudeNaam.getSelectedItem();
                    String text1 = (String) cbxCompetentie.getSelectedItem();
                    for(Competentie p : cod.getAllcompetenties())
                    {
                        if(p.getNaam() == null ? text1 == null : p.getNaam().equals(text1))
                            x = p.getID();
                    }
                    od.changedeelCompetentie(text,txtNieuweNaam.getText(),x);
                    JOptionPane.showMessageDialog(this, "Record changed succs!","Succes",JOptionPane.INFORMATION_MESSAGE);
                    txtNieuweNaam.setText("");
                }
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "gelieve een getal integeven","Error",JOptionPane.ERROR_MESSAGE);
                txtNieuweNaam.setText("");
            }
        }   

    public void fillCbxOudeNaam() 
    {
        deelCompetentiesDAO cdao = new deelCompetentiesDAO();
        cbxOudeNaam.removeAllItems();
        try 
        {
            for(DeelCompetenties p : cdao.getAlldeelCompetentie())
                cbxOudeNaam.addItem(p.getNaam());
        }
        catch(Exception e){System.out.println("FillComboBox mislukt!");}
    }

    public void filCbxCompetentie() 
    {
        CompetentiesDAO cdao = new CompetentiesDAO();
        cbxCompetentie.removeAllItems();
        try
        {
            for(Competentie p : cdao.getAllcompetenties())
                cbxCompetentie.addItem(p.getNaam());
        }
        catch(Exception e){System.out.println("FillComboBox mislukt!");}
    }
}
