/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Cours;
import javafx.geometry.Pos;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CoursCardController implements Initializable {

    @FXML
    private ImageView cours_image;
    @FXML
    private Label cous_name_label;
    @FXML
    private Button decouvrir_button;
    
    private Cours cours; 
    @FXML
    private Button subscribe_button;
    @FXML
    private AnchorPane container;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*BorderStroke borderStroke = new BorderStroke(
                    Color.BLUE,
                    BorderStrokeStyle.SOLID,
                    null,
                    BorderStroke.THIN
                );
                Border border = new Border(borderStroke);*/
                //container.setBorder(border);
                
    }    

    @FXML
    private void discover(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/VoirCours.fxml"));
                                                Parent root = loader.load();
                                                Scene scene = new Scene(root); 
                                                Stage stage = new Stage();
                                                stage.setScene(scene);
                                                VoirCoursController voir_cours_controller=loader.getController(); 
                                                voir_cours_controller.setCours(cours);
                                                voir_cours_controller.setStage(stage);
                                                voir_cours_controller.initialize_data();
                                                stage.show();
       
        } catch (IOException ex) {
          //  Logger.getLogger(HotelDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void subscribe(ActionEvent event) {
        try {
            System.out.println("im here");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutPaiement.fxml"));
                                                Parent root = loader.load();
                                                Scene scene = new Scene(root); 
                                                Stage stage = new Stage();
                                                stage.setScene(scene);
                                                AjoutPaiementController ajout_paiement_controller=loader.getController(); 
                                                ajout_paiement_controller.setCours(cours);
                                                ajout_paiement_controller.setStage(stage);
                                                stage.show();
                                                
       
        } catch (IOException ex) {
          //  Logger.getLogger(HotelDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //functions 
    public void initialize_data(){ 
        BorderStroke borderStroke = new BorderStroke(
                Color.BLUE,
                BorderStrokeStyle.SOLID,
                null,
                BorderStroke.THIN,
                new Insets(5)
        );
        Border border = new Border(borderStroke);
        Image image = new Image(new ByteArrayInputStream(cours.getFichier_c()));
        cours_image.setPreserveRatio(false);
        cours_image.setFitWidth(256);
        cours_image.setFitHeight(160);
        AnchorPane.setTopAnchor(cours_image, 1.0);
        AnchorPane.setLeftAnchor(cours_image, 1.5);
        this.cours_image.setImage(image); 
        cous_name_label.setText(cours.getTitre_c());
        cous_name_label.setAlignment(Pos.CENTER);
        
        
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    
    
    
}
