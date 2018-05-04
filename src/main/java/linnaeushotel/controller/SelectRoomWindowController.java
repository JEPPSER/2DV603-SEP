package linnaeushotel.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import linnaeushotel.model.ReservationModel;
import linnaeushotel.model.RoomModel;
import linnaeushotel.room.Location;
import linnaeushotel.room.Room;
import linnaeushotel.room.RoomQuality;
import linnaeushotel.room.RoomType;

public class SelectRoomWindowController implements LinnaeusHotelController {

	@FXML public VBox floorContainer;
	@FXML public RadioButton vaxjoRadioButton;
	@FXML public RadioButton kalmarRadioButton;
	@FXML public ToggleGroup Group;
	@FXML public VBox roomContainer;
	@FXML public Button cancelButton;
	@FXML public Button okButton;

	private RoomModel roomModel;
	private ReservationModel reservationModel;
	
	private LocalDate arrival;
	private LocalDate departure;
	private RoomType roomType;
	private RoomQuality roomQuality;
	private boolean smoker;
	private ToggleGroup validGroup;
	private Room selectedRoom;

	@FXML
	public void initialize() {
		validGroup = new ToggleGroup();
		
		vaxjoRadioButton.setOnAction(c-> {
			selectedRoom = null;
			ArrayList<Room> validRooms = getValidRooms();
			setRoomContainer(validRooms);
		});
		
		kalmarRadioButton.setOnAction(c-> {
			selectedRoom = null;
			ArrayList<Room> validRooms = getValidRooms();
			setRoomContainer(validRooms);
		});
		
		okButton.setOnAction(c -> {
			if(selectedRoom == null){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Room Selection Error");
				alert.setContentText("A room must be selected!");
				alert.showAndWait();
			} else {
				roomModel.setCurrentRoom(selectedRoom);
				Stage stage = (Stage) okButton.getScene().getWindow();
				stage.close();
			}
		});
		
		cancelButton.setOnAction(c -> {
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		});
	}

	public void initializeRoomModel(RoomModel roomModel) {
		if (this.roomModel != null) {
			throw new IllegalStateException("Model can only be initialize once");
		}
		this.roomModel = roomModel;
		this.arrival = roomModel.getArrival();
		this.departure = roomModel.getDeparture();
		this.roomType = roomModel.getRoomType();
		this.roomQuality = roomModel.getRoomQuality();
		this.smoker = roomModel.isSmoker();
	}
	
	public void initializeReservationModel(ReservationModel reservationModel){
		if (this.reservationModel != null) {
			throw new IllegalStateException("Model can only be initialize once");
		}
		this.reservationModel = reservationModel;
		
		ArrayList<Room> validRooms = getValidRooms();
		setRoomContainer(validRooms);
	}
	
	private ArrayList<Room> getValidRooms(){
		ArrayList<Room> validRooms = new ArrayList<Room>();
		
		for(int i = 0; i < roomModel.getRooms().size(); i++){
			boolean isValid = true;
			Room room = roomModel.getRooms().get(i);
			if(room.getType() == roomType && room.getLocation() == getLocation() && 
					room.getQuality() == roomQuality && room.isSmoker() == smoker){
				for(int j = 0; j < reservationModel.getReservations().size(); j++){
					if(reservationModel.getReservations().get(j).isOverlapping(arrival, departure, room)){
						isValid = false;
						break;
					}
				}
			} else {
				isValid = false;
			}
			if(isValid){
				validRooms.add(room);
			}
		}
		return validRooms;
	}
	
	private void setRoomContainer(ArrayList<Room> rooms){
		roomContainer.getChildren().clear();
		for(int i = 0; i < rooms.size(); i++){
			RadioButton rb = new RadioButton();
			Room room = rooms.get(i);
			rb.setText(String.valueOf(rooms.get(i).getRoomNumber()));
			roomContainer.getChildren().add(rb);
			rb.setToggleGroup(validGroup);
			rb.setOnAction(c -> {
				selectedRoom = room;
			});
		}
	}
	
	private Location getLocation(){
		if(vaxjoRadioButton.isSelected()){
			return Location.VAXJO;
		}
		return Location.KALMAR;
	}
}
