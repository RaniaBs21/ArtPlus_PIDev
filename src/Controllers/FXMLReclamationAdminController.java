/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


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
public class FXMLReclamationAdminController implements Initializable {


    @FXML
    private Button searchbtn;

    @FXML
    private TextField searchtxt;
    
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
    private TableView<Reclamation> tablereclamation;

    
@FXML
    private Label labelDesc;

    @FXML
    private Label labelType;

    @FXML
    private Label labelid;
        @FXML
    private Label resultLabel;
    
    @FXML
    private TextField txtDescription_Rec;

    @FXML
    private TextField txtType_Rec;

    @FXML
    private TableColumn<Reclamation, String> typecolomn;
    @FXML
    private Button recenv;


   
 
    
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
        typecolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Type_Rec"));
        descriptioncolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Description_Rec"));
        affichertable();
        
    }    
 
    @FXML
    private void add(ActionEvent event) {
         ReclamationCRUD rec = new ReclamationCRUD();
        if (txtDescription_Rec.getText().trim().length() > 0 || txtType_Rec.getText().trim().length() > 0 ) {
        rec.ajouterreclamation2(new Reclamation(txtType_Rec.getText(),txtDescription_Rec.getText() ));
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
        rec.modifierreclamation(new Reclamation(txtType_Rec.getText(),txtDescription_Rec.getText() ));
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
        typecolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Type_Rec"));
        descriptioncolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Description_Rec"));
        tablereclamation.getColumns().addAll( typecolomn, descriptioncolomn);
        tablereclamation.setItems(FXCollections.observableList(rec));
    }

     @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Interface_admin.fxml"));
        Parent root = loader.load();
         btnback.getScene().setRoot(root);
    }
     public void searchRec(ActionEvent event) {
    // Get the search text from a TextField named searchField
    String searchText = searchtxt.getText();
    
    // Call the searchRec method to search for Reclamation objects with the specified Type_Rec value
    Reclamation result = searchRec(searchText);
    
    // Display the search result in a Label named resultLabel
    if (result != null) {
        resultLabel.setText(result.toString());
    } else {
        resultLabel.setText("No matching reclamation found.");
    }
}

public Reclamation searchRec(String type) {
    // String sql = "SELECT * FROM reclamations WHERE type_rec = ?";
    // PreparedStatement stmt = connection.prepareStatement(sql);
    // stmt.setString(1, type);
    // ResultSet rs = stmt.executeQuery();
    // if (rs.next()) {
    //     Reclamation rec = new Reclamation();
    //     rec.setId(rs.getInt("Id_Rec"));
    //     rec.setType_Rec(rs.getString("Type_Rec"));
    //     rec.setDescription(rs.getString("Description_Rec"));
    //     return rec;
    // } else {
    //     return null;
    // }
    
    // For this example, assume that there is a hardcoded Reclamation object with the specified Type_Rec value
    Reclamation rec = new Reclamation("error", "error consultation");
    if (type.equals(rec.getType_Rec())) {
        return rec;
    } else {
        return null;
    }
    
    
}

    @FXML
    void envoyerreclamation(ActionEvent event) {
  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamation");
            alert.setHeaderText(null);
            alert.setContentText("Validation de la reclamation avec succés!");
            alert.show();
    }

    }


