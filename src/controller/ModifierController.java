/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class ModifierController implements Initializable {

    private ComboBox id_role_modif;
    private ComboBox id_genre_modif;
    @FXML
    private Button btn_back;
    private Button btn_modif;
 
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private ComboBox id_role_modifo;
    @FXML
    private ComboBox id_genre_modifo;
    @FXML
    private TextField id_nom_modifo;
    @FXML
    private TextField id_prenom_modifo;
    @FXML
    private TextField id_age_modifo;
    @FXML
    private TextField id_adresse_modifo;
    @FXML
    private TextField id_telephone_modifo;
    @FXML
    private TextField id_pwd_modifo;
    @FXML
    private TextField id_filed_modifo;
    @FXML
    private Button btn_modifo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> list = FXCollections.observableArrayList("Admin" , "user","Guide" ) ;
        id_role_modifo.setItems(list);
        ObservableList<String> liste = FXCollections.observableArrayList("Femme" ,"Homme") ;
        id_genre_modifo.setItems(liste);
        
       ImageView imag = new ImageView(getClass().getResource("/Resources/mod3.png").toExternalForm());
        btn_modifo.setGraphic(imag);
           
        ImageView imag2 = new ImageView(getClass().getResource("/Resources/retour.png").toExternalForm());
        btn_back.setGraphic(imag2);
        
        
             btn_modifo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               modif_P();
            }
        });
             
             btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToMyGallerie(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }    
       public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/Gallerie_produit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void modif_P() {}
}
