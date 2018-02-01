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
import static java.lang.System.in;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Marco Werson
 */
public class ServerTCP {
    private final int porta;
    
    public ServerTCP(){
        porta=2000;
        this.avviaServer();
    }
    public void avviaServer(){
        ServerSocket SocketS =null;
        Socket connessione;
        
        try{
            SocketS = new ServerSocket(porta);
            System.out.println("in attesa di connesione");
            connessione=SocketS.accept();
            System.out.println("Connessione stabilita!");
            System.out.println("Socket server: " + connessione.getLocalSocketAddress());
            System.out.println("Socket client: " + connessione.getRemoteSocketAddress());
            this.mandaRisposta(SocketS,connessione);     
        }
        catch(IOException e){
            System.err.println("Errore di I/O!");
        }
    }
    
    public void mandaRisposta(ServerSocket SocketS,Socket connessione){
     String Input="";
     String Output = "";
            try{
                BufferedReader inputServer= new BufferedReader(new InputStreamReader(connessione.getInputStream()));//prende in input il messaggio inviato dal client(non avviene più la lettura da tastiera)
                PrintStream outputServer= new PrintStream(connessione.getOutputStream());
                while(!"ciao ciao".equals(Output)){//ciclo finchè il messaggio che restituisce il server è quello di chiusura
                    Input=inputServer.readLine();
                    switch(Input){//controllo del messaggio di input con la quale si definisce la risposta da definire
                            case "chiudi":
                                Output="ciao ciao";
                                break;
                            case "ciao":
                                Output ="salve";
                                break;
                                default:
                                Output ="snon so che dirti";
                        }
                        outputServer.println(Output);
                        outputServer.flush();//svuoto lo stream e invio il messaggio
                        System.out.println(Output);
                }
                this.chiudiConnessione(SocketS);//richiamo la chiusura della connessione una volta finito lo scambio di messaggi
            }catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
        }
    void chiudiConnessione(ServerSocket SocketS){
            try {
                if (SocketS!=null) SocketS.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        
        }

}
