/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.indicatoren;

import DAO.*;
import domein.*;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Kevin
 */
public class IndicatorScorePanel extends JPanel{
    
    private JTable scoreTabel;
    private JComboBox cbxPartimList;
    private JButton btnUpdate;
    private PuntenTabelDAO indicatorPunten;
    private JScrollPane scrollPane;
    private Object[][] data;
    private Object[][] copyOfData;
    private Object huidigPartim = null;
    private JLabel lbluitleg;
    
    public IndicatorScorePanel()
    {
        super();
        setLayout(new MigLayout());
        this.setSize(900,400);
        cbxPartimList = new JComboBox();
        scoreTabel = new JTable();
        btnUpdate = new JButton("Update");
        indicatorPunten = new PuntenTabelDAO();
        FillComboBox();
        addComponents();
    }
    
    private void addComponents()
    {

        lbluitleg = new JLabel();
        lbluitleg.setText("Invoegen van Score:");
        lbluitleg.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 16));
        add(lbluitleg,"wrap");
        add(cbxPartimList,"wrap,center");        
        huidigPartim = cbxPartimList.getSelectedItem();

        add(cbxPartimList,"wrap,center");        
        huidigPartim = cbxPartimList.getSelectedItem();

        RefreshTable();
        scrollPane = new JScrollPane(scoreTabel);
        
        add(scrollPane,"wrap");
        Border border = BorderFactory.createEmptyBorder(10, 0, 0, 0);
        scrollPane.setBorder(border);
        add(btnUpdate,"center");
        btnUpdate.setFont(new Font("verdana", Font.BOLD, 12));
        cbxPartimList.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                scrollPane.getViewport().remove(scoreTabel);
                huidigPartim = cbxPartimList.getSelectedItem();
                RefreshTable();
                scrollPane.getViewport().add(scoreTabel); 
            }
        });
        btnUpdate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                UpdateTable();
                scrollPane.getViewport().remove(scoreTabel);
                huidigPartim = cbxPartimList.getSelectedItem();
                RefreshTable();
                scrollPane.getViewport().add(scoreTabel);
            }
        });
    }
    
    public void FillComboBox()
    {
        PartimsDAO pDao = new PartimsDAO();
        try
        {
            for(Partims p : pDao.getAllPartims())
                cbxPartimList.addItem(p);
        }
        catch(Exception e){System.out.println("FillComboBox mislukt!");}
    }
    
    private void RefreshTable()
    {   
        Partims p = (Partims)huidigPartim;
        data = indicatorPunten.GetColumnData(p);
        copyOfData = indicatorPunten.GetColumnData(p);
        Object[] columnNames = indicatorPunten.GetColumnNames(p).toArray();
        
        scoreTabel = new JTable(data,columnNames);
        scoreTabel.setPreferredScrollableViewportSize(new Dimension(850,75));
    }
    
    private void UpdateTable()
    {
        for (int i = 0; i < data.length; i++)
        {
            for (int j = 1; j < data[i].length-1; j++)
            {
                if(data[i][j] instanceof String)
                {
                    String score = (String)data[i][j];
                    int intScore = Integer.parseInt(score);
                    ((Student_Indicator)copyOfData[i][j]).update(intScore);
                }
            }
        }
    }
    
}
