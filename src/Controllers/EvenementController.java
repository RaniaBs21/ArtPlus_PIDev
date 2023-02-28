/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Evenement;
import artplus.utils.MyConnection;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rahma
 */
public class EvenementController implements Initializable {

    Connection cnx;
    

    public EvenementController() {
        cnx = MyConnection.getInstance().getCnx();
    }
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
    private Button btnParticiper;
    @FXML
    private Button btnV;
    @FXML
    private Text nomEv;
    @FXML
    private Text DateEV;
    @FXML
    private Text LocalisationEV;
    @FXML
    private ImageView imgEv;
    @FXML
    private ImageView iconCalendrier;
    @FXML
    private ImageView iconLocal;
    @FXML
    private Text descriptionEv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nom_Ev;
        Timestamp Date_EV;
        String description_EV;
        String Localisation_EV;
        Blob imag_Ev;
        byte byte_Img[];
        try {
            String req = "select titre_ev, description_ev, image_ev, adresse_ev, date_ev FROM evenement";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                nom_Ev = rs.getString("titre_ev");
                nomEv.setText(nom_Ev);
                description_EV=rs.getString("description_ev");
                descriptionEv.setText(description_EV);
                imag_Ev=rs.getBlob("image_ev");
                byte_Img=imag_Ev.getBytes(1, (int)imag_Ev.length());
                Image img = new Image(new ByteArrayInputStream(byte_Img, (int)imgEv.getFitWidth(), (int) imgEv.getFitHeight()));
                imgEv.setImage(img);
//Image img = new Image(new BySteArrayInputStream(byteImg),imgEv.getFitWidth(), imgEv.getFitHeight());
                Localisation_EV=rs.getString("adresse_ev");
                Date_EV=rs.getTimestamp("date_ev");
                DateEV.setText(Date_EV.toString());
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        btnParticiper.setOnAction(event -> {
            try {
                Parent parent2 = FXMLLoader.load(getClass().getResource("/Views/Page2.fxml"));

                Scene scene = btnParticiper.getScene();
                scene.setRoot(parent2);

            } catch (IOException ex) {
                Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);

            }
        });
    }

}
