/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
//import com.stripe.Stripe;
//import com.stripe.model.Charge;
import java.util.HashMap;
import java.util.Map;
import javax.naming.AuthenticationException;
import javax.smartcardio.CardException;


/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PaiementController implements Initializable {

  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
      /* Stripe.apiKey = "sk_test_51MfpzeHNXQGXsKQNfyUWwUQiZTZm4jCJ7oR1YwabPZtSlWM1QVrd9NgJt68tHszl4x8PRdkFzZoEBYhAbyovocv600464BWMo2";
     
       
Map<String, Object> params = new HashMap<>();
params.put("amount", 1000);
params.put("currency", "usd");
params.put("description", "Exemple de paiement");
params.put("source", "tok_visa"); // Obtenir le token de carte de crédit à partir de Stripe.js
try {
    Charge charge = Charge.create(params);
}catch (Exception e) {
      // Capturez toutes les autres exceptions
      System.out.println("Une erreur s'est produite: " + e.getMessage());
    }
        // Capturez l'exception si la carte est invalide*/
        

    }    
    
}
