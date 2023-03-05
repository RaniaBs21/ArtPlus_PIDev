/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entity.user;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import service.ServiceAdmin;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class RechercheController implements Initializable {

    @FXML
    private TextField filterfield;
    @FXML
    private Button btn_show_detail;
    @FXML
    private Button btn_back;
    @FXML
    private AnchorPane user_list_parent;
    private TableView table;
    private ObservableList data;

    private static int id_user;
    private static int age;
    @FXML
    private Label lb_age_r;
    @FXML
    private Label lb_adresse_r;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      ImageView imageView = new ImageView(getClass().getResource("/ressources/img/rechercher.png").toExternalForm());
       btn_show_detail.setGraphic(imageView);
       
       
        table = new TableView();
        try {
            data = getInitialTableData();
        } catch (Exception ex) {
            Logger.getLogger(RechercheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        table.setItems(data);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id_user"));

        TableColumn prixCol = new TableColumn("NOM");
        prixCol.setCellValueFactory(new PropertyValueFactory("nom_u"));

        TableColumn etatCol = new TableColumn("PRENOM");
        etatCol.setCellValueFactory(new PropertyValueFactory("prenom_u"));

        TableColumn adresseCol = new TableColumn("AGE");
        adresseCol.setCellValueFactory(new PropertyValueFactory("age"));
        
        TableColumn adresseCol2 = new TableColumn("ADRESSE");
        adresseCol.setCellValueFactory(new PropertyValueFactory("adresse"));
        
        TableColumn adresseCol3 = new TableColumn("TELEPHONE");
        adresseCol.setCellValueFactory(new PropertyValueFactory("tel"));
        
        TableColumn adresseCol4 = new TableColumn("ROLE");
        adresseCol.setCellValueFactory(new PropertyValueFactory("role"));

        TableColumn adresseCol5 = new TableColumn("GENERE");
        adresseCol.setCellValueFactory(new PropertyValueFactory("genere"));
        
        TableColumn adresseCol6 = new TableColumn("PASSWORD");
        adresseCol.setCellValueFactory(new PropertyValueFactory("pwd"));


        table.getColumns().setAll(idCol, prixCol, etatCol, adresseCol,adresseCol2,adresseCol3,adresseCol4,adresseCol5,adresseCol6);
        table.setPrefWidth(565);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        user_list_parent.getChildren().add(table);
        
        
        
          FilteredList<user> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(sa -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (sa.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				
				}
				else if (String.valueOf(sa.getAge()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<user> sorteddata = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sorteddata.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sorteddata);
    }    

    @FXML
    private void show_detail() {
             int id = Integer.valueOf(table.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));
        id_user = id;
       
        ServiceAdmin sa = new ServiceAdmin();
        user currentGallerie = sa.getUniqueUser(id);
        
        age = currentGallerie.getAge();

        lb_age_r.setText(String.valueOf(currentGallerie.getAge()));
        lb_adresse_r.setText(currentGallerie.getAdresse());

    }
  
        private ObservableList getInitialTableData(){
        List list = new ArrayList();
        ServiceAdmin sa = new ServiceAdmin();
        list = sa.getAlla();
        ObservableList data = FXCollections.observableList(list);
        return data;
    }
}
