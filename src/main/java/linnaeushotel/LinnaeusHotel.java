package linnaeushotel;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point of the application.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name LinnaeusHotel.java
 */
public class LinnaeusHotel extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		StageManager sm = StageManager.getInstance();
		sm.showLinnaeusHotel(arg0);
	}
}
