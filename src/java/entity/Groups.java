package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Thomas Kragsberger
 */
@Entity
@Table(name = "GROUPS")
@XmlRootElement
@NamedQueries(
        {
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g"),
    @NamedQuery(name = "Groups.findByGroupName", query = "SELECT g FROM Groups g WHERE g.groupName = :groupName"),
    @NamedQuery(name = "Groups.findByGroupDesc", query = "SELECT g FROM Groups g WHERE g.groupDesc = :groupDesc")
})
public class Groups implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GROUP_NAME")
    private String groupName;
    @Size(max = 200)
    @Column(name = "GROUP_DESC")
    private String groupDesc;
    @JoinTable(name = "PERSON_GROUP", joinColumns =
    {
        @JoinColumn(name = "GROUP_NAME", referencedColumnName = "GROUP_NAME")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL")
    })
    @ManyToMany
    private Collection<Person> personCollection;

    public Groups()
    {
    }

    public Groups(String groupName)
    {
        this.groupName = groupName;
    }

    public void addPerson(Person p)
    {
        if (personCollection == null)
        {
            personCollection = new ArrayList();
        }
        personCollection.add(p);
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroupDesc()
    {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc)
    {
        this.groupDesc = groupDesc;
    }

    @XmlTransient
    public Collection<Person> getPersonCollection()
    {
        return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection)
    {
        this.personCollection = personCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (groupName != null ? groupName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups))
        {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.groupName == null && other.groupName != null) || (this.groupName != null && !this.groupName.equals(other.groupName)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Groups[ groupName=" + groupName + " ]";
    }
}
