/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import artplus.entities.Question;
import artplus.entities.Reponse;
import artplus.services.Reponse_QuizCRUD;
import artplus.utils.MyConnection;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javafx.scene.control.TableColumn;
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

    PreparedStatement st;
    ResultSet result;
    Connection cnx;

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

    private TableView<Reponse> tabView;
    @FXML
    private TableColumn<Reponse, String> id_question;
    @FXML
    private TableColumn<Reponse, String> id_option1;
    @FXML
    private TableColumn<Reponse, String> id_option2;
    @FXML
    private TableColumn<Reponse, String> id_option3;
    @FXML
    private TableColumn<Reponse, String> id_option4;
    private TableColumn<Reponse, Integer> id_repCl;
    @FXML
    private TableColumn<Reponse, String> reponse_correctetxt;
    private TableColumn<Reponse, String> id_quiz;

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
    Reponse r = new Reponse();

    private int id_rep;
    @FXML
    private TableColumn<?, ?> titre;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx = MyConnection.getInstance().getCnx();
        afficherReponse();
    }

    public ObservableList<Reponse> afficherReponse() {
        ObservableList<Reponse> data = FXCollections.observableArrayList();

        String sql = "select * from reponse_quiz";
        try {
            st = cnx.prepareStatement(sql);
            result = st.executeQuery();
            Reponse repdata;

            while (result.next()) {
                repdata = new Reponse(result.getString("option1"), result.getString("option2"), result.getString("option3"), result.getString("option4"), result.getString("reponse_correcte"));
                data.addAll(repdata);
            }

        } catch (SQLException e) {

        }
        return data;
    }

    private ObservableList<Reponse> dataReponse;

    public void ReponseShowData() {
        dataReponse = afficherReponse();
        id_quiz.setCellValueFactory(new PropertyValueFactory<>("id_quiz"));
        id_question.setCellValueFactory(new PropertyValueFactory<>("id_quest"));
        id_option1.setCellValueFactory(new PropertyValueFactory<>("option1"));
        id_option2.setCellValueFactory(new PropertyValueFactory<>("option2"));
        id_option3.setCellValueFactory(new PropertyValueFactory<>("option3"));
        id_option4.setCellValueFactory(new PropertyValueFactory<>("option4"));
        reponse_correctetxt.setCellValueFactory(new PropertyValueFactory<>("reponse_correcte"));
        tabView.setItems(dataReponse);
    }

    public void reponseSelectedData() {
        Reponse repdata = tabView.getSelectionModel().getSelectedItem();
        int num = tabView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        question.setText(String.valueOf(repdata.getQuestion()));
        option1.setText(String.valueOf(repdata.getOption1()));
        option2.setText(String.valueOf(repdata.getOption2()));
        option3.setText(String.valueOf(repdata.getOption3()));
        option4.setText(String.valueOf(repdata.getOption4()));
        reponse_correctecase.setText(String.valueOf(repdata.getReponse_correcte()));

    }

    @FXML
    private void addNextQuestion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddReponseFXML.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML

    private void update(ActionEvent event) {
        Reponse reponse = tabView.getSelectionModel().getSelectedItem();
        if (reponse != null) {
            String titre = id_quiz.getText();
           
            String option1 = id_option1.getText();
            String option2 = id_option2.getText();
            String option3 = id_option3.getText();
            String option4 = id_option4.getText();
            String questions = question.getText();
            String reponse_correcte = reponse_correctecase.getText();

            String reqModif = "UPDATE reponse_quiz SET id_quiz = '" + r.getQuiz().getId_quiz() + "', id_quest = '" + r.getQuestion().getId_quest() + "', option1 = '" + r.getOption1() + "', option2 = '" + r.getOption2() + "', option3 = '" + r.getOption3() + "', option4 = '" + r.getOption4() + "', reponse_correcte = '" + r.getReponse_correcte() + "' WHERE reponse_quiz.`id_rep` = " + r.getId_rep();
            try {

                st.executeUpdate(reqModif);
                System.out.println("participation updated !");

                question.setText("");
                
                id_option1.setText("");
                id_option2.setText("");
                id_option3.setText("");
                id_option4.setText("");
                reponse_correctecase.setText("");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Reponse Modifié avec succées", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        Connection cnx2;
        String insertdata = "INSERT into reponse_quiz(id_quiz,option1,option2,option3,option4,reponse_correcte,id_quest)VALUES(?,?,?,?,?,?,?);";
        cnx2 = MyConnection.getInstance().getCnx();
        try {
            if (txt_quiz.getText().isEmpty() || question.getText().isEmpty() || option1.getText().isEmpty() || option2.getText().isEmpty() || option3.getText().isEmpty() || option4.getText().isEmpty() || reponse_correctecase.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setContentText("Veuillez Enter tous les chapms");
                alert.showAndWait();
            } else {
                String checkData = "select titre from quiz where titre = " + txt_quiz.getText();
                PreparedStatement ps = cnx2.prepareStatement(checkData);
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
                    ReponseShowData();
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void tabEvent(MouseEvent event) {

        Reponse reponse = tabView.getSelectionModel().getSelectedItem();
        String sql = "SELECT id_quiz, id_quest, option1, option2, option3, option4, reponse_correcte\n"
                + "FROM reponse_quiz\n"
                + "WHERE id_quiz = '\" + r.getQuiz().getId_quiz() + \"'\n"
                + "AND id_quest = '\" + r.getQuestion().getId_quest() + \"'\n"
                + "AND option1 = '\" + r.getOption1()+ \"'\n"
                + "AND option2 = '\" + r.getOption2()+  \"'\n"
                + "AND option3 = '\" + r.getOption3()+ \"'\n"
                + "AND option4 = '\" + r.getOption4()+ \"'\n"
                + "AND reponse_correcte = '\" + r.getReponse_correcte()+\"'\n"
                + "AND id_rep = \" + r.getId_rep();";
        try {
            st = cnx.prepareStatement(sql);
            result = st.executeQuery();
            while (result.next()) {
                id_option1.setText(result.getString("option1"));
                id_option1.setText(result.getString("option1"));
                id_option2.setText(result.getString("option2"));
                id_option3.setText(result.getString("option3"));
                id_option4.setText(result.getString("option4"));
                question.setText(result.getString("question"));
                reponse_correctecase.setText(String.valueOf("reponse_correcte"));
                id_option1.setText(result.getString("option1"));

            }
        } catch (SQLException e) {
        }

        try {
            String sq12 = "select id_quiz,id_quest,option1,option2,option3,option4,  from reponse_question, quiz, question_quiz where reponse_quiz.quiz.=quiz.id_quiz where reponse_quiz.question_quiz=question_quiz.id_questegion type where id_rep='" + id_repCl.getText() + ":";
            st = cnx.prepareStatement(sq12);
            st.setInt(1, reponse.getId_quiz());
            st.setInt(2, reponse.getId_quest());
            result = st.executeQuery();
            while (result.next()) {
                int id_rep = result.getInt("Question");
                result = st.executeQuery();
                id_option1.setText(result.getString("option1"));
                id_option2.setText(result.getString("option1"));
                id_option3.setText(result.getString("option1"));
                id_option4.setText(result.getString("option1"));
                question.setText(result.getString("question"));
                reponse_correctecase.setText(result.getString("reponse"));

            }
        } catch (SQLException e) {
        }
    }

    @FXML
    private void delete(ActionEvent event) {

        try {
            String req = "DELETE FROM reponse_quiz WHERE id_rep = " + id_rep;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse supprimé avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
