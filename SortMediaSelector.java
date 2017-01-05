package noahcharlotte;

/*The following code is what we had to import in order to be able to implement certain functionality.*/

import noahcharlotte.generator.Media;
import noahcharlotte.generator.MediaGenerator;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Collections;
import java.util.Comparator;

public class SortMediaSelector {
	
	//These are the fields of the SortMediaSelector
	
	private MediaSelectorLibary mediaSelectorLibary;
	private ArrayList<FilmFile> films;
	private ArrayList<MusicFile> musicTracks;
	
	/**This is the constructor of the SortMediaSelector class*/
	
	public SortMediaSelector(){
		
		mediaSelectorLibary = new MediaSelectorLibary(this);
		films = new ArrayList<FilmFile>();
		musicTracks = new ArrayList<MusicFile>();
		
		mediaSelectorLibary.setVisible(true); //This sets the JFrame is visible and allows it to be seen when the program is run.
			
			for (Media mediaFiles : MediaGenerator.getMedia("noahcharlotte/resources/")){
				
				//Rather than calling the methods getName() and getImage() each time, storing it in a variable makes it less time consuming and easier. 
				
				String fileName = mediaFiles.getName();
				JLabel filePoster = mediaFiles.getImage();
				
//				System.out.println(fileName);
				
				//The following three lines will check whether the media files are classified or not by checking for a . with text on either side
				
				Pattern classificationPattern = Pattern.compile(".*(\\.).*");
				Matcher classificationMatcher = classificationPattern.matcher(fileName);
				classificationMatcher.matches();
				
				/*If the classificationMatcher.matches() returns true then the enclosed code will be implemented - to be explained.
				 *
				 *If the classificationMatcher.matches() returns false then the media files will be untouched and simply added to the Unclassified panel
				 *through the use of calling on the createUnclassifiedThumbnail() method.
				 */
				
				if (classificationMatcher.matches()) { 
					
					Pattern filmPattern = Pattern.compile(".*(\\.flv|\\.gif|\\.mkv|\\.mpeg|\\.mpg|\\.mov)");
					Pattern musicPattern = Pattern.compile(".*(\\.aiff|\\.aac|\\.aax|\\.oog|\\.wav|\\.wma)");
					
					Matcher filmMatcher = filmPattern.matcher(fileName);
					Matcher musicMatcher = musicPattern.matcher(fileName);
					
					/*The following two lines return either true or false.
					 * 
					 *If filmMatcher returns true then the file is a Film, if it returns false then it is a Music Track and vice versa*/
					
					filmMatcher.matches();
					musicMatcher.matches();
					
					/*This nested if statement now checks whether the classified media files are either a film file or a music file by trying to match their
					 *patterns with the media file name.
					 *
					 *If the filmMatcher.matches() returns true, therefore meaning that the media file is in fact a film file then
					 *a new film object will be made and added to the films arraylist.
					 *
					 *However, if the filmMatcher.matches() returns false, therefore meaning that the media file is in fact a music file then
					 *a new music object will be made and added to the musicTracks arraylist.*/
						
					if (filmMatcher.matches()){
					
						films.add(new FilmFile(filePoster, extractString("[A-Za-z\\s]{3,}+", fileName, 0), Integer.parseInt(extractString("(198[0-9]|199[0-9]|20[0-9][0-9])", fileName, 0)), extractString("((S|H)D)", fileName, 0), extractString("(\\d{3,4}p)", fileName, 0)));
						//Despite the regex for the title "[A-Za-z\\s]{3,}+" specifying to find words with a minimum of three letters words like 'of' and 'a' will not be affected as the regex already begins to capture expression so just carries on as normal.
						
					} 
						
					else { //This is the else to the nested if statement
						
						musicTracks.add(new MusicFile(filePoster, extractString("(.*)\\-(.*)(\\.)", fileName, 1), extractString("(.*)\\-(.*)(\\.)", fileName, 2)));

					}

				} 

				else { //This is the else statement to the first if statement 

					mediaSelectorLibary.createUnclassifiedThumbnail(filePoster, fileName);

				}

			}
			
			//The following two lines then call the two methods that place the film and music files as thumbnails on the JFrame.
			
			createAllFilms();
			createAllMusicTracks();

	}
	
	/**This method will create the film thumbnails for each of the film files in the arraylist films.
	 *It does this by looping through the film arraylist and calling the createFilmThumbail method on the mediaSelector object for each film in the arraylist. 
	 */
	
	public void createAllFilms() {
		
		for ( FilmFile film : films ) {
			
			mediaSelectorLibary.createFilmThumbnail(film.getImage(), film.getTitle(), film.getYear(), film.getDefinition());
			
		}
			
	}
	
	/**This method will create the music thumbnails for each of the film files in the arraylist films.
	 *It does this by looping through the music arraylist and calling the createMusicThumbail method on the mediaSelector object for each music in the arraylist. 
	 */
	
	public void createAllMusicTracks() {
		
		for ( MusicFile music : musicTracks ) {
			
			mediaSelectorLibary.createMusicThumbnail(music.getImage(), music.getTitle(), music.getArtist());
			
		}
			
	}
	
	// SORT FILES BY METHODS //
	
	/**This method when called will sort the film files by their name in alphabetical order.*/
	
