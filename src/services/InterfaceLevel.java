/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Level;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface  InterfaceLevel {
     public void ajouterLevel();
     public void ajouterLevel2(Level lev);
     public void modifierLevel(Level lev);
     public List<Level> afficherLevel();
     public void supprimerLevel(int Id_level);
     
    
}