/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.ControlSaisieEvenement;
import artplus.entities.Evenement;
import artplus.services.EvenementService;
import artplus.utils.MyConnection;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author rahma
 */
public class EvenementAccueilGuideController implements Initializable {

    @FXML
    private Text accueil;
    @FXML
    private Text filactualite;
    @FXML
    private Text ev;
    @FXML
    private Text cours;
    @FXML
    private Text shop;
    @FXML
    private Text quiz;
    @FXML
    private Text reclam;
    @FXML
    private TextField txt_nbplaces;
    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_categorie;
    @FXML
    private TextField txt_description;
    @FXML
    private TextField txt_img;
    @FXML
    private TextField txt_addresse;
    @FXML
    private TextField txt_date;
    @FXML
    private TableView<Evenement> tableEv;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String> categorie;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> adresse;
    @FXML
    private TableColumn<Evenement, Timestamp> date;
    @FXML
    private TableColumn<Evenement, Integer> nbplaces;
    @FXML
    private ImageView imgEv;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private ImageView iconImport;
    @FXML
    private TextField rechercher;
    @FXML
    private ImageView iconRech;
    Connection cnx;

    EvenementService evS = new EvenementService();
    Evenement e = new Evenement();

    public EvenementAccueilGuideController() {
        cnx = MyConnection.getInstance().getCnx();
    }

    ObservableList<Evenement> listEv = FXCollections.observableArrayList();

