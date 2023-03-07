/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie_cours;
import java.util.List;

public interface InterfaceCategorie {
public void ajouterCategorie();
public void ajouterCategorie2(Categorie_cours cat);
public void modifierCategorie(Categorie_cours cat);
public List<Categorie_cours> afficherCategorie();
public void supprimerCategorie(int Id_cat) ;


}
