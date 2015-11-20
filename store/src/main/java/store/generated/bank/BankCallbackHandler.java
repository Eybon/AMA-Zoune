/**
 * BankCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.bank;


/**
 *  BankCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class BankCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public BankCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public BankCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for getCurrencyChangeRate method
     * override this method for handling normal response from getCurrencyChangeRate operation
     */
    public void receiveResultgetCurrencyChangeRate(
        store.generated.bank.BankStub.GetCurrencyChangeRateResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getCurrencyChangeRate operation
     */
    public void receiveErrorgetCurrencyChangeRate(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for doTransaction method
     * override this method for handling normal response from doTransaction operation
     */
    public void receiveResultdoTransaction(
        store.generated.bank.BankStub.DoTransactionResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from doTransaction operation
     */
    public void receiveErrordoTransaction(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getStateBankAccount method
     * override this method for handling normal response from getStateBankAccount operation
     */
    public void receiveResultgetStateBankAccount(
        store.generated.bank.BankStub.GetStateBankAccountResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getStateBankAccount operation
     */
    public void receiveErrorgetStateBankAccount(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for checkCreditCard method
     * override this method for handling normal response from checkCreditCard operation
     */
    public void receiveResultcheckCreditCard(
        store.generated.bank.BankStub.CheckCreditCardResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from checkCreditCard operation
     */
    public void receiveErrorcheckCreditCard(java.lang.Exception e) {
    }
}
