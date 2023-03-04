package mail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class emailreponse{
    private String username = "akremcherif472@gmail.com";
    private String password = "xspoulpvrpvfzjtj";

    public void envoyerreponse(String description,String type,String question) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("akremcherif472@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("mohamedakrem.cherif@esprit.tn"));
            message.setSubject("Reponse a votre question");
message.setText("Description: " + description + "\nType: " + type + "\n\nQuestion correspondante: " + question +"\n\n\nBonjour, voici le reponse a votre question");
            Transport.send(message);
            System.out.println("Reponse_envoyé");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } 
    }

    public static void main(String[] args) {
        emailreponse test2 = new emailreponse();
        test2.envoyerreponse("aaa","sss","dddd");
    } 
}
