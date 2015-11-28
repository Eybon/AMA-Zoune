/**
 * BankNotEnoughMoneyExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.bank;

public class BankNotEnoughMoneyExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1448480299929L;
    private store.generated.bank.BankStub.BankNotEnoughMoneyException faultMessage;

    public BankNotEnoughMoneyExceptionException() {
        super("BankNotEnoughMoneyExceptionException");
    }

    public BankNotEnoughMoneyExceptionException(java.lang.String s) {
        super(s);
    }

    public BankNotEnoughMoneyExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public BankNotEnoughMoneyExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        store.generated.bank.BankStub.BankNotEnoughMoneyException msg) {
        faultMessage = msg;
    }

    public store.generated.bank.BankStub.BankNotEnoughMoneyException getFaultMessage() {
        return faultMessage;
    }
}
