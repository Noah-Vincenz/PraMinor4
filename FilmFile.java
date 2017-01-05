package noahcharlotte;

/*The following code is what we had to import in order to be able to implement certain functionality.*/

import javax.swing.JLabel;

public class FilmFile {
	
	//These are the fields of the FilmFile class
	
	private JLabel image;
	private String title;
	private int year;
	private String definition;
	private String pixels;
	
	/**This is a constructor of the Film class which requires these five parameters upon construction.
	 * 
	 * @param image
	 * @param title
	 * @param year
	 * @param definition
	 * @param pixels
	 */

	public FilmFile (JLabel image, String title, int year, String definition, String pixels) {
		
		this.image = image;
		this.title = title;
		this.year = year;
		this.definition = definition;
		this.pixels = pixels;

	}
	
	/**This method will return the image poster of the film file.
	 * 
	 * @return image
	 */
	
	public JLabel getImage() {
		
		return image;
		
	}
	
	/**This method will return the title of the film file.
	 * 
	 * @return title
	 */
	
	public String getTitle() {
		
		return title;
		
	}
	
	/**This method will return the release year of the film file.
	 * 
	 * @return year
	 */
	
	public int getYear() {
		
		return year;
		
	}
	
	/**This method will return the definition of the film file.
	 * 
	 * @return definition
	 */
	
	public String getDefinition() {
		
		return definition;
		
	}
	
	/**This method will return the pixels of the film file.
	 * 
	 * @return pixels
	 */
	
	public String getPixels() {
		
		return pixels;
		
	}
	
}
