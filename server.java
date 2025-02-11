import java.io.*;
import java.net.*;
import java.util.*;

import DES_CBC.descbc;
import destools.trial;

//Assuming that the server knows the master key used already
public class server {
    
    public static void main(String[] args){
        int port = 8081; //port number where server listens

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("server is listening on port "+port);

            //Wait for client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client is connected");

            //Create input and output Streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //Assuming that the master key is already known. 
            /* Please note!
             * Use the same master key value as provided by the user in the client side
             */
            String masterKey = "EFGHHFGE";
            trial.keyGeneration(masterKey);

            //Read message from client
            String message = in.readLine();
            //System.out.println("Encrypted message Received: "+ message);

            System.out.println("Decryption of the text from the client...");


            //Measuring the time taken to decrypt the text
            long startDecrypt = System.nanoTime();
            //Apply decryption
            String decryptedText = descbc.cbc_decrypt(message);
            long endDecrypt = System.nanoTime();
            System.out.println("The time taken to decrypt the same text (which was encrypted at the client side is): " + (endDecrypt - startDecrypt) + " ns");
            System.out.println("\nDecrypted text: " + decryptedText);

            //Send response to client
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a response to send to the client: ");
            
          //Enter the plain text to send to the client
            System.out.println("Enter 8 characters(plaintext): ");
            String plaintext = sc.nextLine();

            //perform initial modifications on the text
            String cipherText = descbc.cbc_Encrypt(plaintext);
            //System.out.println("The encrypted text is: " + cipherText);

            out.println(cipherText);

            sc.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }//try catch end

    }//main end
    
}//class end

