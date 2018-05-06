package linnaeushotel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import linnaeushotel.guest.Guest;
import linnaeushotel.model.GuestModel;
import linnaeushotel.model.ReservationModel;
import linnaeushotel.model.RoomModel;
import linnaeushotel.reservation.Reservation;
import linnaeushotel.room.Location;
import linnaeushotel.room.Room;
import linnaeushotel.room.RoomQuality;
import linnaeushotel.room.RoomType;

/**
 * This is the controller class for ReservationWindow. It initializes the
 * graphical components of the window and defines the onClicks and onActions of
 * them.
 * 
 * @author Jesper Bergstrom
 * @name ReservationWindowController.java
 * @version 0.00.00
 */
public class ReservationWindowController implements LinnaeusHotelController {

	@FXML public RadioButton singleRoomRadioButton;
	@FXML public RadioButton doubleRoomRadioButton;
	@FXML public RadioButton tripleRoomRadioButton;
	@FXML public RadioButton fourBedRoomRadioButton;
	@FXML public RadioButton suiteRadioButton;

	@FXML public RadioButton quality1RadioButton;
	@FXML public RadioButton quality2RadioButton;
	@FXML public RadioButton quality3RadioButton;
	@FXML public RadioButton quality4RadioButton;
	@FXML public RadioButton quality5RadioButton;

	@FXML public RadioButton smokingRadioButton;
	@FXML public RadioButton nonSmokingRadioButton;

	@FXML public ToggleGroup Group;
	@FXML public ToggleGroup Group2;
	@FXML public ToggleGroup Group3;

	@FXML public DatePicker arrivalDatePicker;
	@FXML public DatePicker departureDatePicker;

	@FXML public Button chooseGuestButton;
	@FXML public TextField chooseGuestTextField;
	@FXML public Button chooseRoomButton;
	@FXML public TextField chooseRoomTextField;
	@FXML public TextField priceTextField;
	@FXML public Button clearFieldsButton;
	@FXML public Button okButton;

	@FXML public RadioButton vaxjoRadioButton;
	@FXML public RadioButton kalmarRadioButton;
	
	@FXML public MenuButton monthMenuButton;
	@FXML public MenuButton yearMenuButton;
	@FXML public Button showButton;
	@FXML public GridPane reservationsGridPane;

	private ReservationModel reservationModel = new ReservationModel();
	private RoomModel roomModel = new RoomModel();
	private GuestModel guestModel = new GuestModel();

