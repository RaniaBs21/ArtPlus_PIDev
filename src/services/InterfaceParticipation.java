/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author rahma
 */
public interface InterfaceParticipation <Participation> {
    public void ajouterParticipation(Participation p);
    public void modifierParticipation(Participation p);
    public void supprimerParticipation(int id); 
    public List<Participation> afficherParticipation();
}
