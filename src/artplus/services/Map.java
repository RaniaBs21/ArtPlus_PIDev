/* 
package artplus.services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;


public class Map {
    public static void main(String[] args) {
        // Set up the API context with your API key
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDrlMTE7IJwdKpn71DzSX3feF8HT5y3f14")
                .build();

        try {
            // Geocode an address
            GeocodingResult[] results = GeocodingApi.geocode(context, "1600 Amphitheatre Parkway, Mountain View, CA 94043").await();
            
            // Print the latitude and longitude
            System.out.println("Latitude: " + results[0].geometry.location.lat);
            System.out.println("Longitude: " + results[0].geometry.location.lng);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/


/*
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Map extends JFrame {

    private JMapViewer mapViewer;
    private List<MapMarkerDot> markers;

    public Map() {
        super("OpenStreetMap Example");

        // Create the map
        mapViewer = new JMapViewer();
        mapViewer.setDisplayPosition(new Coordinate(48.858181, 2.29450), 10);

        // Add some markers to the map
        markers = new ArrayList<>();
        markers.add(new MapMarkerDot(new Coordinate(48.858181, 2.29450)));
        markers.add(new MapMarkerDot(new Coordinate(48.859083, 2.294537)));
        markers.add(new MapMarkerDot(new Coordinate(48.858322, 2.294350)));
        markers.add(new MapMarkerDot(new Coordinate(48.857563, 2.294122)));
        markers.add(new MapMarkerDot(new Coordinate(48.857068, 2.294145)));
        markers.forEach(marker -> marker.setBackColor(Color.RED));
        markers.forEach(marker -> mapViewer.addMapMarker(marker));

        // Add the map to a panel
        JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.add(mapViewer);

        // Add the panel to the frame
        getContentPane().add(mapPanel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Map();
    }
}
*/

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



