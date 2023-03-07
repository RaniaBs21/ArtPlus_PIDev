/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import entities.Participation;
import utils.MyConnection;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author rahma
 */
public class EvenementAccueilController implements Initializable {

    @FXML
    private Text accueil;

    @FXML
    private Button btnDesinscrire;

    @FXML
    private Button btnParticiper;

    @FXML
    private Text cours;

    @FXML
    private Text dateEv;

    @FXML
    private TextFlow descrFlow;

    @FXML
    private Text descriptionEv;

    @FXML
    private Text ev;

    @FXML
    private Text filactualite;

    @FXML
    private ImageView iconCalendrier;

    @FXML
    private ImageView iconLocal;

    @FXML
    private ImageView imageLog;

    @FXML
    private Text localisationEv;

    @FXML
    private Text nomEv;

    @FXML
    private Text quiz;

    @FXML
    private Text reclam;

    @FXML
    private Text shop;

    Connection cnx;
    @FXML
    private ToggleButton btnPrecedent;
    @FXML
    private ToggleButton btnSuivant;
    @FXML
    private Text LienMusee;

    public EvenementAccueilController() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void afficherEvenements() {
        
        String req = "select * FROM evenement";
        String nom_Ev;
        Timestamp Date_EV;
        String description_EV;
        String Localisation_EV;
        byte byte_Img[];
        Blob blob;

        try {
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                nom_Ev = rs.getString("titre_ev");
                nomEv.setText(nom_Ev);
                description_EV = rs.getString("description_ev");
                descriptionEv.setText(description_EV);
                blob = rs.getBlob("image_ev");
                byte_Img = blob.getBytes(1, (int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byte_Img), imageLog.getFitWidth(), imageLog.getFitHeight(), true, true);
                imageLog.setImage(img);

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
        AfficherMusees();
       
    }
    

    @FXML
    private void participer() {
        try {
//            String req = "INSERT INTO participation(Id_Ut,id_ev,date_participation) VALUES (?,?,?)";

            String req = "INSERT INTO `participation`(`Id_Ut`, `id_ev`, `date_participation`) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, 1);
            pst.setInt(2, 2);
            Participation part=new Participation();
            pst.setTimestamp(3, part.getDate_participation());

            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vous ete inscris au evenement avec succées", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void desinscrire() {
        try {
            String req = "DELETE FROM `participation`";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vous ete desinscris au evenement avec succées", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void precedent() {
        String nom = nomEv.getText();
        String req = "SELECT `id_ev`,`titre_ev` FROM `evenement` WHERE `titre_ev` = '" + nom + "'";
        //String req = "select id_Ev from evenement where titre_ev like '" + nom + "'";
        int pos = 0;
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pos = rs.getInt("id_ev");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        String req1 = "select * FROM evenement where id_ev <='" + pos + "'";
        String nom_Ev;
        Timestamp Date_EV;
        String description_EV;
        String Localisation_EV;
        byte byte_Img[];
        Blob blob;

        try {
            PreparedStatement st = cnx.prepareStatement(req1);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                nom_Ev = rs.getString("titre_ev");
                nomEv.setText(nom_Ev);
                description_EV = rs.getString("description_ev");
                descriptionEv.setText(description_EV);
                blob = rs.getBlob("image_ev");
                byte_Img = blob.getBytes(1, (int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byte_Img), imageLog.getFitWidth(), imageLog.getFitHeight(), true, true);
                imageLog.setImage(img);

            } else {
                System.out.println("Aucun autre évenement");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void suivant() {
        String nom = nomEv.getText();
        String req = "SELECT `id_ev`,`titre_ev` FROM `evenement` WHERE `titre_ev` = '" + nom + "'";

        int pos = 0;
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                pos = rs.getInt("id_ev");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        String req1 = "select * FROM evenement where id_ev >'" + pos + "'";
        String nom_Ev;
        Timestamp Date_EV;
        String description_EV;
        String Localisation_EV;
        byte byte_Img[];
        Blob blob;

        try {
            PreparedStatement st = cnx.prepareStatement(req1);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                nom_Ev = rs.getString("titre_ev");
                nomEv.setText(nom_Ev);
                description_EV = rs.getString("description_ev");
                descriptionEv.setText(description_EV);
                blob = rs.getBlob("image_ev");
                byte_Img = blob.getBytes(1, (int) blob.length());
                Image img = new Image(new ByteArrayInputStream(byte_Img), imageLog.getFitWidth(), imageLog.getFitHeight(), true, true);
                imageLog.setImage(img);

            } else {
                System.out.println("Aucun autre évenement");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void AfficherMusees() {
        LienMusee.setOnMouseClicked(event
                -> {
            try {
                Parent parent2 = FXMLLoader.load(getClass().getResource("/GUI/Page2.fxml"));

                Scene scene = LienMusee.getScene();
                scene.setRoot(parent2);
            } catch (IOException ex) {
                Logger.getLogger(EvenementAccueilGuideController.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        );

    }

}
