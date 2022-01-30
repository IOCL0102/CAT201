package com.example.cat201_project;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class MailSender {

    public static void sendSingleCodeWithMail(String recipient, String data) throws IOException, MessagingException {
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

        Message message = getSingleCodeMessage(session,myEmail,recipient,data);
        Transport.send(message);
        System.out.println("MESSAGE SENT SUCCESSFULLY TO " + recipient);
    }



    private static Message getSingleCodeMessage(Session session, String myEmail, String recipient, String singleCode) throws MessagingException, IOException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmail));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
        message.setSubject("Your single-use code");

        MimeBodyPart imagePart = new MimeBodyPart();
        message.setText("Hi " + recipient + ", \n\n Your single-use code is: " + singleCode + " . \n\nThanks,\n\nRoyal Cinema Team");
        return message;

    }

    public static void sendQRCode(String recipient, String QRPath, String movieName) throws IOException, MessagingException {
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

        Message message = getQRMessage(session,myEmail,recipient,QRPath,movieName);
        Transport.send(message);
        System.out.println("MESSAGE SENT SUCCESSFULLY TO " + recipient);
    }

    private static Message getQRMessage(Session session, String myEmail, String recipient, String QRpath, String movieName) throws MessagingException, IOException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmail));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
        message.setSubject("Your QR Code for " + movieName );

        MimeBodyPart QRPart = new MimeBodyPart();
        QRPart.attachFile(QRpath);
        Multipart content = new MimeMultipart();
        content.addBodyPart(QRPart);

        message.setContent(content);
        return message;
    }
}
