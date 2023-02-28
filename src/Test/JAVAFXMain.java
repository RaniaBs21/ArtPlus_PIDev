
package Test;

import java.awt.Rectangle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;



public class JAVAFXMain extends Application {
    private Stage stage;
    private Parent parent;
    
   

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage= new Stage();
       parent=FXMLLoader.load(getClass().getResource("/views/FillActualite.fxml"));
        //parent=FXMLLoader.load(getClass().getResource("/views/Commentaire.fxml"));
        
        Scene scene=new Scene(parent);
        stage.setScene(scene);
        stage.show();    
        
        
      
        
    }
    public static void main(String[] args){
        launch(args);
    }
    
}
