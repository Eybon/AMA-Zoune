/**
 * ProviderProductNotFoundExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.provider;

public class ProviderProductNotFoundExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1447950611792L;
    private store.generated.provider.ProviderStub.ProviderProductNotFoundException faultMessage;

    public ProviderProductNotFoundExceptionException() {
        super("ProviderProductNotFoundExceptionException");
    }

    public ProviderProductNotFoundExceptionException(java.lang.String s) {
        super(s);
    }

    public ProviderProductNotFoundExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public ProviderProductNotFoundExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        store.generated.provider.ProviderStub.ProviderProductNotFoundException msg) {
        faultMessage = msg;
    }

    public store.generated.provider.ProviderStub.ProviderProductNotFoundException getFaultMessage() {
        return faultMessage;
    }
}
