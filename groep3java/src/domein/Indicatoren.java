/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;


import java.beans.*;
import javax.persistence.*;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 *
 * @author alexander
 */
@Entity
@Table(name = "indicatoren", catalog = "hogeschool", schema = "")
@NamedQueries
({
    @NamedQuery(name = "Indicatoren.findAll", query = "SELECT i FROM Indicatoren i"),
    @NamedQuery(name = "Indicatoren.findById", query = "SELECT i FROM Indicatoren i WHERE i.id = :id"),
    @NamedQuery(name = "Indicatoren.findByNaam", query = "SELECT i FROM Indicatoren i WHERE i.naam = :naam"),
    @NamedQuery(name = "Indicatoren.findByPartimsId", query = "SELECT i FROM Indicatoren i WHERE i.partimsId = :partimsId")
})
public class Indicatoren implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer ID;
    @Basic(optional = false)
    @Column(name = "naam")
    private String Naam;
    @Column(name = "partims_id")
    private Integer PartimID;
    private Integer Deelcomid;

    public Indicatoren() {}

    public Indicatoren(Integer id) 
    {
        this.ID = id;
    }

    public Indicatoren(Integer id, String naam) 
    {
        this.ID = id;
        this.Naam = naam;
    }
    public Indicatoren(int id,String naam,int partimsid)
    {
        this.ID = id;
        this.Naam = naam;
        this.PartimID = partimsid;
    }
     public Indicatoren(int id,String naam,int deelcomp,int partimsid)
    {
        this.ID = id;
        this.Naam = naam;
        this.PartimID = partimsid;
        this.Deelcomid = deelcomp;
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

    public Integer getPartimsId() 
        {return PartimID;}

    public void setPartimsId(Integer partimsId) 
    {
        Integer oldPartimsId = this.PartimID;
        this.PartimID = partimsId;
        changeSupport.firePropertyChange("partimsId", oldPartimsId, partimsId);
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
        if (!(object instanceof Indicatoren))
            return false;
        Indicatoren other = (Indicatoren) object;
        if ((this.ID == null && other.ID != null) || (this.ID != null && !this.ID.equals(other.ID)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ui.Indicatoren[ id=" + ID + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    /**
     * @return the Deelcomid
     */
    public Integer getDeelcomid() {
        return Deelcomid;
    }

    /**
     * @param Deelcomid the Deelcomid to set
     */
    public void setDeelcomid(Integer Deelcomid) {
        this.Deelcomid = Deelcomid;
    }
    
}
