/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Cours;
import artplus.entities.KeyValuePair;
import artplus.entities.Sous_categorie;
import artplus.services.CategorieServices;
import artplus.services.CoursServices;
import artplus.services.LevelServices;
import artplus.services.Sous_categorieServices;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjoutSousCategorieController implements Initializable {

    @FXML
    private TextField sous_categirie_field;
    @FXML
    private Button deposer;
    @FXML
    private TableView<Sous_categorie> tablecategorie;
    @FXML
    private TableColumn<Sous_categorie, String> sous_categorie;
    @FXML
    private TableColumn<Sous_categorie, String> categorie;
    @FXML
    private TableColumn<Sous_categorie, String> editcol;
    @FXML
    private ChoiceBox<KeyValuePair> categorie_choice;
    @FXML
    private Button vider;
    @FXML
    private Button evenement;
    @FXML
    private Button fillAct;
    @FXML
    private Button btnAcc;
    private Button cours;
    @FXML
    private Button prod;
    @FXML
    private Button quiz;
    @FXML
    private Button ass;
    @FXML
    private Button abonnements;

    
    Sous_categorieServices sous_categorie_service=new Sous_categorieServices();
    ObservableList<Sous_categorie> obsreservationlist=FXCollections.observableArrayList();
    List<KeyValuePair> cat_list = new ArrayList<>();
    private BooleanProperty form_valid= new SimpleBooleanProperty(true);
     private boolean sous_categorie_check,categorie_check=false; 
     private boolean addMode=true;
     private int id_sous_categorie_update;
     private Stage stage;
    @FXML
    private Button cours_bt;
    @FXML
    private Button sous_categories;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        get_sous_categories();
        loadData();
        get_categories();
        
        cours_bt.setOnAction(event->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ajoutCours.fxml"));
                Parent root = loader.load();
                Scene scene2 = new Scene(root);
                stage=(Stage) cours.getScene().getWindow();
                stage.setScene(scene2);

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        abonnements.setOnAction(event->{
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ListAbonnement.fxml"));
                Parent root = loader.load();
                Scene scene2 = new Scene(root);
                stage=(Stage) abonnements.getScene().getWindow();
                stage.setScene(scene2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sous_categories.setOnAction(event->{
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ajoutSousCategorie.fxml"));
                Parent root = loader.load();
                Scene scene2 = new Scene(root);
                stage=(Stage) sous_categories.getScene().getWindow();
                stage.setScene(scene2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        sous_categirie_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 3) {
                sous_categirie_field.setStyle("-fx-border-color: red");
                sous_categorie_check=false; 
            } else {
                sous_categirie_field.setStyle(null); // reset border color if length is greater than or equal to minLength
                sous_categorie_check=true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                sous_categirie_field.setText(newValue.trim());
            }
            System.out.println("sous_categorie "+sous_categorie_check);
            form_valid.set(!check_form());
            System.out.println("form valid :" +form_valid);
        });
        sous_categirie_field.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!isAlphabetic(event.getCharacter())) {
                event.consume(); // Prevent non-alphabetic or non-space characters from being entered
            }
        });
        
        categorie_choice.setOnAction((event) -> {
            if(categorie_choice.getValue().getValue()=="Aucune"){
                categorie_check=false;
           }else{
                categorie_check=true;
           }
           System.out.println("categorie "+categorie_check);
           form_valid.set(!check_form());
           System.out.println("form valid :" +form_valid);
        });
        
        deposer.setOnAction(event -> { 
            Sous_categorie sc=new Sous_categorie(); 
            if (addMode==true){ 
                try {
                    sc = new Sous_categorie(sous_categirie_field.getText(), new CategorieServices().get_categorie_by_id(categorie_choice.getValue().getKey()));
                    sous_categorie_service.ajouterSous_categorie2(sc);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Le sous catégorie est inséré avec succés!");
                    alert.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{ 
                try {
                    sc = new Sous_categorie(id_sous_categorie_update,sous_categirie_field.getText(), new CategorieServices().get_categorie_by_id(categorie_choice.getValue().getKey()));
                    sous_categorie_service.modifierSous_categorie(sc);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Le sous categorie est modifié avec succés!");
                    alert.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            get_sous_categories(); 
            loadData();
            clear_form ();
        });
        deposer.disableProperty().bind(form_valid);
    }    

    @FXML
    private void vider_button(ActionEvent event) {
        clear_form ();

    }
    
    
    
    //functions 
    public void get_sous_categories(){ 
        obsreservationlist.clear();
        tablecategorie.getItems().clear();
        sous_categorie_service.afficherSous_Categorie().forEach((cours)->{
            obsreservationlist.add(cours);
        });
    }
    public void get_categories(){ 
        categorie_choice.getItems().clear();
        cat_list.add(new KeyValuePair(0,"Aucune"));
        new CategorieServices().afficherCategorie().forEach((scat)->{ 
            cat_list.add(new KeyValuePair(scat.getId_cat(),scat.getNom_cat())); 
            
        }); 
        categorie_choice.getItems().addAll(cat_list);
        categorie_choice.getItems().stream()
                                        .filter(keyValue -> keyValue.getKey() == 0)
                                        .findFirst()
                                        .ifPresent(categorie_choice::setValue);
    }
    
    
     public void loadData(){  
        tablecategorie.setItems(obsreservationlist);
        System.out.println("table items "+tablecategorie.getItems());
        sous_categorie.setCellValueFactory(data->{
             String  titre=data.getValue().getNom_sc();
             ObservableValue<String> obs=new SimpleObjectProperty<>(titre);
             return obs;
         });
        categorie.setCellValueFactory(data->{
             String  sc=data.getValue().getCategorie().getNom_cat();
             ObservableValue<String> obs=new SimpleObjectProperty<>(sc);
             return obs;
         });
        
        Callback<TableColumn<Sous_categorie, String>, TableCell<Sous_categorie, String>> cellFoctoryAction;
        cellFoctoryAction = (TableColumn<Sous_categorie, String> param) -> {
            // make cell containing buttons
            final TableCell<Sous_categorie, String> cell = new TableCell<Sous_categorie, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        Sous_categorie sous_cat=(Sous_categorie) this.getTableRow().getItem();
                        HBox managebtn = new HBox();
                        Button button = new Button(); 
                        if(sous_cat!=null){ 
                                FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                                deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:24px;"
                                            + "-fx-fill:#ff1744;"
                                            + "-fx-border-insets: 5px;"
                                            + "-fx-padding: 10px;"
                                 );
                                deleteIcon.setOnMouseClicked((event) -> {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Supprimer un sous categorie");
                                    alert.setHeaderText("Vous voulez vraiment effectuer le sous categorie ?");
                                    Optional<ButtonType> option = alert.showAndWait();
                                    if (option.get() == ButtonType.OK) { 
                                        sous_categorie_service.supprimerSous_categorie(sous_cat.getId_sc());
                                        get_sous_categories(); 
                                        loadData();
                                        clear_form();
                                    }
                                });
                                FontAwesomeIconView modifyIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                                modifyIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:24px;"
                                            + "-fx-fill:blue;"
                                            + "-fx-border-insets: 5px;"
                                            + "-fx-padding: 10px;"
                                 );
                                modifyIcon.setOnMouseClicked((event) -> {
                                    addMode=false;
                                    sous_categirie_field.setText(sous_cat.getNom_sc());
                                    categorie_choice.getItems().stream()
                                        .filter(keyValue -> keyValue.getKey() == sous_cat.getCategorie().getId_cat())
                                        .findFirst()
                                        .ifPresent(categorie_choice::setValue);
                                    id_sous_categorie_update=sous_cat.getId_sc();
                                });
                       
                            managebtn.getChildren().addAll(modifyIcon,deleteIcon);                                 
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(button,new Insets(4, 2, 4, 4));
                            setGraphic(managebtn);
                            setText(null);
                        }
                    }
                }

            };
           
            return cell;
        };
        
        editcol.setCellFactory(cellFoctoryAction);
        tablecategorie.setItems(obsreservationlist);
    }
    
     private boolean check_form(){ 
        if (sous_categorie_check==true&& categorie_check==true )
            return true;
        return false;
     }
     public void clear_form (){ 
         categorie_choice.getItems().stream()
                                        .filter(keyValue -> keyValue.getKey() == 0)
                                        .findFirst()
                                        .ifPresent(categorie_choice::setValue);
         sous_categirie_field.setText("");
         sous_categirie_field.setStyle("-fx-border-color: gray;");
         addMode=true;
     }
     private boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z]*"); // Regular expression to match alphabetic characters
    }
     public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
