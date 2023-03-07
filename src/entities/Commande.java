package entities;

import java.sql.Date;
import java.time.LocalDate;

public class Commande {

    private int Id_Cmd;
    private int Pt_Red_Cmd;
    private Date Date_Cmd;
    private Date Date_Liv;
    private int Id_Cart;

    public Commande(int Pt_Red_Cmd, Date Date_Cmd, Date Date_Liv, int Id_Cart) {
        this.Pt_Red_Cmd = Pt_Red_Cmd;
        this.Date_Cmd = Date_Cmd;
        this.Date_Liv = Date_Liv;
        this.Id_Cart = Id_Cart;
    }

    public Commande(int Id_Cmd, int Pt_Red_Cmd, Date Date_Cmd, Date Date_Liv, int Id_Cart) {
        this.Id_Cmd = Id_Cmd;
        this.Pt_Red_Cmd = Pt_Red_Cmd;
        this.Date_Cmd = Date_Cmd;
        this.Date_Liv = Date_Liv;
        this.Id_Cart = Id_Cart;
    }

    public Commande(int Pt_Red_Cmd, LocalDate Date_Cmd, LocalDate Date_Liv, int Id_Cart) {
        this.Pt_Red_Cmd = Pt_Red_Cmd;
        this.Date_Cmd = Date.valueOf(Date_Cmd);
        this.Date_Liv = Date.valueOf(Date_Liv);
        this.Id_Cart = Id_Cart;
    }

    public Commande(int Pt_Red_Cmd, LocalDate Date_Cmd, Date Date_Liv, int Id_Cart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_Cmd() {
        return Id_Cmd;
    }

    public int getPt_Red_Cmd() {
        return Pt_Red_Cmd;
    }

    public Date getDate_Cmd() {
        return Date_Cmd;
    }

    public Date getDate_Liv() {
        return Date_Liv;
    }

    public int getId_Cart() {
        return Id_Cart;
    }

    public void setId_Cmd(int Id_Cmd) {
        this.Id_Cmd = Id_Cmd;
    }

    public void setPt_Red_Cmd(int Pt_Red_Cmd) {
        this.Pt_Red_Cmd = Pt_Red_Cmd;
    }

    public void setDate_Cmd(Date Date_Cmd) {
        this.Date_Cmd = Date_Cmd;
    }

    public void setDate_Cmd(LocalDate Date_Cmd) {
        this.Date_Cmd = Date.valueOf(Date_Cmd);
    }

    public void setDate_Liv(Date Date_Liv) {
        this.Date_Liv = Date_Liv;
    }

    public void setDate_Liv(LocalDate Date_Liv) {
        this.Date_Liv = Date.valueOf(Date_Liv);
    }

    public void setId_Cart(int Id_Cart) {
        this.Id_Cart = Id_Cart;
    }

}