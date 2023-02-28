/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author rahma
 */
public class Page2Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            URL obj = new URL("https://api.harvardartmuseums.org/object?apikey=21fd108b-a528-4da8-be40-76cf10cd183c");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuffer response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                System.out.println(response);
            } else {
                System.out.println("GET request di");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Page2Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Page2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {


        /*
        URLConnection urlc = url.openConnection(); //use post mode 
        urlc.setDoOutput(true);
        urlc.setAllowUserInteraction(false); //send query 
        PrintStream ps = new PrintStream(urlc.getOutputStream());
        ps.print(query);
        ps.close(); //get result  
        BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        String l = null;
        while ((l = br.readLine()) != null) {
            System.out.println(l);
        }
        br.close();
         */
    }

}
