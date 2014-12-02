/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.beans.PropertyChangeSupport;

/**
 *
 * @author alexander
 */
public class Competentie 
{
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    
    private Integer ID;
    private String Naam;
    private String omschrijving;
    
    public Competentie() {}

    public Competentie(Integer id)
    {
        this.ID = id;
    }

    public Competentie(Integer id, String naam) {
        this.ID = id;
        this.Naam = naam;
    }
    public Competentie(Integer id, String naam,String omschrijving) {
        this.ID = id;
        this.Naam = naam;
        this.omschrijving = omschrijving;
    }
 
 

    public Integer getID() 
        {return ID;}

    public void setID(Integer ID) 
    {
        Integer oldId = this.ID;
        this.ID = ID;
        changeSupport.firePropertyChange("id", oldId, ID);
    }

    public String getNaam() 
        {return Naam;}

    public void setNaam(String naam) 
    {
        String oldNaam = this.Naam;
        this.Naam = naam;
        changeSupport.firePropertyChange("naam", oldNaam, naam);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ID != null ? ID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the ID fields are not set
        if (!(object instanceof Competentie))
            return false;
        Competentie other = (Competentie) object;
        if ((this.ID == null && other.ID != null) || (this.ID != null && !this.ID.equals(other.ID)))
            return false;
        return true;
    }

    @Override
    public String toString() 
    {
        return "ui.Indicatoren[ id=" + ID + " ]";
    }

    /**
     * @return the omschrijving
     */
    public String getOmschrijving() {
        return omschrijving;
    }

    /**
     * @param omschrijving the omschrijving to set
     */
    public void setOmschrijving(String omschrijving) {
        String oldomschrijning = this.omschrijving;
        this.Naam = omschrijving;
        changeSupport.firePropertyChange("naam", oldomschrijning, omschrijving);
        this.omschrijving = omschrijving;
    }
    
}
