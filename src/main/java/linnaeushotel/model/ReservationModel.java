package linnaeushotel.model;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import linnaeushotel.DB_manager;
import linnaeushotel.reservation.Reservation;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name ReservationModel.java
 */
public class ReservationModel {

	private final ObjectProperty<Reservation> currentReservation = new SimpleObjectProperty<>();
	private final ObservableList<Reservation> reservationModel = FXCollections.observableArrayList();
	
	private final DB_manager db = new DB_manager();
	
	public ReservationModel(){
		reservationModel.setAll(db.getReservations());
	}
	
	public ObservableList<Reservation> getReservations() {
		return this.reservationModel;
	}
	
	public void setReservations(List<Reservation> reservationList) {
		this.reservationModel.setAll(reservationList);
	}
	public ObjectProperty<Reservation> getCurrentReservation(){
		return currentReservation;
	}
	
	public void setCurrentReservation(Reservation reservation){
		this.currentReservation.set(reservation);
	}
	
	public void addReservation(Reservation r){
		db.insertReservation(r);
	}
}
