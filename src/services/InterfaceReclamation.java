/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Reclamation;
import java.util.List;

/**
 *
 * @author akrem
 */
public interface InterfaceReclamation {
   public void ajouterreclamation();
   public void ajouterreclamation2 (Reclamation r);
   public void modifierreclamation(Reclamation r);
   public void supprimerreclamation(Reclamation r);
   public List<Reclamation> afficherreclamation(); 
}
