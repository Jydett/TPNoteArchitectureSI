/**
 * UserServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package fr.polytech.messager.client.axis2.generated;

public class UserServiceExceptionException extends Exception {
    private static final long serialVersionUID = 1589903313946L;
    private UserServiceStub.UserServiceException faultMessage;

    public UserServiceExceptionException() {
        super("UserServiceExceptionException");
    }

    public UserServiceExceptionException(String s) {
        super(s);
    }

    public UserServiceExceptionException(String s,
                                         Throwable ex) {
        super(s, ex);
    }

    public UserServiceExceptionException(Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        UserServiceStub.UserServiceException msg) {
        faultMessage = msg;
    }

    public UserServiceStub.UserServiceException getFaultMessage() {
        return faultMessage;
    }
}
