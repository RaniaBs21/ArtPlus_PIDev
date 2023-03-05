/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Evenement;
import artplus.services.EvenementService;
import artplus.utils.MyConnection;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
        titre.setCellValueFactory(new PropertyValueFactory<Evenement, String>("titre_ev"));
        categorie.setCellValueFactory(new PropertyValueFactory<Evenement, String>("categorie_ev"));
        description.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description_ev"));
        adresse.setCellValueFactory(new PropertyValueFactory<Evenement, String>("adresse_ev"));
        date.setCellValueFactory(new PropertyValueFactory<Evenement, Timestamp>("date_ev"));
        nbplaces.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("nbre_places"));
        tableEv.setItems(listEv);

        Evenement ev = tableEv.getSelectionModel().getSelectedItem();
        String req2 = "select titre_ev,categorie_ev,description_ev,adresse_ev,image_ev,date_ev,nbre_places FROM evenement WHERE";
        //        String req2 = "select titre_ev,categorie_ev,description_ev,adresse_ev,image_ev,date_ev,nbre_places FROM evenement WHERE titre_ev '" + titre + "'" ;

        try {
            PreparedStatement st = cnx.prepareStatement(req2);
            st.setString(1, ev.getTitre_ev());
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
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherEvenements();
        RechEV();
    }

    @FXML
    private void ImporterImg() {
    }

    @FXML
    private void RechEV() {/*
        utilisateurs = us.getAll();
        ObservableList<Utilisateur> listuser = FXCollections.observableArrayList(utilisateurs);
        FilteredList<Utilisateur> filteredList = new FilteredList<>(listuser);
        // Configurer le prédicat en fonction de la saisie de l'utilisateur
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Utilisateur -> {
                // Si le champ de texte est vide, afficher tous les éléments
                if (newValue == null || newValue.isEmpty()) {
                    
                    return true;
                }
                
                // Vérifier si le texte de recherche correspond à l'un des champs de l'utilisateur
                String lowerCaseFilter = newValue.toLowerCase();
                
                // Correspondance trouvée dans le champ utilisateur
                
                
                if (Utilisateur.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (Utilisateur.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }
                return false; // Pas de correspondance trouvée
            });
        });*/
       
        EvenementService evS= new EvenementService();
        List<Evenement> evenements = new ArrayList<>();
        evenements = evS.afficherEvenements();
        ObservableList<Evenement> listEv = FXCollections.observableArrayList(evenements);
        FilteredList<Evenement> filteredList = new FilteredList<>(listEv);
        // Configurer le prédicat en fonction de la saisie de l'utilisateur
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Evenement -> {
                // Si le champ de texte est vide, afficher tous les éléments
                if (newValue == null || newValue.isEmpty()) {
                    
                    return true;
                }
                
                // Vérifier si le texte de recherche correspond à l'un des champs de l'evenement
                String lowerCaseFilter = newValue.toLowerCase();
                
                // Correspondance trouvée dans le champ evenement
                
                
                if (Evenement.getTitre_ev().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (Evenement.getCategorie().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }
                return false; // Pas de correspondance trouvée
            });
        });
               
            
    }

}
