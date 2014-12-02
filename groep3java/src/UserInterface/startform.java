package UserInterface;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import ui.Competentie.*;
import ui.deelCompetentie.*;
import ui.indicatoren.*;
import ui.koppelen.*;
import ui.studenten.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/**
 *
 * @author Maxim
 */
public class startform extends JFrame
{                
      Startpanel startpanel = new Startpanel();
    //<editor-fold desc="Menu">
        JMenuBar menuBar = new JMenuBar();
        //JMenu delen
        JMenu Competentie = new JMenu("Competenties");
        JMenu Deelcompetentie = new JMenu("Deelcompetentie");
        JMenu Indicatoren = new JMenu("Indicatoren");
        JMenu Score = new JMenu("Score");
        JMenu Overzicht = new JMenu("Overzicht");
        // JMenuItems voor JMenu Competenties
        JMenuItem compToon = new JMenuItem("Toon alle competenties");
        JMenuItem compDelete = new JMenuItem("Verwijderen competentie");
        JMenuItem compToevoegen = new JMenuItem("Toevoegen competentie");
        JMenuItem compWijzigen = new JMenuItem("Wijzigen competentie");
        JMenuItem compid = new JMenuItem("Toon deelcompetentie per competentie");
        // JMenuItems voor JMenu Deelcompetenties
        JMenuItem deelcompToon = new JMenuItem("Toon alle deelcompetenties");
        JMenuItem deelcompDelete = new JMenuItem("Verwijderen deelcompetentie");
        JMenuItem deelcompToevoegen = new JMenuItem("Toevoegen deelcompetentie");
        JMenuItem deelcompWijzigen = new JMenuItem("Wijzigen deelcompetentie");
        JMenuItem deelcompid = new JMenuItem("Toon indicatoren per Deelcompetentie");
        // JMenuItems voor JMenu Indicatoren
        JMenuItem indToon = new JMenuItem("Toon alle indicatoren");
        JMenuItem indDelete = new JMenuItem("Verwijderen indicator");
        JMenuItem indToevoegen = new JMenuItem("Toevoegen indicator");
        JMenuItem indWijzigen = new JMenuItem("Wijzigen indicator");
        JMenuItem indKoppel = new JMenuItem("Koppelen van indicator");
        JMenuItem indMetPartims = new JMenuItem("Toon indicator met partim");
        
        //JMenuItems voor JMenu Score
        JMenuItem scoreUpdate = new JMenuItem("Score updaten");
        //JMenuItems voor JMenu Overzicht
        JMenuItem overzichtStudent = new JMenuItem("Algemeen overzicht vorderingen");
        JMenuItem scoreindicator = new JMenuItem("score indicatoren per student");
        
    //</editor-fold>
    
    //<editor-fold desc="panels">
        //Competentie
        Addcompetentie addcompetentie = new Addcompetentie();
        Changecompetentie changecompetentie = new Changecompetentie();
        Deletecompetentie deletecompetentie = new Deletecompetentie();
        Showcompetentie showcompetentie = new Showcompetentie();
        ShowDeelcompperCompPanel showDeelcompperCompPanel = new ShowDeelcompperCompPanel();
        //indicatoren
        Addindicatorpanel addindicatorpanel = new Addindicatorpanel();
        Showindicatorenpanel showindicatorenpanel = new Showindicatorenpanel();
        Deleteindicatorpanel deleteindicatorpanel = new Deleteindicatorpanel();
        Changeindicatorpanel changeindicatorpanel = new Changeindicatorpanel();
        addpartimidpanel addpartimidPanel = new addpartimidpanel();
        showindicatorperpartimspanel showindicatorPerPartimPanel;
        //deelcompetentie
        ShowDeelCompetentie showDeelCompetentie= new ShowDeelCompetentie();
        AddDeelCompetentie addDeelCompetentie = new AddDeelCompetentie();
        DeleteDeelCompetentie deleteDeelCompetentie = new DeleteDeelCompetentie();
        ChangedeelCompetentie changedeelCompetentie = new ChangedeelCompetentie();
        ShowindicatorperdeelcompPanel showindicatorperdeelcompPanel = new ShowindicatorperdeelcompPanel();
        //Score
        IndicatorScorePanel scorePanel = new IndicatorScorePanel();
        //Overzicht
        ShowStudentenPanel showStudentenPanel = new ShowStudentenPanel();
        StudentenIndicatorenPanel studentenIndicatorenPanel = new StudentenIndicatorenPanel();
        
    //</editor-fold>
    
