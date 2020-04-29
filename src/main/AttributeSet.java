package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Receives the file and decomposes them line by line, sending it to the correct place.
 * It can receive a train set or a test set.
 * @author 
 *
 */
public class AttributeSet {
	
	
	/**
	 * 
	 * @param file
	 * 			CSV type of file formated with a heading containing the attributes of the random variables (X1, ... , Xn, C) and bellow the elements
	 */
	public AttributeSet(File file) {

        BufferedReader br = null;   
        String line = "";
        String cvsSplitBy = ",";
        
        
        try {
        	br = new BufferedReader(new FileReader(file));
        	boolean fileHeading = false;
            while ((line = br.readLine()) != null) {
            	if(!fileHeading) { //it hasn't reached the heading with the name of the variables
            		if(line.equals("")) {//line's still empty
            			continue;
            		}
            		else { //found line with the heading names
            			fileHeading = true;
            			// use comma as separator
                        String[] names = line.split(cvsSplitBy);
                        
                        // MANDAR ISTO PARA ONDE????
            		}
            		continue;//keeps reading until it finds the heading
            	}
            	
                // Has found the heading and now it's reading the values
            	if(!line.equals("")) {//line has something writen
            		// MANDAR ISTO PARA ONDE????
            	}
            	else
            		continue;
              

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
	}
}
