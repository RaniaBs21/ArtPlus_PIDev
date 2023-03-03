/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import artplus.entities.Question;
import artplus.entities.Admin;
import artplus.entities.Reponse_User;
import artplus.services.Question_QuizCRUD;
import artplus.services.Reponse_QuizCRUD;
import artplus.services.Reponse_UserCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author nour
 */
public class QuizController implements Initializable {

    @FXML
    private Button evenement11;
    @FXML
    private Button evenement1;
    @FXML
    private Button evenement;
    @FXML
    private Button evenement12;
    @FXML
    private Button evenement13;
    @FXML
    private Button evenement131;
    @FXML
    private Button evenement1311;
    @FXML
    private Label question;
    @FXML
    private Button opt1;
    @FXML
    private Button opt3;
    @FXML
    private Button opt4;
    @FXML
    private Button opt2;
    @FXML
    private Button nextbtn;
    Button btn;
    @FXML
    private Button boutton;
    int id_quiz;
    String reponse;
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
       @FXML
    private void opt1clicked(ActionEvent event)  throws SQLException {
       
        

    }

   
        
            
       @FXML
    private void opt2clicked(ActionEvent event) throws SQLException {
       
    }

    @FXML
    private void opt3clicked(ActionEvent event) throws SQLException {
        
    }

    @FXML
    private void opt4clicked(ActionEvent event) throws SQLException {
  
    }
 @FXML
    private void Nextbtn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/QuizFXML2.fxml"));
        Scene scene = nextbtn.getScene();
        scene.setRoot(root);
        scene.getWindow();
}
    @FXML
    private void bouttonValider(ActionEvent event) throws SQLException {
    Reponse_User reponse=new Reponse_User();
    if(opt1.isVisible()){
         boutton.setStyle("-fx-background-color:#d63031");
              boutton.setText("Bravo!");
              
    
         
    }
    else if(opt4.isVisible()){
         boutton.setStyle("-fx-background-color:#d63031");
         boutton.setText("oppppps!");
         
    
         
    }
    Reponse_UserCRUD reponseUserCRUD = new Reponse_UserCRUD();
    Reponse_User reponseUser = new Reponse_User();
  
    reponseUserCRUD.enregistrerReponseUtilisateur(reponseUser);

    
        
          
            
             
           
              
    }
        
    }

    

