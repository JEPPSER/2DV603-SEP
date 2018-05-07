package linnaeushotel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import linnaeushotel.controller.LinnaeusHotelController;
import linnaeushotel.controller.MainWindowController;

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
	
	private static DB_manager db;
	
	private StageManager() {
	}
	
	public static StageManager getInstance() {
		if (instance == null) {
			instance = new StageManager();
			db = new DB_manager();
		}
		
		return instance;
	}
	
	
	/**
	 * Stores the controllers of each layout view
	 */
	private Map<String, LinnaeusHotelController> controllers = new HashMap<>();
	
	public MainWindowController getMainWindowController(){
		return (MainWindowController) controllers.get(LinnaeusHotelController.MAIN_WINDOW);
	}
	
	/**
	 * Manages the setting up and loading of the main window.
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	public void showLinnaeusHotel(Stage primaryStage) throws IOException {
		mainStage = primaryStage;
		mainStage.setTitle("LinnaeusHotel");
		
		VBox mainWindow = (VBox) loadLayout(LinnaeusHotelController.MAIN_WINDOW);
		getMainWindowController().initializeDB(db);
		
		Scene mainScene = new Scene(mainWindow);
		
		mainStage.setScene(mainScene);
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
