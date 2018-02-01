/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco Werson
 */
public class ClientTCP {
    private final int porta;
    private final String indirizzo;
    
    public ClientTCP(){       
        porta=2000;
        indirizzo="localhost";
        this.avviaConnessione();        //alla creazione della 
    }
    
    public void  avviaConnessione(){
        Socket connessione=null;
        String indirizzo="localhost";
        
        try{
            connessione = new Socket (indirizzo, porta);
            System.out.println("connesione stabilita");
            this.scrivirichiesta(connessione);
        }
        catch(ConnectException e){
          System.err.println("server non disponibile");
        } 
        catch (IOException ex) {
            System.err.println("Errore I/O");
        }
    }
    
    public void ascoltaRisposta(){
    }
    
    public void scrivirichiesta(Socket connessione){
        String messaggio="";
            try {
                BufferedReader inputClient= new BufferedReader(new InputStreamReader(System.in));//Input da tastiera
                BufferedReader inputRisposta= new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                PrintStream outputClient= new PrintStream(connessione.getOutputStream());
                while(!messaggio.equals("chiudi")){
                    System.out.println("Scrivi un messaggio da inoltrare al server");
                    messaggio=inputClient.readLine();
                    outputClient.println(messaggio);
                    outputClient.flush();
                    String rispostaServer=inputRisposta.readLine();
                    System.out.println(rispostaServer);
                    }      
                } 
            catch (IOException ex) {
             Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
         }
         
          }
   
    
    void chiudiConnessione(Socket connection){
         try {
            if (connection!=null)
                {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            }
         catch(IOException e){
            System.err.println("Errore nella chiusura della connessione!");
       }
    }        
         
}
