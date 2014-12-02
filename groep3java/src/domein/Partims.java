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
@Table(name = "partims", catalog = "hogeschool", schema = "")
@NamedQueries
({
    @NamedQuery(name = "Partims.findAll", query = "SELECT p FROM Partims p"),
    @NamedQuery(name = "Partims.findById", query = "SELECT p FROM Partims p WHERE p.id = :id"),
    @NamedQuery(name = "Partims.findByNaam", query = "SELECT p FROM Partims p WHERE p.naam = :naam"),
    @NamedQuery(name = "Partims.findByOpleidingsonderdeelid", query = "SELECT p FROM Partims p WHERE p.opleidingsonderdeelid = :opleidingsonderdeelid")
})

public class Partims implements Serializable 
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
    @Column(name = "opleidingsonderdeelid")
    private Integer opleidingsonderdeelid;

    public Partims() {}

    public Partims(Integer id) 
    {this.id = id;}

    public Partims(Integer id, String naam) 
    {
        this.id = id;
        this.naam = naam;
    }
     public Partims(Integer id, String naam,int pid) 
     {
        this.id = id;
        this.naam = naam;
        this.opleidingsonderdeelid = pid;
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

    public Integer getOpleidingsonderdeelid() 
        {return opleidingsonderdeelid;}

    public void setOpleidingsonderdeelid(Integer opleidingsonderdeelid) 
    {
        Integer oldOpleidingsonderdeelid = this.opleidingsonderdeelid;
        this.opleidingsonderdeelid = opleidingsonderdeelid;
        changeSupport.firePropertyChange("opleidingsonderdeelid", oldOpleidingsonderdeelid, opleidingsonderdeelid);
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
        if (!(object instanceof Partims))
            return false;
        Partims other = (Partims) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
            return false;
        return true;
    }

    @Override
    public String toString() 
        {return naam;}

    public void addPropertyChangeListener(PropertyChangeListener listener)
        {changeSupport.addPropertyChangeListener(listener);}

    public void removePropertyChangeListener(PropertyChangeListener listener) 
        {changeSupport.removePropertyChangeListener(listener);}
}