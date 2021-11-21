import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.*; 
public class AutoCompletor {
	private JPanel autoSuggestionPopUpWindow;

	AutoCompletor(Window mainWindow,JTextField search){
		mainWindow.repaint();
		 this.autoSuggestionPopUpWindow = new JPanel();
		JButton b1 = new JButton("Searasdch");	
		this.autoSuggestionPopUpWindow.add(b1);
//		this.autoSuggestionPopUpWindow.setBounds(search.getX(), search.getY(), 10, 100);
		this.autoSuggestionPopUpWindow.setLocation(0,0);
//        this.autoSuggestionPopUpWindow.setLocation(0,0);
		
		 this.autoSuggestionPopUpWindow.setVisible(true);
		 mainWindow.add(autoSuggestionPopUpWindow,BorderLayout.NORTH);

//		 mainWindow.repaint();
//		 
	}
}
