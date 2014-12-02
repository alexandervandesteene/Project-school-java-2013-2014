/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Competentie;

import DAO.CompetentiesDAO;
import domein.Competentie;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public class Showcompetentie extends JPanel
{
      
    private JTable tabel;
    private JButton btnLoad;
    private JLabel lbluitleg;
    
    public Showcompetentie()
    {
        super();
        setLayout(new MigLayout());
        this.setSize(500,300);
        addComponents();
    }

    private void addComponents() 
    {
        lbluitleg = new JLabel();
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        
        lbluitleg.setText("Toon alle Competentie:");
        add(lbluitleg,"wrap");
            Object [][] data = 
            {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
            };
            String [] columnNames = {"Competentie ID", "naam","Omschrijving"};
            
            tabel = new JTable(data,columnNames);
            tabel.setPreferredScrollableViewportSize(new Dimension(500,50));
            
            JScrollPane scrollpane = new JScrollPane(tabel);
            add(scrollpane,"wrap");
            scrollpane.setVisible(true);
        
            btnLoad = new JButton();
            btnLoad.setText("load");
            btnLoad.setFont(new Font("verdana", Font.BOLD, 12));
            add(btnLoad);
            
            btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            { buttonaction(e);}
            });
        }
    private void buttonaction(ActionEvent e) 
    {
        CompetentiesDAO od = new CompetentiesDAO();
        List<Competentie> l = new ArrayList<>();
        try
        {
            for(Object em:(ArrayList)od.getAllcompetenties())
                l.add((Competentie)em);
        } 
        catch (SQLException ex) {System.out.println(ex.getMessage());}
                
        DefaultTableModel amod = new DefaultTableModel(); //setting the column name
        Object[] tableColumnNames = new Object[3]; 

        tableColumnNames[0] = "Competentie ID";
        tableColumnNames[1] = "naam";
        tableColumnNames[2] = "Omschrijving";

        amod.setColumnIdentifiers(tableColumnNames);
       

        Object[] objects = new Object[3];
        
        if (l.size() > 0)
        {
            for (int i = 0; i < l.size(); i++)
            { 
                Competentie hashmap = l.get(i);
                objects[0]=hashmap.getID().toString(); 
                objects[1]=hashmap.getNaam().toString();
                objects[2]=hashmap.getOmschrijving().toString();
                amod.addRow(objects);
            }
            
            this.tabel.setModel(amod);
           
        }   
    }            
}
