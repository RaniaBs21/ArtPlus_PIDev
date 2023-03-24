/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author akrem
 */
public class FXMLReclamationUserController implements Initializable {

    
    @FXML
    private TextField txtid_user;

    
    @FXML
    private Button btnback;
 
    @FXML
    private Button btnajouter;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnsupprimer;
    
        @FXML
    private Button btnrecuser;
        
            @FXML
    private Button btnquerep;


       @FXML
    private Button searchbtn;

    @FXML
    private TextField searchtxt;
    

    
    @FXML
    private TextField txtDescription_Rec;

    @FXML
    private TextField txtType_Rec;


    @FXML
    private TableColumn<Reclamation, String> descriptioncolomn;

    @FXML
    private TableView<Reclamation> tablereclamation;
    
    
    @FXML
    private TableColumn<Reclamation, String> typecolomn;
private ObservableList<Reclamation> reclamationList;
   
 
    
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

        Connect();
        typecolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Type_Rec"));
        descriptioncolomn.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Description_Rec"));
        affichertable();
     reclamationList = FXCollections.observableArrayList(tablereclamation.getItems());
    btnmodifier.setOnAction(this::handleModifierButtonClick);

    }   
    
    
    
    public void modifierreclamation(Reclamation r) {
    String req = "UPDATE  reclamation SET Type_Rec=?, Description_Rec=? WHERE Id_Rec=?";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(3, r.getId_Rec());
        pst.setString(1, r.getType_Rec());
        pst.setString(2, r.getDescription_Rec());
        pst.executeUpdate();
        System.out.println("reclamation modifiée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


@FXML
private void handleModifierButtonClick(ActionEvent event) {
    Reclamation selectedReclamation = tablereclamation.getSelectionModel().getSelectedItem();
    if (selectedReclamation != null) {
        // Get the updated reclamation information from the text fields
        String typeRec = txtType_Rec.getText();
        String descriptionRec = txtDescription_Rec.getText();
        Reclamation updatedReclamation = new Reclamation(selectedReclamation.getId_Rec(), typeRec, descriptionRec);

        // Update the reclamation in the database
        modifierreclamation(updatedReclamation);

        // Refresh the table view to show the updated data
        affichertable();
    } else {
        // Display an error message if no reclamation is selected
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une réclamation à modifier.");
        alert.showAndWait();
    }
}

    @FXML
private void searchReclamation(ActionEvent event) {
    // Get the search text and convert it to lowercase
    String searchText = searchtxt.getText().toLowerCase();

    // Create a filtered list for the search results
    FilteredList<Reclamation> filteredList = new FilteredList<>(reclamationList);

    // Loop through each item in the table view and add it to the filtered list if it matches the search text
    filteredList.setPredicate(reclamation -> {
        String typeRec = reclamation.getType_Rec().toLowerCase();
        String descriptionRec = reclamation.getDescription_Rec().toLowerCase();
        return typeRec.contains(searchText) || descriptionRec.contains(searchText);
    });

    // Create a new table view to display the filtered data
    TableView<Reclamation> filteredTableView = new TableView<>();
    TableColumn<Reclamation, String> filteredTypeColumn = new TableColumn<>("Type");
    filteredTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type_Rec"));
    TableColumn<Reclamation, String> filteredDescriptionColumn = new TableColumn<>("Description");
    filteredDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description_Rec"));
    filteredTableView.getColumns().addAll(filteredTypeColumn, filteredDescriptionColumn);
    filteredTableView.setItems(filteredList);

    // Set the table view to display the filtered data
    tablereclamation.setItems(filteredList);
    tablereclamation.refresh();
}
    
    
    
    
@FXML
   private void add(ActionEvent event) {
    // Create a new PDF document
    Document document = new Document();

    try {
        
       ReclamationCRUD rec = new ReclamationCRUD();
    String description = txtDescription_Rec.getText();
    String type = txtType_Rec.getText();
    int id_user = Integer.parseInt(txtid_user.getText());
    rec.ajouterreclamation2(new Reclamation(id_user, type, description));
        // Create a PdfWriter object to write the document to a file or stream
        PdfWriter.getInstance(document, new FileOutputStream("reclamation.pdf"));

        // Open the document
        document.open();

        // Add the type and description of the reclamation to the document
        document.add(new Paragraph("Type: " + txtType_Rec.getText()));
        document.add(new Paragraph("Description: " + txtDescription_Rec.getText()));

        // Close the document
        document.close();

        // Display a success message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reclamation");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation ajouté avec succés et un PDF a été généré!");
        alert.show();
    } catch (FileNotFoundException | DocumentException e) {
        e.printStackTrace();
    }
            affichertable();

}


    @FXML
    private void update(ActionEvent event) {
            ReclamationCRUD rec = new ReclamationCRUD();
        if (txtDescription_Rec.getText().trim().length() > 0 || txtType_Rec.getText().trim().length() > 0 ) {
        rec.modifierreclamation(new Reclamation(Integer.parseInt(txtid_user.getText()),txtType_Rec.getText(),txtDescription_Rec.getText() ));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Interface_utilisateur.fxml"));
        Parent root = loader.load();
         btnback.getScene().setRoot(root);
    }
    
    

}




    


