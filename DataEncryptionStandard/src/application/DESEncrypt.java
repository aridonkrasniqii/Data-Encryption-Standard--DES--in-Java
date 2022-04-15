package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

public class DESEncrypt extends DESAlgorithm{

  private final String modeOperator;

  private DESEncrypt(
      File fileInput ,
      File fileOutput,
      String key,
      String modeOperator
    ) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, InvalidKeySpecException {
    super(fileInput, fileOutput, key ,modeOperator);

    this.modeOperator = modeOperator;


  }

  public static DESEncrypt getInstance(
        File fileInput ,
        File fileOutput,
        String key,
        String modeOperator
    ) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, InvalidKeySpecException {

      return new DESEncrypt(fileInput, fileOutput, key, modeOperator);

    }

  public void encrypt() throws InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IOException {


    Cipher cipher;
    byte[] vectorBytes = new byte[8];
    IvParameterSpec initializeVector = new IvParameterSpec(vectorBytes);


     if(modeOperator.equals("CBC"))
     {
        cipher = super.getCipher();
        cipher.init(
            Cipher.ENCRYPT_MODE,
            (Key) super.getSecretKey(),
            initializeVector,
            SecureRandom.getInstance("SHA1PRNG")
        );

        CipherInputStream cipherInput = new CipherInputStream(
          super.getFileInput(),
          cipher
        );

        super.write(
            cipherInput,
            super.getFileOutput()
        );
     }
     else if(modeOperator.equals("ECB"))
     {
       cipher = super.getCipher();

        cipher.init(
            Cipher.ENCRYPT_MODE,
            (Key) super.getSecretKey(),
            SecureRandom.getInstance("SHA1PRNG")
        );

        CipherInputStream cipherInput = new CipherInputStream(
          super.getFileInput(),
          cipher
        );

        super.write(
            cipherInput,
            super.getFileOutput()
        );


     }





  }

  @Override
  public void decrypt()
      throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException {
    // TODO Auto-generated method stub

  }




}
