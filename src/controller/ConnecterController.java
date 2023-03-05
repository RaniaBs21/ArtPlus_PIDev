/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DB.Mail;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class ConnecterController implements Initializable {
    
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private TextField id_nom_conn;
    @FXML
    private PasswordField id_mdp_conn;
    @FXML
    private Button id_creerCompte2;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           id_creerCompte2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToCreateUser(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    } 
        public void redirectToCreateUser(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/CreateAccount.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void se_connecte(ActionEvent event) throws Exception {
            String recipient = "souha.solaani@esprit.tn";
        try {
        DB.Mail.envoyer(recipient);
        System.out.println("Le message a été envoyé avec succès.");
    } catch (Exception e) {
        System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
        e.printStackTrace();
    }
    }
}
