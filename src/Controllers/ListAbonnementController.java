/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Abonement;
import artplus.entities.Cours;
import artplus.entities.KeyValuePair;
import artplus.services.AbonnementServices;
import artplus.services.CoursServices;
import artplus.services.LevelServices;
import artplus.services.Sous_categorieServices;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class ListAbonnementController implements Initializable {

    @FXML
    private Button evenement;
    @FXML
    private TableView<Abonement> tablecours;
    @FXML
    private TableColumn<Abonement, Integer> niveau;
    @FXML
    private TableColumn<Abonement, String> date;
    @FXML
    private TableColumn<Abonement, Float> prix;
    @FXML
    private TableColumn<Abonement, String> user;
    
    AbonnementServices abonnement_services=new AbonnementServices();
         ObservableList<Abonement> obsreservationlist=FXCollections.observableArrayList(); 
    @FXML
    private TableColumn<Abonement, String> cours;
    @FXML
    private Button fillAct;
    @FXML
    private Button btnAcc;
    @FXML
    private Button cours_bt;
    @FXML
    private Button prod;
    @FXML
    private Button quiz;
    @FXML
    private Button ass;
    @FXML
    private Button abonnements;
    
    private Stage stage;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        get_abonnement(); 
        loadData();
        
        cours_bt.setOnAction(event->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ajoutCours.fxml"));
                Parent root = loader.load();
                Scene scene2 = new Scene(root);
                stage=(Stage) cours_bt.getScene().getWindow();
                stage.setScene(scene2);

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        abonnements.setOnAction(event->{
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ListAbonnement.fxml"));
                Parent root = loader.load();
                Scene scene2 = new Scene(root);
                stage=(Stage) abonnements.getScene().getWindow();
                stage.setScene(scene2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    


    @FXML
    private void afficherAbonnemnts(ActionEvent event) {
        get_abonnement(); 
        loadData();
    }
    public void get_abonnement(){ 
        obsreservationlist.clear();
        tablecours.getItems().clear();
        abonnement_services.afficherAbonement().forEach((cours)->{
            obsreservationlist.add(cours);
        });
    }
     public void loadData(){  
        tablecours.setItems(obsreservationlist);
        System.out.println("table items "+tablecours.getItems());
        niveau.setCellValueFactory(data->{
             int  nv= data.getValue().getCours().getNiveau_c();
             ObservableValue<Integer> obs=new SimpleObjectProperty<>(nv);
             return obs;
         });
        prix.setCellValueFactory(data->{
             Float  nv= data.getValue().getCours().getPrix();
             ObservableValue<Float> obs=new SimpleObjectProperty<>(nv);
             return obs;
         });
        user.setCellValueFactory(data->{
             String  desc= data.getValue().getUser();
             ObservableValue<String> obs=new SimpleObjectProperty<>(desc);
             return obs;
         });
        cours.setCellValueFactory(data->{
             String  desc= data.getValue().getCours().getTitre_c();
             ObservableValue<String> obs=new SimpleObjectProperty<>(desc);
             return obs;
         });
        date.setCellValueFactory(data->{
             Date date= data.getValue().getDate_abon();
             ObservableValue<String> obs=new SimpleObjectProperty<>(date.toString());
             return obs;
         });
        
        tablecours.setItems(obsreservationlist);
        
    }


    
}
