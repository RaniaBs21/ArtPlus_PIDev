/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Jeu.JeuController;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nour
 */
public class HomeController implements Initializable {

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
    private Button playquizbtn;
    @FXML
    private Button btnjeu;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Playquizbtn(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/QuizFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

        }
    }
 

    @FXML
    private void btnjeu(MouseEvent event) throws IOException {
   
    }
}