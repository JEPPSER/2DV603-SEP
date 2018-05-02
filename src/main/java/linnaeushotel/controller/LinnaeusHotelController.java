package linnaeushotel.controller;

import linnaeushotel.StageManager;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name LinnaeusHotelController.java
 */
public interface LinnaeusHotelController {
	
	String LAYOUTS_PATH = "/view/";

	String ROOT_LAYOUT 			= LAYOUTS_PATH + "";
	String MAIN_WINDOW 			= LAYOUTS_PATH + "MainWindow.fxml";
	String RESERVATION_WINDOW 	= LAYOUTS_PATH + "ReservationWindow.fxml";
	String GUEST_WINDOW 		= LAYOUTS_PATH + "GuestWindow.fxml";
	String ADDITIONAL_DATA_WINDOW = LAYOUTS_PATH + "AdditionalDataWindow.fxml";
	String SEARCH_GUEST_WINDOW = LAYOUTS_PATH + "SearchGuestWindow.fxml";
	String SELECT_ROOM_WINDOW = LAYOUTS_PATH + "SelectRoomWindow.fxml";
	String VIEW_SINGLE_RESERVATION_WINDOW = LAYOUTS_PATH + "ViewSingleReservationWindow.fxml";
	
	StageManager stageManager = StageManager.getInstance();
}
