/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "studenten", catalog = "hogeschool", schema = "")
@NamedQueries({
    @NamedQuery(name = "Studenten.findAll", query = "SELECT s FROM Studenten s"),
    @NamedQuery(name = "Studenten.findById", query = "SELECT s FROM Studenten s WHERE s.id = :id"),
    @NamedQuery(name = "Studenten.findByNaam", query = "SELECT s FROM Studenten s WHERE s.naam = :naam")})
public class Studenten implements Serializable {

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

    public Studenten() {
    }

    public Studenten(Integer id) {
        this.id = id;
    }

    public Studenten(Integer id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        String oldNaam = this.naam;
        this.naam = naam;
        changeSupport.firePropertyChange("naam", oldNaam, naam);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof domein.Partims)) {
            return false;
        }
        domein.Studenten other = (domein.Studenten) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ui.Studenten[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}