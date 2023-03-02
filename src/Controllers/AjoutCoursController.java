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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjoutCoursController implements Initializable {

    Connection cnx;
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
    private TextField levelc;
    @FXML
    private TextField fichierc;
    @FXML
    private TextField desc;
    @FXML
    private Label labelcours;
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
     private boolean titre_check,description_check,level_check,ficher_check,sc_check=false; 
     private boolean addMode=true;
     private int id_cours_update ;
    
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        get_cours();
        loadData();
        get_sous_categories();
        fichierc.setDisable(true);
        levelc.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!isPositiveInteger(event.getCharacter())) {
                event.consume(); // Prevent non-positive integer characters from being entered
            }
        });
        levelc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 1) {
                levelc.setStyle("-fx-border-color: red");
                level_check=false; 
            } else {
                levelc.setStyle(null); // reset border color if length is greater than or equal to minLength
                level_check=true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                levelc.setText(newValue.trim());
            }
            form_valid.set(!check_form());
            System.out.println("form valid :" +form_valid);
        });
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
            if (newValue.matches(".*\\d+.*")) {
                title.setText(newValue.replaceAll("\\d", ""));
            }

            form_valid.set(!check_form());
            System.out.println("form valid :" +form_valid);
        });
        title.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!isAlphabetic(event.getCharacter())) {
                event.consume(); // Prevent non-alphabetic characters from being entered
            }
        });
        fichierc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 5) {
                fichierc.setStyle("-fx-border-color: red");
                ficher_check=false; 
            } else {
                fichierc.setStyle(null); // reset border color if length is greater than or equal to minLength
                ficher_check=true;
            }
           

            form_valid.set(!check_form());
            System.out.println("form valid :" +form_valid);
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
            form_valid.set(!check_form());
            System.out.println("form valid :" +form_valid);
        });
        sccours.setOnAction((event) -> {
           sc_check=true;
           form_valid.set(!check_form());
        });
        
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
                    c1 = new Cours(title.getText(), new Sous_categorieServices().get_sous_categorie_by_id(sccours.getValue().getKey()), Integer.parseInt(levelc.getText()), imageBytes, desc.getText());
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
                    c1 = new Cours(id_cours_update,title.getText(), new Sous_categorieServices().get_sous_categorie_by_id(sccours.getValue().getKey()), Integer.parseInt(levelc.getText()), selectedFile_update, desc.getText());
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
    
    
    @FXML
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
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        Stage stage = (Stage) fichierc.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
                fichierc.setText(selectedFile.getAbsolutePath());
        }
        else{ 
            fichierc.setText(null);
        }
    } 
    
    
    //getting data functions : 
     public void get_cours(){ 
        obsreservationlist.clear();
        tablecours.getItems().clear();
        cours_services.afficherCours().forEach((cours)->{
            System.out.println("cours "+ cours);
            obsreservationlist.add(cours);
        });
    }
    public void get_sous_categories(){ 
        sccours.getItems().clear();
        sous_categorie_service.afficherSous_Categorie().forEach((scat)->{ 
            sc_list.add(new KeyValuePair(scat.getId_sc(),scat.getNom_sc())); 
            
        }); 
        sccours.getItems().addAll(sc_list); 
        
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
                                        
                                    }
                                });
                               
                                button.setText("Modifier");
                                button.setOnMouseClicked((event) -> {
                                    addMode=false;
                                    title.setText(cours.getTitre_c());
                                    sccours.getItems().stream()
                                        .filter(keyValue -> keyValue.getKey() == cours.getSous_categorie().getId_sc())
                                        .findFirst()
                                        .ifPresent(sccours::setValue);
                                    levelc.setText(cours.getNiveau_c()+"");
                                    desc.setText(cours.getDescription_c());
                                    fichierc.setText("photo");
                                    selectedFile_update=cours.getFichier_c();
                                    id_cours_update=cours.getId_c();
                                });
                            managebtn.getChildren().addAll(button,deleteIcon);                                 
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
        fichier.setCellFactory(cellFoctoryPhoto);
        editcol.setCellFactory(cellFoctoryAction);
        tablecours.setItems(obsreservationlist);
        
    }

    
    
   
     
     public void clear_form (){ 
         title.setText("");
         desc.setText("");
         levelc.setText("");
         fichierc.setText("");
         title.setStyle("-fx-border-color: gray;");
         levelc.setStyle("-fx-border-color: gray;");
         desc.setStyle("-fx-border-color: gray;");
         fichierc.setStyle("-fx-border-color: gray;");
         get_sous_categories();
         addMode=true;
         selectedFile=null;
     }
    
   
    
    
    
   /* public void modifierCours (ActionEvent event){
         Cours cours= new  Cours();
        int Id_c=Integer.parseInt(idmodifc.getText());
        String Titre_c=title.getText();
        Sous_categorie Sous_categorie= new Sous_categorie( sccours.getValue().getKey(),sccours.getValue().getValue());
        int Niveau_c= Integer.parseInt(levelc.getText());
        String Description_c=desc.getText();
       CoursServices abon1=new  CoursServices();
        if (selectedFile != null) {
                try {
                    // Convert the image file to a byte array
                    InputStream inputStream = new FileInputStream(selectedFile);
                    byte[] imageBytes = new byte[inputStream.available()];
                    inputStream.read(imageBytes);
                    inputStream.close();
                    cours= new  Cours(Id_c,Titre_c,Sous_categorie,Niveau_c, imageBytes,Description_c);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else cours= new  Cours(Id_c,Titre_c,Sous_categorie,Niveau_c, new byte[0],Description_c);  
       
        abon1.modifierCours(cours);
    }*/
    
    /*private void chercherCours(ActionEvent event){
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
        
    }*/
    
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
     

    @FXML
    private void vider_button(ActionEvent event) {
        clear_form ();
    }
    
    
    
    private boolean check_form(){ 
        if (titre_check==true && description_check==true && level_check==true && ficher_check==true && sc_check==true)
            return true;
        return false;
     }

// Helper method to check if a string is a positive integer
    private boolean isPositiveInteger(String str) {
        return str.matches("[1-9]\\d*"); // Regular expression to match positive integers
    }
    private boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z]*"); // Regular expression to match alphabetic characters
    }

}
