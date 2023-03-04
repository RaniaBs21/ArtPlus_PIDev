/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import artplus.entities.Reponse_User;
import artplus.services.Reponse_UserCRUD;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author nour
 */
public class QuizController implements Initializable {
 static int counter = 0;
    static int correct = 0;
    static int wrong = 0;
    @FXML
    private Button evenement11;
    @FXML
    private Button evenement1;
    @FXML
    private Button evenement;
    @FXML
    private Button evenement12;
    @FXML
    private Button evenement13;
    @FXML
    private Label question;
    @FXML
    private Button opt1;
    @FXML
    private Button opt3;
    @FXML
    private Button opt4;
    @FXML
    private Button opt2;
    @FXML
    private Button nextbtn;
    Button btn;
    @FXML
    private Button boutton;
    int id_quiz;
    String reponse;
    @FXML
    private Label timerLabel;
     private Timeline timeline;
    private int secondsElapsed;
    @FXML
    private Button evenement131;
    @FXML
    private Button evenement1311;

    
 

    

    private void initialize() {
        loadQuestions();
    }
    

    private void loadQuestions() {
        startTimer();

        if (counter == 0) { //Question 1
            question.setText("1. Qui a écrit La Chartreuse de Parme ??");
            opt1.setText("Balzak");
            opt2.setText("Stendhal");
            opt3.setText("Flaubert");
            opt4.setText("Marivaux");
        }
        if (counter == 1) { //Question 2
            question.setText("2. Le Parthénon se trouve :?");
            opt1.setText("En Grèce");
            opt2.setText("En Crète");
            opt3.setText("En Sicile");
            opt4.setText("En France");
        }
        if (counter == 2) { //Question 3
            question.setText("3. Qui a composé Le Boléro ?");
            opt1.setText("Camille Saint-Saens");
            opt2.setText("Maurce Ravel");
            opt3.setText("Serge prokofiev");
            opt4.setText("Mark Rthko");
        }
        if (counter == 3) { //Question 4
            question.setText("4. Qui a peint le plafond de la chapelle Sixtine ?");
            opt1.setText("Leonardo de Vinci");
            opt2.setText("Michel_Ange");
            opt3.setText("Véronèse");
            opt4.setText("Claude Monet");
        }
        if (counter == 4) {//Question 5
            question.setText("5. Quel cinéaste a réalisé le film E.T. en 1982?");
            opt1.setText("James Cameron");
            opt2.setText("Georges Lucas");
            opt3.setText("David Lynch");
            opt4.setText("Steven Spielber");
        }
        if (counter == 5) { //Question 6
            question.setText("6.Qui a peint le tableau intitulé Le Radeau de la Méduse ?");
            opt1.setText("David");
            opt2.setText("Gericault");
            opt3.setText("Ingers");
            opt4.setText("Paul Cézanne");
        }
        if (counter == 6) { //Question 7
            question.setText("7. Maurice Bejart était :");
            opt1.setText("Un danseur Français");
            opt2.setText("Un compositeur de musique");
            opt3.setText("Un metteur en scéne");
            opt4.setText("Un peinteur");
        }
        if (counter == 7) { //Question 8
            question.setText("8. Laquelle de ces couleurs n’est pas une couleur primaire ou fondamentale en peinture ?");
            opt1.setText("Rouge");
            opt2.setText("Vert");
            opt3.setText("Bleu");
            opt4.setText("Orangé");
        }
        if (counter == 8) { //Question 9
            question.setText("9.Qui est le réalisateur du film Titanic sorti en 1997 ?");
            opt1.setText("Ridley Scott");
            opt2.setText("James Cameron");
            opt3.setText("Steven Speilberg");
            opt4.setText("Jackson Polloch");
        }
        if (counter == 9) { //Question 10
            question.setText("10.Qui a écrit La Bête humaine ?");
            opt1.setText("Emile Zola");
            opt2.setText("Gustave Flaubert");
            opt3.setText("Gu de Maupassant");
            opt4.setText("Victor Hugo");
        }

    }


    @FXML
    public void opt1clicked(ActionEvent event) {
        secondsElapsed = 30;
        timeline.stop();
      
    
        checkAnswer(opt1.getText());
        if (checkAnswer(opt1.getText())) {
            correct += 1;
        } else {
            wrong += 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/view/ResultFXML.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
            }
        } else {
            counter++;
            loadQuestions();
             Reponse_UserCRUD az=new Reponse_UserCRUD();
            Reponse_User reponse=new Reponse_User();
               az.enregistrerReponseUtilisateur(reponse);
        }

    }

    boolean checkAnswer(String answer) {

        if (counter == 0) {
            if (answer.equals("Stendhal")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 1) {
            if (answer.equals("En Grèce")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 2) {
            if (answer.equals("Maurce Ravel")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 3) {
            if (answer.equals("Michel_Ange")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 4) {
            if (answer.equals("Steven Spielber")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 5) {
            if (answer.equals("Gericault")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 6) {
            if (answer.equals("Un danseur Français")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 7) {
            if (answer.equals("Bleu")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 8) {
            if (answer.equals("James Cameron")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 9) {
            if (answer.equals("Emile Zola")) {
                return true;
            } else {
                return false;
            }
        }
        return false;


    }

    @FXML
    public void opt2clicked(ActionEvent event) {
                secondsElapsed = 30;
        timeline.stop();

        checkAnswer(opt2.getText());
        if (checkAnswer(opt2.getText())) {
            correct += 1;
        } else {
            wrong += 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/view/ResultFXML.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
            }
        } else {
            counter++;
            loadQuestions();
             Reponse_UserCRUD az=new Reponse_UserCRUD();
            Reponse_User reponse=new Reponse_User();
               az.enregistrerReponseUtilisateur(reponse);
        }
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
         secondsElapsed =30;
        timeline.stop();
      
        checkAnswer(opt3.getText());
        if (checkAnswer(opt3.getText())) {
            correct += 1;
        } else {
            wrong += 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/view/ResultFXML.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
            }
        } else {
            counter++;
            loadQuestions();
             Reponse_UserCRUD az=new Reponse_UserCRUD();
            Reponse_User reponse=new Reponse_User();
               az.enregistrerReponseUtilisateur(reponse);
        }
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        secondsElapsed = 30;
        timeline.stop();
        
        checkAnswer(opt4.getText());
        if (checkAnswer(opt4.getText())) {
            correct += 1;
        } else {
            wrong += 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("/view/ResultFXML.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
            } catch(IOException e) {
            }
        } else {
            counter++;
            loadQuestions();
              Reponse_UserCRUD az=new Reponse_UserCRUD();
            Reponse_User reponse=new Reponse_User();
               az.enregistrerReponseUtilisateur(reponse);
            
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
          startTimer();
          stopTimer();
        loadQuestions();
    }
    

    @FXML
    private void Nextbtn(ActionEvent event) {
    }

    @FXML
    private void bouttonValider(ActionEvent event) {
    }
     private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
           
            timerLabel.setText("Time: " + secondsElapsed +"");
             secondsElapsed--;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
          timeline.playFromStart();        
    }

    private void stopTimer() {
        timeline.stop();
    }

}

