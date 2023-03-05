import artplus.entities.Evenement;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import static javafx.application.Application.launch;

public class AjoutEvenement extends Application {

    ArrayList<Evenement> evenements = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ajouter un événement");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Titre
        Label titreLabel = new Label("Titre:");
        GridPane.setConstraints(titreLabel, 0, 0);
        TextField titreInput = new TextField();
        GridPane.setConstraints(titreInput, 1, 0);

        // Description
        Label descLabel = new Label("Description:");
        GridPane.setConstraints(descLabel, 0, 1);
        TextField descInput = new TextField();
        GridPane.setConstraints(descInput, 1, 1);

        // Date
        Label dateLabel = new Label("Date:");
        GridPane.setConstraints(dateLabel, 0, 2);
        DatePicker dateInput = new DatePicker();
        GridPane.setConstraints(dateInput, 1, 2);

        // Catégorie
        Label catLabel = new Label("Catégorie:");
        GridPane.setConstraints(catLabel, 0, 3);
        TextField catInput = new TextField();
        GridPane.setConstraints(catInput, 1, 3);
        
        // adresse
        Label adresseLab = new Label("Adresse:");
        GridPane.setConstraints(adresseLab, 0, 3);
        TextField adresseInput = new TextField();
        GridPane.setConstraints(catInput, 1, 3);

        // Nombre de places
        Label placesLabel = new Label("Nombre de places:");
        GridPane.setConstraints(placesLabel, 0, 4);
        TextField placesInput = new TextField();
        GridPane.setConstraints(placesInput, 1, 4);

        Button ajouterButton = new Button("Ajouter");
        GridPane.setConstraints(ajouterButton, 1, 5);

        ajouterButton.setOnAction(e -> {
            String titre = titreInput.getText();
            String desc = descInput.getText();
            LocalDate date = dateInput.getValue();
            String cat = catInput.getText();
            String adresse = adresseInput.getText();
            int places = Integer.parseInt(placesInput.getText());

            if (evenementExiste(titre)) {
                System.out.println("Cet événement existe déjà.");
            } else {
                Evenement evenement = new Evenement(titre,cat , desc, date, places);
                evenements.add(evenement);
                System.out.println("Événement ajouté avec succès.");
            }
        });

        grid.getChildren().addAll(titreLabel, titreInput, descLabel, descInput, dateLabel, dateInput,
                catLabel, catInput, placesLabel, placesInput, ajouterButton);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean evenementExiste(String titre) {
        for (Evenement evenement : evenements) {
            if (evenement.getTitre_ev().equals(titre)) {
                return true;
            }
        }
        return false;
    }
}