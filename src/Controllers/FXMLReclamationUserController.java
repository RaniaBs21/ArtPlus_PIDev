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
    private TextField txtDescription_Rec;

    @FXML
    private TextField txtType_Rec;


    

   
 
    
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


    }    
@FXML
   private void add(ActionEvent event) {
    // Create a new PDF document
    Document document = new Document();

    try {
        
        ReclamationCRUD rec = new ReclamationCRUD();
        rec.ajouterreclamation2(new Reclamation(txtType_Rec.getText(),txtDescription_Rec.getText() ));
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
 
    }
    


     @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Interface_utilisateur.fxml"));
        Parent root = loader.load();
         btnback.getScene().setRoot(root);
    }
    
    

}




    