	@FXML
	public void initialize() {

		// initializeReservationModel(reservationModel);
		// initializeRoomModel(roomModel);
		Room room1 = new Room(5, RoomType.SINGLE_ROOM, RoomQuality.QUALITY_ONE, Location.VAXJO, false, false);
		Room room2 = new Room(8, RoomType.SINGLE_ROOM, RoomQuality.QUALITY_ONE, Location.VAXJO, false, false);
		Room room3 = new Room(3, RoomType.SINGLE_ROOM, RoomQuality.QUALITY_ONE, Location.KALMAR, false, false);
		ArrayList<Room> rooms = new ArrayList<Room>();
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		roomModel.setRooms(rooms);

		// Initialization and onAction for the monthMenuButton.
		String month = monthMenuButton.getItems().get(LocalDate.now().getMonthValue() - 1).getText();
		monthMenuButton.setText(month);
		for (int i = 0; i < monthMenuButton.getItems().size(); i++) {
			MenuItem item = monthMenuButton.getItems().get(i);
			item.setOnAction(c -> {
				monthMenuButton.setText(item.getText());
			});
		}

		// Initialization and onAction for the yearMenuButton.
		int year = LocalDate.now().getYear();
		yearMenuButton.setText(String.valueOf(year));
		for (int i = 0; i < 10; i++) {
			MenuItem y = new MenuItem();
			y.setText(String.valueOf(year - 5 + i));
			yearMenuButton.getItems().add(y);
			y.setOnAction(c -> {
				yearMenuButton.setText(y.getText());
			});
		}

		// Clears all fields.
		clearFieldsButton.setOnAction(c -> {
			arrivalDatePicker.setValue(null);
			departureDatePicker.setValue(null);
			chooseGuestTextField.setText("");
			guestModel.getCurrentGuest().set(null);
			chooseRoomTextField.setText("");
			roomModel.getCurrentRoom().set(null);
			priceTextField.setText("");
		});

		setColumns();
		setRows();
		setReservationOnClicked();

		showButton.setOnAction(c -> {
			setColumns();
			setRows();
			setReservationOnClicked();
		});

		chooseRoomButton.setOnAction(c -> {
			LocalDate arrival = arrivalDatePicker.getValue();
			LocalDate departure = departureDatePicker.getValue();
			RoomType roomType = getSelectedRoomType();
			RoomQuality roomQuality = getSelectedRoomQuality();
			boolean smoker = isSmoking();
			
			if(arrival != null && departure != null && arrival.isBefore(departure)){
				Parent root;
				URI locationURI = new File("src/main/resources/" + SELECT_ROOM_WINDOW).toURI();
				SelectRoomWindowController selectRoomWindowController;

				try {
					FXMLLoader loader = new FXMLLoader(locationURI.toURL());
					root = loader.load();

					Stage stage = new Stage();
					stage.setTitle("Select Room");
					stage.setScene(new Scene(root));
					
					roomModel.setArrival(arrival);
					roomModel.setDeparture(departure);
					roomModel.setRoomType(roomType);
					roomModel.setRoomQuality(roomQuality);
					roomModel.setSmoker(smoker);

					selectRoomWindowController = loader.<SelectRoomWindowController>getController();
					selectRoomWindowController.initializeRoomModel(this.roomModel);
					selectRoomWindowController.initializeReservationModel(this.reservationModel);
					stage.showAndWait();
					if(roomModel.getCurrentRoom().get() != null){
						Room r = roomModel.getCurrentRoom().get();
						chooseRoomTextField.setText(String.valueOf(r.getRoomNumber()) + ", " + r.getLocation());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Room Selection Error");
				alert.setContentText("Arrival and Departure must be valid!");
				alert.showAndWait();
			}
		});

		chooseGuestButton.setOnAction(c -> {
			try {	
				Parent root;
				URI locationURI = new File("src/main/resources/" + 	SEARCH_GUEST_WINDOW).toURI();
				SearchGuestWindowController searchGuestWindowController; 
				
				FXMLLoader loader = new FXMLLoader(locationURI.toURL());
				root = loader.load();

				Stage stage = new Stage();
				stage.setTitle("Select Room");
				stage.setScene(new Scene(root));

				searchGuestWindowController = loader.<SearchGuestWindowController>getController();
				searchGuestWindowController.initializeGuestModel(this.guestModel);
				stage.showAndWait();
				if(guestModel.getCurrentGuest().get() != null){
					Guest g = guestModel.getCurrentGuest().get();
					chooseGuestTextField.setText(g.getFirstName() + " " + g.getLastName());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		okButton.setOnAction(c -> {
			LocalDate arrival = arrivalDatePicker.getValue();
			LocalDate departure = departureDatePicker.getValue();

			double price;

			if (!isValidReservationInfo(arrival, departure)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Reservation Error");
				alert.setContentText("The reservatoin information was not sufficient!");
				alert.showAndWait();
			} else {
				price = Double.parseDouble(priceTextField.getText());
				Reservation r = new Reservation(arrival, departure, roomModel.getCurrentRoom().get(), price, guestModel.getCurrentGuest().get());
				
				if(reservationModel.getReservations().size() == 0){
					reservationModel.getReservations().add(r);
				} else {
					if(isReservationOverlapping(r)){
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Dialog");
						alert.setHeaderText("Reservation Error");
						alert.setContentText("Reservations may not overlap with other reservations!");
						alert.showAndWait();
					} else {
						reservationModel.getReservations().add(r);
					}
				}
				setColumns();
				setRows();
				setReservationOnClicked();
			}
		});
	}

	private void setColumns() {
		int daysInMonth = daysOfSelectedMonth();
		reservationsGridPane.getChildren().clear();
		reservationsGridPane.getColumnConstraints().get(0).setPrefWidth(30);
		for (int i = 0; i < daysInMonth + 1; i++) {
			StackPane p = new StackPane();
			Rectangle r = new Rectangle(p.getTranslateX(), p.getTranslateY(), 30, 30);
			r.setFill(Color.WHITE);
			Color c = new Color(0, 0, 0, 0.5);
			r.setStroke(c);
			Text t = new Text("");
			if (i > 0) {
				t = new Text(String.valueOf(i));
			}
			p.getChildren().addAll(r, t);
			reservationsGridPane.add(p, i, 0);
		}
	}

	private void setRows() {
		for (int i = 0; i < roomModel.getRooms().size(); i++) {
			if (roomModel.getRooms().get(i).getLocation() == getSelectedLocation()) {
				int roomNumber = roomModel.getRooms().get(i).getRoomNumber();
				for (int j = 0; j < daysOfSelectedMonth() + 1; j++) {
					StackPane p = new StackPane();
					Rectangle r = new Rectangle(p.getTranslateX(), p.getTranslateY(), 30, 30);
					r.setFill(Color.WHITE);
					Color c = new Color(0, 0, 0, 0.5);
					r.setStroke(c);
					p.getChildren().add(r);

					if (j == 0) {
						Text t = new Text(String.valueOf(roomNumber));
						p.getChildren().add(t);
					}

					// Mark all reservations in the calendar.
					for (int rIndex = 0; rIndex < reservationModel.getReservations().size(); rIndex++) {
						Reservation res = reservationModel.getReservations().get(rIndex);
						if (res.getRoom().getRoomNumber() == roomNumber && isWithinReservationTime(res, j)
								&& res.getRoom().getLocation() == getSelectedLocation()) {
							r.setFill(Color.LIGHTGREEN);
						}
					}
					reservationsGridPane.add(p, j, i + 1);
				}
			}
		}
	}

	private void setReservationOnClicked() {
		// onMouseClicked for each reservation in the gridpane.
		for (int i = 0; i < reservationsGridPane.getChildren().size(); i++) {
			StackPane node = (StackPane) reservationsGridPane.getChildren().get(i);
			node.setOnMouseClicked(c -> {
				int day = GridPane.getColumnIndex(node);
				int room = GridPane.getRowIndex(node);

				if (day > 0 && room > 0) {
					int roomNumber = roomModel.getRooms().get(room - 1).getRoomNumber();

					// Check which reservation matches the node.
					for (int rIndex = 0; rIndex < reservationModel.getReservations().size(); rIndex++) {
						Reservation res = reservationModel.getReservations().get(rIndex);
						if (res.getRoom().getRoomNumber() == roomNumber && isWithinReservationTime(res, day)
								&& res.getRoom().getLocation() == getSelectedLocation()) {
							System.out.println(res.getStartDate() + ", " + res.getEndDate() + ", "
									+ res.getRoom().getRoomNumber());
						}
					}
				}
			});
		}
	}

	private boolean isReservationOverlapping(Reservation r) {
		boolean isOverlapping = false;
		for (int i = 0; i < reservationModel.getReservations().size(); i++) {
			Reservation old = reservationModel.getReservations().get(i);
			LocalDate otherStart = old.getStartDate();
			LocalDate otherEnd = old.getEndDate();
			
			// Check if room is valid
			if (r.getRoom().getRoomNumber() == old.getRoom().getRoomNumber()
					&& r.getRoom().getLocation() == old.getRoom().getLocation()) {
				isOverlapping = true;
				// Check if date is valid
				if (!(r.getStartDate().isBefore(otherStart) && r.getEndDate().isBefore(otherStart.plusDays(1))
						|| r.getStartDate().isAfter(otherEnd.minusDays(1)))) {

					isOverlapping = true;
					break;
				} else {
					isOverlapping = false;
				}
			} else {
				isOverlapping = false;
			}
		}
		return isOverlapping;
	}

	private boolean isValidReservationInfo(LocalDate arrival, LocalDate departure) {
		if (arrival == null || departure == null || guestModel.getCurrentGuest().get() == null
				|| roomModel.getCurrentRoom().get() == null || !isValidPrice(priceTextField.getText())
				|| !arrival.isBefore(departure)) {
			return false;
		}
		return true;
	}

	private boolean isSmoking() {
		if (smokingRadioButton.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	private RoomQuality getSelectedRoomQuality() {
		RoomQuality roomQuality = null;
		RadioButton radioButton2 = (RadioButton) Group3.getSelectedToggle();
		if (radioButton2.getText().equals("Quality 1")) {
			roomQuality = RoomQuality.QUALITY_ONE;
		} else if (radioButton2.getText().equals("Quality 2")) {
			roomQuality = RoomQuality.QUALITY_TWO;
		} else if (radioButton2.getText().equals("Quality 3")) {
			roomQuality = RoomQuality.QUALITY_THREE;
		} else if (radioButton2.getText().equals("Quality 4")) {
			roomQuality = RoomQuality.QUALITY_FOUR;
		} else if (radioButton2.getText().equals("Quality 5")) {
			roomQuality = RoomQuality.QUALITY_FIVE;
		}
		return roomQuality;
	}

	private RoomType getSelectedRoomType() {
		RoomType roomType1 = null;
		RadioButton radioButton1 = (RadioButton) Group.getSelectedToggle();
		if (radioButton1.getText().equals("Single room")) {
			roomType1 = RoomType.SINGLE_ROOM;
		} else if (radioButton1.getText().equals("Double room")) {
			roomType1 = RoomType.DOUBLE_ROOM;
		} else if (radioButton1.getText().equals("Triple room")) {
			roomType1 = RoomType.TRIPLE_ROOM;
		} else if (radioButton1.getText().equals("Four-bed room")) {
			roomType1 = RoomType.FOUR_BED_ROOM;
		} else if (radioButton1.getText().equals("Appartment/Suite")) {
			roomType1 = RoomType.APARTMENT_SUITE;
		}
		return roomType1;
	}

	private boolean isValidPrice(String string) {
		if (string.equals("")) {
			return false;
		}
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private int daysOfSelectedMonth() {
		int m = getSelectedMonth();
		int y = Integer.parseInt(yearMenuButton.getText());
		YearMonth yearMonthObject = YearMonth.of(y, m);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		return daysInMonth;
	}

	private Location getSelectedLocation() {
		if (vaxjoRadioButton.isSelected()) {
			return Location.VAXJO;
		}
		return Location.KALMAR;
	}

	private int getSelectedMonth() {
		int m = 0;
		for (int i = 0; i < monthMenuButton.getItems().size(); i++) {
			if (monthMenuButton.getItems().get(i).getText().equals(monthMenuButton.getText())) {
				m = i + 1;
			}
		}
		return m;
	}

	private boolean isWithinReservationTime(Reservation res, int day) {

		if (day == 0) {
			return false;
		}

		String dateStr = yearMenuButton.getText() + "-";
		if (getSelectedMonth() < 10) {
			dateStr += "0" + getSelectedMonth();
		} else {
			dateStr += getSelectedMonth();
		}
		dateStr += "-";
		if (day < 10) {
			dateStr += "0" + day;
		} else {
			dateStr += day;
		}

		LocalDate date = LocalDate.parse(dateStr);
		return !(date.isBefore(res.getStartDate()) || date.isAfter(res.getEndDate().minusDays(1)));
	}

	public void initializeReservationModel(ReservationModel reservationModel) {
		if (this.reservationModel != null) {
			throw new IllegalStateException("Model can only be initialize once");
		}
		this.reservationModel = reservationModel;
	}

	public void initializeRoomModel(RoomModel roomModel) {
		if (this.roomModel != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		this.roomModel = roomModel;
	}
}
