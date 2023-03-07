/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Commande;
import entities.Produit;
import services.CommandeCrud;
import services.Mailing;
import services.ProduitCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ProduitAdmin3Controller implements Initializable {

    @FXML
    private TextField txtptredcmd;
    @FXML
    private DatePicker datecmd;
    @FXML
    private DatePicker dateliv;
    @FXML
    private TextField txtideutcmd;
    @FXML
    private TextField txtidcmd;
    @FXML
    private TableColumn<Commande, Integer> idcmd;
    @FXML
    private TableColumn<Commande, Integer> ptredcmd;
    @FXML
    private TableColumn<Commande, Integer> idutcmd;
    @FXML
    private Button cati;
    @FXML
    private Button prodi;
    @FXML
    private TableView<Commande> tvcmd;
    @FXML
    private TableColumn<Commande, String> dateducmd;
    @FXML
    private TableColumn<Commande, String> datedeliv;
    @FXML
    private Button btnajoutercmd;
    @FXML
    private Button btnmodifiercmd;
    @FXML
    private Button btnsupprimercmd;
    @FXML
    private Button btnaffichercmd;
    @FXML
    private Button btnuser;
    @FXML
    private Button btnuser1;
      Commande u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void gotoproduit2(ActionEvent event)throws IOException {
        Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/ProduitAdmin2.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }

    @FXML
    private void gotoproduit(ActionEvent event)  throws IOException {
        Parent produitAdminParent = FXMLLoader.load(getClass().getResource("/Views/ProduitAdmin.fxml"));
        Scene produitAdminScene = new Scene(produitAdminParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdminScene);
        window.show();
    }

    @FXML
    private void ajoutercmd(ActionEvent event) {
        Calendar cal = Calendar.getInstance();
        Mailing mail = new Mailing();
        
        int Pt_Red_Cmd=Integer.parseInt(txtptredcmd.getText());

LocalDate Date_Cmd = datecmd.getValue();
LocalDate Date_Liv = dateliv.getValue();
int Id_Cart=Integer.parseInt(txtideutcmd.getText());
Commande cmd = new Commande(Pt_Red_Cmd,Date_Cmd,Date_Liv,Id_Cart);
int idbody = 0 ;
CommandeCrud ccd = new CommandeCrud();
ccd.ajouterCommande(cmd);
        
        try {
            
            mail.sendEmail("chedybouhlel00@gmail.com", "VALIDATION DU COMMANDE",mail.Body(idbody));
        } catch (Exception ex) {
            Logger.getLogger(ProduitAdmin3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

      
        
        
    }

    @FXML
    private void modifiercmd(ActionEvent event) {
   
        u  =tvcmd.getSelectionModel().getSelectedItem();
        int selectedID = tvcmd.getSelectionModel().getSelectedIndex();
      // int Pt_Red_Cmd= Integer.parseInt(txtptredcmd.getText());
         int Pt_Red_Cmd = Integer.parseInt(txtptredcmd.getText());
           
           java.sql.Date  Date_Cmd = java.sql.Date.valueOf(datecmd.getValue());
           java.sql.Date  Date_Liv = java.sql.Date.valueOf(dateliv.getValue());
          
        
        //LocalDate Date_Cmd=datecmd.getValue();
    
     //   LocalDate Date_Liv=dateliv.getValue();
        int Id_Cart=Integer.parseInt(txtideutcmd.getText());
//        int Id_Cmd=Integer.parseInt(txtidcmd.getText());
        Commande cmd = new Commande(Pt_Red_Cmd, Date_Cmd, Date_Liv, Id_Cart);
    System.out.println("ttttt"+cmd);
        CommandeCrud ccd = new CommandeCrud();
        ccd.modifierCommande(cmd,u.getId_Cmd());
        
    }

    @FXML
    private void supprimercmd(ActionEvent event) {
             CommandeCrud pcd = new CommandeCrud();
          Commande u  =tvcmd.getSelectionModel().getSelectedItem();
        int selectedID = tvcmd.getSelectionModel().getSelectedIndex();
       Stage stage;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove");
        alert.setHeaderText("vous voulez supprimer commande ?");
    
        if (alert.showAndWait().get() == ButtonType.OK ) {
            pcd.supprimerCommande(u);

            System.out.println("");
    }
    }

    @FXML
    private void affichercmd(ActionEvent event) {
        CommandeCrud ccd = new CommandeCrud();
    List<Commande> commandes = ccd.afficherCommande();
  
    idcmd.setCellValueFactory(new PropertyValueFactory("Id_Cmd"));
    ptredcmd.setCellValueFactory(new PropertyValueFactory("Pt_Red_Cmd"));
    dateducmd.setCellValueFactory(new PropertyValueFactory("Date_Cmd"));
    datedeliv.setCellValueFactory(new PropertyValueFactory("Date_Cmd"));
    idutcmd.setCellValueFactory(new PropertyValueFactory("Id_Cart"));

    ObservableList<Commande> observableCommandes = FXCollections.observableArrayList(commandes);
    tvcmd.setItems(observableCommandes);
    }

      private void gotouserproduit(ActionEvent event) throws IOException {
            Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/ProduitUser.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }

    @FXML
    private void gotousermode(ActionEvent event) {
    }

    private void selectrow(MouseEvent event) {
         u  = tvcmd.getSelectionModel().getSelectedItem();
        System.out.println("id"+u.getId_Cmd());
        String Pt_Red_Cmd = Integer.toString(u.getPt_Red_Cmd());
        ptredcmd.setText(Pt_Red_Cmd);
        LocalDate Date_Cmd=datecmd.getValue();
        LocalDate Date_Liv=dateliv.getValue();
        String Id_Cart = Integer.toString(u.getId_Cart());
        txtideutcmd.setText(Id_Cart);
    }

    @FXML
    private void gotopanier(ActionEvent event) throws IOException {
        Parent produitAdmin2Parent = FXMLLoader.load(getClass().getResource("/Views/Panier.fxml"));
        Scene produitAdmin2Scene = new Scene(produitAdmin2Parent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(produitAdmin2Scene);
        window.show();
    }
    
}
