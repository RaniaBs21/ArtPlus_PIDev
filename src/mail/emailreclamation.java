package mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class emailreclamation {
    private String username = "akremcherif472@gmail.com";
    private String password = "xspoulpvrpvfzjtj";
    
    public void envoyerreclamation(String description, String type) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("akremcherif472@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mohamedakrem.cherif@esprit.tn"));
            message.setSubject("Reclamation");
            message.setText("Description: " + description + "\nType: " + type + "\n\nBonjour, votre reclamation est validé");
            Transport.send(message);
            System.out.println("Reclamation_envoyé");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        emailreclamation test1 = new emailreclamation();
        test1.envoyerreclamation("Problème de connexion", "Technique");
    } 
}