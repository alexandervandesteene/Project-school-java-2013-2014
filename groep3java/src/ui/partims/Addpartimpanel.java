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
public class Addpartimpanel extends JPanel
{
    private JLabel jl;
    private JTextField jt;
    private JButton jb;
    public Addpartimpanel()
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
        
        jl.setText("naam");
        jt.setPreferredSize(new Dimension(200, 25));
        jb.setText("Toevoegen");
        add(jl);
        add(jt);
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

           
      

        });
                }         
        private void buttonaction(ActionEvent e) throws SQLException 
        {
              PartimsDAO pd=new PartimsDAO();
              if(jt.getText().equalsIgnoreCase(""))
              {
                JOptionPane.showMessageDialog(this, "het invoer veld is leeg!","Error",JOptionPane.ERROR_MESSAGE);  
              }
              else
              {
                pd.insertPartims(jt.getText());
                JOptionPane.showMessageDialog(this, "Record "+jt.getText()+ " is inserted into partims table!","Succes",JOptionPane.INFORMATION_MESSAGE);
                jt.setText("");
              }  
        }
}