/**
 * BankRemoteExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.bank;

public class BankRemoteExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1447954219788L;
    private store.generated.bank.BankStub.BankRemoteException faultMessage;

    public BankRemoteExceptionException() {
        super("BankRemoteExceptionException");
    }

    public BankRemoteExceptionException(java.lang.String s) {
        super(s);
    }

    public BankRemoteExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public BankRemoteExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        store.generated.bank.BankStub.BankRemoteException msg) {
        faultMessage = msg;
    }

    public store.generated.bank.BankStub.BankRemoteException getFaultMessage() {
        return faultMessage;
    }
}
