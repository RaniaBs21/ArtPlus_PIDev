/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import artplus.entities.Reponse_ass;
import artplus.services.Reponse_assCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import artplus.utils.MyConnection;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import mail.emailreclamation;
import mail.emailreponse;

/**
 * FXML Controller class
 *
 * @author akrem
 */
public class FXMLReponseController implements Initializable {

 
    
    
   
Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    String Type_Rep_Ass;
    
    
 @FXML
    private Button Acc;

    @FXML
    private Button Crs;

    @FXML
    private TableColumn<Reponse_ass, String> Descriptioncolomn;

    @FXML
    private Button Ev;

    @FXML
    private Button FAcc;

    @FXML
    private Button Prod;

    @FXML
    private Button Qz;

    @FXML
    private Button btn_ass;

    @FXML
    private Button btn_querep;

    @FXML
    private Button btn_recuser;


    @FXML
    private Button btnback;

    @FXML
    private Button btnmodifierR;

    @FXML
    private Button btnsupprimerR;

    @FXML
    private TableColumn<Reponse_ass, String> idcolomn;

    @FXML
    private Button repbtn;

    @FXML
    private TableView<Reponse_ass> tablereponse;

    @FXML
    private TextField txtDescription_Rep_Ass;

    @FXML
    private TextField txtQue_Rep_Ass;

    @FXML
    private TextField txtType_Rep_Ass;

    @FXML
    private TableColumn<Reponse_ass, String> typecolomn;

 

     public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/artplus","root","");
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
        idcolomn.setCellValueFactory(new PropertyValueFactory<Reponse_ass, String>("Que_Rep_Ass"));
        typecolomn.setCellValueFactory(new PropertyValueFactory<Reponse_ass, String>("Type_Rep_Ass"));
        Descriptioncolomn.setCellValueFactory(new PropertyValueFactory<Reponse_ass, String>("Description_Rep_Ass"));
        affichertable();
    }    



   public void addr(ActionEvent event) {
        Reponse_assCRUD repa = new Reponse_assCRUD();
        if (txtQue_Rep_Ass.getText().trim().length() > 0 || txtDescription_Rep_Ass.getText().trim().length() > 0 || txtType_Rep_Ass.getText().trim().length() > 0 ) {
            repa.ajouterreponse2(new Reponse_ass(txtQue_Rep_Ass.getText(),txtType_Rep_Ass.getText(),txtDescription_Rep_Ass.getText() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("reponse");
            alert.setHeaderText(null);
            alert.setContentText("reponse ajouté avec succés!");
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
    private void updater(ActionEvent event) {
         Reponse_assCRUD repa = new Reponse_assCRUD();
        if (txtQue_Rep_Ass.getText().trim().length() > 0 || txtDescription_Rep_Ass.getText().trim().length() > 0 || txtType_Rep_Ass.getText().trim().length() > 0 ) {
            repa.modifierreponse(new Reponse_ass(txtQue_Rep_Ass.getText(),txtType_Rep_Ass.getText(),txtDescription_Rep_Ass.getText() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("reponse");
            alert.setHeaderText(null);
            alert.setContentText("reponse modifié avec succés!");
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
    private void deleter(ActionEvent event) {
         Reponse_assCRUD repa = new Reponse_assCRUD();
        if (txtQue_Rep_Ass.getText().trim().length() > 0 || txtDescription_Rep_Ass.getText().trim().length() > 0 || txtType_Rep_Ass.getText().trim().length() > 0 ) {
          repa.supprimerreponse(new Reponse_ass(txtType_Rep_Ass.getText() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("reponse");
            alert.setHeaderText(null);
            alert.setContentText("reponse supprimé avec succés!");
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
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Interface_admin.fxml"));
        Parent root = loader.load();
         btnback.getScene().setRoot(root);
    }
    
     public void affichertable(){
 Reponse_assCRUD r = new Reponse_assCRUD();
        List<Reponse_ass> rep = r.afficherreponse();
        tablereponse.getColumns().clear();
        idcolomn.setCellValueFactory(new PropertyValueFactory<Reponse_ass, String>("Que_Rep_Ass"));
        typecolomn.setCellValueFactory(new PropertyValueFactory<Reponse_ass, String>("Type_Rep_Ass"));
        Descriptioncolomn.setCellValueFactory(new PropertyValueFactory<Reponse_ass, String>("Description_Rep_Ass"));
        tablereponse.getColumns().addAll( idcolomn,typecolomn, Descriptioncolomn);
        tablereponse.setItems(FXCollections.observableList(rep));
    }
    
@FXML
private void handleSendEmailButtonAction(ActionEvent event) {
    emailreponse email = new emailreponse();
    email.envoyerreponse();
    // display a message to the user indicating that the email has been sent
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Reponse");
    alert.setHeaderText(null);
    alert.setContentText("Le reponse a été envoyé au client avec succés!");
    alert.showAndWait();
}
    
    

    }
    

