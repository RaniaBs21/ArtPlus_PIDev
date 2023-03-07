/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author akrem
 */
public class AccueilController_1 implements Initializable {

    /**
     * Initializes the controller class.
     */
 

    @FXML
    private Button btn_rec;
    @FXML
    private Button Acc;
    @FXML
    private Button FAcc;
    @FXML
    private Button Ev;
    @FXML
    private Button Crs;
    @FXML
    private Button Prod;
    @FXML
    private Button Qz;
    @FXML
    private Button btn_ass;
        @FXML
    private Button btn_ut;
        
                @FXML
    private Button btn_ad;
        
        @Override
    public void initialize(URL url, ResourceBundle rb) {

                btn_ut.setOnAction(event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/Views/Interface_utilisateur.fxml"));
               Scene scene=btn_ut.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               Logger.getLogger(AccueilController_1.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
                
                       btn_ad.setOnAction(event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/Views/Interface_admin.fxml"));
               Scene scene=btn_ad.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               Logger.getLogger(AccueilController_1.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
    }    
    


   
}
        

    


