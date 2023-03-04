/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Abonement;
import artplus.entities.Carte;
import artplus.entities.Cours;
import artplus.entities.Transaction;
import artplus.entities.user;
import artplus.services.AbonnementServices;
import artplus.services.ServiceStripeIMP;
import artplus.services.ServiceTransactionIMP;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjoutPaiementController implements Initializable {
    
    @FXML
    private TextField card_number;
    @FXML
    private DatePicker exipration_date;
    @FXML
    private TextField cvc;
    @FXML
    private Button pay_button;
    
    
    
    Cours cours;
    Stage stage; 
    public static final int maxLength = 16;
    public static final int maxLengthCvc = 3;
 
    ServiceStripeIMP stripe =new ServiceStripeIMP();
    ServiceTransactionIMP service_transaction=new ServiceTransactionIMP();
    AbonnementServices service_abonnement=new AbonnementServices();
    private BooleanProperty form_valid= new SimpleBooleanProperty(true); 
    private boolean card_number_check,exipration_date_check,cvc_check=false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pay_button.setDisable(true);
        Stripe.apiKey="sk_test_51MfpzeHNXQGXsKQNfyUWwUQiZTZm4jCJ7oR1YwabPZtSlWM1QVrd9NgJt68tHszl4x8PRdkFzZoEBYhAbyovocv600464BWMo2";
        card_number.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length()<16) {
                card_number_check=false; 
            }else{ 
                card_number_check=true;
            }
            if (!newValue.matches("\\d{0,16}")) {
                card_number.setText(oldValue);
            }
            
            
            System.out.println("card check"+card_number_check);
            form_valid.set(!check_form());
        });
        exipration_date.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
        exipration_date.setOnAction(event -> {
            LocalDate selectedDate = exipration_date.getValue();
            if (selectedDate == null) {
                exipration_date_check=false;
            } else {
                exipration_date_check=true;
            }
            System.out.println("expiration check"+exipration_date_check);
            form_valid.set(!check_form());
        });
        
        cvc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length()<3) {
                cvc_check=false; 
            }else{ 
                cvc_check=true;
            }
            if (!newValue.matches("\\d{0,3}")) {
                cvc.setText(oldValue);
            }
            System.out.println("cvc check"+cvc_check);
            form_valid.set(!check_form());
        });
       pay_button.disableProperty().bind(form_valid);
    }
    
   

  
    @FXML
    private void pay_subscription(ActionEvent event) {
        if(check_from_filled()){ 
               if(check_card_valid()){
                    Carte carte=new Carte(); 
                    int  exp_month= exipration_date.getValue().getMonth().getValue();  
                    int  exp_year=exipration_date.getValue().getYear(); 
                    carte.setCvc(Integer.parseInt(cvc.getText()));
                    carte.setExp_month(exp_month);
                    carte.setExp_year(exp_year); 
                    carte.setNumber(card_number.getText());
                                 String paymentIntent_id=stripe.payment(user.user_connecter, (int) cours.getPrix(), carte);
                                 System.out.println("paymment intet "+ paymentIntent_id);
                                 if (paymentIntent_id!=""){
                                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                             alert.setTitle("Paiement etat");
                                             alert.setHeaderText(null);
                                             alert.setContentText("Paiement effectué avec succées");
                                             alert.showAndWait();
                                                   Transaction transaction =new Transaction(); 
                                                   transaction.setCvc(Integer.parseInt(cvc.getText()) );
                                                   transaction.setExp_mois(exp_month);
                                                   transaction.setExp_annee(exp_year);
                                                   transaction.setNom_carte("Visa");
                                                   transaction.setNumero_carte(card_number.getText());
                                                   transaction.setPaymentIntent_id(paymentIntent_id);
                                                   int tr=service_transaction.add_transaction(transaction);
                                                   Abonement abonnement=new Abonement(); 
                                                   abonnement.setCours(cours);
                                                   abonnement.setUser(user.user_connecter.getAdresse()); 
                                                   abonnement.setTransaction(service_transaction.get_transaction_by_id(tr));
                                                   service_abonnement.ajoutAbonement(abonnement);
                                                   Stage this_stage=(Stage) pay_button.getScene().getWindow();
                                                   this_stage.close();

                                        }else{ 
                                             Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.setTitle("Paiement non éffectué");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Solde insuffissant");
                                                alert.showAndWait();
                                         }    
                   
               }else{ 
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Erreur Validation Formulaire");
                    alert.setHeaderText(null);
                    alert.setContentText("Numéro de carte invalide ");
                    alert.showAndWait();
               }
                 
        }
    }
    
     private boolean check_form(){ 
        if (exipration_date_check==true && card_number_check==true && cvc_check==true)
            return true;
        return false;
     }
    
    public boolean check_from_filled(){
        boolean check=false;
        if(card_number.getLength()==16 &&cvc.getLength()==maxLengthCvc && exipration_date.getValue()!=null ){
            check=true;
        }
        return check;
    }
    public boolean check_card_valid(){
        boolean check=false; 
        String card=card_number.getText().toString();
        if(card.equals("4242424242424242") || 
                card.equals("4000056655665556") || 
                card.equals("5555555555554444")|| 
                card.equals("2223003122003222") || 
                card.equals("5200828282828210") ||
                card.equals("5105105105105100") ||
                card.equals("378282246310005") ||
                card.equals("6011111111111117") ||
                card.equals("3056930009020004")){ 
                check=true;
        }
        return check;
    }
    
    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    

    
}

