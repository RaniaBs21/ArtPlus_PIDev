/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Categorie_cours;
import artplus.entities.Cours;
import artplus.services.CategorieServices;
import artplus.services.CoursServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btn;
    @FXML
    private Button evenement ;
    @FXML
    private ImageView txt3;
    @FXML
    private Text txt1;
    @FXML
    private ImageView txt31;
    @FXML
    private ImageView txt311;
   
    @FXML
    private Button cours;
    @FXML
    private Button prod;
    @FXML
    private Button quiz;
    @FXML
    private Button ass;
    @FXML
    private Button fillAct;
    private TextField txtidcours;
    private Label labelcours;

    @FXML
    private Button btnAcc;
    ArrayList categorie = new ArrayList(); 
    private TextField searchField;
    @FXML
    private ComboBox<Categorie_cours> combocat;
     List<Categorie_cours> listeCategories = new ArrayList<>();
    @FXML
    private TextField txtidcourses;
    @FXML
    private Label textlabel;
    @FXML
    private Button decouvrir1;
    @FXML
    private Button decouvrir2;
    @FXML
    private Button decouvrir3;
   
    
    public void getCtegories() {
        CategorieServices catService = new CategorieServices();
        listeCategories = catService.afficherNomCategorie();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
          CategorieServices catService = new CategorieServices();
        listeCategories = catService.afficherNomCategorie();
        
        
          
       combocat.setConverter(new StringConverter<Categorie_cours>() {
            @Override
            public String toString(Categorie_cours categorie) {
                if (categorie.getNom_cat() != null) {
                    return categorie.getNom_cat();
                } else {
                    return "";
                }
            }

            public Categorie_cours fromString(String string) {
                return combocat.getSelectionModel().getSelectedItem();
            }
        });
       combocat.setItems(FXCollections.observableArrayList(listeCategories));
        combocat.setValue(listeCategories.get(0));
       
        
         btnAcc.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/Acceuil0.fxml"));
               
                Scene scene= btnAcc.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
        fillAct.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/FillActualite.fxml"));
               
                Scene scene= fillAct.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/abonuser.fxml"));
                
                //Scene scene=new Scene(parent2);
                //Stage stage=(Stage) ((Node) event.getSource())
                        //.getScene().getWindow();
                //stage.setScene(scene);
                Scene scene= btn.getScene();
                scene.setRoot(parent2);
                //stage.setTitle("Interface 2");
               // stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        evenement.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/evenement.fxml"));
               
                Scene scene= evenement.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         cours.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/Accueil.fxml"));
               
                Scene scene= cours.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         prod.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/Produit.fxml"));
               
                Scene scene= prod.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         quiz.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/Quiz.fxml"));
               
                Scene scene= quiz.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
         ass.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/Assistance.fxml"));
               
                Scene scene= ass.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
         
         
          /* decouvrir1.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/abonuser.fxml"));
               
                Scene scene= decouvrir1.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); */
          
          
          
/*            decouvrir2.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/abonuser.fxml"));
               
                Scene scene= decouvrir2.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
            
            
             
            decouvrir3.setOnAction(event->{
            try {
                Parent parent2=FXMLLoader
                        .load(getClass().getResource("/Views/abonuser.fxml"));
               
                Scene scene= decouvrir3.getScene();
                scene.setRoot(parent2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          /* categories.setOnAction((ActionEvent event)->{
          CategorieServices psc = new CategorieServices();
            System.out.println(psc.afficherCategorie());
           
           }
           );*/
    }
    public void handleSearchButton(ActionEvent event) {
    String searchName = searchField.getText().toLowerCase();

    List<Cours> filteredList = new ArrayList<>();
            Iterable<Cours> coursList = null;

    for (Cours cours : coursList) {
        if (cours.getTitre_c().toLowerCase().contains(searchName)) {
            filteredList.add(cours);
        }
    }

   
}
    @FXML
    private void chercherCours(ActionEvent event){
        String Nom_cours=(txtidcourses.getText());
        CoursServices css=new CoursServices();
        Cours c = css.rechercheCoursbyNOM(Nom_cours);
        if ( c != null){
          textlabel.setText("cours trouvé :" +"Nom_cours"+c.getTitre_c());
         // labelcours.setText("titre :" +c.getTitre_c());
        textlabel.setText("Description :" +c.getDescription_c());
        }
        else{
           textlabel.setText(("cours n'est pas trouvé"));
        } 
        
    }



    
           
        
    }    
    

