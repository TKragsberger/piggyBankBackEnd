package entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import utils.PasswordDigestGenerator;

/**
 *
 * @author Thomas Kragsberger
 */
@Entity
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByPersonId", query = "SELECT p FROM Person p WHERE p.personId = :personId"),
    @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"),
    @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Person.findByUserPassword", query = "SELECT p FROM Person p WHERE p.userPassword = :userPassword")
})
public class Person implements Serializable
{
    private static final long serialVersionUID = 1L;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "PERSON_ID")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s1")
//    @SequenceGenerator(name = "s1", sequenceName = "PERSON_SEQ", initialValue = 1000, allocationSize = 1)
//    private int personId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 30)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 40)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @ManyToMany(mappedBy = "personCollection")
    private Collection<Groups> groupsCollection;
    @OneToMany(mappedBy = "owner")
    private Collection<Account> accountCollection;

    public Person()
    {
    }

    public Person(String email)
    {
        this.email = email;
    }

//    public Person(String email, int personId, String userPassword)
//    {
//        this.email = email;
//        this.personId = personId;
//        this.userPassword = userPassword;
//    }
//
    public Person(String email, String firstName, String lastName, String userPassword)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        try
        {
            this.userPassword = PasswordDigestGenerator.getEncoded(userPassword);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex)
        {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addGroups(Groups group)
    {
        if(groupsCollection == null)
        {
            groupsCollection = new ArrayList();
        }
        groupsCollection.add(group);
    }

//    public int getPersonId()
//    {
//        return personId;
//    }
//
//    public void setPersonId(int personId)
//    {
//        this.personId = personId;
//    }
//
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    @XmlTransient
    public Collection<Groups> getGroupsCollection()
    {
        return groupsCollection;
    }

    public void setGroupsCollection(Collection<Groups> groupsCollection)
    {
        this.groupsCollection = groupsCollection;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection()
    {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection)
    {
        this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person))
        {
            return false;
        }
        Person other = (Person) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Person[ email=" + email + " ]";
    }
    
}
