/**
 * BankWrongCreditCardDetailsExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.bank;

public class BankWrongCreditCardDetailsExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1448480299947L;
    private store.generated.bank.BankStub.BankWrongCreditCardDetailsException faultMessage;

    public BankWrongCreditCardDetailsExceptionException() {
        super("BankWrongCreditCardDetailsExceptionException");
    }

    public BankWrongCreditCardDetailsExceptionException(java.lang.String s) {
        super(s);
    }

    public BankWrongCreditCardDetailsExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public BankWrongCreditCardDetailsExceptionException(
        java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        store.generated.bank.BankStub.BankWrongCreditCardDetailsException msg) {
        faultMessage = msg;
    }

    public store.generated.bank.BankStub.BankWrongCreditCardDetailsException getFaultMessage() {
        return faultMessage;
    }
}
