/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.partims;

import DAO.PartimsDAO;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author alexander
 */
public class Deletepartimpanel extends JPanel
{
     private JLabel jl;
    private JTextField jt;
    private JButton jb;
    
    public Deletepartimpanel()
    {
        super();
        setLayout(new FlowLayout());
        addComponents();
    }

    private void addComponents() 
    {
        jl = new JLabel();
        jt = new JTextField();
        jb = new JButton();
        
        jl.setText("id");
        jt.setPreferredSize(new Dimension(200, 25));
        jb.setText("Verwijderen");
        add(jl);
        add(jt);
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
         PartimsDAO pd = new PartimsDAO();
         try
         {
         if(jt.getText().equals(""))
         {
            JOptionPane.showMessageDialog(this, "je record is leeg!","Error",JOptionPane.ERROR_MESSAGE); 
            
         }
         else
         {
             int id = Integer.parseInt(jt.getText());
             pd.deletePartims(id);
             JOptionPane.showMessageDialog(this, "Record is succesvol deleted","Succes",JOptionPane.INFORMATION_MESSAGE);
             jt.setText("");
         }
          }
          catch(NumberFormatException ex)
          {
             JOptionPane.showMessageDialog(this, "gelieve een getal integeven","Error",JOptionPane.ERROR_MESSAGE);
             jt.setText("");
          }
     }
}
