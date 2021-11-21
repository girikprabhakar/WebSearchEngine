import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  
public class BrowserOld {
	JFrame frame;
	BrowserOld(){
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addTopControls();
		frame.setSize(500,500);//400 width and 500 height  
//		frame.setLayout(null);//using no layout managers  
		frame.setVisible(true);//making the frame visible
		
	}
	
	void addTopControls(){
		JPanel panel=new JPanel();  
		JButton b1 = new JButton("Search");
//		frame.add(b1,BorderLayout.NORTH);
        JTextField searchQuery=new JTextField(25);  
//        frame.add(searchQuery,BorderLayout.NORTH);
        panel.add(searchQuery);
        panel.add(b1);
        panel.setVisible(true);
        frame.add(panel,BorderLayout.NORTH);
        frame.repaint();
        b1.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				new AutoCompletor(frame,searchQuery);				
			}  
        	    });  
        
        
	}
	void searchResults(){
		JPanel panel=new JPanel();  
		JButton b1 = new JButton("Search");
//		frame.add(b1,BorderLayout.NORTH);
        JTextField searchQuery=new JTextField(25);  
//        frame.add(searchQuery,BorderLayout.NORTH);
        panel.add(searchQuery);
        panel.add(b1);
        frame.add(panel,BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
//		new Browser();
		}  
}
