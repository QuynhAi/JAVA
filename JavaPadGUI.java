import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
public class JavaPadGUI {
	
	private static JTextArea textArea;
	private static JFrame frame;
	private static JPanel buttons;
	private static final String FILENAME = "hardcode.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		frame = new JFrame();
		frame.setTitle("Macrosoft Java Pad");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocation(new Point(0,0));
		frame.setSize(new Dimension(300	,300));
		frame.setLayout(new BorderLayout());
		
		buttons = new JPanel();
		allButtons();
		frame.add(buttons, BorderLayout.NORTH);
		
		textArea = new JTextArea(15, 25);
		textArea.setLineWrap(true);
		frame.add(textArea);
		
		JScrollPane scroll = new JScrollPane(textArea);
		frame.add(scroll, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		southPanel.add(new JLabel("Macrosoft: Resistance is futile."));
		frame.add(southPanel, BorderLayout.SOUTH);
		
		frame.setResizable(true);
		frame.setVisible(true);
		buttons.setVisible(true);	
	}
	
	// This methods create 4 buttons including new, save, load and quit
	public static void allButtons() {
		JButton newButton = new JButton();
		newButton.setText("New");
		ActionListener newListener = new newbuttonListener();
		newButton.addActionListener(newListener);
		buttons.add(newButton);
		
		JButton saveButton = new JButton();
		saveButton.setText("Save");
		ActionListener saveListener = new savebuttonListener();
		saveButton.addActionListener(saveListener);
		buttons.add(saveButton);
		
		JButton loadButton = new JButton();
		loadButton.setText("Load");
		ActionListener loadListener = new loadbuttonListener();
		loadButton.addActionListener(loadListener);
		buttons.add(loadButton);
		
		JButton quitButton = new JButton();
		quitButton.setText("Quit");
		ActionListener quitListener = new quitbuttonListener();
		quitButton.addActionListener(quitListener);
		buttons.add(quitButton);
	}
	
	// When the "new" button is clicked, clears text in the main text area
	public static class newbuttonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			textArea.setText("");
		}
	}
	
	// When the load button is clicked,
	public static class loadbuttonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				File myFile = new File(FILENAME);
				Scanner scanner = new Scanner(myFile);
				String result = "";
				while(scanner.hasNextLine()) {
					result += scanner.nextLine();
				}
				textArea.setText(result);
			} catch (FileNotFoundException e1) {
				String MESSAGE = "Could not access file " + FILENAME;
				JOptionPane.showMessageDialog(null, MESSAGE, "I/O Error", 0);
			}
		}
	}
	
	// When the user clicked on the button Save
	// everything from the text area will be saved into a text file
	// If the text file is not found, display an error message
	public static class savebuttonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			saveMethod();	
		}
	}
	
	// When the "quit" button is clicked, display a panel with yes/no question to 
	// confirm that the user want to quit
	// if yes is clicked, the save everything from the text area into a text file
	// else, exit
	public static class quitbuttonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int choice = JOptionPane.showConfirmDialog(null, "Quitting.Save?");
			if(choice == JOptionPane.YES_OPTION) {
				saveMethod();
			}else if(choice == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}
	}
	
	
	// If could not find the file to save, then display an error message
	// Else, save everything from the text area into a text file
	private static void saveMethod() {
		try {
			File myFile = new File(FILENAME);
			PrintWriter myWrite = new PrintWriter(myFile);
			myWrite.write(textArea.getText());
			myWrite.close();
		} catch (FileNotFoundException e1) {
			String MESSAGE = "Could not access file " + FILENAME;
			JOptionPane.showMessageDialog(null, MESSAGE, "I/O Error", 0);
		}
		
	}
}