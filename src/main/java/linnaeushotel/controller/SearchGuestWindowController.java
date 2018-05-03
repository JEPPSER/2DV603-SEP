package linnaeushotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import linnaeushotel.guest.Guest;
import linnaeushotel.model.GuestModel;

public class SearchGuestWindowController implements LinnaeusHotelController {

	@FXML public TextField searchGuestTextField;
	@FXML public Button searchGuestButton;
	@FXML public ListView<Guest> guestsListView;
	@FXML public Button cancelButton;
	@FXML public Button loadButton;
	
	private GuestModel guestModel;
	
	@FXML
	public void initialize() {
		
		searchGuestButton.setOnAction(c -> {
			
		});
		
		cancelButton.setOnAction(c -> {
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		});
		
		loadButton.setOnAction(c -> {
			
		});
	}
	
	public void initializeGuestModel(GuestModel guestModel) {
		if (this.guestModel != null) {
			throw new IllegalStateException("Model can only be initialize once");
		}
		
		this.guestModel = guestModel;
	}
}