    public void afficherEvenements() {
        tableEv.getItems().clear();
        String req = "select titre_ev,categorie_ev,description_ev,adresse_ev,date_ev,nbre_places FROM evenement";

        try {
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listEv.add(new Evenement(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getInt(6)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        titre.setCellValueFactory(new PropertyValueFactory<>("titre_ev"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie_ev"));
        description.setCellValueFactory(new PropertyValueFactory<>("description_ev"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_ev"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_ev"));
        nbplaces.setCellValueFactory(new PropertyValueFactory<>("nbre_places"));
        tableEv.setItems(listEv);
        /*
        List<Evenement> evenements = evS.afficherEvenements();

        //ObservableList<Evenement> list = FXCollections.observableArrayList(evenements);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre_ev"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie_ev"));
        description.setCellValueFactory(new PropertyValueFactory<>("description_ev"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_ev"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_ev"));
        nbplaces.setCellValueFactory(new PropertyValueFactory<>("nbre_places"));

        tableEv.setItems(list);*/

    }

    @FXML
    private void RechEV(javafx.scene.input.KeyEvent event) {

        EvenementService evS = new EvenementService();
        List<Evenement> evenements = evS.afficherEvenements();
        //ObservableList<Evenement> listEv = FXCollections.observableArrayList(evenements);
        FilteredList<Evenement> filteredList = new FilteredList<>(listEv, p -> true);
        // Configurer le prédicat en fonction de la saisie de l'utilisateur
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(even -> {
                // Si le champ de texte est vide, afficher tous les éléments
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Vérifier si le texte de recherche correspond à l'un des champs de l'evenement
                String lowerCaseFilter = newValue.toLowerCase();

                // Correspondance trouvée dans le champ evenement
                if (even.getTitre_ev().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (even.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false; // Pas de correspondance trouvée
            });
            SortedList<Evenement> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableEv.comparatorProperty());
            tableEv.setItems(sortedList);
        });

    }

    public void showEv() {
        /*Evenement ev = tableEv.getSelectionModel().getSelectedItem();
        String req2 = "select titre_ev,categorie_ev,description_ev,adresse_ev,image_ev,date_ev,nbre_places FROM evenement ";
        //        String req2 = "select titre_ev,categorie_ev,description_ev,adresse_ev,image_ev,date_ev,nbre_places FROM evenement WHERE titre_ev= '" + titre + "'" ;

        try {
            PreparedStatement st = cnx.prepareStatement(req2);
            st.setString(2, ev.getTitre_ev());
            ResultSet rs = st.executeQuery();
            String categ;
            String desc;
            String adresse;
            Timestamp date;
            int nbrP;
            Blob imag_Ev;
            byte byte_Img[];
            while (rs.next()) {
                String titre = rs.getString("titre_ev");
                rechercher.setText(String.valueOf(titre));

                txt_titre.setText(rs.getString(titre));
                categ = rs.getString("categorie_ev");
                txt_categorie.setText(String.valueOf(categ));
                desc = rs.getString("description_ev");
                txt_description.setText(String.valueOf(desc));
                adresse = rs.getString("adresse_ev");
                txt_addresse.setText(String.valueOf(adresse));
                date = rs.getTimestamp("date_ev");
                txt_date.setText(String.valueOf(date));
                nbrP = rs.getInt("nbre_places");
                txt_nbplaces.setText(String.valueOf(nbrP));
                imag_Ev = rs.getBlob("image_ev");
                byte_Img = imag_Ev.getBytes(1, (int) imag_Ev.length());
                Image img = new Image(new ByteArrayInputStream(byte_Img), imgEv.getFitWidth(), imgEv.getFitHeight(), true, true);
                //Image img = new Image(new ByteArrayInputStream(byte_Img), (int) imgEv.getFitWidth(), (int) imgEv.getFitHeight()));
                imgEv.setImage(img);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private void ImporterImg() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            txt_img.setText(f.getAbsolutePath());
            Image img = new Image(f.toURI().toString(), imgEv.getFitWidth(), imgEv.getFitHeight(), true, true);
            //Image img = new Image(new ByteArrayInputStream(byte_Img), (int) imgEv.getFitWidth(), (int) imgEv.getFitHeight()));
            imgEv.setImage(img);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficherEvenements();
        showEv();

        //showEv();
    }

    private List<Evenement> evenementsAjoutes = new ArrayList<>();

    private boolean controleSaisieEvenement(String titre, String categorie, String description, String adresse, Timestamp date, int nombrePlaces) {
        /*for (Evenement evenement : evenementsAjoutes) {
            if (evenement.getTitre_ev().equals(titre)
                    && evenement.getCategorie().equals(categorie)
                    && evenement.getDescription_ev().equals(description)
                    && evenement.getAdresse_ev().equals(adresse)
                    && evenement.getDateTime_ev().equals(date)
                    && evenement.getNbre_place() == nombrePlaces) {
                System.out.println("L'événement existe déjà.");
                Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("L'événement existe déjà.");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
                return false;
            }
        }*/
        try {
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM evenement WHERE titre_Ev = '"
                    + titre + "' AND categorie_ev = '" + categorie + "' AND description_ev = '"+ description + "' AND adresse_ev = '"+ adresse +"' AND date_ev = '"
                    + date + "' AND nbre_places = " + nombrePlaces);

            // Si un événement avec ces attributs existe déjà, renvoyer false
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
            return false;
        }

        /*// Si l'événement n'existe pas, l'ajouter à la liste
        Evenement nouvelEvenement = new Evenement(titre, categorie, date, nbPlaces);
        evenementsAjoutes.add(nouvelEvenement);
        System.out.println("L'événement a été ajouté.");*/
        //return true;
    }

    private FileInputStream fs;

    @FXML
    private void ajouter() throws FileNotFoundException, ParseException {
/*
        
        String titre = txt_titre.getText();
        String cat = txt_categorie.getText();
        String desc = txt_description.getText();
        String dat = txt_date.getText();
        //Timestamp date = Timestamp.valueOf(txt_date.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date = dateFormat.parse(dateString);
        java.util.Date date = dateFormat.parse(dat);
        Timestamp timestamp = new Timestamp(date.getTime());

        String adress = txt_addresse.getText();
        int nbreP = Integer.parseInt(txt_nbplaces.getText());
        File img = new File(txt_img.getText());
        
    // Appeler la méthode de contrôle de saisie
    boolean ajoutPossible = controleSaisieEvenement(titre, cat, desc, adress, timestamp, nbreP);

    // Vérifier si l'ajout est possible et effectuer l'action appropriée
    if (ajoutPossible) {
        // Ajouter l'événement à la base de données ou effectuer toute autre action appropriée
        String sql = "insert into evenement(titre_ev,categorie_ev,description_ev,adresse_ev,image_ev,date_ev,nbre_places) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, titre);
            st.setString(2, cat);
            st.setString(3, desc);
            st.setString(4, adress);
            fs = new FileInputStream(img);
            st.setBinaryStream(5, fs, img.length());
            st.setTimestamp(6, timestamp);
            st.setInt(7, nbreP);
            st.executeUpdate();
            afficherEvenements();

            txt_titre.setText("");
            txt_categorie.setText("");
            txt_description.setText("");
            txt_addresse.setText("");
            txt_date.setText("");
            txt_nbplaces.setText("");
            imgEv.setImage(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Evenement ajouté avec succées", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        // Afficher un message d'erreur à l'utilisateur
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Evenement existe déjà");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
    }
 */  ControlSaisieEvenement control = new ControlSaisieEvenement();
        String titre = txt_titre.getText();
        String cat = txt_categorie.getText();
        String desc = txt_description.getText();
        String dat = txt_date.getText();
        //Timestamp date = Timestamp.valueOf(txt_date.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date = dateFormat.parse(dateString);
        java.util.Date date = dateFormat.parse(dat);
        Timestamp timestamp = new Timestamp(date.getTime());

        String adress = txt_addresse.getText();
        int nbreP = Integer.parseInt(txt_nbplaces.getText());
        File img = new File(txt_img.getText());
        Boolean retourstr= true;
        Evenement ev= new Evenement(titre, cat, desc, adress, adress, timestamp, nbreP);
        if (txt_titre.getText().isEmpty() || !control.ControleTitre(ev)){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir le Titre");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            retourstr=false;
        }
        if (cat.isEmpty() || !control.ControleCategorie(ev)){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir la categorie");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            retourstr=false;
        }
        if (desc.isEmpty() || !control.ControleDescription(ev)){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir description");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            retourstr=false;
        }
        if (adress.isEmpty() || !control.ControleAdresse(ev)){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir l'emplacement");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            retourstr=false;
        }
        if (dat.isEmpty() || !control.ControleDate(ev)){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir La date sous la forme AAAA-MM-JJ HH:MM:SS");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            retourstr=false;
                        
        }
       
    // Appeler la méthode de contrôle de saisie
    boolean ajoutPossible = controleSaisieEvenement(titre, cat, desc, adress, timestamp, nbreP);

    // Vérifier si l'ajout est possible et effectuer l'action appropriée
    if (ajoutPossible && retourstr==true) {
        // Ajouter l'événement à la base de données ou effectuer toute autre action appropriée
        String sql = "insert into evenement(titre_ev,categorie_ev,description_ev,adresse_ev,image_ev,date_ev,nbre_places) VALUES(?,?,?,?,?,?,?)";
        try {

            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, titre);
            st.setString(2, cat);
            st.setString(3, desc);
            st.setString(4, adress);
            fs = new FileInputStream(img);
            st.setBinaryStream(5, fs, img.length());
            st.setTimestamp(6, timestamp);
            st.setInt(7, nbreP);
            st.executeUpdate();
            afficherEvenements();

            txt_titre.setText("");
            txt_categorie.setText("");
            txt_description.setText("");
            txt_addresse.setText("");
            txt_date.setText("");
            txt_nbplaces.setText("");
            imgEv.setImage(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Evenement ajouté avec succées", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        // Afficher un message d'erreur à l'utilisateur
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Evenement existe déjà");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
    }
    }

    @FXML
    private void supprimer() {
        Evenement ev = tableEv.getSelectionModel().getSelectedItem();
        int idE = ev.getId_ev();
        try {
            String req = "DELETE FROM `evenement` WHERE id_ev = " + idE;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement supprimé avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Evenement supprimé avec succées", javafx.scene.control.ButtonType.OK);
        alert.showAndWait();
        showEv();

    }

    @FXML
    private void modifier() throws ParseException, FileNotFoundException {
        Evenement ev = tableEv.getSelectionModel().getSelectedItem();
        int idE = ev.getId_ev();
        String titre = txt_titre.getText();
        String cat = txt_categorie.getText();
        String desc = txt_description.getText();
        String dat = txt_date.getText();
        //Timestamp date = Timestamp.valueOf(txt_date.getText());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date date = dateFormat.parse(dateString);
        java.util.Date date = dateFormat.parse(dat);
        Timestamp timestamp = new Timestamp(date.getTime());

        String adress = txt_addresse.getText();
        int nbreP = Integer.parseInt(txt_nbplaces.getText());
        File img = new File(txt_img.getText());
        //String sql = "update evenement titre_ev =?,categorie_ev=?,description_ev=?,adresse_ev=?,image_ev=?,date_ev=?,nbre_places=? WHERE titre_ev= '" + rechercher.getText() + "'";
        String sql = "UPDATE `evenement` SET `titre_ev`=?,`categorie_ev`=?',`description_ev`=?,`image_ev`=?,`adresse_ev`=?,`date_ev`=?,`nbre_places`=? WHERE id_ev = " + idE;
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, titre);
            st.setString(2, cat);
            st.setString(3, desc);
            fs = new FileInputStream(img);
            st.setBinaryStream(4, fs, img.length());
            st.setString(5, adress);
            st.setTimestamp(6, timestamp);
            st.setInt(7, nbreP);
            st.executeUpdate();
            afficherEvenements();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Evenement Modifié avec succées", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            txt_titre.setText("");
            txt_categorie.setText("");
            txt_description.setText("");
            txt_addresse.setText("");
            txt_date.setText("");
            txt_nbplaces.setText("");
            imgEv.setImage(null);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }/*
     public void get_Evenement(List<Evenement> evenements_list){
        grid_pane.getChildren().clear();
        int  col = 0;
        int row = 1;
        for (Cours c:courses_list){
            try {
                AnchorPane cours_card = new AnchorPane();
                cours_card.setStyle("-fx-background-color: white; -fx-border-color: red;");
                //anchorPane.setPrefSize(300, 300);
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Views/Cours_card.fxml"));
                cours_card = fxmlloader.load();
                CoursCardController cours_card_controller = fxmlloader.getController();
                cours_card_controller.setCours(c);
                cours_card_controller.initialize_data();
                BorderStroke borderStroke = new BorderStroke(
                Color.BLUE,
                BorderStrokeStyle.SOLID,
                null,
                BorderStroke.THIN,
                new Insets(5)
                );
                Border border = new Border(borderStroke);
                cours_card.setBorder(border); 
                if(col == 3){
                    col = 0;
                    row++;
                }
                GridPane.setHgrow(cours_card, Priority.ALWAYS);
                GridPane.setVgrow(cours_card, Priority.ALWAYS);
                grid_pane.add(cours_card, col++, row );
                
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/


}
