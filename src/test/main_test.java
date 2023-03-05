
package test;
import DB.BD;
import DB.Mail;
import Entity.user;
import services.ServiceAdmin;

/**
 *
 * @author mahmo
 */
public class main_test {
    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
    String recipient = "mahmoud.lakhal@esprit.tn";
     //String recipient = "votreadresseemail@example.com";
    try {
        Mail.envoyer(recipient);
        System.out.println("Le message a été envoyé avec succès.");
    } catch (Exception e) {
        System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
        e.printStackTrace();
    }
}
   
}