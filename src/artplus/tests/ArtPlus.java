/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.tests;

import artplus.services.CoursServices;
import artplus.utils.MyConnection;

/**
 *
 * @author Admin
 */
public class ArtPlus {
    public static void main(String[] args) {
     MyConnection mc = MyConnection.getInstance();
     CoursServices cs=new CoursServices();
     System.out.println(""+cs.get_cours_by_categorie(2));
     
      

        
    }
}

    

