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
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author akrem
 */
public class FXMLReclamationController implements Initializable {


    @FXML
    private TextField txtNum_Rec;
    @FXML
    private TextField txtType_Rec;
    @FXML
    private TextField txtDescription_Rec;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, Integer> numcolmn;
    @FXML
    private TableColumn<Reclamation, String> typecolmn;
    @FXML
    private TableColumn<Reclamation, String> descriptioncolmn;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;

    

   
 
    
Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    
    
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
    }    
 
    @FXML
    private void add(ActionEvent event) {
         ReclamationCRUD rec = new ReclamationCRUD();
        rec.ajouterreclamation2(new Reclamation(Integer.parseInt(txtNum_Rec.getText()),txtDescription_Rec.getText(),txtType_Rec.getText() ));
    }

    @FXML
    private void update(ActionEvent event) {
        ReclamationCRUD rec = new ReclamationCRUD();
        rec.modifierreclamation(new Reclamation(Integer.parseInt(txtNum_Rec.getText()),txtDescription_Rec.getText(),txtType_Rec.getText() ));
    }

    @FXML
    private void delete(ActionEvent event) {
         ReclamationCRUD rec = new ReclamationCRUD();
         rec.supprimerreclamation(id);
    }
    
}
