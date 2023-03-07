/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import jeu.JeuController;
import jeu.TriviaQuiz;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nour
 */
public class HomeController implements Initializable {

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
    private Button evenement131;
    @FXML
    private Button evenement1311;
    @FXML
    private Button playquizbtn;
    @FXML
    private ProgressBar progressBar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
  TriviaQuiz t = new TriviaQuiz();
   @FXML
    private Button btndelete1;
    @FXML
    private void cont(ActionEvent event) throws SQLException, IOException {
      btndelete1.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent event) {
        
        
        t.quiz();
    }
}); 
    }
   @FXML
    private Button btndelete11;
    @FXML
    private void cont1(ActionEvent event) throws SQLException, IOException {
      btndelete11.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent event) {
 
    JeuController j = new JeuController();
    
    }
}); 
    }
    @FXML
    private void Playquizbtn(ActionEvent event) {

     // Définir la progression de la barre à 0 au début
     progressBar.setProgress(0);

     // Créer une tâche pour simuler le chargement
     Task<Void> task = new Task<Void>() {
         @Override
         protected Void call() throws Exception {
             // Simuler le chargement en bouclant de 0 à 100
             for (int i = 0; i <= 100; i++) {
                 Thread.sleep(25);
                 updateProgress(i, 100);
             }
             return null;
         }
     };

     // Définir la barre de progression à observer la progression de la tâche
     progressBar.progressProperty().bind(task.progressProperty());

     // Créer une tâche secondaire pour charger le quiz en arrière-plan
     Task<Void> loadTask = new Task<Void>() {
         @Override
         protected Void call() throws Exception {
             // Charger le fichier FXML du quiz
             Parent quizRoot = FXMLLoader.load(getClass().getResource("/GUI/QuizFXML.fxml"));

             // Créer une nouvelle scène pour le quiz
             Scene quizScene = new Scene(quizRoot);

             // Récupérer la fenêtre actuelle
             Stage currentStage = (Stage) playquizbtn.getScene().getWindow();

             // Mettre à jour la fenêtre avec la nouvelle scène
             Platform.runLater(() -> {
                 currentStage.setScene(quizScene);
             });

             return null;
         }
     };

     // Lancer la tâche de chargement du quiz après la tâche de progression
     task.setOnSucceeded(e -> new Thread(loadTask).start());

     // Lancer la tâche de progression
     new Thread(task).start();
}
    }

