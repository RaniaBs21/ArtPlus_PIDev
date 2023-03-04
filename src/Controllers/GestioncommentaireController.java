/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class GestioncommentaireController implements Initializable {

    @FXML
    private Label labeltitre;
    @FXML
    private Label labelcont;
    @FXML
    private Label labelnbcom;
    @FXML
    private TableView<?> tvcommentaire;
    @FXML
    private TableColumn<?, ?> colcontenu;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private TableColumn<?, ?> coliduser;
    @FXML
    private TableColumn<?, ?> colnblike;
    @FXML
    private TableColumn<?, ?> colnbdislike;
    @FXML
    private TableColumn<?, ?> editcol;
    @FXML
    private FontAwesomeIconView backbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutercommentaire(MouseEvent event) {
    }

    @FXML
    private void backtopic(MouseEvent event) {
    }
    
}
