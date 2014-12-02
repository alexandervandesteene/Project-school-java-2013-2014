/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.indicatoren;

import DAO.IndicatorenDAO;
import domein.Indicatoren;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class Deleteindicatorpanel extends JPanel
{
    private JLabel jl,lbluitleg;
    private JComboBox jcb;
    private JButton jb;
    
    public Deleteindicatorpanel()
    {
        super();
        setLayout(new MigLayout());
        addComponents();
    }

    private void addComponents() 
    {
        jl = new JLabel();
        jcb  = new JComboBox();
        FillComboBox();
        jb = new JButton();
        lbluitleg = new JLabel();
        
        lbluitleg.setText("Verwijderen van Indicator:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        jl.setText("Indicator");
        jl.setFont(new Font("verdana", Font.BOLD, 12));
        
        jb.setText("Verwijderen");
        jb.setFont(new Font("verdana", Font.BOLD, 12));
        add(lbluitleg,"wrap");
        add(jl,"split");
        add(jcb,"wrap");
        add(jb);
        jb.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttenaction(e);
                } catch (SQLException ex) {
                     System.out.println(ex.getMessage());
                }
            }  
        });}
   
     private void buttenaction(ActionEvent e) throws SQLException 
     {
          IndicatorenDAO pd = new IndicatorenDAO();
          String text = (String) jcb.getSelectedItem();
             
             pd.deleteIndicator(text);
             JOptionPane.showMessageDialog(this, "Record is succesvol deleted","Succes",JOptionPane.INFORMATION_MESSAGE);
             FillComboBox();
     }
         
     

    public void FillComboBox() 
    {
        IndicatorenDAO cdao = new IndicatorenDAO();
        jcb.removeAllItems();
        try {
        for( Indicatoren p : cdao.getAllIndicatoren())
        {
            jcb.addItem(p.getNaam());
        }
        }catch(Exception e)
        {
            System.out.println("FillComboBox mislukt!");
        }
    }
}
