/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Sous_categorie;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface InterfaceSous_categorie {
    public void ajouterSous_categorie();
    public void ajouterSous_categorie2(Sous_categorie scat);
    public void modifierSous_categorie(Sous_categorie scat);
    public List<Sous_categorie> afficherSous_Categorie();
    public void supprimerSous_categorie(int Id_sc);
}
