import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class EmailUtility {
	public static void sendEmail(String host, String port,final String userName, final String password, String toAddress,String subject, String message,String filepath) throws AddressException,
			MessagingException {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:Ms");
		Date date = new Date();
		
		String[] emails  = new String[5];
		final String FILENAME = "/home/vipin/Desktop/Programs/filename.txt";
		int count_emails=0;
		int temp= 0 ;
		 String recipient = "vipin@expanion.com,acetheultimate@gmail.com";
		String[] recipientList = recipient.split(",");
		InternetAddress[] toemails = new InternetAddress[recipientList.length];
		
//		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
//			String sCurrentLine;
//			while ((sCurrentLine=br.readLine())!= null) {
//				 emails[count_emails] = sCurrentLine;    				
//			}			
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		for(String em : recipientList)
		{
			toemails[temp] = new InternetAddress(em);
			temp++;
			
		}
	
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);
		int s=0;
		for(String em : recipientList)
		{
//		// creates a new e-mail message
//			//System.out.println(dateFormat.format(date));
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName));
//		//System.out.println(toemails[s]);
		msg.addRecipient(Message.RecipientType.TO, toemails[s]);
		
		msg.setSubject(subject);
		
		BodyPart messageBodyPart1 = new MimeBodyPart();  
	    messageBodyPart1.setText(message);  
	      
	    //4) create new MimeBodyPart object and set DataHandler object to this object      
	    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
	  
	    String filepath1 = "/home/vipin/Desktop/Programs/filename.txt";//change accordingly 
	    System.out.println("file name:  "+filepath);
	    DataSource source = new FileDataSource(filepath1);  
	    messageBodyPart2.setDataHandler(new DataHandler(source));  
	    messageBodyPart2.setFileName(filepath1);  
	     
	     
	    //5) create Multipart object and add MimeBodyPart objects to this object      
	    Multipart multipart = new MimeMultipart();  
	    multipart.addBodyPart(messageBodyPart1);  
	    multipart.addBodyPart(messageBodyPart2);

		msg.setContent(multipart);
		msg.setSentDate(new Date());
//		// sends the e-mail
		Transport.send(msg);
//		System.out.println(toemails[s]);
		s++;
//		}
		//System.out.println(dateFormat.format(date));
		//System.out.println("loop close");

	}
  }
	
}
