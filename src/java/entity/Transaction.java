package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thomas Kragsberger
 */
@Entity
@Table(name = "TRANSACTIONS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
})
public class Transaction implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;
    @Column(name = "TRANSACTIONS_DATE")
    @Temporal(TemporalType.DATE)
    private Date transactionsDate;
    @Column(name = "AMMOUNT_CHANGE")
    private Integer ammountChange;
    @Size(max = 100)
    @Column(name = "INFO")
    private String info;
    @JoinColumn(name = "SOURCE_ID", referencedColumnName = "ACCOUNT_ID")
    @ManyToOne(optional = false)
    private Account sourceAccount;
    @JoinColumn(name = "TARGET_ID", referencedColumnName = "ACCOUNT_ID")
    @ManyToOne(optional = false)
    private Account targetAccount;

    public Transaction()
    {
    }

    public Transaction(Integer transactionId)
    {
        this.transactionId = transactionId;
    }

    public Integer getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId)
    {
        this.transactionId = transactionId;
    }

    public Date getTransactionsDate()
    {
        return transactionsDate;
    }

    public void setTransactionsDate(Date transactionsDate)
    {
        this.transactionsDate = transactionsDate;
    }

    public Integer getAmmountChange()
    {
        return ammountChange;
    }

    public void setAmmountChange(Integer ammountChange)
    {
        this.ammountChange = ammountChange;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public Account getSourceAccount()
    {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount)
    {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount()
    {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount)
    {
        this.targetAccount = targetAccount;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction))
        {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Transactions[ transactionId=" + transactionId + " ]";
    }
    
}
