package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thomas Kragsberger
 */
@Entity
@Table(name = "CHECKING_ACCOUNT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "CheckingAccount.findAll", query = "SELECT c FROM CheckingAccount c"),
    @NamedQuery(name = "CheckingAccount.findByAccountId", query = "SELECT c FROM CheckingAccount c WHERE c.accountId = :accountId")
})
public class CheckingAccount extends Account
{
    private static final long serialVersionUID = 1L;

    public CheckingAccount()
    {
    }

    public CheckingAccount(Integer accountId)
    {
        super(accountId);
    }

    @Override
    public String toString()
    {
        return "entity.CheckingAccount[ accountId=" + getAccountId() + " ]";
    }
    
}
