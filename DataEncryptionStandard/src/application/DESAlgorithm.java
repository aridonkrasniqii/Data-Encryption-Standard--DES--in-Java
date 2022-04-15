package application;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public abstract class DESAlgorithm {
  /*
  A FileInputStream obtains input bytes from a file in a file system. What files are available depends on the host environment.
  FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.
 */

  FileInputStream fileInput;
  FileOutputStream fileOutput;
  DESKeySpec desKeySpec ;
  SecretKey secretKey;
  SecretKeyFactory secretKeyFactor;

  // Padding
  Cipher cipher;
  public DESAlgorithm(
      File fileInput ,
      File fileOutput,
      String key,
      String modeOperator

  ) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, InvalidKeySpecException  {
    this.fileInput = new FileInputStream(fileInput);
    this.fileOutput = new FileOutputStream(fileOutput);
    this.desKeySpec = new DESKeySpec(key.getBytes());
	  this.secretKeyFactor = SecretKeyFactory.getInstance("DES");
		this.secretKey = secretKeyFactor.generateSecret(desKeySpec);
    this.cipher = Cipher.getInstance("/DES/" + modeOperator +"/PKCS5Padding");

  }


  public FileInputStream getFileInput() {
    return this.fileInput;
  }
  public FileOutputStream getFileOutput(  ) {
    return this.fileOutput;
  }

  public DESKeySpec getDesKeySpec( ) {
    return this.desKeySpec;
  }
  public Cipher getCipher() {
    return this.cipher;
  }

//  public SecretKeyFactory getSecretKey() {
//    return this.secretKey;
// 	 }
  public void test( ) throws InvalidKeySpecException, InvalidKeyException, NoSuchAlgorithmException {



  }
  public void write(InputStream input, OutputStream output )
  throws IOException {

    // 64 bytes
    byte[] buffer = new byte[64];

    int numberOfBytesReaded;

    while( (numberOfBytesReaded = input.read(buffer)) != - 1 ) {

        output.write(buffer, 0 , numberOfBytesReaded);

    }


    output.close();
    input.close();

  }

  public abstract void encrypt() throws InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IOException;
  public abstract void decrypt() throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException;


  public Key getSecretKey() {
    return this.secretKey;
  }

}
