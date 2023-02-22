/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Jaime;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface InterfaceJaimeServices {
     public void ajouterJaime();
     public void ajouterJaime2(Jaime j);
     public void modifierJaime(Jaime j);
     public void supprimerJaime(int Id_J);
     public List<Jaime> afficherJaime();
}
