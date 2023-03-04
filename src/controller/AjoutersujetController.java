/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Sujet;
import entities.Topic;
import entities.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCommentaireIMP;
import services.ServiceSujetIMP;
import services.ServiceTopicIMP;
import static utils.BadWords.chackwords;
import utils.Mail;

/**
 * FXML Controller class
 *
 * @author rania
 */
public class AjoutersujetController implements Initializable {
ServiceTopicIMP st=new ServiceTopicIMP();
GestionsujetController  gestionsujetController=new GestionsujetController();
                ServiceSujetIMP sp = new ServiceSujetIMP();

@FXML
    private JFXTextField inputtitre;
@FXML
    private TextArea inputdesc;
    @FXML
    private JFXButton addbtn;
public static int idtopic;
   int attention=0;
    @FXML
    private TextField image_field;
    @FXML
    private Button browse_button;
    FileChooser fileChooser = new FileChooser();
    File selectedFile=null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

    @FXML
    private void save(MouseEvent event)  {
            ServiceCommentaireIMP scom=new ServiceCommentaireIMP();

         Utilisateur user=scom.getuserbyid(GestiontopicController.iduser);
        String titre = inputtitre.getText();
        String description = inputdesc.getText();

        if (titre.isEmpty() || description.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {try {
            if(chackwords(titre).equals("false")&&chackwords(description).equals("false")){
                InputStream inputStream = new FileInputStream(selectedFile);
                byte[] imageBytes = new byte[inputStream.available()];
                inputStream.read(imageBytes);
                inputStream.close();
                if(sp.verfisujetexiste(titre,description)==0){
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Sujet s=new Sujet();
                s.setTitresujet(titre);
                s.setContenu(description);
                s.setDate(dateFormat.format(date));
                s.setIdtopic(idtopic);
                s.setIduser(user.getId());
                s.setPhoto(imageBytes);
                if(sp.ajout(s))
                {  int nbsujet=st.getnbsujet(idtopic);
                nbsujet++;
                st.setnbsujet(idtopic, nbsujet);
                Stage stage = (Stage) addbtn.getScene().getWindow();
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("sujet ajouté avec succes!");
                alert.show();
                clean();
                /*Notifications notificationbuilder;
                    notificationbuilder = Notifications.create()
                           .title("Alert").text("topic ajouté avec succes!").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event)
                                {
                                    System.out.println("clicked on");
                                }
                            });
                notificationbuilder.darkStyle();
                notificationbuilder.showInformation();*/
                }else
                {
                    System.err.println("System tray not supported!");
                }}
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("sujet existe deja!");
                alert.show();
                }
            }else
            {
                   attention++;
                 clean();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Worning !! ");
                alert.setContentText("vous ne pouvez pas ajouter ce sujet avec ces mots ! ");
                alert.show();
                
                if(attention>2)
                {
                    System.out.println(attention);
                 Mail.envoyer(user);
                }
               
                }
                
            }    catch (IOException ex) {
                Logger.getLogger(AjoutersujetController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void clean() {
        inputtitre.setText(null);
        inputdesc.setText(null);
    }

    @FXML
    private void browse_images(ActionEvent event) { 
        fileChooser.setTitle("Select File");
        
        // Set initial directory (optional)
        File initialDirectory = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(initialDirectory);

        // Add file filters (optional)
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        Stage stage = (Stage) browse_button.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
                image_field.setText(selectedFile.getAbsolutePath());
        }
        else{ 
            image_field.setText(null);
            
        }
       
    }
    
}
