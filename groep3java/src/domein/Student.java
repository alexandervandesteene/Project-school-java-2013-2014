/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.beans.PropertyChangeSupport;

/**
 *
 * @author Kevin
 */
public class Student 
{
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String naam;
    private String adres;
    
    public Student() {}

    public Student(int id)
    {
        this.id = id;
    }

    public Student(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }
    
    public Student(int id, String naam, String adr) {
        this.id = id;
        this.naam = naam;
        adres = adr;
    }
 

    public Integer getID() 
        {return id;}

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNaam() 
        {return naam;}

    public void setNaam(String naam) 
    {
        String oldNaam = this.naam;
        this.naam = naam;
        changeSupport.firePropertyChange("naam", oldNaam, naam);
    }

    @Override
    public String toString() 
    {
        return naam;
    }
}