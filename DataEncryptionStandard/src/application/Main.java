package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.NoSuchPaddingException;

public class Main {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			FileNotFoundException, InvalidKeySpecException {

		Scanner scanner = new Scanner(System.in);

		File plaintext = new File("/home/teknikashi/plain.txt");
		File encrypted = new File("/home/teknikashi/encrypted.txt");
		File decrypted = new File("/home/teknikashi/decrypted.txt");

		String menu = "Application: \n " + "Select [1] to encrypt decrypt file \n"
				+ "Select [2] to encrypt decrypt text \n" + "Select [3] to stop program\n";
		System.out.println(menu);

		while (true) {

      int mode = scanner.nextInt();
      if (mode == 3) {
        break;
			}

			switch (mode) {
			case 1:
				System.out.println("Enter mode operator: ");
				String modeOperator = scanner.next().trim();
				System.out.println("Enter key: ");
				String key = scanner.next().trim();

				DESEncrypt encrypt = DESEncrypt.getInstance(plaintext, encrypted, key, modeOperator);
				try {
					encrypt.encrypt();
					System.out.println("Encryption completed ");
				} catch (Exception e) {
					System.out.println("Error in main method:  " + e.getMessage());

				}

				DESDecrypt decrypt = DESDecrypt.getInstance(encrypted, decrypted, key, modeOperator);

				try {
					decrypt.decrypt();
					System.out.println("Decryption completed ");
				} catch (Exception e) {
					System.out.println("Error in main method:  " + e.getMessage());
				}

				break;
			case 2:
          	// System.out.println("Enter mode operator: ");
				    // String modeOperatorText = scanner.next().trim();
				    // System.out.println("Enter key: ");
				    // String keyText = scanner.next().trim();
            System.out.println("Write text you want to encrypt and decrypt: ");
            String txt = scanner.next();
            DESAlgorithm desInstance = new DESAlgorithm();

            try {
                System.out.println("Output : " + Arrays.toString(desInstance.encryptText(txt)));
                System.out.println("Encryption completed ");
            }catch (Exception e) {
                System.out.println("encrypted text error :  " + e.getMessage());
            }


				break;
			default:
				System.out.println("Wrong command ");

			}
      System.out.println(menu);
		}

		// TODO: me provu me ndryshu celsin
		// TODO: me kqyre a eshte vektori inicializus i njejt per decrypt edhe encrypt
		// TODO: pse nuk eshte ^
    scanner.close();
	}

}
