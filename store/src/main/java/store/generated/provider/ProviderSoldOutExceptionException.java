/**
 * ProviderSoldOutExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.provider;

public class ProviderSoldOutExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1448480276022L;
    private ProviderStub.ProviderSoldOutException faultMessage;

    public ProviderSoldOutExceptionException() {
        super("ProviderSoldOutExceptionException");
    }

    public ProviderSoldOutExceptionException(java.lang.String s) {
        super(s);
    }

    public ProviderSoldOutExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public ProviderSoldOutExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        ProviderStub.ProviderSoldOutException msg) {
        faultMessage = msg;
    }

    public ProviderStub.ProviderSoldOutException getFaultMessage() {
        return faultMessage;
    }
}
