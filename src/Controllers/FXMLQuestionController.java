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
import entities.Question_ass;
import entities.Reponse_ass;
import services.Question_assCRUD;
import services.Reponse_assCRUD;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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

 
    
    
   
Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
        @FXML
    private Button btnback;
        
    @FXML
    private Button btnajouterQ;

    @FXML
    private Button btnajouterR;

    @FXML
    private Button btnmodifierQ;

    @FXML
    private Button btnmodifierR;

    @FXML
    private Button btnsupprimerQ;

    @FXML
    private Button btnsupprimerR;

    @FXML
    private TextField txtDescription_Q_Ass;

    @FXML
    private TextField txtDescription_Rep_Ass;

    @FXML
    private TextField txtQue_Rep_Ass;

    @FXML
    private TextField txtType_Q_Ass;

    @FXML
    private TextField txtType_Rep_Ass;
    
    
    
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
    private void addq(ActionEvent event) {
         Question_assCRUD q = new Question_assCRUD();
        if (txtDescription_Q_Ass.getText().trim().length() > 0 || txtType_Q_Ass.getText().trim().length() > 0 )
{
        q.ajouterquestion2(new Question_ass(txtType_Q_Ass.getText(),txtDescription_Q_Ass.getText() ));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("question");
            alert.setHeaderText(null);
            alert.setContentText("question ajouté avec succés! nous vous envoyons par courrier la réponse à cette question");
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
    private void updateq(ActionEvent event) {
      Question_assCRUD q = new Question_assCRUD();
              if (txtDescription_Q_Ass.getText().trim().length() > 0 || txtType_Q_Ass.getText().trim().length() > 0 )
              {
      q.modifierquestion(new Question_ass(txtType_Q_Ass.getText(),txtDescription_Q_Ass.getText() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("question");
            alert.setHeaderText(null);
            alert.setContentText("question modifié avec succés! nous vous envoyons par courrier la réponse à cette question");
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
    private void deleteq(ActionEvent event) {
         Question_assCRUD q = new Question_assCRUD();
               if (txtDescription_Q_Ass.getText().trim().length() > 0 || txtType_Q_Ass.getText().trim().length() > 0 )
              {
         q.supprimerquestion(new Question_ass(txtType_Q_Ass.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("question");
            alert.setHeaderText(null);
            alert.setContentText("question supprimé avec succés!");
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
    

