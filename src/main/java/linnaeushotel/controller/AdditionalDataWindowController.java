package linnaeushotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import linnaeushotel.guest.Guest;
import linnaeushotel.model.GuestModel;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name AdditionalDataWindowController.java
 */
public class AdditionalDataWindowController implements LinnaeusHotelController {
	
	@FXML public ToggleGroup Group1;
	@FXML public ToggleGroup Group2;
	@FXML public TextField spouseTextField;
	@FXML public TextArea childrenTextArea;
	@FXML public TextField phoneTextField;
	@FXML public TextField mobileTextField;
	@FXML public TextField faxTextField;
	@FXML public TextField emailTextField;
	@FXML public RadioButton privateRadioButton;
	@FXML public RadioButton businessRadioButton;
	@FXML public TextField favouriteRoomTextField;
	@FXML public RadioButton smokerYesRadioButton;
	@FXML public RadioButton smokerNoRadioButton;
	@FXML public Button cancelButton;
	@FXML public Button okButton;
	
	private GuestModel guestModel;
	
	@FXML
	public void initialize() {
		
		cancelButton.setOnAction(c -> {
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		});
		
		okButton.setOnAction(c -> {
			Guest modifiedGuest = this.guestModel.getCurrentGuest().get();
			if (modifiedGuest == null) {
				modifiedGuest = new Guest();
			}
			
			modifiedGuest.setSpouse(spouseTextField.getText());
			modifiedGuest.setChildren(childrenTextArea.getText());
			modifiedGuest.setPhone(phoneTextField.getText());
			modifiedGuest.setMobile(mobileTextField.getText());
			modifiedGuest.setFax(faxTextField.getText());
			modifiedGuest.setEmail(emailTextField.getText());
			
			if (smokerYesRadioButton.isSelected()) {
				modifiedGuest.setSmoker(true);				
			} else {
				modifiedGuest.setSmoker(false);
			}
			
			modifiedGuest.setFavouriteRoom(favouriteRoomTextField.getText());
			
			
			this.guestModel.setCurrentGuest(modifiedGuest);
			
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
		});
	}
	
	public void initializeGuestModel(GuestModel guestModel) {
		if (this.guestModel != null) {
			throw new IllegalStateException("Model can only be initialize once");
		}
		
		this.guestModel = guestModel;
		
		Guest guest = this.guestModel.getCurrentGuest().get();
		if (guest != null) {
			spouseTextField.setText(guest.getSpouse());
			childrenTextArea.setText(guest.getChildren());
			phoneTextField.setText(guest.getPhone());
			mobileTextField.setText(guest.getMobile());
			faxTextField.setText(guest.getFax());
			emailTextField.setText(guest.getEmail());
			
			if (guest.isSmoker()) {
				smokerYesRadioButton.setSelected(true);
			} else {
				smokerNoRadioButton.setSelected(true);
			}
			
			favouriteRoomTextField.setText(guest.getFavouriteRoom());
		}
	}
	
}
