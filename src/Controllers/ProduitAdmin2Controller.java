/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Categories;
import services.CategoriesCrud;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
 *
 * @author Lenovo
 */
public class ProduitAdmin2Controller implements Initializable {

    @FXML
    private TextField txtnomcatprod;
    @FXML
    private Button btnmodifiercatprod;
    @FXML
    private Button btnsupprimercatprod;
    @FXML
    private TextField txtidcatprod;
    @FXML
    private TableView<Categories> tvcatprod;
    @FXML
    private TableColumn<Categories, Integer> clidcatprod;
    @FXML
    private TableColumn<Categories, String> clnomcatprod;
    @FXML
    private Button btnaffichercatprod;
    @FXML
    private Button btnajoutercatprod;
    @FXML
    private Button comi;
    @FXML
    private Button prodi;
    @FXML
    private Button btnuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifiercatprod(ActionEvent event) {
        String Nom_Cat_Prod=txtnomcatprod.getText();
        int Id_Cat_Prod=Integer.parseInt(txtidcatprod.getText());
        CategoriesCrud ccd= new CategoriesCrud();
        Categories cat= new Categories(Nom_Cat_Prod);
        ccd.modifierCategorie(Id_Cat_Prod, cat);
    }

    @FXML
    private void supprimercatprod(ActionEvent event) { 
        int Id_Cat_Prod =Integer.parseInt(txtidcatprod.getText());
        CategoriesCrud ccd = new CategoriesCrud();
        ccd.supprimerCategorie(Id_Cat_Prod);
    }

    @FXML
    private void affichercatprod(ActionEvent event) {
         CategoriesCrud ccd = new CategoriesCrud();
    List<Categories> categories = ccd.afficherCategorie();

    clidcatprod.setCellValueFactory(new PropertyValueFactory("Id_Cat_Prod"));
    clnomcatprod.setCellValueFactory(new PropertyValueFactory("Nom_Cat_Prod"));
   

    ObservableList<Categories> observablecategories = FXCollections.observableArrayList(categories);
    tvcatprod.setItems(observablecategories);
    }

    @FXML
    private void ajoutercatprod(ActionEvent event) {
        
        String Nom_Cat_Prod=txtnomcatprod.getText();
        Categories c = new Categories(Nom_Cat_Prod);
        CategoriesCrud ccd = new CategoriesCrud();
        ccd.ajouterCategorie(c);
    }

    @FXML
    private void gotoproduit3(ActionEvent event) throws IOException {
        Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/ProduitAdmin3.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }

    @FXML
    private void gotoproduit(ActionEvent event) throws IOException {
        
          Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/ProduitAdmin.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }

    @FXML
    private void gotouserproduit(ActionEvent event) throws IOException {
            Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/ProduitUser.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }
    }
    
    

