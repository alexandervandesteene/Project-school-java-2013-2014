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
@Table(name = "opleidingsonderdeel", catalog = "hogeschool", schema = "")
@NamedQueries
({
    @NamedQuery(name = "Opleidingsonderdeel.findAll", query = "SELECT o FROM Opleidingsonderdeel o"),
    @NamedQuery(name = "Opleidingsonderdeel.findById", query = "SELECT o FROM Opleidingsonderdeel o WHERE o.id = :id"),
    @NamedQuery(name = "Opleidingsonderdeel.findByNaam", query = "SELECT o FROM Opleidingsonderdeel o WHERE o.naam = :naam"),
    @NamedQuery(name = "Opleidingsonderdeel.findByOpleidingsid", query = "SELECT o FROM Opleidingsonderdeel o WHERE o.opleidingsid = :opleidingsid")
})

public class Opleidingsonderdeel implements Serializable 
{
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "naam")
    private String naam;
    @Column(name = "opleidingsid")
    private Integer opleidingsid;

    public Opleidingsonderdeel() {}

    public Opleidingsonderdeel(Integer id)
    {this.id = id;}

    public Opleidingsonderdeel(Integer id, String naam) 
    {
        this.id = id;
        this.naam = naam;
    }
    public Opleidingsonderdeel(Integer id, String naam,int pid) 
    {
        this.id = id;
        this.naam = naam;
        this.opleidingsid = pid;
    }

    public Integer getId() 
        {return id;}

    public void setId(Integer id) 
    {
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

    public Integer getOpleidingsid() 
        {return opleidingsid;}

    public void setOpleidingsid(Integer opleidingsid) 
    {
        Integer oldOpleidingsid = this.opleidingsid;
        this.opleidingsid = opleidingsid;
        changeSupport.firePropertyChange("opleidingsid", oldOpleidingsid, opleidingsid);
    }

    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opleidingsonderdeel))
            return false;
        Opleidingsonderdeel other = (Opleidingsonderdeel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ui.Opleidingsonderdeel[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) 
    {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) 
    {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
