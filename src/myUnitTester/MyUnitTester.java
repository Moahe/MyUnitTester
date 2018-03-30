
/**
 * Application development in Java, 5dv135-apjava-ht17,
 * Assignment 1: MyUnitTester,
 * 
 * 
 * Last Edited: 2017-12-06
 * @author Moa Hermansson, id14mhn
 */

package myUnitTester;
/**
 * A main method that 
 * creates and shows the interface.
 * 
 */
public class MyUnitTester {
    public static void main( String[] args ) {

	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {

	    	ViewT gui = new ViewT();
	    	
	    	new ControllerT(gui);

	    	
	    }
	    });
    }
	}
