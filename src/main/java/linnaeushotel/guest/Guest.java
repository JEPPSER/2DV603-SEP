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
	
	// Guest's private information
	private String privateAddress;
	private String privatePhone;
	private String privateMobile;
	private String privateFax;
	private String privateEmail;
	
	// Guest's business information
	private String businessAddress;
	private String businessPhone;
	private String businessMobile;
	private String businessFax;
	private String businessEmail;
	
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
	 * @return the privateAddress
	 */
	public String getPrivateAddress() {
		return privateAddress;
	}

	/**
	 * @return the privatePhone
	 */
	public String getPrivatePhone() {
		return privatePhone;
	}

	/**
	 * @return the privateMobile
	 */
	public String getPrivateMobile() {
		return privateMobile;
	}

	/**
	 * @return the privateFax
	 */
	public String getPrivateFax() {
		return privateFax;
	}

	/**
	 * @return the privateEmail
	 */
	public String getPrivateEmail() {
		return privateEmail;
	}

	/**
	 * @return the businessAddress
	 */
	public String getBusinessAddress() {
		return businessAddress;
	}

	/**
	 * @return the businessPhone
	 */
	public String getBusinessPhone() {
		return businessPhone;
	}

	/**
	 * @return the businessMobile
	 */
	public String getBusinessMobile() {
		return businessMobile;
	}

	/**
	 * @return the businessFax
	 */
	public String getBusinessFax() {
		return businessFax;
	}

	/**
	 * @return the businessEmail
	 */
	public String getBusinessEmail() {
		return businessEmail;
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
	 * @param privateAddress the privateAddress to set
	 */
	public void setPrivateAddress(String privateAddress) {
		this.privateAddress = privateAddress;
	}

	/**
	 * @param privatePhone the privatePhone to set
	 */
	public void setPrivatePhone(String privatePhone) {
		this.privatePhone = privatePhone;
	}

	/**
	 * @param privateMobile the privateMobile to set
	 */
	public void setPrivateMobile(String privateMobile) {
		this.privateMobile = privateMobile;
	}

	/**
	 * @param privateFax the privateFax to set
	 */
	public void setPrivateFax(String privateFax) {
		this.privateFax = privateFax;
	}

	/**
	 * @param privateEmail the privateEmail to set
	 */
	public void setPrivateEmail(String privateEmail) {
		this.privateEmail = privateEmail;
	}

	/**
	 * @param businessAddress the businessAddress to set
	 */
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	/**
	 * @param businessPhone the businessPhone to set
	 */
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	/**
	 * @param businessMobile the businessMobile to set
	 */
	public void setBusinessMobile(String businessMobile) {
		this.businessMobile = businessMobile;
	}

	/**
	 * @param businessFax the businessFax to set
	 */
	public void setBusinessFax(String businessFax) {
		this.businessFax = businessFax;
	}

	/**
	 * @param businessEmail the businessEmail to set
	 */
	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
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
