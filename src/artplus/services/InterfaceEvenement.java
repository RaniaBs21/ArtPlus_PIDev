/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import java.util.List;

/**
 *
 * @author rahma
 */
public interface InterfaceEvenement <Evenement>{
    public void ajouterEvenement(Evenement e );
    public void modifierEvenement(Evenement e );
    public void supprimerEvenement(int id); 
    public List<Evenement> afficherEvenements();
    public Evenement findOneById(int id);
}
