import java.util.*;
import java.io.*;
import java.net.*;
import destools.trial;
import DES_CBC.descbc;
public class client {

    public static void main(String[] args){
       String serverAddress = "localhost";
       int port = 8081;

       try(Socket socket = new Socket(serverAddress, port)){

        //create input and output streams
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        //Send message to server
        Scanner sc = new Scanner(System.in);
        System.out.println("Connected to the server. Please type as required...");
        //Enter the master key
            System.out.println("Enter a master key (8 characters): ");
            String masterKey = sc.nextLine();

            // Ensure the master key is exactly 8 characters
            while (masterKey.length() != 8) {
            System.out.println("Invalid input. Please enter exactly 8 characters: ");
            masterKey = sc.nextLine();
            }

            trial.keyGeneration(masterKey);
        
          //Enter the plain text to send to the server
            System.out.println("Enter the plain text to be sent to the server: ");
            String plaintext = sc.nextLine();


           //Inorder to measure the time taken to encrypt this text
           long startEncrypt = System.nanoTime();
           //ENCRYPTION PERFORMED
            String cipherText = descbc.cbc_Encrypt(plaintext);
           long endEncrypt = System.nanoTime();
           System.out.println("Time taken to encrypt the text: " + plaintext + " is " + (endEncrypt - startEncrypt) + " nanoseconds");



        //System.out.println("The encrypted text is: " + cipherText);
        out.println(cipherText);

        //Read response from server
        String response = in.readLine();
        //System.out.println("Crypted message From the server: "+ response);

        System.out.println("Decryption of the text(sent from server)...");

            //Apply decryption
            String decryptedText = descbc.cbc_decrypt(response);

            System.out.println("\nDecrypted text: " + decryptedText);

        sc.close();

       } catch(IOException e){
        e.printStackTrace();
       }//try catch end

    }//main end
}//class end

