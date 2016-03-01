package ua.net.forum.mail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail implements Runnable {

    private String email;
    private String login;
    private String password;
    private Properties props;

    private static final String PROPERTIES_FILE = "resources/mail.properties";
    private static final String FROM_PROPERTY = "from";
    private static final String PASSWORD_PROPERTY = "password";
    private static final String USERNAME_PROPERTY = "username";
    private static final String SUBJECT = "Registration message";
    private static final String SUCCESS_MESSAGE = "You were successfuly registered.\n";

    public SendEmail(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public void run() {
        String to = email;
        props = new Properties();
        InputStream input;
        try {
            input = new FileInputStream(System.getProperty("user.dir") + PROPERTIES_FILE);
            props.load(input);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props
                        .getProperty(USERNAME_PROPERTY), props
                        .getProperty(PASSWORD_PROPERTY));
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty(FROM_PROPERTY)));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(SUBJECT);
            String content = SUCCESS_MESSAGE + "Login: " + login + "\nPassword: " + password;
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}