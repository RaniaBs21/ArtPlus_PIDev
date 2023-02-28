/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import artplus.entities.Evenement;
import artplus.services.EvenementService;
import artplus.utils.MyConnection;
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    }

    public void showEv() {
        Evenement ev = tableEv.getSelectionModel().getSelectedItem();
        String req2 = "select titre_ev,categorie_ev,description_ev,adresse_ev,image_ev,date_ev,nbre_places FROM evenement ";
        //        String req2 = "select titre_ev,categorie_ev,description_ev,adresse_ev,image_ev,date_ev,nbre_places FROM evenement WHERE titre_ev= '" + titre + "'" ;

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

    @FXML
    private void RechEV() {
/*
        String categorie = String.valueOf(rechercher.getText());
        EvenementService evS = new EvenementService();
        Evenement ev = evS.searchEvenementbyCategorie(categorie);
        if (ev != null) {
            titre.setCellValueFactory(new PropertyValueFactory<Evenement, String>("titre_ev"));
            categorie.setCellValueFactory(new PropertyValueFactory<Evenement, String>("categorie_ev"));
            description.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description_ev"));
            adresse.setCellValueFactory(new PropertyValueFactory<Evenement, String>("adresse_ev"));
            date.setCellValueFactory(new PropertyValueFactory<Evenement, Timestamp>("date_ev"));
            nbplaces.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("nbre_places"));
            tableEv.setItems(listEv);
        } else {
            labelpost.setText(("commentaire n'a pas trouvé"));
        }
*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficherEvenements();

        //showEv();
    }
    private FileInputStream fs;

    @FXML
    private void ajouter() throws FileNotFoundException, ParseException {
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
        String sql = "UPDATE `evenement` SET `titre_ev`=?,`categorie_ev`=?',`description_ev`=?,`image_ev`=?,`adresse_ev`=?,`date_ev`=?,`nbre_places`=? WHERE id_ev = " + idE ;
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

            txt_titre.setText("");
            txt_categorie.setText("");
            txt_description.setText("");
            txt_addresse.setText("");
            txt_date.setText("");
            txt_nbplaces.setText("");
            imgEv.setImage(null);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Evenement Modifié avec succées", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
