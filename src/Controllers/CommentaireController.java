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
import static java.sql.JDBCType.NULL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<?,?> IdCol;
    @FXML
    private TableColumn<?,?> contenuCol;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Commentaire> tableCom;
    
    int myIndex;
    int id;
    @FXML
    private Button affCom;
    @FXML
    private TableColumn<?,?> dateCol;
    @FXML
    private TextArea idSupp;
    @FXML
    private TextArea idModif;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    private Label labelcommentaire;
    @FXML
    private Label labelid;
    @FXML
    private Label labelDesc;
    @FXML
    private Label labeldate;
    
    
    
    
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
        
            
        });
    }    
/* ************** modification ******************* */
    @FXML
    private void Update(ActionEvent event) {
        
        int Id_Com =Integer.parseInt(idModif.getText()) ;
        String Description_Com = txtDescription.getText();
        CommentaireServices cs = new  CommentaireServices();
        Commentaire c= new Commentaire(Id_Com,Description_Com);
        cs.modifierCommentaire(c);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("commentaire modifié avec succés!");
            alert.show();
          
        
    }
/* ************** suppression ******************* */
    @FXML
    private void Delete(ActionEvent event) {
        int Id_Com =Integer.parseInt(idSupp.getText());
        CommentaireServices c  = new CommentaireServices();
        c.supprimerCom(Id_Com); 
        
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("commentaire supprimé avec succés!");
            alert.show();
        
        
    }
    
/* ************** affichage *********** */
    
    @FXML
    private void afficherCom(ActionEvent event) {
       CommentaireServices c = new CommentaireServices();
       List<Commentaire> myList= c.afficherCommentaire();
        
       IdCol.setCellValueFactory(new PropertyValueFactory("Id_Com"));
       contenuCol.setCellValueFactory(new PropertyValueFactory("Description_Com"));
       dateCol.setCellValueFactory(new PropertyValueFactory("Date_Com"));

       ObservableList<Commentaire> observablecommentaire = FXCollections.observableArrayList(myList);
       tableCom.setItems(observablecommentaire);
    }

    @FXML
    private void search(ActionEvent event) {
       Date Date_Com=Date.valueOf(txtSearch.getText());
        CommentaireServices css=new CommentaireServices();
        Commentaire c = css.searchCommentbyDate(Date_Com);
        if ( c != null){
          labelid.setText("Id_Com"+c.getId_Com());
          labelDesc.setText("Description_Com :" +c.getDescription_Com());
          labeldate.setText("Date_Com :" +c.getDate_Com());
        }
        else{
            labelcommentaire.setText(("commentaire n'a pas trouvé"));
        } 
    }

    

    

   

    
    
    
}
