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
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
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

 
    
    
   
Connection cnx;
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

    @FXML
    private Button searchbtn;

    @FXML
    private TextField searchtxt;
    
    private ObservableList<Reponse_ass> reponseList;

 

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
        idcolomn.setCellValueFactory(new PropertyValueFactory<Reponse_ass, String>("Que_Rep_Ass"));
        typecolomn.setCellValueFactory(new PropertyValueFactory<Reponse_ass, String>("Type_Rep_Ass"));
        Descriptioncolomn.setCellValueFactory(new PropertyValueFactory<Reponse_ass, String>("Description_Rep_Ass"));
        affichertable();
    reponseList = FXCollections.observableArrayList(tablereponse.getItems());
    }    

    
    
        @FXML
private void searchReclamation(ActionEvent event) {
    // Get the search text and convert it to lowercase
    String searchText = searchtxt.getText().toLowerCase();

    // Create a filtered list for the search results
    FilteredList<Reponse_ass> filteredList = new FilteredList<>(reponseList);

    // Loop through each item in the table view and add it to the filtered list if it matches the search text
    filteredList.setPredicate(Reponse_ass -> {
        String quesrep = Reponse_ass.getQue_Rep_Ass().toLowerCase();
         String typeRep = Reponse_ass.getType_Rep_Ass().toLowerCase();
        String descriptionRep = Reponse_ass.getDescription_Rep_Ass().toLowerCase();
        return typeRep.contains(searchText) || quesrep.contains(searchText) || descriptionRep.contains(searchText) ;
    });

    // Create a new table view to display the filtered data
    TableView<Reponse_ass> filteredTableView = new TableView<>();
    TableColumn<Reponse_ass, String> filteredquesrepColumn = new TableColumn<>("quesrep");
    filteredquesrepColumn.setCellValueFactory(new PropertyValueFactory<>("Que_Rep_Ass"));
    TableColumn<Reponse_ass, String> filteredtypeRepColumn = new TableColumn<>("typeRep");
    filteredtypeRepColumn.setCellValueFactory(new PropertyValueFactory<>("Type_Rep_Ass"));
    TableColumn<Reponse_ass, String> filtereddescriptionRepColumn = new TableColumn<>("descriptionRep");
    filtereddescriptionRepColumn.setCellValueFactory(new PropertyValueFactory<>("Description_Rec"));
    filteredTableView.getColumns().addAll(filteredquesrepColumn, filteredtypeRepColumn,filtereddescriptionRepColumn );
    filteredTableView.setItems(filteredList);

    // Set the table view to display the filtered data
    tablereponse.setItems(filteredList);
    tablereponse.refresh();
}
@FXML
private void modifierReponse(ActionEvent event) {
    Reponse_ass a = tablereponse.getSelectionModel().getSelectedItem();
    if (a == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une réponse à modifier");
        alert.showAndWait();
        return;
    }
    String req = "UPDATE Reponse_ass SET Que_Rep_Ass=?, Type_Rep_Ass=?, Description_Rep_Ass=? WHERE Id_Rep_Ass=?";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, txtQue_Rep_Ass.getText());
        pst.setString(2, txtType_Rep_Ass.getText());
        pst.setString(3, txtDescription_Rep_Ass.getText());
        pst.setInt(4, a.getId_Rep_Ass());
        pst.executeUpdate();
        System.out.println("Réponse modifiée !");
        affichertable();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
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
    private void handleSendEmail(ActionEvent event) {
    String description = txtDescription_Rep_Ass.getText();
    String type = txtType_Rep_Ass.getText();
    String question = txtQue_Rep_Ass.getText();
        emailreponse email = new emailreponse();
        email.envoyerreponse(description,type,question);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reponse envoyé");
        alert.setHeaderText(null);
        alert.setContentText("Le reponse a été envoyé avec succés");
        alert.showAndWait();
    }
    
}
    

    
    

