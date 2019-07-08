package _01_File_Recorder;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileRecorder {
	// Create a program that takes a message from the user and saves it to a file.
	
	public static void main(String[] args) {
		String message = JOptionPane.showInputDialog("What is the message you want to record into the file?");
		try {
			FileWriter fw = new FileWriter("src/_01_File_Recorder/inform.txt", true);
			fw.append("\n"+message);
			fw.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong.");
			e.printStackTrace();
		}
	}
		
	
}