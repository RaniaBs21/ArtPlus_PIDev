/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.utils.MyConnection;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author rahma
 */
public class EvenementGuideController implements Initializable {

    Connection cnx;

    public EvenementGuideController() {
        cnx = MyConnection.getInstance().getCnx();
    }
    @FXML
    private ImageView imgEv;
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
    private Button ajouter;
    @FXML
    private Text dateEv;
    @FXML
    private Button btnDesinscrire;

    @FXML
    private ImageView iconCalendrier;
    @FXML
    private ImageView iconLocal;
    @FXML
    private Text descriptionEv;
    @FXML
    private Text nomEv;
    @FXML
    private Text localisationEv;

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
            String req = "select titre_ev, description_ev, image_ev, adresse_ev, date_ev FROM evenement where id_ev = 4";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                nom_Ev = rs.getString("titre_ev");
                nomEv.setText(nom_Ev);
                description_EV = rs.getString("description_ev");
                descriptionEv.setText(description_EV);
                /*byte[] img =rs.getBytes("image_Ev");
                ImageIcon imagEv =new ImageIcon(img);
                java.awt.Image im = imagEv.getImage();
                java.awt.Image myImg = im.getScaledInstance(imgEv.getWidth, 0, 0);*/

                imag_Ev = rs.getBlob("image_ev");
                byte_Img = imag_Ev.getBytes(1, (int) imag_Ev.length());
                Image img = new Image(new ByteArrayInputStream(byte_Img), imgEv.getFitWidth(), imgEv.getFitHeight(), true, true);
                //Image img = new Image(new ByteArrayInputStream(byte_Img), (int) imgEv.getFitWidth(), (int) imgEv.getFitHeight()));
                imgEv.setImage(img);

                /*ImageView imageView = new ImageView(img);
                VBox root = new VBox();
                root.getChildren().add(imageView);
                // Création de la scène avec la boîte verticale comme racine
                Scene scene = new Scene(root, 300, 200);*/
                Localisation_EV = rs.getString("adresse_ev");
                localisationEv.setText(Localisation_EV);
                Date_EV = rs.getTimestamp("date_ev");
                dateEv.setText(Date_EV.toString());

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        ajouter.setOnAction(event
                -> {
            try {
                Parent parent2 = FXMLLoader.load(getClass().getResource("/Views/AjouterEvenement.fxml"));

                Scene scene = ajouter.getScene();
                scene.setRoot(parent2);
                /* Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();
                
                stage.setScene(scene);
                Scene scene = btnParticiper.getScene();*/

//               stage.setScene(scene);
//              stage.setTitle("Interface2");
//               stage.show();
            } catch (IOException ex) {
                Logger.getLogger(EvenementGuideController.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        );
    }

    @FXML
    private void participer() {
    }

    @FXML
    private void desinscrire() {
    }

}
