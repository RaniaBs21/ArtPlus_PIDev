
package artplus.services;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Map extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create a WebView to display the map
        WebView webView = new WebView();

        // Load the OpenStreetMap webpage
        webView.getEngine().load("https://www.openstreetmap.org");

        // Create a scene and add the WebView to it
        Scene scene = new Scene(webView, 800, 600);

        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



