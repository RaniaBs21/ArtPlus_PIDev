/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Abonement;
import artplus.entities.Categorie_cours;
import artplus.services.AbonnementServices;
import artplus.services.CategorieServices;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AbonementController implements Initializable {

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
    private Button valider;
    private TextField Id_level;
    @FXML
    private TableColumn<Abonement,String > Id_user;
    private TextField prix_abon;
    @FXML
    private ComboBox<Categorie_cours> categorieCombo;

    List<Categorie_cours> listeCategories = new ArrayList<>();
    @FXML
    private TableColumn<Abonement, String> Id_abon;
    @FXML
    private TableColumn<Abonement, String> date_abon;
    
    @FXML
    private TableView<Abonement> tableabon;
    @FXML
    private TableColumn<Abonement, String> Id_levels;
    @FXML
    private TableColumn<Abonement, String> prix_abonn;
    @FXML
    private Button afficheabon;
    @FXML
    private Button supprimer;
    @FXML
    private TextField idsupp;
    @FXML
    private TextField idmodif;
    @FXML
    private TextField niveau;
    private TextField id;
    @FXML
    private TextField price;
    @FXML
    private Button ajouteradmin;
    @FXML
    private TextField coursab;

    public void getCtegories() {
        CategorieServices catService = new CategorieServices();
        listeCategories = catService.afficherNomCategorie();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieServices catService = new CategorieServices();
        listeCategories = catService.afficherNomCategorie();
        categorieCombo.setConverter(new StringConverter<Categorie_cours>() {
            @Override
            public String toString(Categorie_cours categorie) {
                if (categorie.getNom_cat() != null) {
                    return categorie.getNom_cat();
                } else {
                    return "";
                }
            }

            @Override
            public Categorie_cours fromString(String string) {
                return categorieCombo.getSelectionModel().getSelectedItem();
            }
        });
        categorieCombo.setItems(FXCollections.observableArrayList(listeCategories));
        categorieCombo.setValue(listeCategories.get(0));

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
        
    
      ajouteradmin.setOnAction(event -> {

            Abonement ab = new Abonement(coursab.getText(), niveau.getText(), Integer.parseInt(price.getText()));
            AbonnementServices pd = AbonnementServices.getInstance();
            pd.ajouterAbonment2(ab);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Abonnement insérée avec succés! Bienvenue dans notre plateforme");
            alert.show();
            coursab.setText("");
            niveau.setText("");
            price.setText("");
        });

    }
    @FXML
    private void afficherAbon(ActionEvent event) {
       AbonnementServices ab = new AbonnementServices();
       List<Abonement> myList= ab.afficherAbonement();
        

      Id_user.setCellValueFactory(new PropertyValueFactory("level"));
      Id_levels.setCellValueFactory(new PropertyValueFactory("cours"));
      prix_abonn.setCellValueFactory(new PropertyValueFactory("prix_abon"));
      date_abon.setCellValueFactory(new PropertyValueFactory("date_abon"));

       ObservableList<Abonement> observableabonnement = FXCollections.observableArrayList(myList);
       tableabon.setItems(observableabonnement);
    }
    
    @FXML
    private void supprimerAbon(ActionEvent event){
        int id_abon=Integer.parseInt(idsupp.getText());
          AbonnementServices abon=new   AbonnementServices();
          abon.supprimerAbonement(id_abon);
    }
    
    @FXML
    public void modifierAbon (ActionEvent event){
        int Id_abon=Integer.parseInt(idmodif.getText());
        String level= niveau.getText();
        String cours=(coursab.getText());
        int prix_abon= Integer.parseInt(price.getText());
        AbonnementServices abon1=new   AbonnementServices();
        Abonement ab1= new  Abonement(Id_abon,level,cours,prix_abon);
        abon1.modifierAbon(ab1);
        
         
        
    }

}
