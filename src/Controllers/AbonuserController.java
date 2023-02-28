/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Abonement;
import artplus.entities.Categorie_cours;
import artplus.entities.Cours;
import artplus.services.AbonnementServices;
import artplus.services.CategorieServices;
import artplus.services.CoursServices;
import artplus.services.LevelServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import artplus.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AbonuserController implements Initializable {

    Connection cnx;
    @FXML
    private Button annuler;
    @FXML
    private AnchorPane prix;
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
    private Button valider;
    @FXML
    private TextField Id_level;
    @FXML
    private TextField Id_user;
    @FXML
    private TextField prix_abon;
 
    List<Cours> listeCours = new ArrayList<>();
    List<Level> listeLevel = new ArrayList<>();
    @FXML
    private ComboBox<Level> levelcombo;
    @FXML
    private ComboBox<Cours> courscombo;

    public void getCtegories() {
    CoursServices catService = new CoursServices();
        listeCours = catService.afficherNomCours();
    }

  /*  public void getLevel() {
        LevelServices levService = new LevelServices();
        List<edu.artplus.entities.Level> listeLevel = levService.afficherNomLevel();
    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      CoursServices catService = new CoursServices();
        listeCours= catService.afficherNomCours();
        
        /*LevelServices levService= new LevelServices();
        List<edu.artplus.entities.Level> listeLevel = levService.afficherNomLevel();*/
        
       courscombo.setConverter(new StringConverter<Cours>() {
            public String toString(Cours cours) {
                if (cours.getTitre_c() != null) {
                    return cours.getTitre_c();
                } else {
                    return "";
                }
            }

            public Cours fromString(String string) {
                return courscombo.getSelectionModel().getSelectedItem();
            }

          
        });
        
       
      
     /*   levelcombo.setConverter(new StringConverter<Level>() {
          @Override
            public String toString(Level level) {
                if (Level.getNom_level() != null) {
                    return level.getNom_level();
                } else {
                    return "";
                }
            }
            @Override
            public Level fromString(String string) {
                return levelcombo.getSelectionModel().getSelectedItem();
            }
        });*/
     courscombo.setItems(FXCollections.observableArrayList(listeCours));
     courscombo.setValue(listeCours.get(0));
       
       /* levelcombo.setItems(FXCollections.observableArrayList(listeLevel));
        levelcombo.setValue(listeLevel.get(0));*/

        annuler.setOnAction(event -> {
            try {
                Parent parent3 = FXMLLoader
                        .load(getClass().getResource("/Views/Accueil.fxml"));

                Scene scene = annuler.getScene();
                scene.setRoot(parent3);

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        valider.setOnAction(event -> {

            Abonement ab = new Abonement((Id_user.getText()), (Id_level.getText()), Integer.parseInt(prix_abon.getText()));
            AbonnementServices pd = AbonnementServices.getInstance();
            pd.ajouterAbonment2(ab);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Abonnement insérée avec succés!");
            alert.show();
            Id_user.setText("");
            Id_level.setText("");
            prix_abon.setText("");
        });
        
       
    }
 /*   @FXML
   public void setabontitre(ActionEvent event ){
            if(Id_user.getText().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("PAS ENCORE");
            alert.setHeaderText("Veuillez spécifier le cours voulu");
            alert.showAndWait();
            
            
        }
        
    }*/

}
