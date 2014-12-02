/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.deelCompetentie;

import DAO.CompetentiesDAO;
import DAO.deelCompetentiesDAO;
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
public class AddDeelCompetentie extends JPanel 
{
    private JLabel lblNaam,lblID,lblCompetentie,lbluitleg;
    private JTextField txtNaam,txtID;
    private JButton btnToevoegen;
    private JComboBox cbxDeelcmpetentie;
    
    public AddDeelCompetentie()
    {
        super();
        setLayout(new MigLayout());
        addComponents();
    }

    private void addComponents()
    {
        cbxDeelcmpetentie = new JComboBox();
        fillcombobox();
        lblNaam = new JLabel();
        txtNaam = new JTextField();
        btnToevoegen = new JButton();
        lblID = new JLabel();
        lblCompetentie = new JLabel();
        txtID = new JTextField();
        lbluitleg = new JLabel();
        
        lbluitleg.setText("Toevoegen van Deelcompetentie:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        
        lblNaam.setText("naam:");
        lblNaam.setFont(new Font("verdana", Font.BOLD, 12));
        txtNaam.setPreferredSize(new Dimension(200, 25));
        txtID.setPreferredSize(new Dimension(200, 25));
        btnToevoegen.setText("Toevoegen");
        btnToevoegen.setFont(new Font("verdana", Font.BOLD, 12));
        txtID.setEnabled(false);
        lblID.setText("id:");
        lblID.setFont(new Font("verdana", Font.BOLD, 12));
        lblCompetentie.setText("Competentie:");
        lblCompetentie.setFont(new Font("verdana", Font.BOLD, 12));
        
        add(lbluitleg,"wrap");
        add(lblID,"split");
        add(txtID,"wrap");
        add(lblNaam,"split");
        add(txtNaam,"wrap");
        add(lblCompetentie,"split");
        add(cbxDeelcmpetentie,"wrap");
        add(btnToevoegen);
        
        btnToevoegen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {   
                try {buttonaction(e);} 
                catch (SQLException ex) {System.out.println(ex.getMessage());}
            }
        });
                }         
        private void buttonaction(ActionEvent e) throws SQLException 
        {
              CompetentiesDAO cod = new CompetentiesDAO();
              deelCompetentiesDAO od = new deelCompetentiesDAO();
              if(txtNaam.getText().equalsIgnoreCase(""))
                  JOptionPane.showMessageDialog(this, "het invoer veld is leeg!","Error",JOptionPane.ERROR_MESSAGE); 
              else
              {
                int x = 0;
                String text = (String) cbxDeelcmpetentie.getSelectedItem();
                for(Competentie p : cod.getAllcompetenties())
                {
                    if(p.getNaam() == null ? text == null : p.getNaam().equals(text))
                        x = p.getID();
                }
                System.out.println(x);
                od.insertdeelCompetentie(txtNaam.getText(),x);
                JOptionPane.showMessageDialog(this, "Record "+txtNaam.getText()+ " is inserted into deelCompetentie table!","Succes",JOptionPane.INFORMATION_MESSAGE);
                txtNaam.setText("");
              }            
        }

        public void fillcombobox() 
        {
            CompetentiesDAO cdao = new CompetentiesDAO();
            cbxDeelcmpetentie.removeAllItems();
            try
            {
                for(Competentie p : cdao.getAllcompetenties())
                    cbxDeelcmpetentie.addItem(p.getNaam());
            }
            catch(Exception e){System.out.println("FillComboBox mislukt!");}
    }
}
