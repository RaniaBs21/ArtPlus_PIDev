/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Post;
import artplus.services.JaimeServices;
import artplus.services.PostServices;
import java.io.ByteArrayInputStream;
//import java.awt.Image;
//import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FillActualiteController implements Initializable {

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
    private Button btn_post;
    @FXML
    private Button btnCom;
    
 
    @FXML
    private Button btnLike;
    
    private int likeCount = 0;
    @FXML
    private TextField searchtxt;
    @FXML
    private Button searchbtn;
    private Label labelpost;
    Connection  cnx;
    @FXML
    private Label labelid;
    @FXML
    private Label labelDesc;
    @FXML
    private Label labeldate;
    @FXML
    private Label labelHR;
    @FXML
    private Label labelimg;
    @FXML
    private ScrollPane scrollPane;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //VBox postVBox = afficherPostVBox();
        //scrollPane.setContent(postVBox);
        
        btn_post.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/views/AjoutPost.fxml"));
               Scene scene=btn_post.getScene();
             scene.setRoot(parent2);
           }catch (IOException ex) {
               Logger.getLogger(FillActualiteController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
        btnCom.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/views/Commentaire.fxml"));
               Scene scene=btnCom.getScene();
             scene.setRoot(parent2);
            
           }catch (IOException ex) {
               Logger.getLogger(FillActualiteController.class.getName()).log(Level.SEVERE, null, ex);
            
           }
        });
        
        btnLike.setOnAction(event -> {
            
            if (btnLike.getText().equals("J'aime")) {
                likeCount++;
                 
                btnLike.setText("Je n'aime plus");
               btnLike.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
            } else {
                likeCount--;
                btnLike.setText("J'aime");
                btnLike.setStyle("");
            }
        });
       

        

 
    } 
      @FXML
    private void searchPost(ActionEvent event) {
        Date Date_Post=Date.valueOf(searchtxt.getText());
        PostServices css=new PostServices();
        Post p = css.searchPostbyDate(Date_Post);
        if ( p != null){
          labelid.setText("Id_Post"+p.getId_Post());
          labelDesc.setText("Description_Post :" +p.getDescription_Post());
          labeldate.setText("Date_Post :" +p.getDate_Post());
          labelHR.setText("Heure_Post :" +p.getHeure_Post());
          labelimg.setText("Img_Post :" +p.getImg_Post());
        }
        else{
            labelpost.setText(("commentaire n'a pas trouvé"));
        } 
    }



   public VBox afficherPostVBox() {
        PostServices pcd = new PostServices();
        List <Post> myList = pcd.afficherPost();

        VBox vBox = new VBox();
        vBox.setSpacing(20); // espacement vertical entre les éléments

        //affichage pour les Oeuvres/////
        for (Post p : myList) {
            HBox hbox = new HBox();
            
            Label nomLabel = new Label(p.getDescription_Post());
            nomLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black; -fx-font-weight: bold;");
            
            
            
            ImageView imageView = new ImageView(new Image(p.getImg_Post()));
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            
            Label dateLabel = new Label(p.getDate_Post().toString());
            dateLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
            

            hbox.getChildren().addAll( nomLabel,imageView, dateLabel, createStatusBox());
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(10));

            vBox.getChildren().add(hbox);
        }

        return vBox;
    }

    private HBox createStatusBox() {
        HBox statusHBox = new HBox();
        statusHBox.setSpacing(5);
        statusHBox.setAlignment(Pos.CENTER_LEFT);

        // add status elements to statusHBox

        return statusHBox;
    }




  
 
} 
       

       
       
    
   
