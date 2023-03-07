/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;

/**
 *
 * @author rahma
 */
public class Participation {

    private int id_part;
    private Utilisateur_1 utilisateur;
    private Evenement evenement;
    private Timestamp date_participation;

    public Participation() {
    }

    public Participation(int id_part, Utilisateur_1 utilisateur, Evenement evenement, Timestamp date_participation) {
        this.id_part = id_part;
        this.utilisateur = utilisateur;
        this.evenement = evenement;
        this.date_participation = date_participation;
    }

    public Participation(Utilisateur_1 utilisateur, Evenement evenement, Timestamp date_participation) {
        this.utilisateur = utilisateur;
        this.evenement = evenement;
        this.date_participation = date_participation;
    }

    public int getId_part() {
        return id_part;
    }

    public void setId_part(int id_part) {
        this.id_part = id_part;
    }

    public Utilisateur_1 getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur_1 utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Timestamp getDate_participation() {
        return date_participation;
    }

    public void setDate_participation(Timestamp date_participation) {
        this.date_participation = date_participation;
    }

    @Override
    public String toString() {
        return "Participation{" + "id_part=" + id_part + ", utilisateur=" + utilisateur + ", evenement=" + evenement + ", date_participation=" + date_participation + '}';
    }

}
