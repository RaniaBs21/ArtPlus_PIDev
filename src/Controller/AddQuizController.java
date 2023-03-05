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
import java.awt.event.ActionListener;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author nour
 */
public class AddQuizController implements Initializable {

    Quiz quiz = new Quiz();

    PreparedStatement st;
    ResultSet result;
    Connection cnx;
    int Myindex;
    int id_quiz;
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

    @FXML
    private TableColumn<Quiz, String> titre;
    @FXML
    private TextField questiontxt;
    @FXML
    private TextField option1txt;
    @FXML
    private TextField option2txt;
    @FXML
    private TextField option3txt;
    @FXML
    private TextField option4txt;

    Question q = new Question();
    Quiz r = new Quiz();

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
                    questiontxt.setText(tabView.getItems().get(Myindex).getQuestion());
                    option1txt.setText(tabView.getItems().get(Myindex).getOption1());
                    option2txt.setText(tabView.getItems().get(Myindex).getOption2());
                    option3txt.setText(tabView.getItems().get(Myindex).getOption3());
                    option4txt.setText(tabView.getItems().get(Myindex).getOption4());
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
        questiontxt.setText(String.valueOf(repdata.getQuestion()));
        option1txt.setText(String.valueOf(repdata.getOption1()));
        option2txt.setText(String.valueOf(repdata.getOption2()));
        option3txt.setText(String.valueOf(repdata.getOption3()));
        option4txt.setText(String.valueOf(repdata.getOption4()));
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
    private void delete(ActionEvent event) throws SQLException {
        cnx = MyConnection.getInstance().getCnx();

        // obtenir l'objet sélectionné dans le TableView
        Quiz selectedQuiz = tabView.getSelectionModel().getSelectedItem();

        // demander une confirmation avant de supprimer le quiz
        Alert confirmDialog = new Alert(AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmer la suppression");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("Êtes-vous sûr de vouloir supprimer le quiz '" + selectedQuiz.getTitre() + "' ?");
        Optional<ButtonType> dz = confirmDialog.showAndWait();

        if (dz.get() == ButtonType.OK) {

            String req = "DELETE FROM quiz WHERE id_quiz = " + id_quiz;
            Statement pst2 = cnx.createStatement();
            pst2.executeUpdate(req);
            QuizCRUD quizDAO = new QuizCRUD();
            quizDAO.supprimerQuiz(selectedQuiz.getId_quiz());
            
            tabView.getItems().remove(selectedQuiz);

            // afficher un message de succès
            Alert successDialog = new Alert(AlertType.INFORMATION);
            successDialog.setTitle("Quiz supprimé");
            successDialog.setHeaderText(null);
            successDialog.setContentText("Le quiz a été supprimé avec succès.");
            successDialog.showAndWait();
        }
        Quizclearbtn();
    }

    public void Quizclearbtn() {
        txt_quiz.setText("");
        questiontxt.setText("");
        option1txt.setText("");
        option2txt.setText("");
        option3txt.setText("");
        option4txt.setText("");
        reponse_correctecase.setText("");

    }

    @FXML
    private void update(ActionEvent event) throws SQLException {

        // Récupérer la ligne sélectionnée dans le TableView
        Quiz qui = tabView.getSelectionModel().getSelectedItem();

        // Récupérer les valeurs des champs de texte modifiés
        String titre = txt_quiz.getText();
        String question = questiontxt.getText();
        String option1 = option1txt.getText();
        String option2 = option2txt.getText();
        String option3 = option3txt.getText();
        String option4 = option4txt.getText();

        String reponse_correcte = reponse_correctecase.getText();

        // Mettre à jour l'objet Quiz avec les nouvelles valeurs
        qui.setTitre(titre);
        qui.setOption1(option1);
        qui.setOption2(option2);
        qui.setOption3(option3);
        qui.setOption4(option4);
        qui.setQuestion(question);
        qui.setReponse_correcte(reponse_correcte);

        // Exécuter la requête SQL pour mettre à jour la base de données
        Connection conn = MyConnection.getInstance().getCnx();
        String query = "UPDATE quiz SET titre=?, option1=?, option2=?, option3=?, option4=?, question=?, reponse_correcte=? WHERE id_quiz=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, titre);
        stmt.setString(2, option1);
        stmt.setString(3, option2);
        stmt.setString(4, option3);
        stmt.setString(5, option4);
        stmt.setString(6, question);
        stmt.setString(7, reponse_correcte);
        stmt.setInt(8, qui.getId_quiz());
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Le quiz a été modifié avec succès !");
        } else {
            JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la modification du quiz.");
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {

        String insertdata = "INSERT into quiz (titre,question,option1,option2,option3,option4,reponse_correcte)VALUES(?,?,?,?,?,?,?);";
        cnx = MyConnection.getInstance().getCnx();
        try {
            if (txt_quiz.getText().isEmpty() || questiontxt.getText().isEmpty() || option1txt.getText().isEmpty() || option2txt.getText().isEmpty() || option3txt.getText().isEmpty() || option4txt.getText().isEmpty() || reponse_correctecase.getText().isEmpty()) {
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
                    st.setString(2, questiontxt.getText());
                    st.setString(3, option1txt.getText());
                    st.setString(4, option2txt.getText());
                    st.setString(5, option3txt.getText());
                    st.setString(6, option4txt.getText());
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
}
