/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/artplus";
    public String user="root";
    public String pwd="";
    Connection conx;
    public static MyConnection instance;
    
     public MyConnection(){
        
        try {
            conx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());     
        }
    
    }
     
     public Connection getConx(){
          return conx;
    }

    public static MyConnection getInstance(){
        if (instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    
    
}
