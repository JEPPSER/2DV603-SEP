package linnaeushotel.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import linnaeushotel.reservation.Reservation;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name ReservationModel.java
 */
public class ReservationModel {

	private final ObservableList<Reservation> reservationModel = FXCollections.observableArrayList();
	
	public ObservableList<Reservation> getReservations() {
		return this.reservationModel;
	}
	
	public void setReservations(List<Reservation> reservationList) {
		this.reservationModel.setAll(reservationList);
	}
}
