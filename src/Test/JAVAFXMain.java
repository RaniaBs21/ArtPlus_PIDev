/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class JAVAFXMain extends Application {
    private Stage stage;
    private Parent parent;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage= new Stage();
       parent=FXMLLoader.load(getClass().getResource("/views/FillActualite.fxml"));
        //parent=FXMLLoader.load(getClass().getResource("/views/Commentaire.fxml"));
        Scene scene=new Scene(parent);
        stage.setScene(scene);
        stage.show();    
    }
    public static void main(String[] args){
        launch(args);
    }
    
}
