/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Commentaire;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface InterfaceCommentaireServices {
     public void ajouterCommentaire();
     public void ajouterCommentaire2(Commentaire c);
     public void modifierCommentaire(Commentaire c);
     public void supprimerCom(int Id_Com);
     public List<Commentaire> afficherCommentaire();
    
}