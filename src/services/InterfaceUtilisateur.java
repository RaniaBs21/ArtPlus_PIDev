/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur_1;

/**
 *
 * @author rahma
 */
public interface InterfaceUtilisateur {
     public Utilisateur_1 findOneById(int id);
}
