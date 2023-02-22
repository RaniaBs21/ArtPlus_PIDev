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
    private AnchorPane rootLayout;
    private Stage primaryStage;
    @FXML
    private Button btn_querep;

    @FXML
    private Button btn_rec;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btn_rec.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/FXMLReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btn_querep.setOnAction(event -> {
            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/FXMLQuestionReponse.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
}

