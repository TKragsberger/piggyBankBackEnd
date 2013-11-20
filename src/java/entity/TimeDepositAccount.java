package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thomas Kragsberger
 */
@Entity
@Table(name = "TIME_DEPOSIT_ACCOUNT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "TimeDepositAccount.findAll", query = "SELECT t FROM TimeDepositAccount t"),
    @NamedQuery(name = "TimeDepositAccount.findByAccountId", query = "SELECT t FROM TimeDepositAccount t WHERE t.accountId = :accountId"),
    @NamedQuery(name = "TimeDepositAccount.findByExpireDate", query = "SELECT t FROM TimeDepositAccount t WHERE t.expireDate = :expireDate")
})
public class TimeDepositAccount extends Account
{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.DATE)
    private Date expireDate;

    public TimeDepositAccount()
    {
    }

    public TimeDepositAccount(Integer accountId)
    {
        super(accountId);
    }

    public TimeDepositAccount(Integer accountId, Date expireDate)
    {
        super(accountId);
        this.expireDate = expireDate;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    @Override
    public String toString()
    {
        return "entity.TimeDepositAccount[ accountId=" + getAccountId() + " ]";
    }
    
}
