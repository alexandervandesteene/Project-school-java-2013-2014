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
@Table(name = "opleidingen", catalog = "javaproject", schema = "")
@NamedQueries
({
    @NamedQuery(name = "Opleidingen.findAll", query = "SELECT o FROM Opleidingen o"),
    @NamedQuery(name = "Opleidingen.findById", query = "SELECT o FROM Opleidingen o WHERE o.id = :id"),
    @NamedQuery(name = "Opleidingen.findByNaam", query = "SELECT o FROM Opleidingen o WHERE o.naam = :naam")
})
public class Opleidingen implements Serializable {
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

    public Opleidingen() {}

    public Opleidingen(Integer id)
    {
        this.id = id;
    }

    public Opleidingen(Integer id, String naam)
    {
        this.id = id;
        this.naam = naam;
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
        if (!(object instanceof Opleidingen))
            return false;
        Opleidingen other = (Opleidingen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ui.Opleidingen[ id=" + id + " ]";
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
