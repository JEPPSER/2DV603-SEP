package linnaeushotel.controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import linnaeushotel.guest.Guest;
import linnaeushotel.model.GuestModel;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SearchGuestWindowController.java
 */
public class SearchGuestWindowController implements LinnaeusHotelController {

	@FXML public TextField searchGuestTextField;
	@FXML public Button searchGuestButton;
	@FXML public ListView<Guest> guestsListView;
	@FXML public Button cancelButton;
	@FXML public Button loadButton;
	
	private GuestModel guestModel;
	
	@FXML
	public void initialize() {
		
		/**
		 * Defining how the guestsListView should display the guest objects within the list.
		 */
		guestsListView.setCellFactory(new Callback<ListView<Guest>, ListCell<Guest>>() {

			@Override
			public ListCell<Guest> call(ListView<Guest> param) {
				ListCell<Guest> cell = new ListCell<Guest>() {
					@Override
					protected void updateItem(Guest t, boolean bln) {
						super.updateItem(t,  bln);
						if (t != null) {
							setText(t.getFirstName() + " " + t.getLastName());
						} else {
							setText("");
						}
					}
				};
				
				return cell;
			}
		});
		
		searchGuestButton.setOnAction(c -> {
			if (!searchGuestTextField.getText().isEmpty()) {
				ArrayList<Guest> guestList = new ArrayList<>();
				
				for (Guest g : guestModel.getGuests()) {
					// For each guest in the guest model check if the name match the search keyword.
					if (nameMatch(searchGuestTextField.getText(), g)) {
						guestList.add(g);
					}
				}
				
				// Refresh the guest listview with all the found guests.
				guestsListView.getItems().clear();
				guestsListView.getItems().setAll(guestList);
			} else {
				// If search field was empty re-populate listview with content of the guest model.
				guestsListView.getItems().setAll(guestModel.getGuests());
			}
		});
		
		cancelButton.setOnAction(c -> {
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		});
		
		loadButton.setOnAction(c -> {
			if (this.guestsListView.getSelectionModel().getSelectedItem() != null) {
				this.guestModel.setCurrentGuest(guestsListView.getSelectionModel().getSelectedItem());
				Stage stage = (Stage) loadButton.getScene().getWindow();
				stage.close();
			}
		});
	}
	
	private boolean nameMatch(String keyword, Guest guest) {
		if (guest.getFirstName().toLowerCase().contains(keyword.toLowerCase().trim())) {
			return true;
		} else if (guest.getLastName().toLowerCase().contains(keyword.toLowerCase())) {
			return true;
		}
		
		return false;
	}
	
	public void initializeGuestModel(GuestModel guestModel) {
		if (this.guestModel != null) {
			throw new IllegalStateException("Model can only be initialize once");
		}
		
		this.guestModel = guestModel;
		
		this.guestsListView.getItems().setAll(this.guestModel.getGuests());
	}
}
