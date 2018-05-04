package linnaeushotel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainWindowController implements LinnaeusHotelController {
	
	@FXML public Button guestManagementButton;
	@FXML public Button reservationManagementButton;
	
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
				
				//TODO: Make this button invalid while window is alive. - Oskar Mendel 2018-05-03
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		reservationManagementButton.setOnAction(c -> {
			Parent root;
			ReservationWindowController reservationWindowController;
			
			try {
				URI location = new File("src/main/resources/" + RESERVATION_WINDOW).toURI();
				root = FXMLLoader.load(location.toURL());
				Stage stage = new Stage();
				stage.setTitle("Reservation Management");
				stage.setScene(new Scene(root));
				
				FXMLLoader loader = new FXMLLoader(location.toURL());
				reservationWindowController = loader.<ReservationWindowController>getController();
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
