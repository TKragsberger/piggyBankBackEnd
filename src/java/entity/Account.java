package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "ACCOUNT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountId", query = "SELECT a FROM Account a WHERE a.accountId = :accountId"),
    @NamedQuery(name = "Account.findByAmount", query = "SELECT a FROM Account a WHERE a.amount = :amount")
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Account implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    @Column(name = "AMOUNT")
    private Integer amount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sourceAccount")
    private Collection<Transaction> outGoingTransactions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "targetAccount")
    private Collection<Transaction> incommingTransactions;
    
    @JoinColumn(name = "OWNER", referencedColumnName = "PERSON_ID")
    @ManyToOne
    private Person owner;

    public Account()
    {
    }

    public Account(Integer accountId)
    {
        this.accountId = accountId;
    }

    public Integer getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Integer accountId)
    {
        this.accountId = accountId;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    @XmlTransient
    public Collection<Transaction> getOutGoingTransactions()
    {
        return outGoingTransactions;
    }

    public void setOutGoingTransactions(Collection<Transaction> transactionsCollection)
    {
        this.outGoingTransactions = transactionsCollection;
    }

    @XmlTransient
    public Collection<Transaction> getIncommingTransactions()
    {
        return incommingTransactions;
    }

    public void setIncommingTransactions(Collection<Transaction> transactionsCollection1)
    {
        this.incommingTransactions = transactionsCollection1;
    }

    public Person getOwner()
    {
        return owner;
    }

    public void setOwner(Person owner)
    {
        this.owner = owner;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account))
        {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Account[ accountId=" + accountId + " ]";
    }
    
}
