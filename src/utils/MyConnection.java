/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rania
 */
public class MyConnection {
     private String url="jdbc:mysql://localhost:3306/artplus";
    private String user="root";
    private String pass="";
    private Connection connection; 
    static MyConnection instance;
    
    private MyConnection(){
        try {
            connection =  DriverManager.getConnection(url, user, pass);
            System.out.println("connection etablie");
        } catch (SQLException ex) {
            System.out.println("connection echoué");
        }
    
    }
    public static MyConnection getInstance(){
        
        if(instance ==null)
        {
            instance= new MyConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
