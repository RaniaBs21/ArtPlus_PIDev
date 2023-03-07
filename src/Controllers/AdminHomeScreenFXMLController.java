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
import javafx.scene.control.Button;
import javafx.scene.control.Tab;

import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nour
 */
public class AdminHomeScreenFXMLController implements Initializable {

    @FXML
    private Tab addQuizTab;
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
    private AnchorPane adminTabPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{    
           Parent node =FXMLLoader.load(getClass().getResource("/view/AddReponseFXML.fxml"));
        addQuizTab.setContent(node);
        } catch (IOException ex) {
            Logger.getLogger(AdminHomeScreenFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
  
        // TODO
    
    

