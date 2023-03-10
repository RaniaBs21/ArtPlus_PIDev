/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Sujet;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class VoirPhotoController implements Initializable {

    @FXML
    private ImageView image_view; 
    Sujet sujet;
    Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void setImage_view() { 
        System.out.println("sujet "+sujet);
        Image image = new Image(new ByteArrayInputStream(sujet.getPhoto()));
        image_view.setPreserveRatio(true);
        image_view.setFitWidth(400);
        image_view.setFitHeight(400);
        this.image_view.setImage(image);
        stage.setResizable(false);
        
    }
    
    

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    } 

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
}
