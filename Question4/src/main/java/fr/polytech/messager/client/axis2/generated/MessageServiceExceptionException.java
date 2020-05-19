/**
 * MessageServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package fr.polytech.messager.client.axis2.generated;

public class MessageServiceExceptionException extends Exception {
    private static final long serialVersionUID = 1589897294213L;
    private MessageServiceStub.MessageServiceException faultMessage;

    public MessageServiceExceptionException() {
        super("MessageServiceExceptionException");
    }

    public MessageServiceExceptionException(String s) {
        super(s);
    }

    public MessageServiceExceptionException(String s,
                                            Throwable ex) {
        super(s, ex);
    }

    public MessageServiceExceptionException(Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        MessageServiceStub.MessageServiceException msg) {
        faultMessage = msg;
    }

    public MessageServiceStub.MessageServiceException getFaultMessage() {
        return faultMessage;
    }
}
