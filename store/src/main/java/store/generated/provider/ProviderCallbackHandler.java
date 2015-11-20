/**
 * ProviderCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package store.generated.provider;


/**
 *  ProviderCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class ProviderCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public ProviderCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public ProviderCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for orderProduct method
     * override this method for handling normal response from orderProduct operation
     */
    public void receiveResultorderProduct(
        store.generated.provider.ProviderStub.OrderProductResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from orderProduct operation
     */
    public void receiveErrororderProduct(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getProduct method
     * override this method for handling normal response from getProduct operation
     */
    public void receiveResultgetProduct(
        store.generated.provider.ProviderStub.GetProductResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getProduct operation
     */
    public void receiveErrorgetProduct(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getListProduct method
     * override this method for handling normal response from getListProduct operation
     */
    public void receiveResultgetListProduct(
        store.generated.provider.ProviderStub.GetListProductResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getListProduct operation
     */
    public void receiveErrorgetListProduct(java.lang.Exception e) {
    }
}
