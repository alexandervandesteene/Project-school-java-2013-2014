/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alexander
 */
public class Startpanel extends JPanel {

    private JLabel jlwelkom, jlgemaakt;

    public Startpanel() {
        super();
        try {
            //UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Startpanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Startpanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Startpanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Startpanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setLayout(new MigLayout());
        addComponents();
       
    }

    private void addComponents() {
        jlwelkom = new JLabel();
        jlgemaakt = new JLabel();

        jlwelkom.setText("Welkom");
        jlwelkom.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 60));
        jlgemaakt.setText("made by Maxim,Kevin,Steven,Alexander");
        jlgemaakt.setFont(new Font("verdana", Font.ITALIC + Font.BOLD, 12));

        add(jlwelkom, "gapleft 30,wrap");
        add(jlgemaakt,"dock south");


    }
}
