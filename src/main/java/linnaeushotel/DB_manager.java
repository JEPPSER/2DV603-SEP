package linnaeushotel;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import linnaeushotel.guest.Guest;

public class DB_manager{
	
	private MongoClientURI uri;
	private MongoClient client;
	private DBCollection collection;
	
	public DB_manager(){
		
	}
	
	
	public void insertGuest(Guest g){

		
		try{
			
			uri= new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client= new MongoClient(uri);
			
			@SuppressWarnings("deprecation")
			DB db= client.getDB("Hotel");
			collection=db.getCollection("guest");
			
			DBObject guest = new BasicDBObject("_id",g.getId())
				.append("firstName",g.getFirstName())
				.append("lastName", g.getLastName())
				.append("Reservations", g.getReservations())
				.append("address", g.getAddress())
				.append("phone", g.getPhone())
				.append("mobile", g.getMobile())
				.append("fax", g.getFax())
				.append("email", g.getEmail())
				.append("favouriteRoom", g.getFavouriteRoom())
				.append("smoker", g.isSmoker())
				.append("birthday",g.getBirthday())
				.append("spouse", g.getSpouse())
				.append("children", g.getChildren())
				.append("company", g.getCompany());
				
			collection.insert(guest);
			client.close();
			}catch(Exception e){
				System.err.println(e);
			}	
			
	
	
		}

	public void deleteGuest(Guest g){
		
		try{
			
			uri= new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client= new MongoClient(uri);
			
			@SuppressWarnings("deprecation")
			DB db= client.getDB("Hotel");
			collection=db.getCollection("guest");
			
			DBObject query = new BasicDBObject("_id",g.getId());
			collection.remove(query);
		
			client.close();
			}catch(Exception e){
				System.err.println(e);
			}	
		
		
	
	}
	
	
	public void updateGuest(Guest g){
		
	
				try{
					
					uri= new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
					client= new MongoClient(uri);
					
					@SuppressWarnings("deprecation")
					DB db= client.getDB("Hotel");
					collection=db.getCollection("guest");
					
					
				
					DBObject guest = new BasicDBObject("_id",g.getId())
						.append("firstName",g.getFirstName())
						.append("lastName", g.getLastName())
						.append("Reservations", g.getReservations())
						.append("address", g.getAddress())
						.append("phone", g.getPhone())
						.append("mobile", g.getMobile())
						.append("fax", g.getFax())
						.append("email", g.getEmail())
						.append("favouriteRoom", g.getFavouriteRoom())
						.append("smoker", g.isSmoker())
						.append("birthday",g.getBirthday())
						.append("spouse", g.getSpouse())
						.append("children", g.getChildren())
						.append("company", g.getCompany());
					
					
					DBObject query = new BasicDBObject("_id",g.getId());
					collection.update(query, guest);
					client.close();
					
					}catch(Exception e){
						System.err.println(e);
					}	
				
			}





}
	




