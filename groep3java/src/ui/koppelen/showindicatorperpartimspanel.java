/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.koppelen;

import DAO.IndicatorenDAO;
import DAO.PartimsDAO;
import domein.Indicatoren;
import domein.Partims;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class showindicatorperpartimspanel extends JPanel
{
   private JTable jt;
   private JComboBox jcb;
   private JScrollPane sp;
   private Object huidigpartim = null;
   private Indicatoren id;
   private int x;
   private PartimsDAO  pda;
   private JLabel lbluitleg;
    
    public showindicatorperpartimspanel() throws SQLException
    {
        super();
        setLayout(new MigLayout());
        
        
        jt = new JTable();
        
       
        addComponents();
      
    }
    
    private void addComponents() throws SQLException 
    {
        lbluitleg = new JLabel();
        
        lbluitleg.setText("Toon Indicator per Partim:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        add(lbluitleg,"wrap");
        jcb = new JComboBox();
        FillComboBox();
        add(jcb,"wrap,center");
        
        sp = new JScrollPane(jt);
        RefreshTable();
        
        
        add(sp);
        
        jcb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RefreshTable();
                } catch (SQLException ex) {
                    Logger.getLogger(showindicatorperpartimspanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void FillComboBox() {
          PartimsDAO cdao = new PartimsDAO();
        jcb.removeAllItems();
        try {
        for(Partims p : cdao.getAllPartims())
        {
            jcb.addItem(p.getNaam());
        }
        }catch(Exception e)
        {
            System.out.println("FillComboBox mislukt!");
        }
    }

    private void RefreshTable() throws SQLException 
    {   PartimsDAO cd = new PartimsDAO();
          String text1 = (String) jcb.getSelectedItem();
         for (Partims p : cd.getAllPartims())
              {
                 if(p.getNaam() == null ? text1 == null : p.getNaam().equals(text1))
                    {
                        x = p.getId();
                    }    
              }
         System.out.println(x+"");
      
        
        IndicatorenDAO pd = new IndicatorenDAO();
                List<Indicatoren> l = new ArrayList<>();
                try
                {
                    for(Object em:(ArrayList)pd.getIndicatorenPerPartims(x))
                        l.add((Indicatoren)em);
                } catch (SQLException ex) {
                   System.out.println(ex.getMessage());
                }
                
                DefaultTableModel amod = new DefaultTableModel(); //setting the column name
                Object[] tableColumnNames = new Object[4]; 

                sp.getViewport().remove(jt);

                tableColumnNames[0] = "indicator ID";
                tableColumnNames[1] = "name";
                tableColumnNames[2] = "DeelCompetentie_id";
                tableColumnNames[3] = "partims_id";

                amod.setColumnIdentifiers(tableColumnNames);
                
                sp.getViewport().add(jt);
                

                Object[] objects = new Object[4];
                if (l.size() > 0)
                {
                    for (int i = 0; i < l.size(); i++)
                    { 
                    Indicatoren hashmap = l.get(i);
                    objects[0]=hashmap.getId().toString();
                    
                    objects[1]=hashmap.getNaam().toString();
                    
                    objects[2] = hashmap.getDeelcomid().toString();
                    
                    objects[3]=hashmap.getPartimsId().toString();
 
                    amod.addRow(objects);
                    }
                    this.jt.setModel(amod);

                    } 
                else
                {
                     sp.getViewport().remove(jt);
                     JOptionPane.showMessageDialog(this, "Geen indicatoren gekoppeld aan dit partim","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }  

  
    
    }

    

   
    