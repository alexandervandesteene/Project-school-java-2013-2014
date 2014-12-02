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
public class Changepartimpanel extends JPanel 
{
   private JLabel jl,jl1;
    private JTextField jt,jt1;
    private JButton jb;
    public Changepartimpanel()
    {
        super();
        setLayout(new FlowLayout());
        addComponents();
    }

    private void addComponents() 
    {
        jl = new JLabel();
        jt = new JTextField();
        jl1 = new JLabel();
        jt1 = new JTextField();
        jb = new JButton();
        
        jl.setText("id");
        jt.setPreferredSize(new Dimension(200, 25));
        jl1.setText("naam");
        jt1.setPreferredSize(new Dimension(200, 25));
        jb.setText("wijzig");
        add(jl);
        add(jt);
        add(jl1);
        add(jt1);
        add(jb);
        
        jb.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) 
            {   try {
                    buttonaction(e);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
               
            }

           

        });}

        private void buttonaction(ActionEvent e) throws SQLException 
        {
           PartimsDAO pd=new PartimsDAO();
           try
           {
           if(jt.getText().equals("") || jt1.getText().equals(""))
           {
              JOptionPane.showMessageDialog(this, "één van de invoer velden is leeg!","Error",JOptionPane.ERROR_MESSAGE);  
           }
           else
           {
                int id = Integer.parseInt(jt.getText());
                JOptionPane.showMessageDialog(this, "Record changed succs!","Succes",JOptionPane.INFORMATION_MESSAGE);
                pd.changePartims(id, jt1.getText());
                jt.setText("");
                jt1.setText(""); 
           }
            }
          catch(NumberFormatException ex)
          {
             JOptionPane.showMessageDialog(this, "gelieve een getal integeven","Error",JOptionPane.ERROR_MESSAGE);
             jt.setText("");
             jt1.setText("");
          }
        }   
}
