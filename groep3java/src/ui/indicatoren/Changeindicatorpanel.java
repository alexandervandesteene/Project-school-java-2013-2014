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
public class Changeindicatorpanel extends JPanel
{
    private JLabel lblID,lblNaam,lblDeelCompetentie,lblPartims,lbluitleg;
    private JTextField jt,txtNaam;
    private JButton btnWijzig;
    private JComboBox cbxID,cbxDeelCompetentie,cbxPartim;

    
    public Changeindicatorpanel()
    {
        super();
        setLayout(new MigLayout());
        addComponents();
    }

    private void addComponents() 
    {
        cbxID = new JComboBox();
        FillCbxID();
        cbxDeelCompetentie = new JComboBox();
        FillCbxDeelCompetentie();
        cbxPartim = new JComboBox();
        FillCbxParim();
        
        lblID = new JLabel();
        jt = new JTextField();

        lbluitleg = new JLabel();
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));

        lblNaam = new JLabel();
        txtNaam = new JTextField();
        btnWijzig = new JButton();
        lblDeelCompetentie = new JLabel();
        lblPartims = new JLabel();

        lbluitleg.setText("Wijzigen van Indicatoren:");
      

        lblID.setText("oude naam:");
        lblID.setFont(new Font("verdana", Font.BOLD, 12));
        jt.setPreferredSize(new Dimension(200, 25));

       
        add(lbluitleg,"wrap");
        
        lblNaam.setText("naam:");
        lblNaam.setFont(new Font("verdana", Font.BOLD, 12));
        txtNaam.setPreferredSize(new Dimension(200, 25));
        btnWijzig.setText("wijzig");
        btnWijzig.setFont(new Font("verdana", Font.BOLD, 12));
        lblDeelCompetentie.setText("DeelCompetentie:");
        lblDeelCompetentie.setFont(new Font("verdana", Font.BOLD, 12));
        lblPartims.setText("Partims:");
        lblPartims.setFont(new Font("verdana", Font.BOLD, 12));

        
        add(lblID,"split");
        add(cbxID,"wrap");
        add(lblNaam,"split");
        add(txtNaam,"wrap");
        add(lblDeelCompetentie,"split");
        add(cbxDeelCompetentie,"wrap");
        add(lblPartims,"split");
        add(cbxPartim,"wrap");
        add(btnWijzig);
        
        btnWijzig.addActionListener(new ActionListener()
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
        IndicatorenDAO ia=new IndicatorenDAO();
        deelCompetentiesDAO cd = new deelCompetentiesDAO();  
        PartimsDAO pd = new PartimsDAO();
           
        try  
        {
            if(txtNaam.getText().equals(""))
                JOptionPane.showMessageDialog(this, "één van de invoer velden is leeg!","Error",JOptionPane.ERROR_MESSAGE);     
            else          
            {
                int x =0;
                int y =0;
                String text = (String) cbxID.getSelectedItem();
                String text1 = (String) cbxDeelCompetentie.getSelectedItem();
                String text2 = (String) cbxPartim.getSelectedItem();
                for(DeelCompetenties p : cd.getAlldeelCompetentie())
                {
                    if(p.getNaam() == null ? text1 == null : p.getNaam().equals(text1))
                        x = p.getId();
                }
                for(Partims p : pd.getAllPartims())
                {
                    if(p.getNaam() == null ? text2 == null : p.getNaam().equals(text2))
                        y = p.getId();
                }
                JOptionPane.showMessageDialog(this, "Record changed succs!","Succes",JOptionPane.INFORMATION_MESSAGE);           
                ia.changeIndicator(text,txtNaam.getText(),x,y);            
                txtNaam.setText("");           
            }           
        }         
        catch(NumberFormatException ex)         
        {            
            JOptionPane.showMessageDialog(this, "gelieve een getal integeven","Error",JOptionPane.ERROR_MESSAGE);             
            jt.setText("");            
            txtNaam.setText("");         
        }        
    }   

    public void FillCbxID() 
    {
        IndicatorenDAO cdao = new IndicatorenDAO();
        cbxID.removeAllItems();
        try
        {
            for(Indicatoren p : cdao.getAllIndicatoren())
                cbxID.addItem(p.getNaam());
        }
        catch(Exception e){System.out.println("FillComboBox mislukt!");}
     }

    public void FillCbxDeelCompetentie() 
    {
        deelCompetentiesDAO cdao = new deelCompetentiesDAO();
        cbxDeelCompetentie.removeAllItems();
        try
        {
            for(DeelCompetenties p : cdao.getAlldeelCompetentie())
                cbxDeelCompetentie.addItem(p.getNaam());
        }
        catch(Exception e){System.out.println("FillComboBox mislukt!");}
    }

    public void FillCbxParim() 
    {
        PartimsDAO cdao = new PartimsDAO();
        cbxPartim.removeAllItems();
        try
        {
            for(Partims p : cdao.getAllPartims())
                cbxPartim.addItem(p.getNaam());
        }
        catch(Exception e){System.out.println("FillComboBox mislukt!");}
    }
}
