package _02_File_Encrypt_Decrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

import javax.swing.JOptionPane;

public class FileDecryptor {
	// Create a program that opens the file created by FileEncryptor and display
	// the decrypted message to the user in a JOptionPane.
	
	public static void main(String[] args) {
		new FileDecryptor().doDecrypt();
	}
	
	void doDecrypt() {
		//Get the encrypted string from the file
		try {
			BufferedReader fr = new BufferedReader(new FileReader("src/_02_File_Encrypt_Decrypt/storage.txt"));
			String encryptedText = fr.readLine();
			String decryptedText =decrypt(encryptedText, (byte) FileEncryptor.KEY);
			JOptionPane.showMessageDialog(null, decryptedText);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong");
			//e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong");
			//e.printStackTrace();
		}
	}
	public static String decrypt(String cyphertext, byte key) {

		byte[] b = Base64.getDecoder().decode(cyphertext);

		for (int i = 0; i < b.length; i++) {

			b[i] = (byte) (b[i] ^ key);

		}

		return new String(b);

	}
}
