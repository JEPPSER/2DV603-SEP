package linnaeushotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import linnaeushotel.guest.Guest;
import linnaeushotel.reservation.Reservation;

public class GuestWindowController implements LinnaeusHotelController {
	
	@FXML public Button guestSearchButton;
	@FXML public TextField companyTextField;
	@FXML public TextField lastNameTextField;
	@FXML public TextField firstNameTextField;
	@FXML public TextArea addressTextField;
	@FXML public ToggleGroup Group;
	@FXML public RadioButton privateRadioButton;
	@FXML public RadioButton businessRadioButton;
	@FXML public DatePicker birthdayDatePicker;
	@FXML public TextField citizenshipTextField;
	@FXML public Button addDataButton;
	@FXML public Button deleteGuestButton;
	@FXML public Button clearGuestFieldsButton;
	@FXML public Button saveGuestButton;
	@FXML public ListView<Reservation> reservationsListView;
	
	private Guest selectedGuest;
	private boolean guestSelected = false;
	
	@FXML
	public void initialize() {
		
		
		guestSearchButton.setOnAction(c -> {
			//TODO: Open the new window
			
			deleteGuestButton.setDisable(false);
			guestSelected = true;
		});
		
		addDataButton.setOnAction(c -> {
			//TODO: Open the new window
		});
		
		deleteGuestButton.setDisable(true);
		deleteGuestButton.setOnAction(c -> {
			
		});
		
		clearGuestFieldsButton.setOnAction(c -> {
			companyTextField.clear();
			lastNameTextField.clear();
			firstNameTextField.clear();
			addressTextField.clear();
			privateRadioButton.setSelected(false);
			businessRadioButton.setSelected(false);
			birthdayDatePicker.setValue(null);
			citizenshipTextField.clear();
			reservationsListView.getItems().clear();
			selectedGuest = null;
			
			deleteGuestButton.setDisable(true);
			guestSelected = false;
		});
		
		saveGuestButton.setOnAction(c -> {
			if (guestSelected) {
				
			} else {
				
			}
		});
	}
}
