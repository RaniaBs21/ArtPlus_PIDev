/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import java.io.IOException;
import javafx.application.Application;
//import static javafx.application.Application.launch;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
//import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author wiemhjiri
 */
public class JavaFXMain extends Application {
    
    private Stage stage1;

   private Parent parent;

 
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       this.stage1=new Stage();
       //parent=FXMLLoader.load(getClass().getResource("/Views/Accueil.fxml"));
     //parent=FXMLLoader.load(getClass().getResource("/Views/abonuser.fxml"));
      parent=FXMLLoader.load(getClass().getResource("/Views/abonement.fxml"));
        //parent=FXMLLoader.load(getClass().getResource("/Views/ajoutCours.fxml"));
         Scene scene=new Scene(parent);
        stage1.setScene(scene);
        stage1.show();
        
     

      
    }

    public static void main(String[] args) {
        launch(args);
                
    }

    
    
}
