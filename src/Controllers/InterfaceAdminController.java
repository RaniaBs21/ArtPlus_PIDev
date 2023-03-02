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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author akrem
 */
public class InterfaceAdminController implements Initializable {

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
    private Button btn_recadmin;
    @FXML
    private Button btn_rep;
   @FXML
    private Button btnback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                      btn_recadmin.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/Views/FXMLReclamationAdmin.fxml"));
               Scene scene=btn_recadmin.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
                                    btnback.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/Views/Accueil.fxml"));
               Scene scene=btnback.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });      
    }    

}
