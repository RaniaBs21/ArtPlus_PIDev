/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.lang.Integer;
import entities.Produit;
import services.ProduitCrud;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ProduitAdminController implements Initializable {

    @FXML
    private TextField txttypeprod;
    @FXML
    private TextField txtdescprod;
    @FXML
    private TextField txtprixprod;
    @FXML
    private TextField txturl;
    @FXML
    private Button btnajouterprod;
    @FXML
    private Button btnmodifierprod;
    @FXML
    private Button btnsupprimerprod;
    @FXML
    private TextField txtidprod;
    @FXML
    private TableView<Produit> tvprod;
    @FXML
    private TableColumn<Produit,Integer> idprod;
    @FXML
    private TableColumn<Produit, String> typeprod;
    @FXML
    private TableColumn<Produit, String> descprod;
    @FXML
    private TableColumn<Produit, Integer> prixprod;
    @FXML
    private TableColumn<Produit, String> imageprod;
    @FXML
    private Button btnafficherprod;
    @FXML
    private Button cati;
    @FXML
    private Button comi;
    @FXML
    private Button btnuser;
    @FXML
    private Button btnchercherproduit;
    @FXML
    private Label resultLabel;
    @FXML
    private Label resultLabel1;
    @FXML
    private Button btnuser1;
    @FXML
    private Button btnImage;
    String TempprofilePicture;
    Produit uc;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void ajouterprod(ActionEvent event) {
   
        String Type_Prod =txttypeprod.getText();
        String Description_Prod=txtdescprod.getText();
        int Prix_Prod=Integer.parseInt(txtprixprod.getText());
         txturl.setText( TempprofilePicture);
        
        Produit p = new Produit(Type_Prod,Description_Prod,Prix_Prod,TempprofilePicture);
        ProduitCrud pcd = new ProduitCrud();
        pcd.ajouterProduit2(p);
    }

    @FXML
    private void modifierprod(ActionEvent event) {
        Produit u  =tvprod.getSelectionModel().getSelectedItem();
        int selectedID = tvprod.getSelectionModel().getSelectedIndex();
       
        String Type_Prod =txttypeprod.getText();
        String Description_Prod=txtdescprod.getText();
        int Prix_Prod=Integer.parseInt(txtprixprod.getText());
           String Url=btnImage.getText();
        

        ProduitCrud pcd = new ProduitCrud();
        Produit p= new Produit(Type_Prod,Description_Prod,Prix_Prod,Url);
        pcd.modifierProduit(p, uc.getId_Prod());
    }

    @FXML
    private void supprimerprod(ActionEvent event) {
       Produit u  =tvprod.getSelectionModel().getSelectedItem();
        int selectedID = tvprod.getSelectionModel().getSelectedIndex();
      //  int Id_Prod =Integer.parseInt(txtidprod.getText());
        ProduitCrud pcd = new ProduitCrud();
        pcd.supprimerProduit(u);
    }
    

    @FXML
    private void afficherprod(ActionEvent event) {
         ProduitCrud pcd = new ProduitCrud();
    List<Produit> produits = pcd.afficherProduit();

    idprod.setCellValueFactory(new PropertyValueFactory("Id_Prod"));
    typeprod.setCellValueFactory(new PropertyValueFactory("Type_Prod"));
    descprod.setCellValueFactory(new PropertyValueFactory("Description_Prod"));
    prixprod.setCellValueFactory(new PropertyValueFactory("Prix_Prod"));
    imageprod.setPrefWidth(20);
    imageprod.setCellValueFactory(new PropertyValueFactory("url"));

    ObservableList<Produit> observableProduits = FXCollections.observableArrayList(produits);
    tvprod.setItems(observableProduits);
}
       
    
    @FXML
    private void gotoproduit2(ActionEvent event) 
    
 throws IOException {
        Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/ProduitAdmin2.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }
    @FXML
    private void gotoproduit3(ActionEvent event)     
 throws IOException {
        Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/ProduitAdmin3.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }

    @FXML
  
        
      private void gotouserproduit(ActionEvent event) throws IOException {
            Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/ProduitUser2.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }

    @FXML
    private void chercherproduit(ActionEvent event) {
        
        int Id_Produit =Integer.parseInt(txtidprod.getText());
        ProduitCrud pcd = new ProduitCrud();
        
    Produit p = pcd.rechercherProduitbyid(Id_Produit);
    if (p != null) {
        resultLabel.setText("Product found: " + "Id produit="+ p.getId_Prod() + "Nom="+ p.getType_Prod());
                resultLabel1.setText("Prix=" +p.getPrix_Prod());
    } else {
        resultLabel.setText("Product not found.");
    }
}

    @FXML
    private void selectrow(MouseEvent event) {
          uc  = tvprod.getSelectionModel().getSelectedItem();
        System.out.println("id"+uc.getId_Prod());
        
        
         txttypeprod.setText(uc.Type_Prod);
        txtdescprod.setText(uc.Description_Prod);
     //   int Prix_Prod=Integer.parseInt(txtprixprod.getText());
        String Prix_Prod = Integer.toString(uc.getPrix_Prod());
        txtprixprod.setText(Prix_Prod);
        imageprod.setText(uc.Url);
    }

    @FXML
    private void gotopanier(ActionEvent event) throws IOException {
        Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/Panier.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }

    @FXML
    private void selectionnerImage(ActionEvent event) {
              FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    //    fileChooser.setInitialDirectory(new File("C:\\Users\\USER\\Desktop\\image"));
         fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
           TempprofilePicture = file.toURI().toString();
            System.out.println(TempprofilePicture);
            //Image image = new Image(TempprofilePicture);
            txturl.setText(TempprofilePicture);
            
    }
    
}
}

