/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.deelCompetentie;

import DAO.deelCompetentiesDAO;
import domein.DeelCompetenties;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class ShowDeelCompetentie extends JPanel
{
    private JTable tabel;
    private JButton btnLoad;
    private JLabel lbluitleg;
    
    public ShowDeelCompetentie()
    {
        super();
        setLayout(new MigLayout());
        this.setSize(500,300);
        addComponents();
    }

    private void addComponents() 
    {
        lbluitleg = new JLabel();
        
        lbluitleg.setText("Toon alle Deelcompetentie:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        add(lbluitleg,"wrap");
        Object [][] data =
        {
            {null, null, null},
            {null, null, null},
            {null,  null, null},
            {null,  null, null},
        };
        String [] columnNames = {"deelcompetentie ID", "naam","compitentieID"};
            
        tabel = new JTable(data,columnNames);
        tabel.setPreferredScrollableViewportSize(new Dimension(500,50));
        JScrollPane scrollpane = new JScrollPane(tabel);
        
        add(scrollpane,"wrap");
        scrollpane.setVisible(true);
        
        btnLoad = new JButton();
        btnLoad.setText("load");
        btnLoad.setFont(new Font("verdana", Font.BOLD, 12));
        add(btnLoad);
        btnLoad.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            { buttonaction(e);}
        });
    }
    private void buttonaction(ActionEvent e) 
    {
         deelCompetentiesDAO od = new deelCompetentiesDAO();
         List<DeelCompetenties> l = new ArrayList<>();
         try
         {
            for(Object em:(ArrayList)od.getAlldeelCompetentie())
                l.add((DeelCompetenties)em);
         }
         catch (SQLException ex) {System.out.println(ex.getMessage());}
                
         DefaultTableModel defaultTabelModel = new DefaultTableModel(); //setting the column name
         Object[] tableColumnNames = new Object[3]; 
         
         tableColumnNames[0] = "deelcompetentie ID";
         tableColumnNames[1] = "naam";
         tableColumnNames[2] = "Competentie Id";

         defaultTabelModel.setColumnIdentifiers(tableColumnNames);

         Object[] objects = new Object[3];
         if (l.size() > 0)
         {
            for (int i = 0; i < l.size(); i++)
            { 
                DeelCompetenties hashmap = l.get(i);
                objects[0]=hashmap.getId().toString();    
                objects[1]=hashmap.getNaam().toString();
                objects[2]=hashmap.getCompid().toString();
                
                defaultTabelModel.addRow(objects);
            }
            tabel.setModel(defaultTabelModel);
         }   
    }              
}
