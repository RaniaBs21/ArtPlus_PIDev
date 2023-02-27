/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;


import artplus.entities.Cours;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface InterfaceCours {
      public void ajouterCours();
      public void ajouterCours2(Cours c);
      public void modifierCours(Cours c);
      public List<Cours> afficherCours();
      public void supprimerCours(int Id_c); 
    
}
