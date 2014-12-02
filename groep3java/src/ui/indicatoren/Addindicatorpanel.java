/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.indicatoren;

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
public class Addindicatorpanel extends JPanel
{
    private JLabel lblNaam,lblID,lblDeelcompetentie,lblPartim,lbluitleg;
    private JTextField txtNaam,txtID;
    private JButton btnToevoegen;
    private JComboBox cbxDeelcompetentie,cbxPartim;
    
    public Addindicatorpanel()
    {
        super();
        setLayout(new MigLayout());
        addComponents();
    }

    private void addComponents()
    {
        lblNaam = new JLabel();
        lblID = new JLabel();
        lblDeelcompetentie= new JLabel();
        lblPartim = new JLabel();
        txtNaam = new JTextField();
        txtID = new JTextField();
        btnToevoegen = new JButton();
        cbxDeelcompetentie = new JComboBox();
        FillCbxDeelcompetentie();
        cbxPartim = new JComboBox();
        FillCbxPartim();
        lbluitleg = new JLabel();
        
        lbluitleg.setText("Toevoegen van Indicatoren:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        
        lblID.setText("id:");
        lblID.setFont(new Font("verdana", Font.BOLD, 12));
        lblNaam.setText("naam:");
        lblNaam.setFont(new Font("verdana", Font.BOLD, 12));
        lblDeelcompetentie.setText("Deelcompetentie:");
        lblDeelcompetentie.setFont(new Font("verdana", Font.BOLD, 12));
        lblPartim.setText("partim:");
        lblPartim.setFont(new Font("verdana", Font.BOLD, 12));
        txtNaam.setPreferredSize(new Dimension(200, 25));
        txtID.setPreferredSize(new Dimension(200, 25));
        txtID.setEnabled(false);
        btnToevoegen.setText("Toevoegen");
        btnToevoegen.setFont(new Font("verdana", Font.BOLD, 12));
        
        add(lbluitleg,"wrap");
        add(lblID,"split");
        add(txtID,"wrap");
        add(lblNaam,"split");
        add(txtNaam,"wrap");
        add(lblDeelcompetentie,"split");
        add(cbxDeelcompetentie,"wrap");
        add(lblPartim,"split");
        add(cbxPartim,"wrap");
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
              IndicatorenDAO id=new IndicatorenDAO();
              deelCompetentiesDAO dcd = new deelCompetentiesDAO();
              PartimsDAO pd = new PartimsDAO();
              
              if(txtNaam.getText().equalsIgnoreCase(""))
              {
                JOptionPane.showMessageDialog(this, "het invoer veld is leeg!","Error",JOptionPane.ERROR_MESSAGE);  
              }
              else
              {
              int x = 0;
              int y = 0;
              String text = (String) cbxDeelcompetentie.getSelectedItem();
              String text1 = (String) cbxPartim.getSelectedItem();
              for (DeelCompetenties p : dcd.getAlldeelCompetentie() ) 
              {
                  if(p.getNaam() == null ? text == null : p.getNaam().equals(text))
                    {
                        x = p.getId();
                    }   
              }
              for (Partims p : pd.getAllPartims())
              {
                 if(p.getNaam() == null ? text1 == null : p.getNaam().equals(text1))
                    {
                        y = p.getId();
                    }    
              }
              
              id.insertIndicator(txtNaam.getText(),x,y);
              JOptionPane.showMessageDialog(this, "Record "+txtNaam.getText()+ " is inserted into indicatoren table!","Succes",JOptionPane.INFORMATION_MESSAGE);
              txtNaam.setText("");
              }
        }

    public void FillCbxDeelcompetentie() 
    {
         deelCompetentiesDAO cdao = new deelCompetentiesDAO();
        cbxDeelcompetentie.removeAllItems();
        try {
        for(DeelCompetenties p : cdao.getAlldeelCompetentie())
        {
            cbxDeelcompetentie.addItem(p.getNaam());
        }
        }catch(Exception e)
        {
            System.out.println("FillComboBox mislukt!");
        }
    }

    public void FillCbxPartim() 
    {
         PartimsDAO cdao = new PartimsDAO();
        cbxPartim.removeAllItems();
        try {
        for(Partims p : cdao.getAllPartims())
        {
            cbxPartim.addItem(p.getNaam());
        }
        }catch(Exception e)
        {
            System.out.println("FillComboBox mislukt!");
        }
    }
}
