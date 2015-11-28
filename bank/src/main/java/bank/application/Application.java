package bank.application;

import bank.api.services.IDomainService;
import bank.domain.services.impl.Service;
import bank.domain.valueobjects.impl.Data;
import bank.exceptions.*;
import bank.infrastructure.factories.BankDataFactory;
import bank.infrastructure.repositories.EventStoreRepository;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;

/**
 * Application
 * Call to the targeted domain
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Application implements IDomainService {

    private IDomainService domainService;

    public Application() throws AxisFault {
        this.domainService = new Service();
    }

    @Override
    public double getStateBankAccount(String creditcard) throws BankAccountNotFoundException {
        return this.domainService.getStateBankAccount(creditcard);
    }

    @Override
    public double getCurrencyChangeRate(String from, String to) throws RemoteException {
        return this.domainService.getCurrencyChangeRate(from, to);
    }

    @Override
    public boolean checkCreditCard(String creditcard, int limitmonth, int limityear, String owner, String CCV) throws CreditCardNotFoundException, WrongCreditCardDetailsException {
        return this.domainService.checkCreditCard(creditcard, limitmonth, limityear, owner, CCV);
    }

    @Override
    public boolean doTransaction(String creditcard, String currency, double amount) throws BankAccountNotFoundException, RemoteException, NotEnoughMoneyException, CurrencyChangeRateErrorException {

        double previousAmount = this.domainService.getStateBankAccount(creditcard);
        boolean state = this.domainService.doTransaction(creditcard, currency, amount);
        /*EventStoreRepository eventStoreRepository = new EventStoreRepository();

        Data data = new BankDataFactory().getData("doTransaction", amount, state, previousAmount, this.domainService.getStateBankAccount(creditcard));
        eventStoreRepository.sendEvent(data);*/

        return state;
    }
}
