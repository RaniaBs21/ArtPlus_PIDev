/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import artplus.entities.Reponse_ass;
import artplus.services.Reponse_assCRUD;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author akrem
 */
public class FXMLReponseController implements Initializable {

    @FXML
    private TextField txtId_Rep_Ass;
    @FXML
    private TextField txtNum_Rep_Ass;
    @FXML
    private TextField txtType_Rep_Ass;
    @FXML
    private TextField txtDescription_Rep_Ass;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<Reponse_ass, Integer> numcolmn;
    @FXML
    private TableColumn<Reponse_ass, String> typecolmn;
    @FXML
    private TableColumn<Reponse_ass, String> descriptioncolmn;
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
    }    

    @FXML
    private void add(ActionEvent event) {
        Reponse_assCRUD repa = new Reponse_assCRUD();
        repa.ajouterreponse2(new Reponse_ass(Integer.parseInt(txtNum_Rep_Ass.getText()),txtDescription_Rep_Ass.getText(),txtType_Rep_Ass.getText() ));
    }

    @FXML
    private void update(ActionEvent event) {
       Reponse_assCRUD repa = new Reponse_assCRUD();
        repa.modifierreponse(new Reponse_ass(Integer.parseInt(txtNum_Rep_Ass.getText()),txtDescription_Rep_Ass.getText(),txtType_Rep_Ass.getText() ));
    }

    @FXML
    private void delete(ActionEvent event) {
         Reponse_assCRUD repa = new Reponse_assCRUD();
         repa.supprimerreponse(id);
    }
}
