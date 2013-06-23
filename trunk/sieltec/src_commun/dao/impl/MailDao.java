package dao.impl;

import java.util.Properties;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.IMailDao;

@ManagedBean(name="mailDao", eager=true)
@ApplicationScoped

public class MailDao implements IMailDao {
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
    String host, port, username, password;
    Properties props = System.getProperties();
    Session l_session = null;

	@Override
	public void sendMail(String to, String subject, String body) {

        username = "sieltec1@yahoo.fr";
        password = "pfesieltec";
        
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", "587");
		
        l_session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        l_session.setDebug(true); // Enable the debug mode

        
        try {
            MimeMessage message = new MimeMessage(l_session);
            String emailid = username;
            message.setFrom(new InternetAddress(emailid));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //message.addRecipient(Message.RecipientType.BCC, new InternetAddress(AppConstants.fromEmail));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
            logger.trace("Message Sent");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }//end catch block

	}



}
