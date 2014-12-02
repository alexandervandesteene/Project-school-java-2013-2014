/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Competentie;

import DAO.CompetentiesDAO;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class Addcompetentie extends JPanel
{
    private JLabel lblNaam,lblID,lbluitleg,lblomschrijving;
    private JTextField txtNaam,txtID,txtomschrijving;
    private JButton btnToevoegen;
    
    //private Deletecompetentie deletecompetentie;
    
    public Addcompetentie()
    {
        super();
        setLayout(new MigLayout());
        addComponents();
    }

    private void addComponents()
    {
        lblID = new JLabel();
        txtID = new JTextField();
        lblNaam = new JLabel();
        txtNaam = new JTextField();
        btnToevoegen = new JButton();
        lbluitleg = new JLabel();
        lblomschrijving = new JLabel();
        txtomschrijving = new JTextField();
        
        lblomschrijving.setText("Omschrijving");
        lblomschrijving.setFont(new Font("verdana",Font.BOLD, 12));
        lbluitleg.setText("Toevoegen van Competentie:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        lblNaam.setFont(new Font("verdana", Font.BOLD, 12));
        lblID.setFont(new Font("verdana", Font.BOLD, 12));
        
        lblNaam.setText("naam:");
        lblID.setText("id:");
        txtID.setEnabled(false);
        txtID.setPreferredSize(new Dimension(200, 25));
        txtNaam.setPreferredSize(new Dimension(200, 25));
         txtomschrijving.setPreferredSize(new Dimension(200, 25));
        btnToevoegen.setText("Toevoegen");
        btnToevoegen.setFont(new Font("verdana", Font.BOLD, 12));
        
        add(lbluitleg,"wrap");
        add(lblID,"split");
        add(txtID,"wrap");
        add(lblNaam,"split");
        add(txtNaam,"wrap");
        add(lblomschrijving,"split");
        add(txtomschrijving,"wrap");
        add(btnToevoegen,"split");
        
        btnToevoegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {   try {
                    buttonaction(e);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
               
            }
      

        });
                }         
        private void buttonaction(ActionEvent e) throws SQLException 
        {
              CompetentiesDAO od = new CompetentiesDAO();
              if(txtNaam.getText().equalsIgnoreCase(""))
                  JOptionPane.showMessageDialog(this, "het invoer veld is leeg!","Error",JOptionPane.ERROR_MESSAGE); 
              else
              {
                od.insertCompetentie(txtNaam.getText(),txtomschrijving.getText());
                JOptionPane.showMessageDialog(this, "Record "+txtNaam.getText()+ " is inserted into competentie table!","Succes",JOptionPane.INFORMATION_MESSAGE);
                txtNaam.setText("");
                txtomschrijving.setText("");
              }            
        }
      } 

           
        
    

