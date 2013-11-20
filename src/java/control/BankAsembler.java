package control;

import contract.dto.AccountDetail;
import contract.dto.CheckingAccountDetail;
import contract.dto.CustomerDetail;
import contract.dto.CustomerSummary;
import contract.dto.EmployeeDetail;
import contract.dto.MoneyMarketAccountDetail;
import contract.dto.TimeDepositAccountDetail;
import contract.dto.TransactionDetail;
import contract.dto.TransactionSummary;
import entity.Account;
import entity.CheckingAccount;
import entity.MoneyMarketAccount;
import entity.Person;
import entity.TimeDepositAccount;
import entity.Transaction;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Thomas Kragsberger
 */
public class BankAsembler
{
    public static CustomerSummary createCustomerSummary(Person customer)
    {
        CustomerSummary cs = new CustomerSummary(
                customer.getEmail(),
                customer.getFirstName(),
                customer.getLastName()
                );
//        cs.setCustomerId(customer.getPersonId());
        return cs;
    }
    
    public static Collection<CustomerSummary> createCustomerSummaries(Collection<Person> customers)
    {
        Collection<CustomerSummary> summaries = new ArrayList();
        for(Person customer : customers)
        {
            summaries.add(createCustomerSummary(customer));
        }
        return summaries;
    }
    
    public static CustomerDetail createCustomerDetail(Person customer)
    {
        CustomerDetail cd = new CustomerDetail(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getUserPassword());
//        cd.setAccounts(createAccountSummaries(customer.getAccountCollection()));
//        cd.setCustomerId(customer.getPersonId());
        return cd;
    }
    
    public static EmployeeDetail createEmployeeDetail(Person employee)
    {
        EmployeeDetail ed = new EmployeeDetail(employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getUserPassword());
//        ed.setEmployeeId(employee.getPersonId());
        return ed;
    }
    
    public static AccountDetail createAccountDetail(Account account)
    {
        Person customer = account.getOwner();
        CustomerDetail owner = new CustomerDetail(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getUserPassword());
        AccountDetail ad = null;
        if(account instanceof CheckingAccount)
        {
            ad = new CheckingAccountDetail(owner);
        }else if(account instanceof MoneyMarketAccount)
        {
            ad = new MoneyMarketAccountDetail(owner);
        }else if(account instanceof TimeDepositAccount)
        {
            ad = new TimeDepositAccountDetail(owner);
        }
        ad.setAccountId(account.getAccountId());
//        ad.setTransactions(createTransactionSummaries(account.getOutGoingTransactions()));
        return ad;
    }
    
    public static Collection<AccountDetail> createAccountSummaries(Collection<Account> accounts)
    {
        Collection<AccountDetail> summaries = new ArrayList();
        for(Account account : accounts)
        {
            summaries.add(createAccountDetail(account));
        }
        return summaries;
    }
    
    public static TransactionDetail createTransactionDetail(Transaction transaction)
    {
//        account.addToBalance(transaction.getAmmountChange());
        AccountDetail source = createAccountDetail(transaction.getSourceAccount());
        AccountDetail target = createAccountDetail(transaction.getTargetAccount());
        
        return new TransactionDetail(transaction.getAmmountChange(), source, target, transaction.getInfo());
    }
    
    
    public static Collection<TransactionDetail> createTransactionSummaries(Collection<Transaction> transactions)
    {
        Collection<TransactionDetail> summaries = new ArrayList();
        for(Transaction transaction : transactions)
        {
            summaries.add(createTransactionDetail(transaction));
        }
        return summaries;
    }
    
    public static TransactionSummary createTransactionSummary(Transaction transaction)
    {
        return new TransactionSummary();
    }
}
