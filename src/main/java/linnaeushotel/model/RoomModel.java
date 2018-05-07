package linnaeushotel.model;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import linnaeushotel.DB_manager;
import linnaeushotel.room.Room;
import linnaeushotel.room.RoomQuality;
import linnaeushotel.room.RoomType;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name RoomModel.java
 */
public class RoomModel {
	
	private final ObjectProperty<Room> currentRoom = new SimpleObjectProperty<>();
	private final ObservableList<Room> roomModel = FXCollections.observableArrayList();
	
	private final DB_manager db;
	
	private LocalDate arrival;
	private LocalDate departure;
	private RoomType roomType;
	private RoomQuality roomQuality;
	private boolean smoker;
	
	public RoomModel(DB_manager db){
		this.db = db;
		roomModel.setAll(this.db.getRooms());
	}
	
	public ObservableList<Room> getRoomModel() {
		return roomModel;
	}
	
	public ObjectProperty<Room> getCurrentRoom(){
		return currentRoom;
	}
	
	public void setCurrentRoom(Room currentRoom){
		this.currentRoom.set(currentRoom);
	}
	
	public ObservableList<Room> getRooms() {
		return this.roomModel;
	}
	
	public void setRooms(List<Room> roomList) {
		this.roomModel.setAll(roomList);
	}
	
	public LocalDate getArrival() {
		return arrival;
	}

	public void setArrival(LocalDate arrival) {
		this.arrival = arrival;
	}

	public LocalDate getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDate departure) {
		this.departure = departure;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public RoomQuality getRoomQuality() {
		return roomQuality;
	}

	public void setRoomQuality(RoomQuality roomQuality) {
		this.roomQuality = roomQuality;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}
}
