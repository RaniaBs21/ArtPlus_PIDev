/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Smsapi {

    public static final String ACCOUNT_SID = "AC599d195a0005952e3ad6b8f96f3ee12d";
    public static final String AUTH_TOKEN = "50277f53d23753d67ee9dfb8eedcab3b";
    public static void main (String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

      Message message = Message.creator(new PhoneNumber("+21621845071"),new PhoneNumber("+12763832951"), 
      "This is the ship that made the Kessel Run in fourteen parsecs?").create();

    System.out.println(message.getSid());
  }
}


