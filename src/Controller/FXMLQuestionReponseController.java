/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import artplus.entities.Question_ass;
import artplus.entities.Reponse_ass;
import artplus.services.Question_assCRUD;
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
public class FXMLQuestionReponseController implements Initializable {

 
    
    
   
Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
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

    private void add(ActionEvent event) {
        Question_assCRUD q = new Question_assCRUD();
        q.ajouterquestion2(new Question_ass(txtDescription_Q_Ass.getText(),txtType_Q_Ass.getText() ));
    }

    private void update(ActionEvent event) {
      Question_assCRUD q = new Question_assCRUD();
        q.modifierquestion(new Question_ass(txtDescription_Q_Ass.getText(),txtType_Q_Ass.getText() ));
    }

    private void delete(ActionEvent event) {
    Question_assCRUD q = new Question_assCRUD();
         q.supprimerquestion(id);
    }

    @FXML
    private void addq(ActionEvent event) {
                 Question_assCRUD q = new Question_assCRUD();
        if (txtDescription_Q_Ass.getText().trim().length() > 0 || txtType_Q_Ass.getText().trim().length() > 0 )
{
        q.ajouterquestion2(new Question_ass(txtDescription_Q_Ass.getText(),txtType_Q_Ass.getText() ));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("question");
            alert.setHeaderText(null);
            alert.setContentText("question ajouté avec succés!");
            alert.show();
}
else
{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("erreur");
           alert.setHeaderText(null);
           alert.setContentText("error lors de saisie du champ(");
           alert.show();
}

    }
    

    @FXML
    private void updateq(ActionEvent event) {
      Question_assCRUD q = new Question_assCRUD();
              if (txtDescription_Q_Ass.getText().trim().length() > 0 || txtType_Q_Ass.getText().trim().length() > 0 )
              {
      q.modifierquestion(new Question_ass(txtDescription_Q_Ass.getText(),txtType_Q_Ass.getText() ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("question");
            alert.setHeaderText(null);
            alert.setContentText("question modifié avec succés!");
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
         q.supprimerquestion(id);
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
    private void addr(ActionEvent event) {
        Reponse_assCRUD repa = new Reponse_assCRUD();
        if (txtQue_Rep_Ass.getText().trim().length() > 0 || txtDescription_Rep_Ass.getText().trim().length() > 0 || txtType_Rep_Ass.getText().trim().length() > 0 ) {
            repa.ajouterreponse2(new Reponse_ass(txtQue_Rep_Ass.getText(),txtDescription_Rep_Ass.getText(),txtType_Rep_Ass.getText() ));
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
    }
    @FXML
    private void updater(ActionEvent event) {
         Reponse_assCRUD repa = new Reponse_assCRUD();
        if (txtQue_Rep_Ass.getText().trim().length() > 0 || txtDescription_Rep_Ass.getText().trim().length() > 0 || txtType_Rep_Ass.getText().trim().length() > 0 ) {
            repa.modifierreponse(new Reponse_ass(txtQue_Rep_Ass.getText(),txtDescription_Rep_Ass.getText(),txtType_Rep_Ass.getText() ));
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
    }

    @FXML
    private void deleter(ActionEvent event) {
         Reponse_assCRUD repa = new Reponse_assCRUD();
        if (txtQue_Rep_Ass.getText().trim().length() > 0 || txtDescription_Rep_Ass.getText().trim().length() > 0 || txtType_Rep_Ass.getText().trim().length() > 0 ) {
          repa.supprimerreponse(id);
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
    }
    }
    