	public void sortFilmsByName() {
		
		Collections.sort(films, new Comparator<FilmFile>() {

			/**This method will compare two film files and decided which one comes before the other depending on their name.
			 * 
			 *It does this through the comparesTo method by alphabetically comparing two films together. 
			 * 
			 * @param f1
			 * @param f2
			 * @return sortingOutThe.group(2).compareTo(sortingOutThe2.group(2));
			 * @return f1.getTitle().compareTo(f2.getTitle());
			 */
			@Override
			public int compare(FilmFile f1, FilmFile f2) {
				
				Pattern sortWithoutTHE = Pattern.compile(" ?(The)? ?(?:\\([HDS]{2}, \\))? ?(?:\\([0-9]{4}\\))? ?([A-Za-z\\s-]{5,})");
				
				Matcher sortingOutTHEf1 = sortWithoutTHE.matcher(f1.getTitle());
				Matcher sortingOutTHEf2 = sortWithoutTHE.matcher(f2.getTitle());
				
				/*This if statement checks whether the title of film file contains 'The'.
				 * 
				 *If it does then the second capture group (the title of the film file minus the leading 'The') of film 1 and film 2
				 *are compared alphabetically using the compareTo method to determine which film comes before the other.
				 *
				 *But it it does not then it will simply compare the names of both films together using the same process of the compareTo method.*/
				
				if (sortingOutTHEf1.find() && sortingOutTHEf2.find()){
					
					return sortingOutTHEf1.group(2).compareTo(sortingOutTHEf2.group(2));
				
				} 
				
				else {
					
					return f1.getTitle().compareTo(f2.getTitle());
						
				}
				
			}
			
		});

	}
	
	/**This method when called will sort the film files by their pixels in ascending order.*/

	public void sortFilmsByPixels() {
		
		Collections.sort(films, new Comparator<FilmFile>() {
			
			/**This method will compare two film files and decided which one comes before the other.
			 *
			 *It does this through parsing the String argument to an Integer and subtracting the second film (f2) from the first film (f1).
			 *If the result of this subtraction is a minus then film 1 should be ordered before film 2 as the pixels in film 1
			 *are lower than those of film 2.
			 *
			 * @param f1
			 * @param f2
			 * @return Integer.parseInt(extractString("\\d{3,4}", f1.getPixels(), 0)) - Integer.parseInt(extractString("\\d{3,4}", f2.getPixels(), 0));
			 */
			@Override
			public int compare(FilmFile f1, FilmFile f2) {
			
				return Integer.parseInt(extractString("\\d{3,4}", f1.getPixels(), 0)) - Integer.parseInt(extractString("\\d{3,4}", f2.getPixels(), 0));
				
			}

		});
		
	}
	
	/**This method when called will sort the film files by their year in ascending order.*/
	
	public void sortFilmsByYear() {
		
		Collections.sort(films, new Comparator<FilmFile>() {

			/**This method will compare two film files and decided which one comes before the other.
			 *
			 *It does this through subtracting the year of the second film (f2) from the year of the first film (f1). It returns this result and if
			 *it is a minus then film 1 should be ordered before film 2 as the year of film 1 is earlier than the year of film 2.
			 *
			 * @param f1
			 * @param f2
			 * @return f1.getYear() - f2.getYear();
			 */
			@Override
			public int compare(FilmFile f1, FilmFile f2) {
			
				return f1.getYear() - f2.getYear();
				
			}
			
		});
		
	}
	
	/**This method when called will sort the music files by their name (title) in alphabetical order.*/
	
	public void sortMusicByName() {
		
		Collections.sort(musicTracks, new Comparator<MusicFile>() {

			/**This method will compare two music files and decided which one comes before the other.
			 * 
			 *It does this through the comparesTo method by alphabetically comparing the track name of two music tracks together. 
			 * 
			 * @param m1
			 * @param m2
			 * @return m1.getTitle().compareTo(m2.getTitle());
			 */
			@Override
			public int compare(MusicFile m1, MusicFile m2) {
			
				return m1.getTitle().compareTo(m2.getTitle());
				
			}
			
		});
		
	}
	
	/**This method when called will sort the music files by their artist in alphabetical order.*/
	
	public void sortMusicByArtist() {
		
		Collections.sort(musicTracks, new Comparator<MusicFile>() {

			/**This method will compare two music files and decided which one comes before the other.
			 * 
			 *It does this through the comparesTo method by alphabetically comparing the artist of two music tracks together. 
			 * 
			 * @param m1
			 * @param m2
			 * @return m1.getArtist().compareTo(m2.getArtist());
			 */
			@Override
			public int compare(MusicFile m1, MusicFile m2) {
			
				return m1.getArtist().compareTo(m2.getArtist());
				
			}
			
		});
		
	}
	
	/**This is a method that will check the text (so the title (for film and music), year (for film), definition (for film), pixels (for film) and artist (for music)) 
	 *of the media file and check specific regex specified and extract soley that piece of information.
	 * 
	 * @param pattern - this is the regex you would like to extract from the given text (e.g. "((S|H)D)" for the definition of a film file)
	 * @param input - this is the given text that you want the regex to try and match (so the fileName)
	 * @param group - this is the captured group we want to extract from the string after the regex has been matched (if it has)
	 * @return matcher.group(group);
	 */
	
	public String extractString(String pattern, String input, int group) {
		
		Pattern compiledPattern = Pattern.compile(pattern);
		
		Matcher matcher = compiledPattern.matcher(input);
		
		matcher.find();
		
		return matcher.group(group);
		
	}
	
}