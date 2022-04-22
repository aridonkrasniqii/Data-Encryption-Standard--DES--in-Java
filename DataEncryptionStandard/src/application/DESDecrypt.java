package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

public class DESDecrypt extends DESAlgorithm {

	private String modeOperator;

	private DESDecrypt(File fileInput, File fileOutput, String key, String modeOperator) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, InvalidKeySpecException {
		super(fileInput, fileOutput, key, modeOperator);

		this.modeOperator = modeOperator;

	}
  public DESDecrypt(String key , String modeOperator) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, InvalidKeySpecException
  {
    super(key, modeOperator);
  }


	public static DESDecrypt getInstance(File fileInput, File fileOutput, String key, String modeOperator)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException,
			InvalidKeySpecException {

		return new DESDecrypt(fileInput, fileOutput, key, modeOperator);

	}

  public DESDecrypt() {
    super();
  }

	public void decrypt()
			throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException {

		Cipher cipher;

		System.out.println("IV value : " + initializeVector.getIV());

		if (modeOperator.equals("CBC")) {
			cipher = super.getCipher();

			cipher.init(Cipher.DECRYPT_MODE, (Key) super.getSecretKey(), initializeVector,
					SecureRandom.getInstance("SHA1PRNG"));

			CipherOutputStream cipherOutput = new CipherOutputStream(super.getFileOutput(), cipher);

			super.write(super.getFileInput(), cipherOutput);

		} else if (modeOperator.equals("ECB")) {

			cipher = super.getCipher();

			cipher.init(Cipher.DECRYPT_MODE, (Key) super.getSecretKey(), SecureRandom.getInstance("SHA1PRNG"));

			CipherOutputStream cipherOutput = new CipherOutputStream(super.getFileOutput(), cipher);

			super.write(super.getFileInput(), cipherOutput);

		}

	}




}
