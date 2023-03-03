package Controlleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ResultFXMLController {

@FXML
private Label remark;
@FXML
private Label marks;
private Label markstext;
@FXML
private Label correcttext;
private Label wrongtext;

// Ajouter une variable pour stocker le score
private int score;
    @FXML
    private Button playagain;
    @FXML
    private Button retourhome;


public void initialize(URL url, ResourceBundle rb) {
    
}

public void displayScore(int totalQuestions, int correctAnswers) {
    // Calculer le score en pourcentage
    score = (int) ((correctAnswers / (double) totalQuestions) * 100.0);

    // Afficher le score
    marks.setText(Integer.toString(score));
    markstext.setText("Score");

    // Afficher le texte en fonction du score
    if (score >= 80) {
        remark.setText("Excellent travail!");
    } else if (score >= 60) {
        remark.setText("Bon travail!");
    } else {
        remark.setText("Peut mieux faire.");
    }
    
    correcttext.setText("Correctes : " + correctAnswers);
    wrongtext.setText("Incorrectes : " + (totalQuestions - correctAnswers));
}

/**
 * Récupère le score du quiz.
 * @return le score du quiz
 */
public int getScore() {
    return score;
}

    @FXML
    private void PlayAgain(ActionEvent event) {
    }

    @FXML
    private void RetourHome(ActionEvent event) {
    }
}

