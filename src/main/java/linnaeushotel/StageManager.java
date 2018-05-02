package linnaeushotel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import linnaeushotel.controller.LinnaeusHotelController;

/**
 * Singleton that manages the loading and changing of a stage.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name StageManager.java
 */
public class StageManager {
	
	private static StageManager instance;
	
	private Stage mainStage;
	
	private StageManager() {
	}
	
	public static StageManager getInstance() {
		if (instance == null) {
			instance = new StageManager();
		}
		
		return instance;
	}
	
	
	/**
	 * Stores the controllers of each layout view
	 */
	private Map<String, LinnaeusHotelController> controllers = new HashMap<>();
	
	
	
	/**
	 * Manages the setting up and loading of the main window.
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	public void showLinnaeusHotel(Stage primaryStage) throws IOException {
		mainStage = primaryStage;
		mainStage.setTitle("LinnaeusHotel");
		
		//TODO: Use the root layout here instead of BorderPane. - Oskar Mendel 2018-05-01
		Scene mainScene = new Scene(new BorderPane());
		
		mainStage.setScene(mainScene);
		mainStage.setMinWidth(800);
		mainStage.setMinHeight(600);
		mainStage.show();
	}
	
	/**
	 * 
	 * @param layout The *.fxml file to be loaded.
	 * 
	 * @return The {@link Parent} object that is in the layout.
	 * 
	 * @throws IOException thrown if the *.fxxml file was not found.
	 */
	private Parent loadLayout(String layout) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(getClass().getResource(layout));	
		Parent nodeLayout = loader.load();
		
		//Store controller of target layout
		controllers.put(layout, loader.getController());
		
		return nodeLayout;
	}
}
