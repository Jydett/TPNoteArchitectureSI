/**
 * MessageServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package fr.polytech.messager.client.axis2.generated;


/**
 *  MessageServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class MessageServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public MessageServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public MessageServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for getAllMessage method
     * override this method for handling normal response from getAllMessage operation
     */
    public void receiveResultgetAllMessage(
        fr.polytech.messager.client.axis2.generated.MessageServiceStub.GetAllMessageResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getAllMessage operation
     */
    public void receiveErrorgetAllMessage(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for createMessage method
     * override this method for handling normal response from createMessage operation
     */
    public void receiveResultcreateMessage() {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createMessage operation
     */
    public void receiveErrorcreateMessage(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for deleteMessage method
     * override this method for handling normal response from deleteMessage operation
     */
    public void receiveResultdeleteMessage() {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from deleteMessage operation
     */
    public void receiveErrordeleteMessage(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getMessageFrom method
     * override this method for handling normal response from getMessageFrom operation
     */
    public void receiveResultgetMessageFrom(
        fr.polytech.messager.client.axis2.generated.MessageServiceStub.GetMessageFromResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getMessageFrom operation
     */
    public void receiveErrorgetMessageFrom(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateMessage method
     * override this method for handling normal response from updateMessage operation
     */
    public void receiveResultupdateMessage() {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateMessage operation
     */
    public void receiveErrorupdateMessage(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getMyMessage method
     * override this method for handling normal response from getMyMessage operation
     */
    public void receiveResultgetMyMessage(
        fr.polytech.messager.client.axis2.generated.MessageServiceStub.GetMyMessageResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getMyMessage operation
     */
    public void receiveErrorgetMyMessage(java.lang.Exception e) {
    }
}
