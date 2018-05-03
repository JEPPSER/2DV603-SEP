package linnaeushotel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import linnaeushotel.guest.Guest;
import linnaeushotel.model.GuestModel;
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
	
	private GuestModel guestModel;
	private boolean guestSelected = false;
	
	@FXML
	public void initialize() {
		this.initializeGuestModel(new GuestModel());
		
		guestSearchButton.setOnAction(c -> {
			Parent root;
			URI location = new File("src/main/resources/" + SEARCH_GUEST_WINDOW).toURI();
			SearchGuestWindowController searchGuestWindowController;
			
			try {
				FXMLLoader loader = new FXMLLoader(location.toURL());
				root = loader.load();
				
				Stage stage = new Stage();
				stage.setTitle("Search Guest");
				stage.setScene(new Scene(root));
				
				searchGuestWindowController = loader.<SearchGuestWindowController>getController();
				searchGuestWindowController.initializeGuestModel(this.guestModel);
				
				
				//TODO: Make this button invalid while window is alive. - Oskar Mendel 2018-05-03
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			deleteGuestButton.setDisable(false);
			guestSelected = true;
		});
		
		addDataButton.setOnAction(c -> {
			Parent root;
			URI location = new File("src/main/resources/" + ADDITIONAL_DATA_WINDOW).toURI();
			AdditionalDataWindowController additionalDataWindowController;
			
			try {
				FXMLLoader loader = new FXMLLoader(location.toURL());
				root = loader.load();
				
				Stage stage = new Stage();
				stage.setTitle("Guest Additional Data");
				stage.setScene(new Scene(root));
				
				additionalDataWindowController = loader.<AdditionalDataWindowController>getController();
				additionalDataWindowController.initializeGuestModel(this.guestModel);
				
				//TODO: Make this button invalid while window is alive. - Oskar Mendel 2018-05-03
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		deleteGuestButton.setDisable(true);
		deleteGuestButton.setOnAction(c -> {
			if (guestSelected) {
				//TODO: Delete guest from database.
			} else {
				clearAll();
			}
			
		});
		
		clearGuestFieldsButton.setOnAction(c -> {
			clearAll();
		});
		
		saveGuestButton.setOnAction(c -> {
			if (guestSelected) {
				setGuestData();
				
				//TODO: Update guest in database.
			} else {
				setGuestData();
				
				//TODO: Add new guest to database.
			}
		});
	}
	
	private void setGuestData() {
		Guest guest = this.guestModel.getCurrentGuest().get();
		
		if (guest == null) {
			guest = new Guest();
			this.guestModel.setCurrentGuest(guest);
		}
		
		guest.setCompany(companyTextField.getText());
		guest.setLastName(lastNameTextField.getText());
		guest.setFirstName(firstNameTextField.getText());
		guest.setAddress(addressTextField.getText());
		
		
		privateRadioButton.setSelected(false);
		businessRadioButton.setSelected(false);
		
		guest.setBirthday(birthdayDatePicker.getValue());
		guest.setCitizenship(citizenshipTextField.getText());
	}
	
	private void clearAll() {
		companyTextField.clear();
		lastNameTextField.clear();
		firstNameTextField.clear();
		addressTextField.clear();
		privateRadioButton.setSelected(false);
		businessRadioButton.setSelected(false);
		birthdayDatePicker.setValue(null);
		citizenshipTextField.clear();
		reservationsListView.getItems().clear();
		this.guestModel.setCurrentGuest(null);
		
		deleteGuestButton.setDisable(true);
		guestSelected = false;
	}
	
	public void initializeGuestModel(GuestModel guestModel) {
		if (this.guestModel != null) {
			throw new IllegalStateException("Model can only be initialize once");
		}
		
		this.guestModel = guestModel;
	}
}
