/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telnet_chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Main {
    
    public static int PORT = 8080;

    public static void main (String args[]) throws IOException {
        
    	ServerSocket server = null;
        Socket client = null;
        Scanner in = null;
        View view;
        PrintWriter out = null;
        
        try {
	        client = new Socket("localhost", PORT);
        } catch (IOException e) {
            System.err.println("Starting server");
            server = new ServerSocket(PORT);
            client = server.accept();
        }
        
        out = new PrintWriter(client.getOutputStream(), true);
        in = new Scanner(client.getInputStream());
        
        view = new View(out);
        new Thread(view).start();

        String mes;
    	mes = in.nextLine();
    	view.setConnect();
        while (true){
        	mes = in.nextLine();
        	view.setText(mes);
        }
    }
    
}
