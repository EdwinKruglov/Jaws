package Jaws.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Jaws.Controller.FavouriteButtonListener;
import api.jaws.Ping;
import api.jaws.Shark;

public class ResultsPanel extends JPanel{
	private Shark shark;
	private Ping date;
	private Favourites faves;
	private JButton follow;
	private JButton compare;
	private SearchFrame search;
	
	public ResultsPanel(SearchFrame search, Shark shark, Ping ping, Favourites faves) {
		super(new GridLayout(2, 1));
		
		this.shark = shark;
		this.date = ping;
		this.faves = faves;
		this.search = search;
		setName(shark.getName());

		this.setBorder(BorderFactory.createEtchedBorder());
		createWidgets();
	}
	
	public void createWidgets(){
		JPanel jpDetails2 = new JPanel(new GridLayout(6, 2));
		JPanel jpDetails3 = new JPanel(new BorderLayout());
		jpDetails2.add(new JLabel("Name: "));
		//might just make labels and update from view or whatnot
		jpDetails2.add(new JTextArea(shark.getName()){{setEditable(false);}});
		jpDetails2.add(new JLabel("Gender: "));
		jpDetails2.add(new JTextArea(shark.getGender()){{setEditable(false);}});
		jpDetails2.add(new JLabel("Stage of Life: "));
		jpDetails2.add(new JTextArea(shark.getStageOfLife()){{setEditable(false);}});
		jpDetails2.add(new JLabel("Species: "));
		jpDetails2.add(new JTextArea(shark.getSpecies()){{setEditable(false);}});
		jpDetails2.add(new JLabel("Length: "));
		jpDetails2.add(new JTextArea(shark.getLength()){{setEditable(false);}});
		jpDetails2.add(new JLabel("Weight: "));
		jpDetails2.add(new JTextArea(shark.getWeight()){{setEditable(false);}});
		
		jpDetails3.add(new JLabel("Description"), BorderLayout.NORTH);
		JTextArea desc = new JTextArea(shark.getDescription());
		desc.setEditable(false);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		jpDetails3.add(desc, BorderLayout.CENTER);
		JPanel jpBottomStrip = new JPanel(new BorderLayout());
		jpBottomStrip.add(new JLabel("Last Ping: " + date.getTime()), BorderLayout.WEST);
		
		follow = new JButton("Follow");
		follow.addActionListener(new FavouriteButtonListener(faves, shark));
		compare = new JButton("Compare");
		JPanel followCompare = new JPanel(new GridLayout(2, 1));
		followCompare.add(follow);
		followCompare.add(compare);
		
		compare.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(search.getCompare() == null){
					search.setCompare(new CompareView(search));
					search.getCompare().addPanels(shark);
				}
				else{
					search.getCompare().addPanels(shark);
				}
				search.getCompare().setVisible(true);
			}
			
		});
		
		jpBottomStrip.add(followCompare, BorderLayout.EAST);
		
		jpDetails3.add(jpBottomStrip, BorderLayout.SOUTH);
		
		add(jpDetails2);
		add(jpDetails3);
	}
	
	public JButton getFollowed(){
		return follow;
	}
}
