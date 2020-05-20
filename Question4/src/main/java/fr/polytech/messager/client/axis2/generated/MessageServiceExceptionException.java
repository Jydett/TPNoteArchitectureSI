/**
 * MessageServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package fr.polytech.messager.client.axis2.generated;

public class MessageServiceExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1589906371968L;
    private fr.polytech.messager.client.axis2.generated.MessageServiceStub.MessageServiceException faultMessage;

    public MessageServiceExceptionException() {
        super("MessageServiceExceptionException");
    }

    public MessageServiceExceptionException(java.lang.String s) {
        super(s);
    }

    public MessageServiceExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public MessageServiceExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        fr.polytech.messager.client.axis2.generated.MessageServiceStub.MessageServiceException msg) {
        faultMessage = msg;
    }

    public fr.polytech.messager.client.axis2.generated.MessageServiceStub.MessageServiceException getFaultMessage() {
        return faultMessage;
    }
}
