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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FillActualiteController implements Initializable {

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
    private Button Ass;
    @FXML
    private Button btn_post;
    @FXML
    private Button btnCom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_post.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/views/AjoutPost.fxml"));
               Scene scene=btn_post.getScene();
             scene.setRoot(parent2);
             
             //**********methode 2************
             
//                Scene scene=new Scene(parent);
//                Stage stage=(Stage) ((Node) event.getSource())
//                        .getScene().getWindow();
//               stage.setScene(scene);
//               stage.setScene(scene);
//              stage.setTitle("Interface2");
//               stage.show();

           }catch (IOException ex) {
               Logger.getLogger(FillActualiteController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
        btnCom.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/views/Commentaire.fxml"));
               Scene scene=btnCom.getScene();
             scene.setRoot(parent2);
            
           }catch (IOException ex) {
               Logger.getLogger(FillActualiteController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
       
        
    }    
    
}
