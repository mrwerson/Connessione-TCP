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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco Werson
 */
public class ClientMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // porta del server maggiore di 1024 
        int port=2000;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection;
        String inputS="";
        String outputS="";
        boolean a=true;
        while(a){
            try{
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(port);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connection = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());
                BufferedReader input= new BufferedReader(new InputStreamReader(connection.getInputStream()));//prende in input il messaggio inviato dal client(non avviene più la lettura da tastiera)
                PrintStream output= new PrintStream(connection.getOutputStream());
                inputS=input.readLine();
                
                switch(inputS){
                    case "chiudi":
                        outputS="alla prossima";
                        a=false;
                        connection.close();
                        break;
                    case "ciao":
                        outputS ="salve";
                        break;
                    case "ti va di andare al mercatino dell'usato?":
                        outputS ="verrei volentieri se non fossi un server :(";
                        break;   
                    default:
                        outputS ="non so che dirti....";
                }
                output.println(outputS);
                output.flush();
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
            
            //chiusura della connessione con il client
            try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        }
      }
}
