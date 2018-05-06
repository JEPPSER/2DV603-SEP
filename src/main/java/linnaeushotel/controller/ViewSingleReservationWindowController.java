package linnaeushotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ViewSingleReservationWindowController implements LinnaeusHotelController {
	
	@FXML public TextField arrivalTextField;
	@FXML public TextField departureTextField;
	@FXML public TextField companyTextField;
	@FXML public TextField lastNameTextField;
	@FXML public TextField firstNameTextField;
	@FXML public TextField locationTextField;
	@FXML public TextField roomTextField;
	@FXML public TextField checkedInTextField;
	@FXML public TextField priceTextField;
	
	@FXML public Button deleteButton;
	@FXML public Button okButton;
	
	@FXML
	public void initialize(){
		
	}
}
