package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

import application.DESAlgorithm;
import application.DESEncrypt;

public class Main {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, InvalidKeySpecException {

      File plaintext = new File("/home/teknikashi/plain.txt");
      File encrypted = new File("/home/teknikashi/encrypted.txt");
      File decrypted = new File("/home/teknikashi/decrypted.txt");

      String modeOperator = "ECB";
      String key = "12345678";

     DESAlgorithm encrypt = DESEncrypt.getInstance(
         plaintext,
         encrypted,
         key,
         modeOperator
       );
     try {
         encrypt.encrypt();
         System.out.println("Encryption completed ");
     }catch(Exception e ) {
       System.out.println("Error in main method:  "  + e.getMessage());

     }
      		
      
      DESAlgorithm decrypt = DESDecrypt.getInstance(
    		  encrypted,
              decrypted,
              key,
              modeOperator
            );
      	
      try {
          decrypt.decrypt();
          System.out.println("Decryption completed ");
      }catch(Exception e ) {
        System.out.println("Error in main method:  "  + e.getMessage());

      }


	}
}





