package linnaeushotel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name GuestWindowController.java
 */
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
		
		/**
		 * This will open a new SearchGuestWindow which allows the user to
		 * search for guests already in the system.
		 * 
		 * If the guest model's currentGuest was set during the lifetime of the
		 * opened SearchGuestWindow it will when closed fill in the UI with the currentGuest's
		 * information.
		 */
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
				stage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// If a currentGuest was set in the model when the Search Guest Window was closed.
			if (this.guestModel.getCurrentGuest().get() != null) {
				fillUIGuestData();
				deleteGuestButton.setDisable(false);
				guestSelected = true;
			}
		});
		
		/**
		 * This will open the additional data window for the guest allowing the user
		 * to enter additional data for the currentGuest within the model. 
		 * 
		 * If the current guest was null when this window was opened then it will create a 
		 * new guest object to add the information to.
		 */
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
		
		
		/**
		 * The delete guest button. This is disabled by default since no guest is selected by the system.
		 * 
		 * If its available and its pressed it will first check if we are currently modifying an available
		 * guest and if we are it will delete that guest from the database.
		 * 
		 * If there is no guest being modified this button will just clear all the fields.
		 */
		deleteGuestButton.setDisable(true);
		deleteGuestButton.setOnAction(c -> {
			if (guestSelected) {
				int i = this.guestModel.getGuests().indexOf(this.guestModel.getCurrentGuest().get());
				Guest g = this.guestModel.getGuests().get(i);
				this.guestModel.getGuests().remove(i);
				this.guestModel.deleteGuest(g); // Delete guest from database.
				
				clearAll();
			} else {
				clearAll();
			}
			
			deleteGuestButton.setDisable(true);
		});
		
		/**
		 * This button clears all the current information in the UI.
		 */
		clearGuestFieldsButton.setOnAction(c -> {
			clearAll();
		});
		
		/**
		 * The button for saving guests into the system.
		 * 
		 * It first checks if the currentGuest within the model is an already existing guest and if it is
		 * we replace / update that guest in the system. If not we add a new guest to the database.
		 */
		saveGuestButton.setOnAction(c -> {
			if (guestSelected) {
				setGuestData();
				int i = this.guestModel.getGuests().indexOf(this.guestModel.getCurrentGuest().get());
				this.guestModel.getGuests().set(i, guestModel.getCurrentGuest().get());
				
				this.guestModel.updateGuest(this.guestModel.getGuests().get(i));
			} else {
				setGuestData();
				
				if (!guestModel.getCurrentGuest().get().getFirstName().isEmpty() &&
						!guestModel.getCurrentGuest().get().getLastName().isEmpty()) {
					this.guestModel.getGuests().add(guestModel.getCurrentGuest().get());
					
					//TODO: Temporarily set id manually. - Oskar Mendel 2018-05-04
					int i = this.guestModel.getGuests().indexOf(this.guestModel.getCurrentGuest().get());
					this.guestModel.getCurrentGuest().get().setId(i+1);
					
					this.guestModel.addGuest(this.guestModel.getCurrentGuest().get());
				} else {
					displayErrorDialog("Insufficient Information", 
							"Insufficient guest information was entered. "
							+ "Please enter a first and last name for the guest.");
				}
			}
		});
	}
	
	/**
	 * Sets the data for the currentGuest object in the guest model by
	 * the data which is ented in the UI.
	 */
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
	
	/**
	 * TODO: Find a better name for this method. - Oskar Mendel 2018-05-03
	 * 
	 * Fills in the UI with data from the currentGuest in the guest model.
	 */
	private void fillUIGuestData() {
		Guest guest = this.guestModel.getCurrentGuest().get();
		companyTextField.setText(guest.getCompany());
		lastNameTextField.setText(guest.getLastName());
		firstNameTextField.setText(guest.getFirstName());
		addressTextField.setText(guest.getAddress());
		
		birthdayDatePicker.setValue(guest.getBirthday());
		citizenshipTextField.setText(guest.getCitizenship());
	}
	
	/**
	 * Clears the entire UI from values.
	 */
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
	
	/**
	 * Helper method to display an error dialog with the specified
	 * title and content text.
	 * 
	 * @param title - Title of the error dialog.
	 * @param content - Content text of the error dialog.
	 */
	private void displayErrorDialog(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	/**
	 * Initializes the GuestModel for this controller.
	 * 
	 * @param guestModel
	 */
	public void initializeGuestModel(GuestModel guestModel) {
		if (this.guestModel != null) {
			throw new IllegalStateException("Model can only be initialize once");
		}
		
		this.guestModel = guestModel;
	}
}
