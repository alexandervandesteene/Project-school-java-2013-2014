/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.partims;

import DAO.PartimsDAO;
import domein.Partims;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alexander
 */
public class Showpartimpanel extends JPanel
{
    private JTable jt;
    private JButton jb;
    
    public Showpartimpanel()
    {
        super();
        this.setSize(500,300);
        addComponents();
    }

   private void addComponents() 
    {
        /*jt = new JTable();
         jt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));*/
        
            setLayout(new FlowLayout());
            
            String[] columnNames = {"patims id","naam","opleidingsonderdeelid"};
            
            Object [][] data = {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
            };
            
            jt = new JTable(data,columnNames);
            jt.setPreferredScrollableViewportSize(new Dimension(500,50));
            jt.setFillsViewportHeight(true);
            
            JScrollPane sp = new JScrollPane(jt);
            add(sp);
        //add(jt);
        
        jb = new JButton();
        jb.setText("load");
        add(jb);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            { buttonaction(e);
               
            }
        });}
   
    private void buttonaction(ActionEvent e) 
            {
                   PartimsDAO pd = new PartimsDAO();
                ArrayList<Partims> l = new ArrayList<Partims>();
                try
                {
                    for(Object em:(ArrayList)pd.getAllPartims())
                        l.add((Partims)em);
                } catch (SQLException ex) {
                   System.out.println(ex.getMessage());
                }
                
                DefaultTableModel amod = new DefaultTableModel(); //setting the column name
                Object[] tableColumnNames = new Object[3]; 

    

                tableColumnNames[0] = "partims ID";
                tableColumnNames[1] = "name";
                tableColumnNames[2] = "opleidingsonderdeel";
                

                amod.setColumnIdentifiers(tableColumnNames);
                

                Object[] objects = new Object[3];
                if (l.size() > 0)
                {
                    for (int i = 0; i < l.size(); i++)
                    { 
                    Partims hashmap = l.get(i);
                    objects[0]=hashmap.getId().toString();
                    
                    objects[1]=hashmap.getNaam().toString();
                    
                    objects[2]=hashmap.getOpleidingsonderdeelid().toString();
 
                    amod.addRow(objects);
                    }
                    this.jt.setModel(amod);

                    }   
            }
}
