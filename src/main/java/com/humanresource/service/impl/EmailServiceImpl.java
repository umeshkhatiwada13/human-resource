package com.humanresource.service.impl;

import com.humanresource.model.Employee;
import com.humanresource.repo.EmployeeRepo;
import com.humanresource.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author umeshkhatiwada13@infodev
 * @project human-resource
 * @created 29/08/2022 - 06:25
 */
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmployeeRepo employeeRepo;

    @Override
    public void sendMail(Employee employee) {
        String hrEmail = employeeRepo.getHrEmail();
        // Recipient's email
        String to = StringUtils.isEmpty(hrEmail) ? "testdata9898@yopmail.com" : hrEmail;

        // Sender's email ID needs to be mentioned
        String from = "umeshkhatiwada12@gmail.com";

        // Get system properties
        Properties props = System.getProperties();

        /*
         String host = "smtp.mailtrap.io";
         props.setProperty("mail.smtp.host", host);
        props.put("mail.smtp.port", 2525);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.timeout", "20000");
        props.put("mail.smtp.connectiontimeout", "20000");
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");*/

        // Setup mail server
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("umeshkhatiwada12@gmail.com", "snefimiovrluokov");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("About Employee's " + employee.getEmploymentStatus());

            String customMessage = new StringBuilder("<h1>This is actual message</h1>")
                    .append("Employee : ").append(employee.getName())
                    .append(" has joined our company for ")
                    .append(employee.getPosition())
                    .append(" position with salary of ")
                    .append(employee.getSalary()).toString();

            // Send the actual HTML message, as big as you like
            message.setContent(customMessage, "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
