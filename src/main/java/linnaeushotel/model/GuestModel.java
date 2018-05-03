package linnaeushotel.model;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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

	private final ObjectProperty<Guest> currentGuest = new SimpleObjectProperty<>();
	private final ObservableList<Guest> guestModel = FXCollections.observableArrayList();
	
	public ObjectProperty<Guest> getCurrentGuest() {
		return this.currentGuest;
	}
	
	public void setCurrentGuest(Guest g) {
		currentGuest.set(g);
	}
	
	public ObservableList<Guest> getGuests() {
		return this.guestModel;
	}
	
	public void setGuests(List<Guest> guestList) {
		this.guestModel.setAll(guestList);
	}
}
