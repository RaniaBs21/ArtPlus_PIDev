/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Cours;
import artplus.entities.KeyValuePair;
import artplus.entities.Sous_categorie;
import artplus.services.CoursServices;
import artplus.services.LevelServices;
import artplus.services.Sous_categorieServices;
import artplus.utils.MyConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjoutCoursController implements Initializable {

    Connection cnx;
    private Stage stage;
    @FXML
    private Button evenement;
    private TextField files;
    
    @FXML
    private Button fillAct;
    @FXML
    private Button btnAcc;
    @FXML
    private Button cours;
    @FXML
    private Button prod;
    @FXML
    private Button quiz;
    @FXML
    private Button ass;

    @FXML
    private Button selectFileButton;

    String selectedFilePath;
    
    @FXML
    private TableView<Cours> tablecours;
    @FXML
    private TableColumn<Cours, String> titre;
    @FXML
    private TableColumn<Cours, String>  sous_c;
    @FXML
    private TableColumn<Cours, Integer> niveau;
    @FXML
    private TableColumn<Cours, String>  fichier;
    @FXML
    private TableColumn<Cours, String>  description;
    @FXML
    private TableColumn<Cours, Date> date;
    @FXML
    private TableColumn<Cours, String> editcol;
    
    @FXML
    private TextField title;
    @FXML
    private ChoiceBox<KeyValuePair> sccours;
    @FXML
    private TextField fichierc;
    @FXML
    private TextField desc;
    @FXML
    private Button deposer;
    @FXML
    private Button vider;

     FileChooser fileChooser = new FileChooser();
     File selectedFile=null;
     byte[] selectedFile_update=null;
     CoursServices cours_services= new CoursServices();
     Sous_categorieServices sous_categorie_service=new Sous_categorieServices();
     ObservableList<Cours> obsreservationlist=FXCollections.observableArrayList(); 
     List<KeyValuePair> sc_list = new ArrayList<>();
     private BooleanProperty form_valid= new SimpleBooleanProperty(true);
     private boolean titre_check,description_check,ficher_check,sc_check,level_check=false; 
     private boolean addMode=true;
     private int id_cours_update ;
    @FXML
    private TextField prix_field;
    @FXML
    private ChoiceBox<KeyValuePair> level_field;
    @FXML
    private TableColumn<Cours, Float> prix;
    @FXML
    private Button abonnements;
    @FXML
    private AnchorPane souscate;
    @FXML
    private Button sous;
    
    
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        get_cours();
        loadData();
        get_sous_categories();
        get_levels(); 
        
        
        //navbar
        cours.setOnAction(event->{
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
        
        
            sous.setOnAction(event->{
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ajoutSousCategorie.fxml"));
                Parent root = loader.load();
                Scene scene2 = new Scene(root);
                stage=(Stage) sous.getScene().getWindow();
                stage.setScene(scene2);
               

            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //--navbar
        
        //controle de saisie
        title.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 5) {
                title.setStyle("-fx-border-color: red");
                titre_check=false; 
            } else {
                title.setStyle(null); // reset border color if length is greater than or equal to minLength
                titre_check=true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                title.setText(newValue.trim());
            }
            System.out.println("title "+titre_check);
            form_valid.set(!check_form());
            System.out.println("form valid :" +form_valid);
        });
        title.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!isAlphabeticOrSpace(event.getCharacter())) {
                event.consume(); // Prevent non-alphabetic or non-space characters from being entered
            }
        });        
        
        desc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 10) {
                desc.setStyle("-fx-border-color: red"); 
                description_check=false;
            } else {
                desc.setStyle(null); // reset border color if length is greater than or equal to minLength
                description_check=true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                desc.setText(newValue.trim());
            }
            System.out.println("desc "+description_check);
            form_valid.set(!check_form());
            System.out.println("form valid :" +form_valid);
        });
        sccours.setOnAction((event) -> {
            if(sccours.getValue().getValue()=="Aucune"){
                sc_check=false;
           }else{
                sc_check=true;
           }
           System.out.println("desc "+sc_check);
           form_valid.set(!check_form());
           System.out.println("form valid :" +form_valid);
        }); 
        level_field.setOnAction((event) -> {
            if(level_field.getValue().getValue()=="Aucun"){
                level_check=false;
           }else{
                level_check=true;
           }
           System.out.println("level_field "+level_check);
           form_valid.set(!check_form());
           System.out.println("form valid :" +form_valid);
        });
        fichierc.setDisable(true);
        format_price_field_to_positive_float(prix_field);
        //-controle de saisie
        
        //add or modify cours
        deposer.setOnAction(event -> { 
            Cours c1=new Cours(); 
            CoursServices pd = CoursServices.getInstance();
            if (addMode==true){ 
                try {
                    // Convert the image file to a byte array
                    InputStream inputStream = new FileInputStream(selectedFile);
                    byte[] imageBytes = new byte[inputStream.available()];
                    inputStream.read(imageBytes);
                    inputStream.close();
                    System.out.println("prix"+ prix_field.getText());
                    c1 = new Cours(title.getText(), new Sous_categorieServices().get_sous_categorie_by_id(sccours.getValue().getKey()), Integer.parseInt(level_field.getValue().getValue()), imageBytes, desc.getText(),Float.parseFloat(prix_field.getText()));
                    pd.ajouterCours2(c1);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Le cours est inséré avec succés!");
                    alert.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{ 
                try {
                    c1 = new Cours(id_cours_update,title.getText(), new Sous_categorieServices().get_sous_categorie_by_id(sccours.getValue().getKey()), Integer.parseInt(level_field.getValue().getValue()),
                                   selectedFile_update, desc.getText(),Float.parseFloat(prix_field.getText()));
                    System.out.println("sc data  "+sccours.getValue().getKey() +" "+sccours.getValue().getValue() +" cours id "+c1.getId_c());
                    pd.modifierCours(c1);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Le cours est modifié avec succés!");
                    alert.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            get_cours(); 
            loadData();
            clear_form ();
        });
        deposer.disableProperty().bind(form_valid);
  
    }
    
    
    private void afficherCours(ActionEvent event) {
        get_cours(); 
        loadData();
    }
    @FXML
    void selectFile(ActionEvent event) {
        
        fileChooser.setTitle("Select File");
        // Set initial directory (optional)
        File initialDirectory = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(initialDirectory);
        // Add file filters (optional)
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.jpeg", "*.gif")
        );
        Stage stage = (Stage) fichierc.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
                fichierc.setText(selectedFile.getAbsolutePath());
                ficher_check=true;
                form_valid.set(!check_form());
                if(addMode==false){ 
                    InputStream inputStream;
                    try {
                        inputStream = new FileInputStream(selectedFile);
                        byte[] imageBytes = new byte[inputStream.available()];
                            inputStream.read(imageBytes);
                            inputStream.close();
                    selectedFile_update=imageBytes;
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(AjoutCoursController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
        else{ 
            fichierc.setText(null);
            ficher_check=false;
            form_valid.set(!check_form());
        }
        
    } 
    
    
    //getting data functions : 
     public void get_cours(){ 
        obsreservationlist.clear();
        tablecours.getItems().clear();
        cours_services.afficherCours().forEach((cours)->{
            obsreservationlist.add(cours);
        });
    }
    public void get_sous_categories(){ 
        sccours.getItems().clear();
        sc_list.add(new KeyValuePair(0,"Aucune"));
        sous_categorie_service.afficherSous_Categorie().forEach((scat)->{ 
            sc_list.add(new KeyValuePair(scat.getId_sc(),scat.getNom_sc())); 
            
        }); 

        sccours.getItems().addAll(sc_list);
        sccours.getItems().stream()
                                        .filter(keyValue -> keyValue.getKey() == 0)
                                        .findFirst()
                                        .ifPresent(sccours::setValue);
    }
    public void get_levels(){ 
        level_field.getItems().clear();
        List<KeyValuePair> lv_list=new ArrayList<>();
        lv_list.add(new KeyValuePair(0,"Aucun"));
        new LevelServices().afficherLevel().forEach((scat)->{ 
            lv_list.add(new KeyValuePair(scat.getId_level(),scat.getNom_level())); 
        }); 
        level_field.getItems().addAll(lv_list); 
        level_field.getItems().stream()
                                        .filter(keyValue -> keyValue.getKey() == 0)
                                        .findFirst()
                                        .ifPresent(level_field::setValue);
    }
    
     public void loadData(){  
        tablecours.setItems(obsreservationlist);
        System.out.println("table items "+tablecours.getItems());
        titre.setCellValueFactory(data->{
             String  titre=data.getValue().getTitre_c();
             ObservableValue<String> obs=new SimpleObjectProperty<>(titre);
             return obs;
         });
        sous_c.setCellValueFactory(data->{
             String  sc=data.getValue().getSous_categorie().getNom_sc();
             ObservableValue<String> obs=new SimpleObjectProperty<>(sc);
             return obs;
         });
        niveau.setCellValueFactory(data->{
             int  nv= data.getValue().getNiveau_c();
             ObservableValue<Integer> obs=new SimpleObjectProperty<>(nv);
             return obs;
         });
        prix.setCellValueFactory(data->{
             Float  nv= data.getValue().getPrix();
             ObservableValue<Float> obs=new SimpleObjectProperty<>(nv);
             return obs;
         });
        description.setCellValueFactory(data->{
             String  desc= data.getValue().getDescription_c();
             ObservableValue<String> obs=new SimpleObjectProperty<>(desc);
             return obs;
         });
        date.setCellValueFactory(data->{
             Date date= data.getValue().getDate_c();
             ObservableValue<Date> obs=new SimpleObjectProperty<>(date);
             return obs;
         });
        
        Callback<TableColumn<Cours, String>, TableCell<Cours, String>> cellFoctoryAction;
        cellFoctoryAction = (TableColumn<Cours, String> param) -> {
            // make cell containing buttons
            final TableCell<Cours, String> cell = new TableCell<Cours, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        Cours cours=(Cours) this.getTableRow().getItem();
                        HBox managebtn = new HBox();
                        Button button = new Button(); 
                        if(cours!=null){ 
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
                                    alert.setTitle("Supprimer un cours");
                                    alert.setHeaderText("Vous voulez vraiment effectuer le cours ?");
                                    Optional<ButtonType> option = alert.showAndWait();
                                    if (option.get() == ButtonType.OK) { 
                                        cours_services.supprimerCours(cours.getId_c());
                                        get_cours(); 
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
                                    title.setText(cours.getTitre_c());
                                    sccours.getItems().stream()
                                        .filter(keyValue -> keyValue.getKey() == cours.getSous_categorie().getId_sc())
                                        .findFirst()
                                        .ifPresent(sccours::setValue);
                                    level_field.getItems().stream()
                                        .filter(keyValue -> keyValue.getValue().equals(Integer.toString(cours.getNiveau_c())))
                                        .findFirst()
                                        .ifPresent(level_field::setValue);
                                    desc.setText(cours.getDescription_c());
                                    fichierc.setText("photo");
                                    ficher_check=true;
                                    form_valid.set(!check_form());
                                    selectedFile_update=cours.getFichier_c(); 
                                    prix_field.setText(cours.getPrix()+"");
                                    id_cours_update=cours.getId_c();
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
        
        
        Callback<TableColumn<Cours, String>, TableCell<Cours, String>> cellFoctoryPhoto;
        cellFoctoryPhoto = (TableColumn<Cours, String> param) -> {
            // make cell containing buttons
            final TableCell<Cours, String> cell = new TableCell<Cours, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Cours cours=(Cours) this.getTableRow().getItem();
                        Button button = new Button(); 
                        if(cours!=null){ 
                            button.setId("add_button");
                                button.setText("Voir photo");
                                //Button fucntions 
                                button.setOnMouseClicked((event) -> {
                                    try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/VoirPhoto.fxml"));
                                                Parent root = loader.load();
                                                Scene scene = new Scene(root); 
                                                Stage stage = new Stage();
                                                stage.setScene(scene);
                                                VoirPhotoController voir_photo_controller=loader.getController(); 
                                                voir_photo_controller.setReclamation(cours);
                                                voir_photo_controller.setStage(stage);
                                                voir_photo_controller.setImage_view();
                                                stage.show();
                                    } catch (IOException ex) {
                                           // Logger.getLogger(AjouterRéservationChambreController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
                                HBox managebtn = new HBox(button);
                                managebtn.setStyle("-fx-alignment:center");
                                HBox.setMargin(button,new Insets(2, 2, 0, 0));
                                setGraphic(managebtn);
                        }
                        }
                    }
            };
            return cell;
        };
        
        fichier.setCellFactory(cellFoctoryPhoto);
        editcol.setCellFactory(cellFoctoryAction);
        tablecours.setItems(obsreservationlist);
    }

    
    
        
     public void clear_form (){ 
         level_field.getItems().stream()
                                        .filter(keyValue -> keyValue.getKey() == 0)
                                        .findFirst()
                                        .ifPresent(level_field::setValue);
         sccours.getItems().stream()
                                        .filter(keyValue -> keyValue.getKey() == 0)
                                        .findFirst()
                                        .ifPresent(sccours::setValue);
         title.setText("");
         desc.setText("");
         fichierc.setText("");
         title.setStyle("-fx-border-color: gray;");
         level_field.setStyle("-fx-border-color: gray;");
         desc.setStyle("-fx-border-color: gray;");
         fichierc.setStyle("-fx-border-color: gray;");
         prix_field.setText("1.0");
         addMode=true;
         selectedFile=null;
     }
    
   public void format_price_field_to_positive_float(TextField field){ 
        FloatStringConverter floatConverter = new FloatStringConverter() {
            @Override
            public Float fromString(String value) {
                // Throw a NumberFormatException if the input string contains non-numeric characters
                if (value != null && !value.matches("\\d*\\.?\\d+")) {
                    throw new NumberFormatException("Input string is not a valid float number");
                }
                return super.fromString(value);
            }
        };
        
        // Create a TextFormatter that uses the custom FloatStringConverter and a custom UnaryOperator that filters out negative numbers and enforces a minimum value of 1
        TextFormatter<Float> positiveFloatTextFormatter = new TextFormatter<>(floatConverter, 1.0f, change -> {
            if (change.getControlNewText().isEmpty()) {
                return change;
            }

            try {
                float value = Float.parseFloat(change.getControlNewText());
                if (value >= 1) {
                    return change;
                }
            } catch (NumberFormatException e) {
                // Allow the change to be rejected by returning the original change
            }

            return null;
        });

        // Set the TextFormatter on the TextField
        field.setTextFormatter(positiveFloatTextFormatter);
    }
    
    
   

    @FXML
    private void vider_button(ActionEvent event) {
        clear_form ();
    }
    
    
     private boolean check_form(){ 
        if (titre_check==true&& description_check==true && ficher_check==true && level_check==true && sc_check==true)
            return true;
        return false;
     }
     /* private boolean check_form(){ 
        if (titre_check==true && description_check==true && level_check==true && ficher_check==true && sc_check==true)
            return false;
        return true;
     }*/

// Helper method to check if a string is a positive integer
    private boolean isPositiveInteger(String str) {
        return str.matches("[1-9]\\d*"); // Regular expression to match positive integers
    }
    private boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z]*"); // Regular expression to match alphabetic characters
    }
    private boolean isAlphabeticOrSpace(String character) {
        return character.matches("[a-zA-Z\\s]");
    }
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
