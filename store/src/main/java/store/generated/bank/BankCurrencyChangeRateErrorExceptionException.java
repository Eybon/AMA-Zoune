/**
 * BankCurrencyChangeRateErrorExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.bank;

public class BankCurrencyChangeRateErrorExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1447954219792L;
    private store.generated.bank.BankStub.BankCurrencyChangeRateErrorException faultMessage;

    public BankCurrencyChangeRateErrorExceptionException() {
        super("BankCurrencyChangeRateErrorExceptionException");
    }

    public BankCurrencyChangeRateErrorExceptionException(java.lang.String s) {
        super(s);
    }

    public BankCurrencyChangeRateErrorExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public BankCurrencyChangeRateErrorExceptionException(
        java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        store.generated.bank.BankStub.BankCurrencyChangeRateErrorException msg) {
        faultMessage = msg;
    }

    public store.generated.bank.BankStub.BankCurrencyChangeRateErrorException getFaultMessage() {
        return faultMessage;
    }
}