package control;

import contract.DataRepository;
import contract.dto.AccountDetail;
import contract.dto.CheckingAccountDetail;
import contract.dto.CustomerDetail;
import contract.dto.CustomerSummary;
import contract.dto.EmployeeDetail;
import contract.dto.MoneyMarketAccountDetail;
import contract.dto.TimeDepositAccountDetail;
import contract.dto.TransactionDetail;
import entity.Account;
import entity.CheckingAccount;
import entity.Groups;
import entity.MoneyMarketAccount;
import entity.Person;
import entity.TimeDepositAccount;
import java.util.Collection;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

/**
 *
 * @author Thomas Kragsberger
 */
@Stateless
@DeclareRoles(
{
    "Customers", "Employees"
})
public class BankManager implements DataRepository
{

    @PersistenceContext(unitName = "PiggyBankBackEndPU")
    private EntityManager em;
//    private BankAsembler ba = new BankAsembler();

    public void persist(Object object)
    {
        em.persist(object);
    }

    @Override
    public Collection<AccountDetail> getAccountSummay()
    {
        Collection<Account> accounts = em.createNamedQuery("Account.findAll").getResultList();
        return BankAsembler.createAccountSummaries(accounts);
    }

    @Override
    @RolesAllowed("Employees")
    public Collection<CustomerSummary> getCustomers()
    {
        Collection<Person> customers = em.createQuery("Select p from Person p, in (p.groupsCollection) as g where g.groupName = 'Customers'").getResultList();

        return BankAsembler.createCustomerSummaries(customers);
    }

    @Override
    public AccountDetail getAccount(int id)
    {
        Query query = em.createNamedQuery("Account.findByAccountId");
        query.setParameter("accountId", id);
        Account account = (Account) query.getSingleResult();
        return BankAsembler.createAccountDetail(account);
    }

    @Override
    public Collection<TransactionDetail> getTransactions(int id)
    {
        Query query = em.createNamedQuery("Account.findByAccountId");
        query.setParameter("accountId", id);
        Account account = (Account) query.getSingleResult();
        return BankAsembler.createTransactionSummaries(account.getOutGoingTransactions());
    }

    @Override
    @RolesAllowed("Employees")
    public void saveCustomerDetail(CustomerDetail customer)
    {
        Person p = new Person(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPassword());
        Query query = em.createNamedQuery("Groups.findByGroupName");
        query.setParameter("groupName", "Customers");
        Groups group = (Groups) query.getSingleResult();
        p.addGroups(group);
        group.addPerson(p);

        em.persist(p);
    }

    @Override
    @RolesAllowed("Employees")
    public CustomerDetail getCustomerDetail(int id)
    {
        Query query = em.createNamedQuery("Person.findByPersonId");
        query.setParameter("personId", id);
        Person person = (Person) query.getSingleResult();
        return BankAsembler.createCustomerDetail(person);
    }

    @Override
    @RolesAllowed("Customers")
    public void transferMoney(int sendingAccount, int recievingAccount, double amount, String messageToSender, String messageToReciever)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("Employees")
    public void deposit(int accountId, double amount)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("Customers")
    public void withdraw(int accountId, double amount)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed("Customers")
    public CustomerDetail getOwner(String email)
    {
        Person person = em.find(Person.class, email);
        return BankAsembler.createCustomerDetail(person);
    }

    @Override
    public void saveAccount(AccountDetail account)
    {
        Account a = null;
        Person owner = em.find(Person.class, account.getOwner().getEmail());
        if (account instanceof CheckingAccountDetail)
        {
            a = new CheckingAccount();
            a.setOwner(owner);
        } else if (account instanceof MoneyMarketAccountDetail)
        {
            a = new MoneyMarketAccount();
            a.setOwner(owner);
        } else if (account instanceof TimeDepositAccountDetail)
        {
            a = new TimeDepositAccount();
            a.setOwner(owner);
        }
        em.persist(a);
    }

    @Override
    @RolesAllowed("Employees")
    public EmployeeDetail getEmployeeLogon(String email)
    {
        System.err.println("mail " + email);
        Person person = em.find(Person.class, email);
        return BankAsembler.createEmployeeDetail(person);
    }
}
