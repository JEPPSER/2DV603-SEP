package linnaeushotel.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import linnaeushotel.room.Room;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name RoomModel.java
 */
public class RoomModel {
	
	private final ObservableList<Room> roomModel = FXCollections.observableArrayList();
	
	public ObservableList<Room> getRooms() {
		return this.roomModel;
	}
	
	public void setRooms(List<Room> roomList) {
		this.roomModel.setAll(roomList);
	}
}
