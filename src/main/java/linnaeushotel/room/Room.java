package linnaeushotel.room;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Room.java
 */
public class Room {
	
	private int roomNumber;
	
	private RoomType type;
	private RoomQuality quality;
	private Location location;
	
	private boolean smoker;
	private boolean reserved;
	
	public Room(int roomNumber, RoomType type, RoomQuality quality, Location location, boolean smoker, boolean reserved) {
		this.roomNumber = roomNumber;
		this.type = type;
		this.quality = quality;
		this.location = location;
		this.smoker = smoker;
		this.reserved = reserved;
	}

	/**
	 * @return the roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @return the type
	 */
	public RoomType getType() {
		return type;
	}

	/**
	 * @return the quality
	 */
	public RoomQuality getQuality() {
		return quality;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return the smoker
	 */
	public boolean isSmoker() {
		return smoker;
	}

	/**
	 * @return the reserved
	 */
	public boolean isReserved() {
		return reserved;
	}

	/**
	 * @param reserved the reserved to set
	 */
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
}
