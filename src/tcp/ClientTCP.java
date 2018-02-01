/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.net.Socket;

/**
 *
 * @author Marco Werson
 */
public class ClientTCP {
    private final int porta;
    private final String indirizzo;
    
    public ClientTCP(){
        porta=200;
        indirizzo="localhost";
        this.avviaConnessione();
    }
    
    public void  avviaConnessione(){

    }
    
    public void ascoltaRisposta(){
    }
    
    public void scrivirichiesta(){
        
    }
}
