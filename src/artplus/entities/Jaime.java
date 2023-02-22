/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

/**
 *
 * @author DELL
 */
public class Jaime {
     private int Id_J;
     private int Nbre_J;

    public Jaime(int Id_J, int Nbre_J) {
        this.Id_J = Id_J;
        this.Nbre_J = Nbre_J;
    }

    public Jaime(int Nbre_J) {
        this.Nbre_J = Nbre_J;
    }

    public Jaime() {
    }

    public int getId_J() {
        return Id_J;
    }

    public void setId_J(int Id_J) {
        this.Id_J = Id_J;
    }

    public int getNbre_J() {
        return Nbre_J;
    }

    public void setNbre_J(int Nbre_J) {
        this.Nbre_J = Nbre_J;
    }

    @Override
    public String toString() {
        return "Jaime{" + "Id_J=" + Id_J + ", Nbre_J=" + Nbre_J + '}';
    }
     
     
}
