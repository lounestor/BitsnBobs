/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.lou.tut;

/**
 *
 * @author louise
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class email {
    
    
 

 
	public static void main(String[] args) {
		 Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tuttutoring@gmail.com","tuttutoring2012");
            }
        });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("admin@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse("loopyloopers@gmail.com"));
        message.setSubject("SFTS MAIL TESING");
        message.setText("Dear username another</%username%> likes your tutorial!");

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
       }
   }
}