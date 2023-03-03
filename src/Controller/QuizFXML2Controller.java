/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author nour
 */
public class QuizFXML2Controller implements Initializable {

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
    @FXML
    private Button boutton;
    @FXML
    private Label question1;
    @FXML
    private Button opt11;
    @FXML
    private Button opt31;
    @FXML
    private Button opt41;
    @FXML
    private Button opt21;
    @FXML
    private Button nextbtn1;
    @FXML
    private Button boutton1;
    @FXML
    private Button acceuill;
    @FXML
    private Button filactualite;
    @FXML
    private Button ev;
    @FXML
    private Button cours;
    @FXML
    private Button produits;
    @FXML
    private Button quiz;
    @FXML
    private Button assistance;
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
    private void Nextbtn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
        Scene scene = nextbtn.getScene();
        scene.setRoot(root);

    }

    @FXML
    private void opt11clicked(ActionEvent event) {

    }

    @FXML
    private void opt31clicked(ActionEvent event) {

    }

    @FXML
    private void opt41clicked(ActionEvent event) {

    }

    @FXML
    private void opt21clicked(ActionEvent event) {

    }

    @FXML
    private void bouttonValider1(ActionEvent event) {
        if (opt11.isDisabled()) {
            if (!opt11.isDisabled()) {
                opt11.setStyle("-fx-background-color:#009432");
                boutton1.setStyle("-fx-background-color:#009432");
                boutton1.setText("Bravo!");
            } else {

                boutton1.setText("oppppppps!");
            }
        }
        }

    

    @FXML
    private void opt1clicked(ActionEvent event) {
    }

    @FXML
    private void opt3clicked(ActionEvent event) {
    }

    @FXML
    private void opt4clicked(ActionEvent event) {
    }

    @FXML
    private void opt2clicked(ActionEvent event) {
    }

    @FXML
    private void bouttonValider(ActionEvent event) {
    }
}
