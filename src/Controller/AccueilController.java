/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
public class AccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btn_querep;

    @FXML
    private Button btn_rec;
        
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_rec.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/view/FXMLReclamation.fxml"));
               Scene scene=btn_rec.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
                btn_querep.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/view/FXMLQuestionReponse.fxml"));
               Scene scene=btn_querep.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
    }    
    


   
}
        

    


