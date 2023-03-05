package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nour
 */
public class Test extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {

           Parent root = FXMLLoader.load(getClass().getResource("/view/AddQuizFXML.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Erreur lors du chargement du fichier FXML: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
        
    }

}