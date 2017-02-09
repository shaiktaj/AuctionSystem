import DauctionApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.*;

class DauctionImpl extends DauctionPOA {
  private ORB orb;
  private Hashtable<String, Vector<Float>> list; // list holds item as key, value is a vector of all bid prices for this item
  private Hashtable userdata;
  private float price; 
  private String bidder_name;
  private String itemname;

    public DauctionImpl(ORB orb) {
    this.orb = orb;
      list = new Hashtable<>();
    
     userdata = new Hashtable();
     price = (float) 0.0;
     bidder_name = "No bidder";
     itemname = "";   
  }
  
 Vector <Float> vector = new Vector<>();
  public boolean offer_item(String seller_id, String item, float init_price) throws DauctionApp.DauctionPackage.IncorrectOfferException{
  try{
  
  System.out.println("Seller's id = " + seller_id + " placed an item: " + item + " at a Price of:" + init_price);
  vector.add(new Float(seller_id));
  vector.add(init_price);
  list.put(item, vector); // adding the item placed, to the list
  price = init_price;
  itemname = item; 
  
      } catch (Exception e){System.out.println(e); System.exit(1);}
return true; 
} 
  
public boolean sell (String item_name) throws DauctionApp.DauctionPackage.IncorrectSellException
  {
  System.out.println("Item name is: " + item_name);
     Vector<Float> vec1 = list.get(item_name); // obtaining the bid price vecotor for the item to sell
	String sellerID = vec1.elementAt(0).toString(); 
	         
      System.out.println("Seller ID: " + sellerID +  " sold the item "
          + itemname + " at Price of : " +price);

      // Since the item is sold, we need to delete it from our list and userdata
             list.remove(item_name);
             userdata.remove(price);
             userdata.remove(bidder_name);

             itemname = "";
             price = (float) 0.0;
             bidder_name = "No bidder! ";
             return true;                            
 } //end of sell function

public String view_high_bidder(String item_name) throws DauctionApp.DauctionPackage.IncorrectSellException
  {
   System.out.println(list);
   System.out.println("item name is " + item_name);
   
    System.out.println(list.get(item_name));
    Vector<Float> vec1 = list.get(item_name); // obtaing the vector of bid prices
	String sellerID = vec1.elementAt(0).toString(); // get seller id
 	float currentPrice = vec1.elementAt(1); // obtain the bid price
	String message; 
   
   Vector vec2 = (Vector) userdata.get(bidder_name);
      if(vec2 == null) 
        {message= "No bidder yet/n";}
      else {   
  	String userID = (String)vec2.elementAt(0); 
  	message = "User with ID: " +bidder_name+ " is the highest bidder of the Item: " +itemname+ " at a bid Price of: "+price ;
            }  
 return message;
} //end of viewHighBidder function


public boolean bid (String user_id, float offer_price) throws DauctionApp.DauctionPackage.IncorrectBidException
  {
   	Vector user = new Vector(2);
      if(list.size() == 0) return false; // if no items are placed by seller, return false

        Vector<Float> vec = list.get(itemname); // else go for bidding
     	user.addElement(itemname); 
  	user.addElement(new Float(offer_price)); 
  	userdata.put(user_id, user);

 	if(offer_price >= price)
 	{  // Update the current highest price
  	  vec.add(1, offer_price); 
      price = offer_price;
      bidder_name = user_id; 
  	return true; 
      } 
 	else return false;   
  } //end of bid function


public String view_bid_status(String user_id) throws DauctionApp.DauctionPackage.IncorrectBidException
  {
 
 Vector vec = (Vector) userdata.get(user_id);
 String message; 

 if(vec == null) message= "No such user id on record!\n"; // if no such user is on records
 String item_name = (String)vec.elementAt(0); 
 float your_Price = ((Float) vec.elementAt(1)).floatValue();

 Vector<Float> vec1 = list.get(item_name);
 String sellerID = vec1.elementAt(0).toString();      
float current_Price = vec1.elementAt(1); 
	
  if (current_Price == your_Price) // You are the highest bidder
   	message = "Congratulations! You (User id: " +user_id+ ") are the highest bidder for Item: " +item_name+
        " at a bid Price of: "+current_Price+". Keep going!\n" ;

  else    // Some body is on the lead
message = "Sorry! You (User id: " +user_id+ ") are not the highest bidder for Item: " +item_name+
          ".\nThe highest bid Price for this item is : "+current_Price+ ".\nYour current bid price is : "+your_Price+"\n" ;
  
 return message;
} 
//end of view_bid_Status function

public DauctionApp.DauctionPackage.AuctionStatus view_auction_status () throws DauctionApp.DauctionPackage.IncorrectStatusException
  {
 DauctionApp.DauctionPackage.AuctionStatus status = new DauctionApp.DauctionPackage.AuctionStatus();
 
  System.out.println("The item name in view Auction Status is : " + itemname);
  
  if(list.size()==0)  // No items are placed by server (or) All items have been sold
  {
  	status.user = "Sorry No auction"; 
 	status.item = "Sorry no Item"; 
 	status.currentPrice = (float) 0.0;  
	return status; 
 
  }
  else // If there are items currently in bid
	{
	
 	status.user = bidder_name; 
 	status.item = itemname; 
 	status.currentPrice = price;  
	return status; 
	}  
  }
}  //end of viewAuctionStatus function



public class DauctionServer {

  public static void main(String args[]) {
    try{
      // Initializing the ORB
      ORB orb = ORB.init(args, null);

      // registering with the ORB
      DauctionImpl impl = new DauctionImpl(orb);

      // get reference to rootpoa & activate the POAManager
      POA rootpoa = POAHelper.narrow(
        orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      // get object reference from the Seller
      org.omg.CORBA.Object ref = 
        rootpoa.servant_to_reference(impl);
      Dauction href = DauctionHelper.narrow(ref);

      org.omg.CORBA.Object objRef = 
        orb.resolve_initial_references("NameService");

      //  Obtaining Root Naming
      NamingContextExt ncRef = 
        NamingContextExtHelper.narrow(objRef);

      // The Object Reference is bound to Naming
      String name = "Auction";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("The Server is ready ....");

      // obtaining invocation from client
      orb.run();
    } catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
    }
    System.out.println("Exiting Server....");
  }
}



 