    public startform() throws SQLException
    {
          try {
              //UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
              UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(startform.class.getName()).log(Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
              Logger.getLogger(startform.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
              Logger.getLogger(startform.class.getName()).log(Level.SEVERE, null, ex);
          } catch (UnsupportedLookAndFeelException ex) {
              Logger.getLogger(startform.class.getName()).log(Level.SEVERE, null, ex);
          }
        this.showindicatorPerPartimPanel = new showindicatorperpartimspanel();
        setTitle("Java project");
    
        setLocationRelativeTo(null);
        
        try 
        {
            SetAfbeelding();
            this.showindicatorPerPartimPanel = new showindicatorperpartimspanel();
            SetMenu();
            acties();
        } 
        catch (Exception e) {System.out.println("ERROR");}
        
        add(startpanel);
        pack();
         
        setVisible(true);
        //setSize(600,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
    }

    private void SetAfbeelding() throws IOException 
    {
        URL path = getClass().getClassLoader().getResource("Resource/howest.png");
        Image logo = ImageIO.read(path);
        setIconImage(logo);
    }
    private void SetMenu()
    {
        setJMenuBar(menuBar);
        //JMenu toevoegen aan menubar
        menuBar.add(Competentie);
        menuBar.add(Deelcompetentie);
        menuBar.add(Indicatoren);
        menuBar.add(Score);
        menuBar.add(Overzicht);
        //JMenuItems toevoegen aan hun Competentie
        Competentie.add(compToon);
        Competentie.add(compToevoegen);
        Competentie.add(compDelete);
        Competentie.add(compWijzigen);
        Competentie.add(compid);
        //JMenuItems toevoegen aan Deelcompetentie
        Deelcompetentie.add(deelcompToon);
        Deelcompetentie.add(deelcompToevoegen);
        Deelcompetentie.add(deelcompDelete);
        Deelcompetentie.add(deelcompWijzigen);
        Deelcompetentie.add(deelcompid);
        //JMenuItems toevoegen aan Indicatoren
        Indicatoren.add(indToon);
        Indicatoren.add(indToevoegen);
        Indicatoren.add(indDelete);
        Indicatoren.add(indWijzigen);
        Indicatoren.add(indKoppel);
        Indicatoren.add(indMetPartims);
        //JMenuItems toevoegen aan Score
        Score.add(scoreUpdate);
        //JMenuItems toevoegen aan Overzicht
        Overzicht.add(overzichtStudent);
        Overzicht.add(scoreindicator);
     
    }
    private void acties()
    {
        //Competenties
        compToon.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(showcompetentie);
                pack();
                validate();
            }
        });
        compDelete.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(deletecompetentie);
                deletecompetentie.FillComboBox();
                pack();
                validate();
            }

        });
        compToevoegen.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(addcompetentie);
                pack();
                validate();
            }

        });
        compWijzigen.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(changecompetentie);
                changecompetentie.FillComboBox();
                pack();
                validate();
            }

        });
        compid.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(showDeelcompperCompPanel);
                showDeelcompperCompPanel.FillComboBox();
                pack();
                validate();
            }

        });
        //deelcompetenties
        deelcompToon.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(showDeelCompetentie);
                pack();
                validate();
            }

        });
        deelcompDelete.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(deleteDeelCompetentie);
                deleteDeelCompetentie.fillcombobox();
                pack();
                validate();
            }

        });
        deelcompToevoegen.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(addDeelCompetentie);
                addDeelCompetentie.fillcombobox();               
                pack();
                validate();
            }

        });
        deelcompWijzigen.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(changedeelCompetentie);
                changedeelCompetentie.fillCbxOudeNaam();
                changedeelCompetentie.filCbxCompetentie();
                pack();
                validate();
            }

        });
         deelcompid.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(showindicatorperdeelcompPanel);
                showindicatorperdeelcompPanel.FillComboBox();
                pack();
                validate();
            }

        });
        
        //indicatoren
        indToon.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setContentPane(showindicatorenpanel);
                pack();
                validate();
            }

        });
        indDelete.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                setContentPane(deleteindicatorpanel);
                deleteindicatorpanel.FillComboBox();
                pack();
                validate();
            }

        });
        indToevoegen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                setContentPane(addindicatorpanel);
                addindicatorpanel.FillCbxDeelcompetentie();
                addindicatorpanel.FillCbxPartim();
                pack();
                validate();
            }

        });
        indWijzigen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                setContentPane(changeindicatorpanel);
                changeindicatorpanel.FillCbxID();
                changeindicatorpanel.FillCbxDeelCompetentie();
                changeindicatorpanel.FillCbxParim();
                pack();
                validate();
            }

        });
        indKoppel.addActionListener(new ActionListener() 
        {
          @Override
          public void actionPerformed(ActionEvent arg0)
          {
                setContentPane(addpartimidPanel);
                addpartimidPanel.fillCbxIndicator();
                addpartimidPanel.fillCbxPartim();
                pack();
                validate();
            }
        });
        indMetPartims.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent arg0)
          {
                setContentPane(showindicatorPerPartimPanel);
                pack();
                validate();
          }
        });
        //Score
        scoreUpdate.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setContentPane(scorePanel);
                scorePanel.FillComboBox();
                pack();
                setPreferredSize(new Dimension(900,220));
                validate();
            }
        });
        //Overzicht
        overzichtStudent.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setContentPane(showStudentenPanel);
                pack();
                validate();
            }
        });
           //Overzicht
        scoreindicator.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setContentPane(studentenIndicatorenPanel);
                pack();
                setSize(new Dimension(500,500));
                validate();
            }
        });
  
        
        
    }
}
