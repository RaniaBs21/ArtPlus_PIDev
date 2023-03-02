/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Cours;
import artplus.services.CoursServices;
import artplus.utils.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjoutCoursController implements Initializable {

    Connection cnx;
    @FXML
    private Button ajouter;
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
    private TextField files;

    @FXML
    private Button selectFileButton;

    String selectedFilePath;
    @FXML
    private Button deposer;
    private TextField Titre_c;
    private TextField Description_c;
    private TextField Sous_categorie;
    private TextField Niveau_c;
    private TextField Fichier_c;
    @FXML
    private TableView<Cours> tablecours;
    @FXML
    private TableColumn<Cours, String> titre;
    @FXML
    private TableColumn<Cours, String>  sous_c;
    @FXML
    private TableColumn<Cours, String> niveau;
    @FXML
    private TableColumn<Cours, String>  fichier;
    @FXML
    private TableColumn<Cours, String>  description;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private Button supprimerc;
    @FXML
    private Button modifierc;
    @FXML
    private TextField idsuppc;
    @FXML
    private TextField idmodifc;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TextField title;
    @FXML
    private TextField sccours;
    @FXML
    private TextField levelc;
    @FXML
    private TextField fichierc;
    @FXML
    private TextField desc;
    @FXML
    private TextField txtidcours;
    @FXML
    private Label labelcours;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ajouter.setOnAction(event -> {
            try {
                Parent parent2 = FXMLLoader
                        .load(getClass().getResource("/Views/Cours.fxml"));

                Scene scene = ajouter.getScene();
                scene.setRoot(parent2);

            } catch (IOException ex) {
                Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        deposer.setOnAction(event -> {

            Cours c1 = new Cours(title.getText(), sccours.getText(), Integer.parseInt(levelc.getText()), fichierc.getText(), desc.getText());
            CoursServices pd = CoursServices.getInstance();
            pd.ajouterCours2(c1);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le cours est insérée avec succés!");
            alert.show();
            title.setText("");
            sccours.setText("");
            levelc.setText("");
            fichierc.setText("");
             desc.setText("");
        });

      fichierc.setOnAction(event -> {
            cnx = MyConnection.getInstance().getConx();
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            // Utilisez un FileInputStream pour lire le contenu du fichier
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(selectedFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "INSERT INTO cours(fichier_c) VALUES (?)";
            // Utilisez une instruction PreparedStatement pour exécuter la requête SQL d'insertion
            PreparedStatement statement = null;
            try {
                statement = cnx.prepareStatement(sql);
            } catch (SQLException ex) {
                Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                statement.setString(1, selectedFile.getName());
            } catch (SQLException ex) {
                Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                statement.setBinaryStream(2, inputStream);
            } catch (SQLException ex) {
                Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                // Exécutez la requête SQL d'insertion
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    void selectFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("ZIP", "*.zip"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("TEXT", "*.txt"),
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );

        File file = fileChooser.showOpenDialog(fichierc.getScene().getWindow());

        if (file != null) {
            // pickUpPathField it's your TextField fx:id
            fichierc.setText(file.getPath());

        } else {
            System.out.println("error"); // or something else
        }
    }
    
    @FXML
    
    private void afficherCours(ActionEvent event) {
        CoursServices cs = new CoursServices();
        List<Cours> myList = cs.afficherCours();

        titre.setCellValueFactory(new PropertyValueFactory("Titre_c"));
        sous_c.setCellValueFactory(new PropertyValueFactory("Sous_categorie"));
        niveau.setCellValueFactory(new PropertyValueFactory("Niveau_c"));
        fichier.setCellValueFactory(new PropertyValueFactory("Fichier_c"));
        description.setCellValueFactory(new PropertyValueFactory("Description_c"));
        date.setCellValueFactory(new PropertyValueFactory("date_c"));

        ObservableList<Cours> observablecours = FXCollections.observableArrayList(myList);
        tablecours.setItems(observablecours);
    }
    @FXML
    
    private void supprimerCours(ActionEvent event){
        int id=Integer.parseInt(idsuppc.getText());
        CoursServices cs1 =new   CoursServices();
         cs1.supprimerCours(id);
    }
    
    @FXML
    public void modifierCours (ActionEvent event){
        int Id_c=Integer.parseInt(idmodifc.getText());
        String Titre_c=title.getText();
        String Sous_categorie=sccours.getText();
        int Niveau_c= Integer.parseInt(levelc.getText());
        String Fichier_c=fichierc.getText();
        String Description_c=desc.getText();
       CoursServices abon1=new  CoursServices();
       Cours cours= new  Cours(Id_c,Titre_c,Sous_categorie,Niveau_c,Fichier_c,Description_c);
        abon1.modifierCours(cours);
        
         
        
    }
    
    @FXML
    private void chercherCours(ActionEvent event){
        int Id_cours=Integer.parseInt(txtidcours.getText());
        CoursServices css=new CoursServices();
        Cours c = css.rechercheCoursbyidt(Id_cours);
        if ( c != null){
          labelcours.setText("cours trouvé :" +"Id_cours"+c.getId_c());
          labelcours.setText("titre :" +c.getTitre_c());
          labelcours.setText("Description :" +c.getDescription_c());
        }
        else{
            labelcours.setText(("cours n'a pas trouvé"));
        } 
        
    }
    
    /*
    
     @FXML
    private void chercherCours(ActionEvent event){
        String Nom_cours=(txtidcours.getText());
        CoursServices css=new CoursServices();
        Cours c = css.rechercheCoursbyNOM(Nom_cours);
        if ( c != null){
          labelcours.setText("cours trouvé :" +"Nom_cours"+c.getTitre_c());
         // labelcours.setText("titre :" +c.getTitre_c());
          labelcours.setText("Description :" +c.getDescription_c());
        }
        else{
            labelcours.setText(("cours n'a pas trouvé"));
        } 
        
    }*/

}
