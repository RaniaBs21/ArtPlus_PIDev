/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author nour
 * @param <Reponse_Quiz>
 */
public interface InterfaceReponse <Reponse_Quiz> {
    
    public void ajouterReponse2(Reponse_Quiz q );
    public void Modifier_Reponse(Reponse_Quiz q );
    public void supprimerReponse(int id_rep); 
    public List<Reponse_Quiz> AfficherReponse_quizs();
    
    
    
}