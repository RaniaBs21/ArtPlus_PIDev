/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Post;
import artplus.services.PostServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjoutPostController implements Initializable {

    @FXML
    private Button Acc;
    @FXML
    private Button FAcc;
    @FXML
    private Button Ev;
    @FXML
    private Button Crs;
    @FXML
    private Button Prod;
    @FXML
    private Button Qz;
    @FXML
    private Button Ass;
    @FXML
    private Button btnGal;
    @FXML
    private Button btnPub;
    @FXML
    private TextField photoPath;
    @FXML
    private TextArea txtPost;
    @FXML
    private Button idAcc;
 
    private File file;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         btnPub.setOnAction(event -> {
            
           // Post p = new Post(txtPost.getText(),photoPath.getText());
           Post p = new Post(txtPost.getText(),"http://localhost/Img/"+file.getName());
            PostServices cs = PostServices.getInstance();
            cs.ajouterPost2(p);
        
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Post insérée avec succés!");
            alert.show();
            txtPost.setText("");
            //photoPath.setText("");
            
        });
          idAcc.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/views/FillActualite.fxml"));
               Scene scene=idAcc.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
    }    

    @FXML
    void SelectFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMAGE FILES","*.jpg","*.png","*.gif")
        );
        
        File file =fileChooser.showOpenDialog(photoPath.getScene().getWindow());
        
        if (file != null){
            photoPath.setText(file.getPath());
        
        }else {
            System.out.println("error");
        
        }
        
        
    }
}
