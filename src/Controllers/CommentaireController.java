/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Commentaire;
import artplus.services.CommentaireServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CommentaireController implements Initializable {

    @FXML
    private Button Acc;
    @FXML
    private Button FAcc;
    @FXML
    private Button Ev;
    @FXML
    private Button Crs;
    @FXML
    private Button Prod;
    @FXML
    private Button Qz;
    @FXML
    private Button Ass;
    @FXML
    private Button btnAnn;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Button btnAdd;
    @FXML
    private TableColumn<Commentaire, Integer> IdCol;
    @FXML
    private TableColumn<Commentaire, String> contenuCol;
    @FXML
    private TableColumn<Commentaire, Date> dateCol;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Commentaire> tableCom;
    
    int myIndex;
    int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnAnn.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/views/FillActualite.fxml"));
               Scene scene=btnAnn.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
        
    /* ****** ajout d'un commentaire ******* */
    
         btnAdd.setOnAction(event -> {
            
            Commentaire c = new Commentaire(txtDescription.getText());
            CommentaireServices cs = CommentaireServices.getInstance();
            cs.ajouterCommentaire2(c);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("commentaire insérée avec succés!");
            alert.show();
            txtDescription.setText("");
        
            table();
            
        });
    }    

    
    private void table(){
        CommentaireServices c = new CommentaireServices();
        ObservableList<Commentaire> myList=FXCollections.observableArrayList();
        System.out.println(c.afficherCommentaire());

    }  

    @FXML
    private void Update(ActionEvent event) {
          CommentaireServices c = new  CommentaireServices();
          c.modifierCommentaire(new Commentaire(txtDescription.getText()));
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("commentaire modifié avec succés!");
            alert.show();
            txtDescription.setText("");
        
    }

    @FXML
    private void Delete(ActionEvent event) {
        CommentaireServices c  = new CommentaireServices();
         c.supprimerCom(id);
         
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("commentaire supprimé avec succés!");
            alert.show();
            txtDescription.setText("");
        
    }

    
    
}