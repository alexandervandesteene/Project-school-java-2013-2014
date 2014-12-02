/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.koppelen;

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
public class addpartimidpanel extends JPanel
{
    private JLabel lblIndicator,lblPartim,lblKoppelen;
    private JButton btnToevoegen;
    private JTable jp;
    private JComboBox cbxIndicator,cbxPartim;
    
    public addpartimidpanel()
    {
        super();
        setLayout(new MigLayout());
        addComponents();
    }

    private void addComponents() 
    {
        lblIndicator = new JLabel();
        cbxIndicator = new JComboBox();
        fillCbxIndicator();
        cbxPartim = new JComboBox();
        fillCbxPartim();
        btnToevoegen = new JButton(); 
        lblPartim = new JLabel();
        lblKoppelen = new JLabel();
     
        lblKoppelen.setText("koppelen van indicatoren:");
        lblKoppelen.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        lblIndicator.setText("indicator:");
        lblIndicator.setFont(new Font("verdana", Font.BOLD, 12));
        lblPartim.setText("partim:");
        lblPartim.setFont(new Font("verdana", Font.BOLD, 12));
        btnToevoegen.setText("partim id toevoegen");
        btnToevoegen.setFont(new Font("verdana", Font.BOLD, 12));
        
        btnToevoegen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {toevoegen(e);}
                catch (SQLException ex) {System.out.println(ex.getMessage());}
            }
        });
    
        add(lblKoppelen,"wrap");
        add(lblIndicator,"split");
        add(cbxIndicator,"wrap");
        add(lblPartim,"split");
        add(cbxPartim,"wrap");
        add(btnToevoegen);
    }
    
    private void toevoegen(ActionEvent e) throws SQLException 
    {
        IndicatorenDAO pd = new IndicatorenDAO();
        PartimsDAO cd = new PartimsDAO();
        int x =0;
        int y =0;
        
        String text = (String) cbxIndicator.getSelectedItem();
        String text1 = (String) cbxPartim.getSelectedItem();
          
        for (Indicatoren p : pd.getAllIndicatoren()) 
        {
            if(p.getNaam() == null ? text == null : p.getNaam().equals(text))
                x = p.getId();  
        }
        for (Partims p : cd.getAllPartims())
        {
            if(p.getNaam() == null ? text1 == null : p.getNaam().equals(text1))
                y = p.getId();
        }
        
        pd.addPartimsID(x, y);
        JOptionPane.showMessageDialog(this, "Record  is succesvol gekoppeld aan","Succes",JOptionPane.INFORMATION_MESSAGE);
      }

    public void fillCbxIndicator() 
    {
        IndicatorenDAO cdao = new IndicatorenDAO();
        cbxIndicator.removeAllItems();
        try
        {
        for(Indicatoren p : cdao.getAllIndicatoren())
            cbxIndicator.addItem(p.getNaam());
        }
        catch(Exception e){System.out.println("FillComboBox mislukt!");}
    }

    public void fillCbxPartim()
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
