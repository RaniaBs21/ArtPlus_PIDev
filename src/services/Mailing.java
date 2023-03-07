package services;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailing {
    
    Connection cnx;
    
  public static void sendEmail(String recipient, String subject, String body) throws Exception {
      
    // Configure the email server properties
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    
    // Create a mail session with authentication
    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("chedy.bouhlel@esprit.tn", "hcyknsidkhwywgfv");
        }
    });
    
    // Create a new email message
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress("chedy.bouhlel@esprit.tn"));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
    message.setSubject(subject);
    
    // Set the content type to HTML
    message.setContent(body, "text/html");
    
    // Send the email
    Transport.send(message);
    System.out.println("Email sent successfully.");
}
  
  
  public String Body(int Id_Cmd) throws Exception {
    cnx= MyConnection.getInstance().getConnection();
    String body ="";
     
    String req = "SELECT * FROM commande ORDER BY Id_Cmd DESC LIMIT 1";

    PreparedStatement pst = cnx.prepareStatement(req);
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
        int Id = rs.getInt("Id_Cmd");
        Date datecmd = rs.getDate("Date_Cmd");
        Date dateLiv = rs.getDate("Date_Liv");

        body += "<h1>Commande Information</h1>"
         
            + "<p>Date du commande: " + datecmd + "</p>"
            + "<p>Date de livraison: " + dateLiv + "</p>";
    }
    return body;
}
}
    
 


  