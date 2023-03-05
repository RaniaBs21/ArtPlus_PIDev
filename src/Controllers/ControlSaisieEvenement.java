/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import artplus.entities.Evenement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rahma
 */
public class ControlSaisieEvenement {


    public boolean ControleNbrePlaces(Evenement e) {
        // regex expression to verify if the cin inserted contains only 8 numbers
        if ((String.valueOf(e.getNbre_place())).matches("[0-9]'{1,4}")) {
            return true;
        }
        return false;
    }

    public static boolean ControleDate(Evenement e) {

        if (e.getDateTime_ev() == null) {
            return false;
        }
        return true;
    }

    public static boolean ControleTitre(Evenement e) {
        String str = (e.getTitre_ev()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }

   

    public static boolean ControleCategorie(Evenement e) {
        String str = (e.getCategorie()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }
     public static boolean ControleAdresse(Evenement e) {
        String str = (e.getAdresse_ev()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }
    /*
    public static boolean ControleCategorie(Evenement e) {
        String str = (e.getCategorie()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }*/

    public static boolean ControleDescription(Evenement e) {
        String str = (e.getDescription_ev()).toLowerCase();
        if (str.length() == 0) {
            return false;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }

    public boolean verif(Evenement e) {
        if (e.getTitre_ev()== null && e.getCategorie()== null && e.getDescription_ev()== null && null== e.getAdresse_ev() && e.getDateTime_ev()== null && e.getImage_ev()== null && e.getNbre_place()== 0) {
            return true;
        } else {
            return false;
        }
    }
    private Set<String> evenementsAjoutes = new HashSet<>();

    public boolean ajouterEvenement(String nomEvenement) {
        if (evenementsAjoutes.contains(nomEvenement)) {
            System.out.println("L'événement " + nomEvenement + " a déjà été ajouté.");
            return false;
        } else {
            evenementsAjoutes.add(nomEvenement);
            System.out.println("L'événement " + nomEvenement + " a été ajouté.");
            return true;
        }
    }
/*
    public String AjouterEvenement(Evenement e) {
        String retourstr = "";
        if ((verif(getE(u)))) {

            if (GestionEmployerService.AjoutUnEmploye(u) == 0) {
                retourstr += "Une erreur est survenue ! ";
            }

        } else {
            retourstr += "Employé existant. ";
        }

        return retourstr;
    }

    

    
    public String ModificationUnEmploye(USER u) {
        String retourstr = "";
        USER user = getUnEmployeCIN(u);
        if (!verif(user)) {
            u.setId(user.getId());
            if (GestionEmployerService.ModificationUnEmploye(u) == 0) {
                retourstr += "Une erreur est survenue ! ";
            }
        }

        return retourstr;

    }

    public boolean SupprimerUnEmploye(USER u) {
        if (u.getId() != null) {
            return GestionEmployerService.SupprimerUnEmploye(u);
        }
        return false;
    }

    public List AfficheToutEmploye() {
        List liste = GestionEmployerService.AfficheToutEmploye();
        return liste;
    }

    public boolean SupprimerToutEmploye() {
        return GestionEmployerService.SupprimerToutEmploye();
    }
*/
}
