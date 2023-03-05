/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.user;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.ServiceAdmin;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class CrudController implements Initializable {

    @FXML
    private TextField id_nom_delete;
    @FXML
    private TextField id_nom_modif;
    @FXML
    private TextField id_prenom_modif;
    @FXML
    private TextField id_age_modif;
    @FXML
    private TextField id_adresse_modif;
    @FXML
    private TextField id_telephone_modif;
    @FXML
    private TextField id_pwd_modif;
    @FXML
    private ComboBox id_role_modif;
    @FXML
    private ComboBox id_genre_modif;
    @FXML
    private TextField id_prenom_delete;
  
    @FXML
    private Button id_supprimer;
    
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    
  
    @FXML
    private TextField id_filed_modif;
    @FXML
    private Button btn_modif;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Admin" , "user","Guide" ) ;
        id_role_modif.setItems(list);
        
        ObservableList<String> liste = FXCollections.observableArrayList("Femme" , "Homme") ;
        id_genre_modif.setItems(liste);   
        
        ImageView imageView = new ImageView(getClass().getResource("/Resources/sup.png").toExternalForm());
        id_supprimer.setGraphic(imageView);
        
        id_supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                supprimer();
            }
        });      
        
         ImageView imageView2 = new ImageView(getClass().getResource("/Resources/modif2.png").toExternalForm());
           btn_modif.setGraphic(imageView2);
           
            btn_modif.setOnAction(new EventHandler<ActionEvent>() {
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
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/modifier.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
   
    @FXML
    private void supprimer() {
        String nom;
        String prenom;
         if(id_nom_delete.getText().isEmpty() || id_prenom_delete.getText().isEmpty())
     {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("Veuillez remplir tous les champs");
             alert.showAndWait();
     }
          else
        {
        nom=(String)id_nom_delete.getText();
        prenom=(String)id_prenom_delete.getText();   
        ServiceAdmin uc= new ServiceAdmin();
        uc.supprimerUser_home(nom,prenom);
        JOptionPane.showMessageDialog(null,"Succés de suppression ");
        }
    }
    
}
