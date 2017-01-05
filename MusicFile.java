package noahcharlotte;

/*The following code is what we had to import in order to be able to implement certain functionality.*/

import javax.swing.JLabel;

public class MusicFile {
	
	//These are the fields of the MusicFile class
	
	private JLabel image;
	private String title;
	private String artist;
	
	/**This is the constructor of the Music class which requires these three parameters upon construction.
	 * 
	 * @param image
	 * @param title
	 * @param artist
	 */

	public MusicFile (JLabel image, String title, String artist) {
		
		this.image = image;
		this.title = title;
		this.artist = artist;
		
	}
	
	/**This method will return the image poster of the music file.
	 * 
	 * @return image
	 */
	
	public JLabel getImage() {
		
		return image;
	}
	
	/**This method will return the title of the music file.
	 * 
	 * @return title
	 */
	
	public String getTitle() {
		
		return title;
		
	}
	
	/**This method will return the artist of the music file.
	 * 
	 * @return artist
	 */
	
	public String getArtist() {
		
		return artist;
		
	}
	
}
