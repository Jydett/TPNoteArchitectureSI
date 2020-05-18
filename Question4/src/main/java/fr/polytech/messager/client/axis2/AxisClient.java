package fr.polytech.messager.client.axis2;

import org.apache.axis2.client.Options;
import org.apache.axis2.client.Stub;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient3.HttpTransportPropertiesImpl;
import java.util.Arrays;

public class AxisClient {
    public static void main(String[] args) throws Exception {
        MessageServiceStub stub = new MessageServiceStub();

        authentificate(stub,"user" ,"pass");

        MessageServiceStub.GetAllMessage getAllMessageRequest = new MessageServiceStub.GetAllMessage();
        MessageServiceStub.GetAllMessageResponse response = stub.getAllMessage(getAllMessageRequest);
        System.out.println(Arrays.toString(response.get_return()));
    }

    private static void authentificate(Stub stub, String username, String password) {
        HttpTransportPropertiesImpl.Authenticator auth = new HttpTransportPropertiesImpl.Authenticator();
        auth.setPreemptiveAuthentication(true);
        auth.setPassword(username);
        auth.setUsername(password);
        auth.setPreemptiveAuthentication(true);
        Options options = stub._getServiceClient().getOptions();
        options.setProperty(HTTPConstants.AUTHENTICATE, auth);
    }
}
