package linnaeushotel.reservation;

import java.time.LocalDate;

import linnaeushotel.guest.Guest;
import linnaeushotel.room.Room;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Reservation.java
 */
public class Reservation {
	
	private LocalDate startDate;
	private LocalDate endDate;
	private Guest guest;	
	private Room room;
	private boolean checkedIn;
	private int id;
	
	private double price;
	
	public Reservation(LocalDate startDate, LocalDate endDate, Room room, double price, Guest guest) {
		if (price < 0) {
			throw new IllegalArgumentException("Negative price");
		}
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.room = room;
		this.checkedIn = false;
		this.guest = guest;
		this.price = price;
	}
	
	/**
	 * Checks if the reservation overlaps another reservation or potential reservation.
	 * 
	 * @param arrival
	 * @param departure
	 * @param room
	 * @return boolean
	 */
	public boolean isOverlapping(LocalDate arrival, LocalDate departure, Room room){
		// Check if room is valid
		if(room.getLocation() == this.room.getLocation() && room.getRoomNumber() == this.room.getRoomNumber()){
			// Check if date is valid
			if (!(this.startDate.isBefore(arrival) && this.endDate.isBefore(arrival.plusDays(1))
					|| this.startDate.isAfter(departure.minusDays(1)))) {
				return true;
			}
		}
		return false;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public Guest getGuest(){
		return guest;
	}
	
	public void setGuest(Guest guest){
		this.guest = guest;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * @return the checkedIn
	 */
	public boolean isCheckedIn() {
		return checkedIn;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @param checkedIn the checkedIn to set
	 */
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
