package _02_File_Encrypt_Decrypt;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.swing.JOptionPane;

public class FileEncryptor {
	// Create a program that takes a message from the user.
	// Use the methods in the String and Character classes to save
	// an encrypted form of the message to a file
	
	public static char KEY = 'p';
	
	public static void main(String[] args) {
		new FileEncryptor().doEncrypt();
	}
	
	void doEncrypt() {
		//Get and Encrypt String
		String toEncrypt = JOptionPane.showInputDialog("Enter a message to be encrypted");
		String encrypted = encrypt(toEncrypt.getBytes(), (byte) KEY);
		
		//Clear the storage file and put in the encrypted text
		PrintWriter pw;
		try {
			pw = new PrintWriter("src/_02_File_Encrypt_Decrypt/storage.txt");
			pw.print(encrypted);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String encrypt(byte[] plaintext, byte key) {

		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}
}
