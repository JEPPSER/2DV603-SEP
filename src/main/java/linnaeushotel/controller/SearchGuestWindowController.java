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

public class SearchGuestWindowController implements LinnaeusHotelController {

	@FXML public TextField searchGuestTextField;
	@FXML public Button searchGuestButton;
	@FXML public ListView<Guest> guestsListView;
	@FXML public Button cancelButton;
	@FXML public Button loadButton;
	
	private GuestModel guestModel;
	
	@FXML
	public void initialize() {
		
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
			guestsListView.getItems().setAll(this.guestModel.getGuests());
			
			if (!searchGuestTextField.getText().isEmpty()) {
				ArrayList<Guest> guestList = new ArrayList<>();
				
				for (Guest g : guestsListView.getItems()) {
					if (nameMatch(searchGuestTextField.getText(), g)) {
						guestList.add(g);
					}
				}
				guestsListView.getItems().clear();
				guestsListView.getItems().setAll(guestList);
			}
		});
		
		cancelButton.setOnAction(c -> {
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		});
		
		loadButton.setOnAction(c -> {
			
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
