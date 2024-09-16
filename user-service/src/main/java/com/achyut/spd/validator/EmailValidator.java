package com.achyut.spd.validator;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.achyut.spd.userservice.constants.ExceptionConstants;
import com.achyut.spd.userservice.constants.GlobalConstants;

import jakarta.annotation.PostConstruct;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
 // Static reference to the class instance
    private static EmailValidator instance;

    // This method will be called after the object is constructed and Spring has injected the value
    @PostConstruct
    public void init() {
        instance = this;
    }

    // Static method to access the non-static field via the instance
    private static String getFromEmail() {
        return instance.fromEmail;
    }

    // Validate email syntax
    private static boolean isValidEmailFormat(String email) {

        if(email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email)
                .matches();
    }
    
    private static boolean isValidDomain(String email) {
        String domain = email.substring(email.indexOf('@') + 1);
        List<String> mxRecords = getMXRecords(domain);

        if (mxRecords.isEmpty()) {
            return false;
        }

        for (String mxRecord : mxRecords) {
            if (isReachable(mxRecord)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> getMXRecords(String domain) {
        List<String> mxRecords = new ArrayList<>();
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext ictx = new InitialDirContext(env);
            Attributes attrs = ictx.getAttributes(domain, new String[]{"MX"});
            Attribute attr = attrs.get("MX");

            if (attr != null) {
                for (int i = 0; i < attr.size(); i++) {
                    String record = (String) attr.get(i);
                    mxRecords.add(record.split("\\s+")[1]);  // Get the mail server hostname
                }
            }
        } catch (NamingException e) {
            return mxRecords;
        }
        return mxRecords;
    }

    private static boolean isReachable(String mxRecord) {
        try (Socket socket = new Socket(mxRecord, 25)) {  // Try to connect to the MX server on port 25 (SMTP)
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static boolean isValidSMTP(String email) {
        
        String domain = email.substring(email.indexOf('@') + 1);
        
        List<String> mxRecords = getMXRecords(domain);

        if (mxRecords.isEmpty()) {
            return false;
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", mxRecords.get(0));  // Use the first available MX record

        Session session = Session.getInstance(props, null);
        try {
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getFromEmail()));  // Your sending email address
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));  // Recipient's email
            message.setSubject("SMTP Validation Test");
            message.setText("This is a test message to validate the SMTP server.");
            
            Transport transport = session.getTransport("smtp");
            transport.connect();  // Try connecting to the mail server

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;  // Email is valid and accepted by SMTP server
        } catch (Exception ex) {
            return false;  // Email is not valid
        }
    }

    public static void checkEmail(String email) {

        if(!isValidEmailFormat(email)) {
            throw new IllegalArgumentException(GlobalConstants.INVALID_EMAIL_FORMAT);
        }

        if(!isValidDomain(email)) {
            throw new IllegalArgumentException(ExceptionConstants.INVALID_EMAIL_DOMAIN);
        }

        if(!isValidSMTP(email)) {
            throw new IllegalArgumentException(ExceptionConstants.INVALID_SMTP);
        }
    }

}
