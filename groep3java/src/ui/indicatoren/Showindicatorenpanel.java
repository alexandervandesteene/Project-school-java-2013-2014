/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.indicatoren;

import DAO.IndicatorenDAO;
import domein.Indicatoren;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class Showindicatorenpanel  extends JPanel
{

    
    private JLabel lbluitleg;

    private JTable tabel;
    private JButton btnLoad;

    
    public Showindicatorenpanel()
    {
        super();
        this.setSize(500,300);
        setLayout(new MigLayout());
        addComponents();
    }

   private void addComponents() 
    {

            lbluitleg = new JLabel();
        
        lbluitleg.setText("Toon alle Indicatoren:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        add(lbluitleg,"wrap");

        String[] columnNames = {"indicator id","naam","Deelcompetentie id","partims id"};
        Object [][] data =
        {
            {null, null,null,null},
            {null, null,null,null},
            {null, null,null,null},
            {null, null,null,null},
        };
            
        tabel = new JTable(data,columnNames); 
        tabel.setPreferredScrollableViewportSize(new Dimension(500,50));   
        tabel.setFillsViewportHeight(true);

            
        JScrollPane scrollpane = new JScrollPane(tabel);
        add(scrollpane,"wrap");
        
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
        IndicatorenDAO pd = new IndicatorenDAO();       
        List<Indicatoren> l = new ArrayList<>();     
        try       
        {
            for(Object em:(ArrayList)pd.getAllIndicatoren())
                l.add((Indicatoren)em);
        }
        catch (SQLException ex) {System.out.println(ex.getMessage());}
       
        DefaultTableModel amod = new DefaultTableModel();
        Object[] tableColumnNames = new Object[4]; 

        tableColumnNames[0] = "indicator ID";
        tableColumnNames[1] = "name";
        tableColumnNames[2] = "DeelCompetentie_id";
        tableColumnNames[3] = "partims_id";

        amod.setColumnIdentifiers(tableColumnNames);

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
            this.tabel.setModel(amod);

        }  
    }  
}
