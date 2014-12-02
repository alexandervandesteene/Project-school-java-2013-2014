/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.koppelen;

import DAO.CompetentiesDAO;
import DAO.IndicatorenDAO;
import DAO.PartimsDAO;
import DAO.deelCompetentiesDAO;
import domein.Competentie;
import domein.DeelCompetenties;
import domein.Indicatoren;
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
public class ShowDeelcompperCompPanel extends JPanel
{
     private JTable jt;
   private JComboBox jcb;
   private JScrollPane sp;
   private Object huidigpartim = null;
   private Indicatoren id;
   private int x;
   private PartimsDAO  pda;
   private JLabel lbluitleg;
    
    public ShowDeelcompperCompPanel() throws SQLException
    {
        super();
        setLayout(new MigLayout());
        
        
        jt = new JTable();
        
       
        addComponents();
      
    }
    
    private void addComponents() throws SQLException 
    {
        lbluitleg = new JLabel();
        
        lbluitleg.setText("Toon deelcompetentie per competentie:");
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

    public void FillComboBox() {
        CompetentiesDAO cdao = new CompetentiesDAO();
        jcb.removeAllItems();
        try {
        for(Competentie p : cdao.getAllcompetenties())
        {
            jcb.addItem(p.getNaam());
        }
        }catch(Exception e)
        {
            System.out.println("FillComboBox mislukt!");
        }
    }

    private void RefreshTable() throws SQLException 
    {   CompetentiesDAO cd = new CompetentiesDAO();
          String text1 = (String) jcb.getSelectedItem();
         for (Competentie p : cd.getAllcompetenties())
              {
                 if(p.getNaam() == null ? text1 == null : p.getNaam().equals(text1))
                    {
                        x = p.getID();
                    }    
              }
         System.out.println(x+"");
      
        
        deelCompetentiesDAO pd = new deelCompetentiesDAO();
                List<DeelCompetenties> l = new ArrayList<>();
                try
                {
                    for(Object em:(ArrayList)pd.getDeelcompPercomp(x))
                        l.add((DeelCompetenties)em);
                } catch (SQLException ex) {
                   System.out.println(ex.getMessage());
                }
                
                DefaultTableModel amod = new DefaultTableModel(); //setting the column name
                Object[] tableColumnNames = new Object[3]; 

                sp.getViewport().remove(jt);

                tableColumnNames[0] = "Deelcomp ID";
                tableColumnNames[1] = "name";
                tableColumnNames[2] = "Competentie_id";
                
                amod.setColumnIdentifiers(tableColumnNames);
                
                sp.getViewport().add(jt);
                

                Object[] objects = new Object[3];
                if (l.size() > 0)
                {
                    for (int i = 0; i < l.size(); i++)
                    { 
                    DeelCompetenties hashmap = l.get(i);
                    objects[0]=hashmap.getId().toString();
                    
                    objects[1]=hashmap.getNaam().toString();
                    
                    objects[2] = hashmap.getCompid().toString();
                    
                    
 
                    amod.addRow(objects);
                    }
                    this.jt.setModel(amod);

                    } 
                else
                {
                     sp.getViewport().remove(jt);
                     JOptionPane.showMessageDialog(this, "Geen deelcompetentie gekoppeld aan deze competentie","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }  

}
