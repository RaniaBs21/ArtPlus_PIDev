/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author rahma
 */
public class JavaFXMain extends Application {
    
    private Stage stage;
    private Parent parent;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = new Stage();
        //parent = FXMLLoader.load(getClass().getResource("/Views/EvenementGuide.fxml"));
       //parent = FXMLLoader.load(getClass().getResource("/Views/Evenement.fxml"));
        //parent = FXMLLoader.load(getClass().getResource("/Views/EvenementAccueil.fxml"));
       parent = FXMLLoader.load(getClass().getResource("/Views/EvenementAccueilGuide.fxml"));
        Scene scene = new Scene(parent);
        
        
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
    
}
