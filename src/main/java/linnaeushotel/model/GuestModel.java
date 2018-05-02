package linnaeushotel.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import linnaeushotel.guest.Guest;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name GuestModel.java
 */
public class GuestModel {

	private final ObservableList<Guest> guestModel = FXCollections.observableArrayList();
	
	public ObservableList<Guest> getGuests() {
		return this.guestModel;
	}
	
	public void setGuests(List<Guest> guestList) {
		this.guestModel.setAll(guestList);
	}
}
