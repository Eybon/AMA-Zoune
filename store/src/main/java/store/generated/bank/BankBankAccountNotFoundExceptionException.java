/**
 * BankBankAccountNotFoundExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.bank;

public class BankBankAccountNotFoundExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1448480299953L;
    private store.generated.bank.BankStub.BankBankAccountNotFoundException faultMessage;

    public BankBankAccountNotFoundExceptionException() {
        super("BankBankAccountNotFoundExceptionException");
    }

    public BankBankAccountNotFoundExceptionException(java.lang.String s) {
        super(s);
    }

    public BankBankAccountNotFoundExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public BankBankAccountNotFoundExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        store.generated.bank.BankStub.BankBankAccountNotFoundException msg) {
        faultMessage = msg;
    }

    public store.generated.bank.BankStub.BankBankAccountNotFoundException getFaultMessage() {
        return faultMessage;
    }
}
