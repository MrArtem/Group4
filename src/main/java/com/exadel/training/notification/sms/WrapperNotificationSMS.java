package com.exadel.training.notification.sms;

import com.exadel.training.notification.Notification;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 16.07.2015.
 */
@Service
public class WrapperNotificationSMS implements Notification{

    public static final String ACCOUNT_SID = "AC153d280ddafbd38701dcf24ba171b762";
    public static final String AUTH_TOKEN = "8a006dc2490b141eb7a5826db20856b0";

    public WrapperNotificationSMS() {}

    public void send(String phoneNumber, String text) throws TwilioRestException {
        if (!StringUtils.isBlank(phoneNumber)) {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
            Account account = client.getAccount();
            MessageFactory messageFactory = account.getMessageFactory();
            List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
            messageParams.add(new BasicNameValuePair("To", phoneNumber));
            messageParams.add(new BasicNameValuePair("From", "+1 832-981-3678"));
            messageParams.add(new BasicNameValuePair("Body", text));
            messageFactory.create(messageParams);
        }
    }
}
