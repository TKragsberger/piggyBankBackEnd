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
@Table(name = "MONEY_MARKET_ACCOUNT")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "MoneyMarketAccount.findAll", query = "SELECT m FROM MoneyMarketAccount m"),
    @NamedQuery(name = "MoneyMarketAccount.findByAccountId", query = "SELECT m FROM MoneyMarketAccount m WHERE m.accountId = :accountId")
})
public class MoneyMarketAccount extends Account
{
    private static final long serialVersionUID = 1L;

    public MoneyMarketAccount()
    {
    }

    public MoneyMarketAccount(Integer accountId)
    {
        super(accountId);
    }

    @Override
    public String toString()
    {
        return "entity.MoneyMarketAccount[ accountId=" + getAccountId() + " ]";
    }
    
}
