/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.utils;


/**
 *
 * @author akrem
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/artplus";
    public String user="root";
    public String pwd="";
    Connection cnx;
    public static MyConnection instance;
    
     private MyConnection(){
        
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());     
        }
    
    }
     
     public Connection getConx(){
          return cnx;
    }

    public static MyConnection getInstance(){
        if (instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    
    
}
