/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Cours;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class VoirCoursController implements Initializable {

    @FXML
    private ImageView cours_image;
    @FXML
    private Label course_name_label;
    @FXML
    private Label course_categorie_label;
    @FXML
    private Label course_sub_categorie_label;
    @FXML
    private Label course_level_label;
    @FXML
    private TextArea course_description_area;

    
    private Cours cours;
    Stage stage;
    @FXML
    private Label course_prix_label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    public void initialize_data(){ 
        course_description_area.getStyleClass().add("text-area-disabled");
        System.out.println("data cours"+cours );
        Image image = new Image(new ByteArrayInputStream(cours.getFichier_c()));
        cours_image.setPreserveRatio(false);
        this.cours_image.setImage(image);
        /*cours_image.setFitWidth(256);
        cours_image.setFitHeight(160);*/
        course_name_label.setText(cours.getTitre_c());
        course_categorie_label.setText(cours.getSous_categorie().getCategorie().getNom_cat());
        course_sub_categorie_label.setText(cours.getSous_categorie().getNom_sc());
        course_level_label.setText(cours.getNiveau_c()+"");
        course_prix_label.setText(cours.getPrix()+" DT");
        course_description_area.setText(cours.getDescription_c()); 
        course_description_area.setDisable(true);
        stage.setResizable(false);       
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
