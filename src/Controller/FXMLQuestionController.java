/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import artplus.entities.Question_ass;
import artplus.services.Question_assCRUD;
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
public class FXMLQuestionController implements Initializable {

    @FXML
    private TextField txtId_Q_Ass;
    @FXML
    private TextField txtNum_Q_Ass;
    @FXML
    private TextField txtType_Q_Ass;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<Question_ass, Integer> numcolmn;
    @FXML
    private TableColumn<Question_ass, String> typecolmn;
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
        Question_assCRUD q = new Question_assCRUD();
        q.ajouterquestion2(new Question_ass(Integer.parseInt(txtNum_Q_Ass.getText()),txtType_Q_Ass.getText() ));
    }

    @FXML
    private void update(ActionEvent event) {
      Question_assCRUD q = new Question_assCRUD();
        q.modifierquestion(new Question_ass(Integer.parseInt(txtNum_Q_Ass.getText()),txtType_Q_Ass.getText() ));
    }

    @FXML
    private void delete(ActionEvent event) {
    Question_assCRUD q = new Question_assCRUD();
         q.supprimerquestion(id);
    }
    
}
