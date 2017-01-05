package noahcharlotte;

/*The following code is what we had to import in order to be able to implement certain functionality.*/

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class MediaSelectorLibary extends JFrame{
	
	private SortMediaSelector files;
	
	/**This is the constructor of the MediaSelectorLibary class*/
	
	public MediaSelectorLibary( SortMediaSelector files ){
		
		super("Media Libary");
		
		this.files = files;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		
		createWidgets();
		
	}
	
	private JPanel jpMusic, jpFilm, jpUnclassified;
	
	/**This method sets the Media Selector layout of the JFrame.
	 * 
	 * It is divided into three sections: Film, Music and Unclassified - this is where the media files generated will be placed depending on their file.
	 * A JScrollPane is also added to each of these three sections, as well as a JComboBox to Films and Music so that the media files can be sorted based on certain criteria. */
	
	public void createWidgets(){
		
		//Gives the JFrame general functionality and a sets it's layout.
		
		setMinimumSize(new Dimension(550, 700));
		setLocationRelativeTo(null);
		setLayout(new GridLayout(3,1));
		
		//Film Section
		
		JPanel jpRowOne = new JPanel(new BorderLayout());
		jpFilm = new JPanel(new GridLayout(1,0));
		
		JPanel jpFilmHeader = new JPanel(new BorderLayout());
		
		JLabel jlFilmTitle = new JLabel(" Films");
		jpFilmHeader.add(jlFilmTitle, BorderLayout.WEST);
		
		String[] sortFilmBy = new String[] {"Sort", "Title", "Release Year", "Quality"};
		JComboBox jcbFilmSort = new JComboBox(sortFilmBy);
		
		/*The enclosed code below is what will run when the user interacts and selects an option from the Film JComboBox.*/
		
		jcbFilmSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				jpFilm.removeAll();
				
				if (jcbFilmSort.getSelectedItem().equals("Title")){
				
					files.sortFilmsByName();
				
				}
				
				else if (jcbFilmSort.getSelectedItem().equals("Release Year")){
					
					files.sortFilmsByYear();
					
				}
				
				else if(jcbFilmSort.getSelectedItem().equals("Quality")){
					
					files.sortFilmsByPixels();
					
				}
				
				files.createAllFilms();
				
				revalidate();
				
			}
			
			
		});
		
		jpFilmHeader.add(jcbFilmSort, BorderLayout.EAST);
		
		JScrollPane jspFilmScrollPane = new JScrollPane(jpFilm);
		
		jpRowOne.add(jpFilmHeader, BorderLayout.NORTH);
		jpRowOne.add(jspFilmScrollPane, BorderLayout.CENTER);
		
		add(jpRowOne);
		
		//Music Section
		
		JPanel jpRowTwo = new JPanel(new BorderLayout());
		jpMusic = new JPanel(new GridLayout(1, 0));
		
		JPanel jpMusicHeader = new JPanel(new BorderLayout());
		
		JLabel jlMusicTitle = new JLabel(" Music");
		jpMusicHeader.add(jlMusicTitle, BorderLayout.WEST);
		
		String[] sortMusicBy = new String[] {"Sort", "Track Name", "Artist"};
		JComboBox jcbMusicSort = new JComboBox(sortMusicBy);
		
		/*The enclosed code below is what will run when the user interacts and selects an option from the Music JComboBox.*/
		
		jcbMusicSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				jpMusic.removeAll();
				
				if (jcbMusicSort.getSelectedItem().equals("Track Name")){
					
					files.sortMusicByName();
					
				} else if (jcbMusicSort.getSelectedItem().equals("Artist")){
					
					files.sortMusicByArtist();
					
				} 
				
				files.createAllMusicTracks();
				
				revalidate();
				
			}
			
			
		});
		
		jpMusicHeader.add(jcbMusicSort, BorderLayout.EAST);
		
		JScrollPane jspMusicScrollPane = new JScrollPane(jpMusic);
		
		jpRowTwo.add(jpMusicHeader, BorderLayout.NORTH);
		jpRowTwo.add(jspMusicScrollPane, BorderLayout.CENTER);
		
		add(jpRowTwo);
		
		//Unclassified Section
		
		JPanel jpRowThree = new JPanel(new BorderLayout());
		jpUnclassified = new JPanel(new GridLayout(1,0));
		
		JPanel jpUnclassifiedHeader = new JPanel(new BorderLayout());
		
		JLabel jlUnclassifiedTitle = new JLabel(" Unclassified");
		
		jpUnclassifiedHeader.add(jlUnclassifiedTitle, BorderLayout.WEST);
		
		JScrollPane jspUnclassifiedScrollPane = new JScrollPane(jpUnclassified);
		
		jpRowThree.add(jpUnclassifiedHeader, BorderLayout.NORTH);
		jpRowThree.add(jspUnclassifiedScrollPane, BorderLayout.CENTER);
		
		add(jpRowThree);	
		
	}
	
	/**This method creates the thumbnails of the Film media files and adds them to the Film JPanel. 
	 * 
	 *@param filmPoster [instance of a JLabel] - this is the image poster of the media file
	 *@param title - this is the title of the media file*/
	
	public void createFilmThumbnail(JLabel filmPoster, String title, int year, String definition){
		
		JPanel filmThumbnail = new JPanel(new BorderLayout());
		
		JPanel filmText = new JPanel(new BorderLayout()); //This is where the title and subtext of the film thumbnail will go
		
		filmThumbnail.add(filmPoster, BorderLayout.CENTER);
		filmThumbnail.add(filmText, BorderLayout.SOUTH);
		
		JLabel filmMainText = new JLabel();
		JLabel filmSubtext = new JLabel();
		
		filmText.add(filmMainText, BorderLayout.NORTH);
		filmText.add(filmSubtext, BorderLayout.SOUTH);
		
		filmMainText.setText(title);
		filmSubtext.setText(year + " - " + definition);
		
		jpFilm.add(filmThumbnail);
		
		revalidate();
		
	}
	
	/**This method creates the thumbnails of the Music media files and adds them to the Music JPanel. 
	 * 
	 *@param musicPoster [instance of a JLabel] - this is the image poster of the media file
	 *@param title - this is the title of the media file*/
	
	public void createMusicThumbnail(JLabel musicPoster, String title, String artist){
		
		JPanel musicThumbnail = new JPanel(new BorderLayout());
		
		JPanel musicText = new JPanel(new BorderLayout());
		
		musicThumbnail.add(musicPoster, BorderLayout.CENTER);
		musicThumbnail.add(musicText, BorderLayout.SOUTH);
		
		JLabel musicMainText = new JLabel();
		JLabel musicSubText = new JLabel();
		
		musicText.add(musicMainText, BorderLayout.NORTH);
		musicText.add(musicSubText, BorderLayout.SOUTH);
		
		musicMainText.setText(" " + title);
		musicSubText.setText(artist);
		
		jpMusic.add(musicThumbnail);
		
		revalidate();
		
	}
	
	/**This method creates the thumbnails of the Unclassified media files and adds them to the Unclassified JPanel. 
	 * 
	 *@param unclassifiedPoster [instance of a JLabel] - this is the image poster of the media file
	 *@param title - this is the title of the media file*/

	public void createUnclassifiedThumbnail(JLabel unclassifiedPoster, String title){
	
	JPanel unclassifiedThumbnail = new JPanel(new BorderLayout());
	
	JPanel unclassifiedText = new JPanel(new BorderLayout());
	
	unclassifiedThumbnail.add(unclassifiedPoster, BorderLayout.CENTER);
	unclassifiedThumbnail.add(unclassifiedText, BorderLayout.SOUTH);
	
	JLabel unclassifiedMainText = new JLabel();
	JLabel unclassifiedSubText = new JLabel();
	
	unclassifiedText.add(unclassifiedMainText, BorderLayout.NORTH);
	unclassifiedText.add(unclassifiedSubText, BorderLayout.SOUTH);
	
	unclassifiedMainText.setText(title);
	unclassifiedSubText.setText("Unclassified"); //As we keep the main text as the raw file name.
	
	jpUnclassified.add(unclassifiedThumbnail);
	
	revalidate();
	
	}	
	
}
