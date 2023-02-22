/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;
import artplus.entities.Reponse_ass;
import java.util.List;
/**
 *
 * @author akrem
 */
public interface InterfaceReponse_ass {
    public void ajouterreponse();
    public void ajouterreponse2 (Reponse_ass a);
    public void modifierreponse(Reponse_ass a);
    public void supprimerreponse(int id);
    public List<Reponse_ass> afficherreponse();
}
