/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import artplus.entities.Reclamation;
import artplus.services.ReclamationCRUD;
import artplus.utils.MyConnection;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author akrem
 */
public class FXMLReclamationController implements Initializable {

    @FXML
    private Button btnback;
 
    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;

    @FXML
    private TableColumn<Reclamation, String> descriptioncolomn;

    @FXML
    private TableColumn<Reclamation, Integer> idcolomn;

    @FXML
    private TableView<Reclamation> tablereclamation;

    @FXML
    private TextField txtDescription_Rec;

    @FXML
    private TextField txtType_Rec;

    @FXML
    private TableColumn<Reclamation, String> typecolomn;

    

   
 
    
Connection cnx;
    PreparedStatement pst;
    int myIndex;
    int id;
    String Type_Rec;
    
    
    
     public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/artplus","root","");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
        idcolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("Id_Rec"));
        typecolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Type_Rec"));
        descriptioncolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Description_Rec"));
        affichertable();
        
    }    
 
    @FXML
    private void add(ActionEvent event) {
         ReclamationCRUD rec = new ReclamationCRUD();
        if (txtDescription_Rec.getText().trim().length() > 0 || txtType_Rec.getText().trim().length() > 0 ) {
        rec.ajouterreclamation2(new Reclamation(txtDescription_Rec.getText(),txtType_Rec.getText() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setHeaderText(null);
            alert.setContentText("Reclamation ajouté avec succés!");
            alert.show();
    }
        else
              {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("erreur");
           alert.setHeaderText(null);
           alert.setContentText("champ vide");
           alert.show();
              }
        affichertable();
    }

    @FXML
    private void update(ActionEvent event) {
            ReclamationCRUD rec = new ReclamationCRUD();
        if (txtDescription_Rec.getText().trim().length() > 0 || txtType_Rec.getText().trim().length() > 0 ) {
        rec.modifierreclamation(new Reclamation(txtDescription_Rec.getText(),txtType_Rec.getText() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setHeaderText(null);
            alert.setContentText("Reclamation modifié avec succés!");
            alert.show();
    }
        else
              {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("erreur");
           alert.setHeaderText(null);
           alert.setContentText("champ vide");
           alert.show();
              }
        affichertable();
    }
    
    @FXML
    private void delete(ActionEvent event) {
            ReclamationCRUD rec = new ReclamationCRUD();
        if (txtDescription_Rec.getText().trim().length() > 0 || txtType_Rec.getText().trim().length() > 0 ) {
         rec.supprimerreclamation(new Reclamation(txtType_Rec.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setHeaderText(null);
            alert.setContentText("Reclamation supprimé avec succés!");
            alert.show();
    }
        else
              {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("erreur");
           alert.setHeaderText(null);
           alert.setContentText("champ vide");
           alert.show();
              }
        affichertable();
    }
    
    public void affichertable(){
  ReclamationCRUD r = new ReclamationCRUD();
        List<Reclamation> rec = r.afficherreclamation();
        tablereclamation.getColumns().clear();
        idcolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("Id_Rec"));
        typecolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Type_Rec"));
        descriptioncolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Description_Rec"));
        tablereclamation.getColumns().addAll(idcolomn, typecolomn, descriptioncolomn);
        tablereclamation.setItems(FXCollections.observableList(rec));
    }
     @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/page1.fxml"));
        Parent root = loader.load();
         btnback.getScene().setRoot(root);
    }
}
