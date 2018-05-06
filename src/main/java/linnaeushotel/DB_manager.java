package linnaeushotel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;

import linnaeushotel.guest.Guest;
import linnaeushotel.reservation.Reservation;
import linnaeushotel.room.Location;
import linnaeushotel.room.Room;
import linnaeushotel.room.RoomQuality;
import linnaeushotel.room.RoomType;

public class DB_manager {

	private MongoClientURI uri;
	private MongoClient client;
	private DBCollection collection;

	public DB_manager() {

	}

	public void insertGuest(Guest g) {

		try {

			uri = new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client = new MongoClient(uri);

			@SuppressWarnings("deprecation")
			DB db = client.getDB("Hotel");
			collection = db.getCollection("guest");
			g.setId((int) collection.getCount() + 1);

			// fixa så du har defualt id ifrån databasen
			DBObject guest = new BasicDBObject("_id", g.getId()).append("firstName", g.getFirstName())
					.append("lastName", g.getLastName()).append("address", g.getAddress()).append("phone", g.getPhone())
					.append("mobile", g.getMobile()).append("fax", g.getFax()).append("email", g.getEmail())
					.append("favouriteRoom", g.getFavouriteRoom()).append("smoker", g.isSmoker())
					.append("birthday", g.getBirthday()).append("spouse", g.getSpouse())
					.append("children", g.getChildren()).append("company", g.getCompany())
					.append("citizenship", g.getCitizenship());

			List<BasicDBObject> reservs = new ArrayList<>();

			if (g.getReservations() != null) {
				int i = 0;
				while (i < g.getReservations().size() && g.getReservations() != null) {

					Room room = g.getReservations().get(i).getRoom();
					DBObject roomObject = new BasicDBObject("_id", room.getId())
							.append("roomNumber", room.getRoomNumber()).append("RoomType", room.getType().toString())
							.append("RoomQuality", room.getQuality().toString())
							.append("Location", room.getLocation().toString()).append("smoker", room.isSmoker())
							.append("reserved", room.isReserved());

					DBObject test = new BasicDBObject("startDate", g.getReservations().get(i).getStartDate())
							.append("endDate", g.getReservations().get(i).getEndDate())

							.append("room", roomObject).append("price", g.getReservations().get(i).getPrice())
							.append("checked in", g.getReservations().get(i).isCheckedIn());

					reservs.add((BasicDBObject) test);
					i++;
				}
			}

			guest.put("reservations", reservs);

			collection.insert(guest);
			client.close();
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public void deleteGuest(Guest g) {

		try {

			uri = new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client = new MongoClient(uri);

			@SuppressWarnings("deprecation")
			DB db = client.getDB("Hotel");
			collection = db.getCollection("guest");

			DBObject query = new BasicDBObject("_id", g.getId());
			collection.remove(query);

			client.close();
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public void updateGuest(Guest g) {

		try {

			uri = new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client = new MongoClient(uri);

			@SuppressWarnings("deprecation")
			DB db = client.getDB("Hotel");
			collection = db.getCollection("guest");

			DBObject guest = new BasicDBObject("_id", g.getId()).append("firstName", g.getFirstName())
					.append("lastName", g.getLastName())
					// .append("Reservations", g.getReservations())
					.append("address", g.getAddress()).append("phone", g.getPhone()).append("mobile", g.getMobile())
					.append("fax", g.getFax()).append("email", g.getEmail())
					.append("favouriteRoom", g.getFavouriteRoom()).append("smoker", g.isSmoker())
					.append("birthday", g.getBirthday()).append("spouse", g.getSpouse())
					.append("children", g.getChildren()).append("company", g.getCompany())
					.append("citizenship", g.getCitizenship());

			List<BasicDBObject> reservs = new ArrayList<>();

			if (g.getReservations() != null) {
				int i = 0;
				while (i < g.getReservations().size() && g.getReservations() != null) {

					Room room = g.getReservations().get(i).getRoom();
					DBObject roomObject = new BasicDBObject("_id", room.getId())
							.append("roomNumber", room.getRoomNumber()).append("RoomType", room.getType().toString())
							.append("RoomQuality", room.getQuality().toString())
							.append("Location", room.getLocation().toString()).append("smoker", room.isSmoker())
							.append("reserved", room.isReserved());

					DBObject test = new BasicDBObject("startDate", g.getReservations().get(i).getStartDate())
							.append("endDate", g.getReservations().get(i).getEndDate())

							.append("room", roomObject).append("price", g.getReservations().get(i).getPrice())
							.append("checked in", g.getReservations().get(i).isCheckedIn());

					reservs.add((BasicDBObject) test);
					i++;
				}
			}

			guest.put("reservations", reservs);
			DBObject query = new BasicDBObject("_id", g.getId());
			collection.update(query, guest);
			client.close();

		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public ArrayList<Guest> getGuests() {
		ArrayList<Guest> guestList = new ArrayList<Guest>();

		try {

			uri = new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client = new MongoClient(uri);

			@SuppressWarnings("deprecation")
			DB db = client.getDB("Hotel");
			collection = db.getCollection("guest");

			DBCursor cursor = collection.find();

			while (cursor.hasNext()) {

				DBObject obj = cursor.next();
				Guest g = new Guest();
				// fixa default id till sträng osv
				g.setId((int) obj.get("_id"));
				g.setFirstName((String) obj.get("firstName"));
				g.setLastName((String) obj.get("lastName"));
				// g.setReservations((ArrayList<Reservation>)obj.get("Reservations"));
				g.setAddress((String) obj.get("address"));
				g.setPhone((String) obj.get("phone"));
				g.setMobile((String) obj.get("mobile"));
				g.setFax((String) obj.get("fax"));
				g.setEmail((String) obj.get("email"));
				g.setFavouriteRoom((String) obj.get("favouriteRoom"));
				g.setSmoker((Boolean) obj.get("smoker"));
				Date d = (Date) obj.get("birthday");
				if (d != null) {
					g.setBirthday((LocalDate) d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				}
				g.setSpouse((String) obj.get("spose"));
				g.setChildren((String) obj.get("children"));
				g.setCompany((String) obj.get("company"));
				g.setCitizenship((String) obj.get("citizenship"));

				guestList.add(g);
			}

			client.close();
			return guestList;
		} catch (Exception e) {
			System.err.println(e);
		}

		return null;

	}

	public ArrayList<Room> getRooms() {
		ArrayList<Room> roomList = new ArrayList<Room>();

		try {

			uri = new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client = new MongoClient(uri);

			@SuppressWarnings("deprecation")
			DB db = client.getDB("Hotel");
			collection = db.getCollection("room");

			DBCursor cursor = collection.find();

			while (cursor.hasNext()) {

				DBObject obj = cursor.next();
				RoomQuality a = RoomQuality.valueOf((String) obj.get("RoomQuality"));
				RoomType b = RoomType.valueOf((String) obj.get("RoomType"));
				Location c = Location.valueOf((String) obj.get("Location"));

				Room g = new Room(((int) obj.get("roomNumber")), b, a, c, (boolean) obj.get("smoker"),
						(boolean) obj.get("reserved"));
				roomList.add(g);
			}

			client.close();
			return roomList;

		} catch (Exception e) {
			System.err.println(e);
		}

		return null;

	}

	public void updateRoom(Room g) {

		try {

			uri = new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client = new MongoClient(uri);
			boolean reservationStatus;
			@SuppressWarnings("deprecation")
			DB db = client.getDB("Hotel");
			collection = db.getCollection("room");

			if (g.isReserved())
				reservationStatus = false;
			else
				reservationStatus = true;

			DBObject room = new BasicDBObject("roomNumber", g.getRoomNumber())
					.append("RoomType", g.getType().toString()).append("RoomQuality", g.getQuality().toString())
					.append("Location", g.getLocation().toString()).append("smoker", g.isSmoker())
					.append("reserved", reservationStatus);

			DBObject query = new BasicDBObject("roomNumber", g.getRoomNumber());
			collection.update(query, room);
			client.close();

		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public void insertReservation(Reservation g) {

		try {

			uri = new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client = new MongoClient(uri);

			@SuppressWarnings("deprecation")
			DB db = client.getDB("Hotel");
			collection = db.getCollection("reservations");
			DBObject room = new BasicDBObject("roomNumber", g.getRoom().getRoomNumber())
					.append("RoomType", g.getRoom().getType().toString())
					.append("RoomQuality", g.getRoom().getQuality().toString())
					.append("Location", g.getRoom().getLocation().toString()).append("smoker", g.getRoom().isSmoker())
					.append("reserved", g.getRoom().isReserved());
			DBObject guest = new BasicDBObject("_id", g.getGuest().getId())
					.append("firstName", g.getGuest().getFirstName()).append("lastName", g.getGuest().getLastName())
					// .append("Reservations",g.getGuest().getReservations())
					.append("address", g.getGuest().getAddress()).append("phone", g.getGuest().getPhone())
					.append("mobile", g.getGuest().getMobile()).append("fax", g.getGuest().getFax())
					.append("email", g.getGuest().getEmail()).append("favouriteRoom", g.getGuest().getFavouriteRoom())
					.append("smoker", g.getGuest().isSmoker()).append("birthday", g.getGuest().getBirthday())
					.append("spouse", g.getGuest().getSpouse()).append("children", g.getGuest().getChildren())
					.append("company", g.getGuest().getCompany()).append("citizenship", g.getGuest().getCitizenship());

			DBObject reservation = new BasicDBObject("startDate", g.getStartDate()).append("endDate", g.getEndDate())

					.append("room", room).append("price", g.getPrice()).append("guest", guest);

			collection.insert(reservation);

			client.close();
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public ArrayList<Reservation> getReservations() {
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

		try {

			uri = new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client = new MongoClient(uri);

			@SuppressWarnings("deprecation")
			DB db = client.getDB("Hotel");
			collection = db.getCollection("reservations");

			DBCursor cursor = collection.find();

			while (cursor.hasNext()) {

				DBObject obj = cursor.next();
				Date sd = (Date) obj.get("startDate");
				Date ed = (Date) obj.get("endDate");

				RoomType b = RoomType.valueOf((String) ((DBObject) obj.get("room")).get("RoomType"));
				RoomQuality a = RoomQuality.valueOf((String) ((DBObject) obj.get("room")).get("RoomQuality"));
				Location c = Location.valueOf((String) ((DBObject) obj.get("room")).get("Location"));
				Room g = new Room(((int) ((DBObject) obj.get("room")).get("roomNumber")), b, a, c,
						(boolean) ((DBObject) obj.get("room")).get("smoker"),
						(boolean) ((DBObject) obj.get("room")).get("reserved"));

				Date birthday = (Date) (((DBObject) obj.get("guest")).get("birthday"));

				// if birthday=null just assign it some random date
				if (birthday == null)
					birthday = (Date) obj.get("endDate");

				Guest guest = new Guest(((int) ((DBObject) obj.get("guest")).get("_id")),
						((String) ((DBObject) obj.get("guest")).get("company")),
						((String) ((DBObject) obj.get("guest")).get("lastName")),
						((String) ((DBObject) obj.get("guest")).get("firstName")),
						((String) ((DBObject) obj.get("guest")).get("spouse")),
						((String) ((DBObject) obj.get("guest")).get("children")),
						((String) ((DBObject) obj.get("guest")).get("citizenship")),
						((String) ((DBObject) obj.get("guest")).get("address")),
						((String) ((DBObject) obj.get("guest")).get("phone")),
						((String) ((DBObject) obj.get("guest")).get("mobile")),
						((String) ((DBObject) obj.get("guest")).get("fax")),
						((String) ((DBObject) obj.get("guest")).get("email")),
						((String) ((DBObject) obj.get("guest")).get("favouriteRoom")),
						((boolean) ((DBObject) obj.get("guest")).get("smoker")),
						birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

				Reservation r = new Reservation((LocalDate) sd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						(LocalDate) ed.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), g,
						(double) obj.get("price"), guest);

				reservationList.add(r);
			}

			client.close();
			return reservationList;

		} catch (Exception e) {
			System.err.println(e);
		}

		return null;

	}

	public void updateReservation(Reservation g) {

		// wip kommer se ut som update guest

	}

	public void deleteReservation(Reservation g) {

		// wip kommer se ut som delete guest

	}
}
