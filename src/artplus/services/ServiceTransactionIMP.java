/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Transaction;
import artplus.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ServiceTransactionIMP { 
     Connection cnxx;

    public ServiceTransactionIMP() {
        this.cnxx =MyDB.getInstance().getConnection();
    } 
    
    //---Create reservations--
    public int add_transaction(Transaction tr){ 
        System.out.println("transaction nom_carte : "+tr.getNom_carte());
        System.out.println("transaction num_carte : "+tr.getNumero_carte());
        System.out.println("transaction exp m : "+tr.getExp_mois());
        System.out.println("transaction exp an : "+tr.getExp_annee());
        String Request = "INSERT INTO transaction (nom_carte,numero_carte,exp_mois,exp_annee,cvc,paymentIntent_id) VALUES(?,?,?,?,?,?)" ; 
        String columnNames[] = new String[] { "id" };
        PreparedStatement  pst; 
        int transaction_generated_id=0;
        try { 
                
                pst = cnxx.prepareStatement(Request,columnNames);
                pst.setString(1, tr.getNom_carte());
                pst.setString(2,tr.getNumero_carte()); 
                pst.setInt(3,tr.getExp_mois()); 
                pst.setInt(4,tr.getExp_annee()); 
                pst.setInt(5,tr.getCvc()); 
                pst.setString(6, tr.getPaymentIntent_id()); 
                pst.executeUpdate();
                
                java.sql.ResultSet generatedKeys = pst.getGeneratedKeys();
                if ( generatedKeys.next() ) {
                    transaction_generated_id = generatedKeys.getInt(1);
                    System.out.println("trnasaction id : "+transaction_generated_id);
                }
               
                System.out.println("transaction effectué avec  succés");
                            
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }
        
        
        return transaction_generated_id;
    } 
    
    //--show transactions
    public Transaction get_transaction_by_id(int  transaction_id) { 
        
        Transaction transaction=null;
        String Request = "SELECT * FROM transaction where id="+transaction_id;
        PreparedStatement  pst;
        try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs;
            rs = pst.executeQuery(Request);
             while (rs.next()) {
                 transaction= new Transaction(transaction_id,rs.getTimestamp(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
            }
    
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return transaction;
    }
   
    
    public List<Transaction> get_all_transactions() {
         List<Transaction> transaction_list = new ArrayList(); 
         String Request = "SELECT * FROM transaction";
         PreparedStatement  pst;
         try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
                 Transaction transaction= new Transaction(rs.getInt(0),rs.getTimestamp(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
                transaction_list.add(transaction);
            }
            } catch (SQLException e) {
            System.err.println(e.getMessage());
            //   return null;
        }
        return transaction_list;
    } 
        
    
    
}
