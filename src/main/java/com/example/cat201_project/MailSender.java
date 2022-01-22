package com.example.cat201_project;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class MailSender {

    public static void sendSingleCodeWithMail(String recipient, String singleCode) throws FileNotFoundException, MessagingException {
        // set basic properties configuration
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");    // means it requires id and password
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");


        // get credentials information for text file
        String path = "src/main/resources/com/example/cat201_project/";
        String filepath = path + "credentials.txt";
        Scanner scanner = new Scanner(new File(filepath));
        String myEmail = scanner.nextLine();
        String myPassword = scanner.nextLine();
        scanner.close();

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail,myPassword);
            }
        });

        Message message = getMessage(session,myEmail,recipient,singleCode);
        Transport.send(message);
        System.out.println("MESSAGE SENT SUCCESSFULLY TO " + recipient);

    }

    private static Message getMessage(Session session, String myEmail, String recipient, String singleCode) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmail));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
        message.setSubject("Your single-use code");
        message.setText("Hi " + recipient + ", \n\n Your single-use code is: " + singleCode + " . \n\nThanks,\n\nRoyal Cinema Team");
        return message;
    }

}
