/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import artplus.entities.Question;
import artplus.entities.Quiz;
import artplus.services.QuizCRUD;
import artplus.utils.MyConnection;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nour
 */
public class AddReponseController implements Initializable {
    Quiz quiz =new Quiz();

    PreparedStatement st;
    ResultSet result;
    Connection cnx;
    int Myindex;
    int id_quiz;

    @FXML
    private TextField option1;
    @FXML
    private TextField option2;
    @FXML
    private TextField option3;
    @FXML
    private TextField option4;
    @FXML
    private TextField question;
    @FXML
    private Button addNextQuestion;
    @FXML

    private TableView<Quiz> tabView;
    @FXML
    private TableColumn<Quiz, String> id_question;
    @FXML
    private TableColumn<Quiz, String> id_option1;
    @FXML
    private TableColumn<Quiz, String> id_option2;
    @FXML
    private TableColumn<Quiz, String> id_option3;
    @FXML
    private TableColumn<Quiz, String> id_option4;

    @FXML
    private TableColumn<Quiz, String> reponse_correctetxt;

    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

    @FXML
    private Button btnajouter;
    @FXML
    private TextField reponse_correctecase;
    @FXML
    private TextField txt_quiz;

    Question q = new Question();
    Quiz r = new Quiz();

    private int id_rep;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TextField id_quizs;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx = MyConnection.getInstance().getCnx();
        afficherQuiz();
        QuizShowData();
     
       
        

    }

    public ObservableList<Quiz> afficherQuiz() {
        ObservableList<Quiz> data = FXCollections.observableArrayList();

        String sql = "select * from quiz";
        try {
            st = cnx.prepareStatement(sql);
            result = st.executeQuery();
            Quiz repdata;

            while (result.next()) {
                repdata = new Quiz(result.getString("titre"), result.getString("question"), result.getString("option1"), result.getString("option2"), result.getString("option3"), result.getString("option4"), result.getString("reponse_correcte"));
                data.addAll(repdata);
            }

        } catch (SQLException e) {

        }

        tabView.setRowFactory(tv -> {
            TableRow<Quiz> myrow = new TableRow<>();
            myrow.setOnMouseClicked(event
                    -> {
                if (event.getClickCount() == 1 && (!myrow.isEmpty())) {

                    Myindex = tabView.getSelectionModel().getSelectedIndex();
                    txt_quiz.setText(tabView.getItems().get(Myindex).getTitre());
                    question.setText(tabView.getItems().get(Myindex).getQuestion());
                    option1.setText(tabView.getItems().get(Myindex).getOption1());
                    option2.setText(tabView.getItems().get(Myindex).getOption2());
                    option3.setText(tabView.getItems().get(Myindex).getOption3());
                    option4.setText(tabView.getItems().get(Myindex).getOption4());
                    reponse_correctecase.setText(tabView.getItems().get(Myindex).getReponse_correcte());

                }
            }
            );
            return myrow;

        }
        );
        return data;
    }

    private ObservableList<Quiz> dataQuiz;

    public void QuizShowData() {
        dataQuiz = afficherQuiz();
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        id_question.setCellValueFactory(new PropertyValueFactory<>("question"));
        id_option1.setCellValueFactory(new PropertyValueFactory<>("option1"));
        id_option2.setCellValueFactory(new PropertyValueFactory<>("option2"));
        id_option3.setCellValueFactory(new PropertyValueFactory<>("option3"));
        id_option4.setCellValueFactory(new PropertyValueFactory<>("option4"));
        reponse_correctetxt.setCellValueFactory(new PropertyValueFactory<>("reponse_correcte"));
        tabView.setItems(dataQuiz);
    }

    public void reponseSelectedData() {
        Quiz repdata = tabView.getSelectionModel().getSelectedItem();
        int num = tabView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        txt_quiz.setText(String.valueOf(repdata.getTitre()));
        question.setText(String.valueOf(repdata.getQuestion()));
        option1.setText(String.valueOf(repdata.getOption1()));
        option2.setText(String.valueOf(repdata.getOption2()));
        option3.setText(String.valueOf(repdata.getOption3()));
        option4.setText(String.valueOf(repdata.getOption4()));
        reponse_correctecase.setText(String.valueOf(repdata.getReponse_correcte()));

    }

    @FXML
    private void addNextQuestion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddQuizFXML.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML

   private void update(ActionEvent event) {
       
   try {
       
        if (titre.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setContentText("Veuillez entrer tous les champs");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setContentText("Voulez-vous confirmer la modification : " + titre.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if (option.get().equals(ButtonType.OK)) {
                
                  
                String reqModif = "UPDATE quiz SET titre=?, question=?, option1=?, option2=?, option3=?, option4=?, reponse_correcte=? WHERE id_quiz = ?";
                st = cnx.prepareStatement(reqModif);
               
                st.setString(1, titre.getText());
                st.setString(2, id_question.getText());
                st.setString(3, id_option1.getText());
                st.setString(4, id_option2.getText());
                st.setString(5, id_option3.getText());
                st.setString(6, id_option4.getText());
                st.setString(7, reponse_correctetxt.getText());
                st.setInt(8, id_quiz);
               
                 st.executeUpdate();
                
                QuizShowData();
                Quizclearbtn();
                
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setContentText(null);
                alert.setContentText("Quiz modifié");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setContentText(null);
                alert.setContentText("Annulé");
                alert.showAndWait();
            }
        }
            

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());

    }
}


    @FXML
    private void ajouter(ActionEvent event) {

        String insertdata = "INSERT into quiz (titre,question,option1,option2,option3,option4,reponse_correcte)VALUES(?,?,?,?,?,?,?);";
        cnx = MyConnection.getInstance().getCnx();
        try {
            if (txt_quiz.getText().isEmpty() || question.getText().isEmpty() || option1.getText().isEmpty() || option2.getText().isEmpty() || option3.getText().isEmpty() || option4.getText().isEmpty() || reponse_correctecase.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setContentText("Veuillez Enter tous les chapms");
                alert.showAndWait();
            } else {
                String checkData = "select titre from quiz where titre = " + txt_quiz.getText();
                PreparedStatement ps = cnx.prepareStatement(checkData);
                if (result.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setContentText("Titre:" + txt_quiz.getText() + " le Titre est déja existant");
                    alert.showAndWait();

                } else {
                    st = cnx.prepareStatement(insertdata);
                    st.setString(1, txt_quiz.getText());
                    st.setString(2, question.getText());
                    st.setString(3, option1.getText());
                    st.setString(4, option2.getText());
                    st.setString(5, option3.getText());
                    st.setString(6, option4.getText());
                    st.setString(7, reponse_correctecase.getText());
                    st.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("Quiz Ajouté avec suucées");
                    alert.showAndWait();
                    QuizShowData();
                    Quizclearbtn();
                }
            }

        } catch (SQLException ex) {
        }

    }

    @FXML
   private void delete(ActionEvent event) {
  
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("");
    alert.setContentText("Voulez-vous vraiment supprimer cette ligne ?");
    Optional<ButtonType> option = alert.showAndWait();
    
    if (option.get().equals(ButtonType.OK)) {
        // Obtenir l'objet Quiz correspondant à la ligne sélectionnée
       Quiz repdata = tabView.getSelectionModel().getSelectedItem();
        
        try {
            // Construire la requête SQL DELETE correspondante
            String reqDelete = "DELETE FROM quiz WHERE id_quiz = ?";
            st = cnx.prepareStatement(reqDelete);
            st.setInt(1, repdata.getId_quiz());
           
            st.executeUpdate();
            tabView.getItems().remove(quiz);
            // Afficher un message de confirmation
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Message");
            alert2.setContentText("La ligne a été supprimée avec succès");
            alert2.showAndWait();
            
            // Mettre à jour l'affichage de la table
            
            tabView.refresh();
            QuizShowData();
             
        }
        
        
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

        
    

    public void Quizclearbtn() {
        txt_quiz.setText("");
        question.setText("");
        option1.setText("");
        option2.setText("");
        option3.setText("");
        option4.setText("");
        reponse_correctecase.setText("");

    }
}
