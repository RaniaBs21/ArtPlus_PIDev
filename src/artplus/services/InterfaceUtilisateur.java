/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Utilisateur;

/**
 *
 * @author rahma
 */
public interface InterfaceUtilisateur {
     public Utilisateur findOneById(int id);
}
