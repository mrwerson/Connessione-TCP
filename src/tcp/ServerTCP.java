/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedReader;
import static java.lang.System.in;
import java.net.Socket;

/**
 *
 * @author Marco Werson
 */
public class ServerTCP {
    private final String porta;
    
    public ServerTCP(){
        porta="2000";
        this.avviaServer();
    }
    public void avviaServer(){
    
    }
    
    public void leggiRichiesta(Socket connesione){

    }  
    
    public void mandaRisposta(Socket connessione){
        
    }
}
