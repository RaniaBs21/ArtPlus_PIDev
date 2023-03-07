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
public class Evenement {

    private int id_ev;
    private String titre_ev;
    private String categorie;
    private String description_ev;
    private String image_ev;
    private String adresse_ev;
    private Timestamp dateTime_ev;
    private int nbre_place;
    private Guide guide;

    public Evenement() {
    }
    
    

    public Evenement(int id_ev, String titre_ev, String categorie, String description_ev, String image_ev, String adresse_ev, Timestamp dateTime_ev, int nbre_place, Guide guide) {
        this.id_ev = id_ev;
        this.titre_ev = titre_ev;
        this.categorie = categorie;
        this.description_ev = description_ev;
        this.image_ev = image_ev;
        this.adresse_ev = adresse_ev;
        this.dateTime_ev = dateTime_ev;
        this.nbre_place = nbre_place;
        this.guide = guide;
    }

    public Evenement(int id_ev, String titre_ev, String categorie, String description_ev, String image_ev, String adresse_ev, Timestamp dateTime_ev, int nbre_place) {
        this.id_ev = id_ev;
        this.titre_ev = titre_ev;
        this.categorie = categorie;
        this.description_ev = description_ev;
        this.image_ev = image_ev;
        this.adresse_ev = adresse_ev;
        this.dateTime_ev = dateTime_ev;
        this.nbre_place = nbre_place;
    }
    public Evenement( String titre_ev, String categorie, String description_ev, String adresse_ev, Timestamp dateTime_ev, int nbre_place) {
        this.titre_ev = titre_ev;
        this.categorie = categorie;
        this.description_ev = description_ev; 
        this.adresse_ev = adresse_ev;
        this.dateTime_ev = dateTime_ev;
        this.nbre_place = nbre_place;
    }

    public Evenement(String titre_ev, String categorie, String description_ev, String image_ev, String adresse_ev, Timestamp dateTime_ev, int nbre_place, Guide guide) {
        this.titre_ev = titre_ev;
        this.categorie = categorie;
        this.description_ev = description_ev;
        this.image_ev = image_ev;
        this.adresse_ev = adresse_ev;
        this.dateTime_ev = dateTime_ev;
        this.nbre_place = nbre_place;
        this.guide = guide;
    }
    public Evenement( String titre_ev, String categorie, String description_ev, String adresse_ev, int nbre_place) {
        this.titre_ev = titre_ev;
        this.categorie = categorie;
        this.description_ev = description_ev; 
        this.adresse_ev = adresse_ev;
        
        this.nbre_place = nbre_place;
    }

    public Evenement(String titre_ev, String categorie, String description_ev, String image_ev, String adresse_ev, Timestamp dateTime_ev, int nbre_place) {
        this.titre_ev = titre_ev;
        this.categorie = categorie;
        this.description_ev = description_ev;
        this.image_ev = image_ev;
        this.adresse_ev = adresse_ev;
        this.dateTime_ev = dateTime_ev;
        this.nbre_place = nbre_place;
    }

    public int getId_ev() {
        return id_ev;
    }

    public void setId_ev(int id_ev) {
        this.id_ev = id_ev;
    }

    public String getTitre_ev() {
        return titre_ev;
    }

    public void setTitre_ev(String titre_ev) {
        this.titre_ev = titre_ev;
    }

    public String getDescription_ev() {
        return description_ev;
    }

    public void setDescription_ev(String description_ev) {
        this.description_ev = description_ev;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage_ev() {
        return image_ev;
    }

    public void setImage_ev(String image_ev) {
        this.image_ev = image_ev;
    }

    public String getAdresse_ev() {
        return adresse_ev;
    }

    public void setAdresse_ev(String adresse_ev) {
        this.adresse_ev = adresse_ev;
    }

    public Timestamp getDateTime_ev() {
        return dateTime_ev;
    }

    public void setDateTime_ev(Timestamp dateTime_ev) {
        this.dateTime_ev = dateTime_ev;
    }

    public int getNbre_place() {
        return nbre_place;
    }

    public void setNbre_place(int nbre_place) {
        this.nbre_place = nbre_place;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_ev=" + id_ev + ", titre_ev=" + titre_ev + ", categorie=" + categorie + ", description_ev=" + description_ev + ", image_ev=" + image_ev + ", adresse_ev=" + adresse_ev + ", dateTime_ev=" + dateTime_ev + ", nbre_place=" + nbre_place + ", guide=" + guide + '}';
    }

   

}
