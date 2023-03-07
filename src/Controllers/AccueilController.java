/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Categorie_cours;
import entities.Cours;
import entities.KeyValuePair;
import entities.user;
import services.CategorieServices;
import services.CoursServices;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AccueilController implements Initializable {

    @FXML
    private Button evenement ;
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
    private ComboBox<Categorie_cours> combocat;
     List<Categorie_cours> listeCategories = new ArrayList<>();
    @FXML
    private Label textlabel;
    @FXML
    private GridPane grid_pane;
    @FXML
    private ScrollPane scroll_pane;
    
    @FXML
    private Button course_search_button;
    @FXML
    private TextField course_search_field;
    @FXML
    private ComboBox<KeyValuePair> cat_choice;
    
    
    
    //variables 
    List<Cours> cours_list = new ArrayList<>(); 
    CoursServices cours_service=new CoursServices();
    private Stage stage;
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        get_courses();
        get_categories();
        
        
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        col1.setPercentWidth(33.33);
        col2.setPercentWidth(33.33);
        col3.setPercentWidth(33.33);
        grid_pane.getColumnConstraints().addAll(col1, col2,col3);
        
        btnAcc.setOnAction(event->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Accueil.fxml"));
                Parent root = loader.load();
                Scene scene2 = new Scene(root);
                stage=(Stage) btnAcc.getScene().getWindow();
                stage.setScene(scene2);

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        cat_choice.valueProperty().addListener((obs, oldValue, newValue) -> {
            get_course_by_categorie(newValue.getKey());
        });
       
    }
    
    @FXML
    private void search_course(ActionEvent event) {  
        if(course_search_field.getText().trim()==""){ 
            System.out.println("im here");
            get_courses();
        }else
            get_course_by_name(course_search_field.getText());
    } 
    
    
    
    //functions  
    public void get_courses(){ 
        cours_list=new CoursServices().afficherCours();
        get_cours(cours_list);
        
    }
    
    public void get_course_by_name(String course_name){
        cours_list=cours_service.rechercheCoursbyNOM(course_name); 
        get_cours(cours_list);
        
    } 
    public void get_course_by_categorie(int id){
        if(id==0){ 
            get_courses();
        }else{ 
            cours_list=cours_service.get_cours_by_categorie(id); 
            get_cours(cours_list);
        }
    }
    public void get_categories(){ 
        cat_choice.getItems().clear();
        List<KeyValuePair> cat_list=new ArrayList<>();
        cat_list.add(new KeyValuePair(0,"Tous"));
        new CategorieServices().afficherCategorie().forEach((cat)->{ 
            cat_list.add(new KeyValuePair(cat.getId_cat(),cat.getNom_cat())); 
        }); 
        cat_choice.getItems().addAll(cat_list); 
    }

    public void get_cours(List<Cours> courses_list){
        grid_pane.getChildren().clear();
        int  col = 0;
        int row = 1;
        
        for (Cours c:courses_list){
            try {
                AnchorPane cours_card = new AnchorPane();
                cours_card.setStyle("-fx-background-color: white; -fx-border-color: red;");
                //cours_card.setPrefSize(300, 300);
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/GUI/Cours_card.fxml"));
                cours_card = fxmlloader.load();
                CoursCardController cours_card_controller = fxmlloader.getController();
                cours_card_controller.setCours(c);
                cours_card_controller.initialize_data();
                
                
                
                if(col == 3){
                    col = 0;
                    row++;
                }
                
                
                grid_pane.setHgap(10); // Set horizontal gap between cells to 10 pixels
                grid_pane.setVgap(10); // Set vertical gap between cells to 10 pixels
                grid_pane.setPadding(new Insets(-35, 5, 0,5 ));
                GridPane.setHgrow(cours_card, Priority.ALWAYS);
                GridPane.setVgrow(cours_card, Priority.ALWAYS);
                grid_pane.add(cours_card, col++, row );
                
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    
    
    





}
    

