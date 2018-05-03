package linnaeushotel.guest;

import java.time.LocalDate;
import java.util.ArrayList;

import linnaeushotel.reservation.Reservation;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Guest.java
 */
public class Guest {
	
	private String company;
	private String lastName;
	private String firstName;
	private String spouse;
	private String children;
	private String citizenship;
	
	// Guest's private information
	private String address;
	private String phone;
	private String mobile;
	private String fax;
	private String email;
	
	private String favouriteRoom;
	private boolean smoker;
	private LocalDate birthday;
	
	private ArrayList<Reservation> reservations;
	
	public Guest() {		
	}
	
	//TODO: Return actual bill and not void.
	public void getBill() {
		
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the spouse
	 */
	public String getSpouse() {
		return spouse;
	}

	/**
	 * @return the children
	 */
	public String getChildren() {
		return children;
	}

	/**
	 * @return the favouriteRoom
	 */
	public String getFavouriteRoom() {
		return favouriteRoom;
	}

	/**
	 * @return the smoker
	 */
	public boolean isSmoker() {
		return smoker;
	}

	/**
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * @return the reservations
	 */
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param spouse the spouse to set
	 */
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(String children) {
		this.children = children;
	}

	/**
	 * @param favouriteRoom the favouriteRoom to set
	 */
	public void setFavouriteRoom(String favouriteRoom) {
		this.favouriteRoom = favouriteRoom;
	}

	/**
	 * @param smoker the smoker to set
	 */
	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * @param reservations the reservations to set
	 */
	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}
}
