import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class Browser extends JFrame {

	private JPanel contentPane;
	private JTextField searchQuery;
	private String[] suggestions= {"hey","Hi","hello"};
	private JPanel SearchResults;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Browser frame = new Browser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * create search result
	 */
	public void addSearchResult(String headingText,String linkText,String descriptionText) {
		SearchResults.setLayout(new BoxLayout(SearchResults, BoxLayout.Y_AXIS));
		JPanel SearchResult = new JPanel();
		SearchResult.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel Heading = new JLabel(headingText);
		SearchResult.add(Heading);
		
		JLabel link = new JLabel(linkText);
		SearchResult.add(link);
		
		JLabel description = new JLabel(descriptionText);
		SearchResult.add(description);
		JLabel empty = new JLabel("");
		SearchResult.add(empty);

		SearchResults.add(SearchResult);
//		return SearchResult;
	}
	/**
	 * Create the frame.
	 */
	public Browser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel TopBar = new JPanel();
		contentPane.add(TopBar);
		
		JScrollPane pane = new JScrollPane(TopBar);
		contentPane.add(pane);
		
		JPanel AutoSuggestions = new JPanel();
		
		JPanel SearchBox = new JPanel();
		SearchBox.setLayout(new BoxLayout(SearchBox, BoxLayout.X_AXIS));
		
		searchQuery = new JTextField();
		SearchBox.add(searchQuery);
		searchQuery.setColumns(15);
		
		JButton searchButton = new JButton("Search");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AutoSuggestions.removeAll();
				for(String s : suggestions) {
					JLabel lblNewLabel = new JLabel(s);
					AutoSuggestions.add(lblNewLabel);	
					lblNewLabel.addMouseListener(new MouseAdapter()  
					{  
					    public void mouseClicked(MouseEvent e)  
					    {  
					    	searchQuery.setText(lblNewLabel.getText());
					    }  
					}); 
				}

				
				contentPane.validate();
				contentPane.repaint();
				
			}
		});
		SearchBox.add(searchButton);

		AutoSuggestions.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		AutoSuggestions.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		AutoSuggestions.add(lblNewLabel_1);
		
		SearchResults = new JPanel();
		addSearchResult("asd1","asd","asd");
		addSearchResult("asd2","asd","asd");
		addSearchResult("asd3","asd","asd");
		addSearchResult("asd4","asd","asd");
		addSearchResult("asd5","asd","asd");
		


		GroupLayout gl_TopBar = new GroupLayout(TopBar);
		gl_TopBar.setHorizontalGroup(
			gl_TopBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TopBar.createSequentialGroup()
					.addGroup(gl_TopBar.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_TopBar.createSequentialGroup()
							.addContainerGap()
							.addComponent(SearchResults, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
						.addComponent(SearchBox, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addGroup(gl_TopBar.createSequentialGroup()
							.addGap(10)
							.addComponent(AutoSuggestions, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_TopBar.setVerticalGroup(
			gl_TopBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TopBar.createSequentialGroup()
					.addComponent(SearchBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AutoSuggestions, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(SearchResults, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
					.addContainerGap())
		);
		TopBar.setLayout(gl_TopBar);
	}
}
