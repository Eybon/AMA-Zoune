/**
 * BankCreditCardNotFoundExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.bank;

public class BankCreditCardNotFoundExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1448480299922L;
    private store.generated.bank.BankStub.BankCreditCardNotFoundException faultMessage;

    public BankCreditCardNotFoundExceptionException() {
        super("BankCreditCardNotFoundExceptionException");
    }

    public BankCreditCardNotFoundExceptionException(java.lang.String s) {
        super(s);
    }

    public BankCreditCardNotFoundExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public BankCreditCardNotFoundExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        store.generated.bank.BankStub.BankCreditCardNotFoundException msg) {
        faultMessage = msg;
    }

    public store.generated.bank.BankStub.BankCreditCardNotFoundException getFaultMessage() {
        return faultMessage;
    }
}
