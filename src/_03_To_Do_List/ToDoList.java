package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 * 
	 * When add task is clicked:
	 * 		ask the user for a  task and save it to an array list
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list.
	 */
	
	ArrayList<String> taskList = new ArrayList<String>();
	JFrame window;
	JPanel panel;
	JButton addTasks;
	JButton viewTasks;
	JButton removeTask;
	JButton loadList;
	JButton saveList;
	String nameOfFile;
	
	
	public static void main(String[] args) {
		ToDoList code = new ToDoList();
		code.createWindow();
	}
	
	void loadList() {
		try {
			JFileChooser jfc = new JFileChooser();
			int returnVal = jfc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();
				nameOfFile = fileName;
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				boolean done = false;
				while(done == false) {
					String nextTask = br.readLine();
					if(nextTask != null) {
						taskList.add(nextTask);
					}
					else {
						done = true;
						JOptionPane.showMessageDialog(null, "The List has Loaded");
					}
				}
				br.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void createWindow() {
		window = new JFrame();
		panel = new JPanel();
		window.add(panel);
		addTasks = new JButton();
		addTasks.setText("Add Task");
		addTasks.addActionListener(this);
		panel.add(addTasks);
		viewTasks = new JButton();
		viewTasks.setText("View Tasks");
		viewTasks.addActionListener(this);
		panel.add(viewTasks);
		removeTask = new JButton();
		removeTask.setText("Remove Task");
		removeTask.addActionListener(this);
		panel.add(removeTask);
		loadList = new JButton();
		loadList.setText("Load List");
		loadList.addActionListener(this);
		panel.add(loadList);
		saveList = new JButton();
		saveList.setText("Save List");
		saveList.addActionListener(this);
		panel.add(saveList);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton buttonClicked = (JButton) arg0.getSource();
		if(buttonClicked == loadList) {
			loadList();
		}
		else if(buttonClicked == viewTasks) {
			viewTasks();
		}
		else if(buttonClicked == removeTask) {
			removeTask();
		}
		else if(buttonClicked == addTasks) {
			addTask();
		}
		else if(buttonClicked == saveList) {
			saveList();
		}
	}
	
	void viewTasks() {
		String taskOut = "-----TASKS-----"+"\n";
		for(String s : taskList) {
			taskOut = taskOut+s+"\n";
		}
		JOptionPane.showMessageDialog(null, taskOut);
	}
	void removeTask() {
		String toRemove = JOptionPane.showInputDialog("What is the name of the task you want to remove?");
		if(taskList.contains(toRemove)) {
			taskList.remove(toRemove);
			JOptionPane.showMessageDialog(null, "The task: "+toRemove+"\n"+"has been removed.");
		}
	}
	void addTask() {
		String newTask = JOptionPane.showInputDialog("What is the name of the new task?");
		taskList.add(newTask);
		JOptionPane.showMessageDialog(null, "The task: "+newTask+"\n"+"has been added.");
	}
	void saveList() {
		String toSave = "";
		for(String s : taskList) {
			toSave = toSave+s+"\n";
		}
		
		try {
			PrintWriter pw = new PrintWriter(nameOfFile);
			pw.print(toSave);
			pw.close();
			JOptionPane.showMessageDialog(null, "The List has been saved to the file.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
