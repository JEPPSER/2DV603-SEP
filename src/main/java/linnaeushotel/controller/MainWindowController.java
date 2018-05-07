package linnaeushotel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import linnaeushotel.DB_manager;
import linnaeushotel.model.GuestModel;
import linnaeushotel.model.ReservationModel;
import linnaeushotel.model.RoomModel;

public class MainWindowController implements LinnaeusHotelController {
	
	@FXML public Button guestManagementButton;
	@FXML public Button reservationManagementButton;
	
	private DB_manager db;
	
	@FXML public void initialize() {
		
		guestManagementButton.setOnAction(c -> {
			Parent root;
			URI location = new File("src/main/resources/" + GUEST_WINDOW).toURI();
			GuestWindowController guestWindowController;
			
			try {
				FXMLLoader loader = new FXMLLoader(location.toURL());
				root = loader.load();
				
				Stage stage = new Stage();
				stage.setTitle("Guest Management");
				stage.setScene(new Scene(root));
				
				guestWindowController = loader.<GuestWindowController>getController();
				guestWindowController.initializeGuestModel(new GuestModel(this.db));
        
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		reservationManagementButton.setOnAction(c -> {
			Parent root;
			URI location = new File("src/main/resources/" + RESERVATION_WINDOW).toURI();
			ReservationWindowController reservationWindowController;
			
			try {
				FXMLLoader loader = new FXMLLoader(location.toURL());
				root = loader.load();
				
				Stage stage = new Stage();
				stage.setTitle("Reservation Management");
				stage.setScene(new Scene(root));
				
				reservationWindowController = loader.<ReservationWindowController>getController();
				reservationWindowController.initializeGuestModel(new GuestModel(this.db));
				reservationWindowController.initializeReservationModel(new ReservationModel(this.db));
				reservationWindowController.initializeRoomModel(new RoomModel(this.db));
				
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void initializeDB(DB_manager db) {
		if (this.db != null) {
			throw new IllegalStateException("DB can only be initialize once");
		}
		
		this.db = db;
	}
}
