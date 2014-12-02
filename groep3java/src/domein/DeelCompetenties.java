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
public class DeelCompetenties 
{
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    
    private Integer ID;
    private String Naam;
    private Integer compid;
    
     public DeelCompetenties() {}

    public DeelCompetenties(Integer id) 
    {
        this.ID = id;
    }

    public DeelCompetenties(Integer id, String naam) 
    {
        this.ID = id;
        this.Naam = naam;
    }
     public DeelCompetenties(Integer id, String naam,Integer comp) 
    {
        this.ID = id;
        this.Naam = naam;
        this.compid = comp;
    }
    
    
    public Integer getId() 
        {return ID;}

    public void setId(Integer id) 
    {
        Integer oldId = this.ID;
        this.ID = id;
        changeSupport.firePropertyChange("id", oldId, id);
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
    public int hashCode() 
    {
        int hash = 0;
        hash += (ID != null ? ID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeelCompetenties))
            return false;
        DeelCompetenties other = (DeelCompetenties) object;
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
     * @return the compid
     */
    public Integer getCompid() {
        return compid;
    }

    /**
     * @param compid the compid to set
     */
    public void setCompid(Integer compid) {
        this.compid = compid;
    }
}
