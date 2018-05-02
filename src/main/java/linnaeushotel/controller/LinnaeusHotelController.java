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
	String MAIN_WINDOW 			= LAYOUTS_PATH + "";
	String RESERVATION_WINDOW 	= LAYOUTS_PATH + "";
	String GUEST_WINDOW 		= LAYOUTS_PATH + "";
	
	StageManager stageManager = StageManager.getInstance();
}
