package linnaeushotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import linnaeushotel.model.ReservationModel;
import linnaeushotel.reservation.Reservation;

/**
 * This is the controller class for ViewSingleReservationWindowController. This window displays
 * a reservation with all its information. Reservations can also be deleted in this window.
 * 
 * @author Jesper Bergstrom
 * @name ViewSingleReservationWindowController.java
 * @version 0.00.00
 */
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
	
	private ReservationModel reservationModel;
	
	@FXML
	public void initialize(){
		
	}
	
	public void initializeReservationModel(ReservationModel reservationModel) {
		if (this.reservationModel != null) {
			throw new IllegalStateException("Model can only be initialize once");
		}
		this.reservationModel = reservationModel;
		
		Reservation r = this.reservationModel.getCurrentReservation().get();
		arrivalTextField.setText(r.getStartDate().toString());
		departureTextField.setText(r.getEndDate().toString());
		companyTextField.setText(r.getGuest().getCompany());
		lastNameTextField.setText(r.getGuest().getLastName());
		firstNameTextField.setText(r.getGuest().getFirstName());
		locationTextField.setText(r.getRoom().getLocation().toString());
		roomTextField.setText(String.valueOf(r.getRoom().getRoomNumber()));
		checkedInTextField.setText(Boolean.toString(r.isCheckedIn()));
		priceTextField.setText(String.valueOf(r.getPrice()));
		
		deleteButton.setOnAction(c -> {
			reservationModel.getReservations().remove(r);
			reservationModel.setCurrentReservation(null);
			Stage stage = (Stage) deleteButton.getScene().getWindow();
			stage.close();
		});
		
		okButton.setOnAction(c -> {
			Stage stage = (Stage) deleteButton.getScene().getWindow();
			stage.close();
		});
	}
}
