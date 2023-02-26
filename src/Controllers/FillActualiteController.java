/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Post;
import artplus.services.PostServices;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

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
    private TableColumn<?, ?> IdPost;
    @FXML
    private TableColumn<?, ?> DescPost;
    @FXML
    private TableColumn<?, ?> DatePost;
    @FXML
    private TableColumn<?, ?> HeurePost;
    @FXML
    private TableColumn<?, ?> ImgPost;
    @FXML
    private Button btnLike;
    
    private int likeCount = 0;
    @FXML
    private TableView<Post> TablePost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_post.setOnAction( event->{
           try{
               Parent parent2=FXMLLoader
                       .load(getClass().getResource("/views/AjoutPost.fxml"));
               Scene scene=btn_post.getScene();
             scene.setRoot(parent2);
             
             //**********methode 2************
             
//                Scene scene=new Scene(parent);
//                Stage stage=(Stage) ((Node) event.getSource())
//                        .getScene().getWindow();
//               stage.setScene(scene);
//               stage.setScene(scene);
//              stage.setTitle("Interface2");
//               stage.show();

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
//        btnLike.setOnAction(event -> {
//            if (btnLike.getText().equals("J'aime")) {
//                likeCount++;
//                btnLike.setText("Je n'aime plus");
//                btnLike.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
//            } else {
//                likeCount--;
//                btnLike.setText("J'aime");
//                btnLike.setStyle("");
//            }
//        });

       
        
    } 

 

//    @FXML
//    private void afficherPost(ActionEvent event) {
//       PostServices p = new PostServices();
//       List<Post> myList= p.afficherPost();
//        
//       IdPost.setCellValueFactory(new PropertyValueFactory("Id_Post"));
//       DescPost.setCellValueFactory(new PropertyValueFactory("Description_Post"));
//       DatePost.setCellValueFactory(new PropertyValueFactory("Date_Post"));
//       HeurePost.setCellValueFactory(new PropertyValueFactory("Heure_Post"));
//       ImgPost.setCellValueFactory(new PropertyValueFactory("Img_Post"));
//
//       ObservableList<Post> observablepost = FXCollections.observableArrayList(myList);
//       tablePost.setItems(observablepost);
//    
//
//    }
//    

    @FXML
    private void afficherPost(ActionEvent event) {
       PostServices p = new PostServices();
       List<Post> myList= p.afficherPost();
        
       IdPost.setCellValueFactory(new PropertyValueFactory("Id_Post"));
       DescPost.setCellValueFactory(new PropertyValueFactory("Description_Post"));
       DatePost.setCellValueFactory(new PropertyValueFactory("Date_Post"));
       HeurePost.setCellValueFactory(new PropertyValueFactory("Heure_Post"));
       ImgPost.setCellValueFactory(new PropertyValueFactory("Img_Post"));
       
       ObservableList<Post> observablecommentaire = FXCollections.observableArrayList(myList);
       TablePost.setItems(observablecommentaire);
    }
} 
       

       
       
    
   
