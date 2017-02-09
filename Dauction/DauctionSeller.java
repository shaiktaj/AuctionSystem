import DauctionApp.*; 
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.io.*; 
import java.util.*;

public class DauctionSeller {
  public static void main(String args[]) {
  
  Scanner c = new Scanner(System.in);
    try {
      // Initializing the ORB
      ORB orb = ORB.init(args, null);

        // Obtaining Root Naming
      org.omg.CORBA.Object objRef = 
        orb.resolve_initial_references("NameService");

        // Using NamingContextExt for interoperable Naming Service
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        //  Object Reference is resolved in Naming
      String name = "Auction";
      Dauction impl = DauctionHelper.narrow(ncRef.resolve_str(name));

      System.out.println("Handle obtained on server object: " + impl);

      int choice=0;
    
  	while(choice!=5)
  	{
	//menu
      System.out.println("\t......Seller options display.......\n");
      System.out.println("\t1. place an item \n"); 
      System.out.println("\t2. sell an item\n"); 
      System.out.println("\t3. highest bidder \n"); 
      System.out.println("\t4. Auction Status\n"); 
      System.out.println("\t5. Quit\n");
        System.out.println (" choose an option ");

   Scanner sc = new Scanner(System.in);
   String prompt = sc.nextLine();
   
   choice = Integer.parseInt(prompt);

      if (choice==1) //place an item to sell
        {
          try 
	    {
		System.out.println("Enter Seller ID: ");
		Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
	    System.out.println("Enter Item's Name: ");

        String iName = scanner.nextLine();
      
	    System.out.println("Enter the price of an item: ");
        
        String strPrice = scanner.nextLine(); 
        
        float iPrice = Float.parseFloat(strPrice); 
        System.out.println("Seller ID is: " + id + " Item Name is: " + iName + " Price of Item is: " + iPrice);
        
        boolean success = impl.offer_item(id, iName, iPrice);
        
        if(success==true) 
        
          System.out.println("Item has been placed to sell \n");
        
        else
          
          System.out.println("you cannot put an item now\n");

        } 
        
        catch (Exception e){System.out.println(e);}
      }
 	
 	else if(choice==2) // sell an item
        
        {
 	
 	try 
 	{
		
		System.out.println("Do you want to sell the Item (y/n?) : ");
		
		Scanner scan = new Scanner(System.in);
       String prompt1 = scan.nextLine();
       
		String choice1 = prompt1.toLowerCase(); 
       if (choice1.startsWith("y")){
             try {
				 
				System.out.println( "Enter Item Name: ");
		
	   Scanner scann = new Scanner(System.in);		
       String i = scann.nextLine(); 
       System.out.println( "You have entered Item name as: " + i);
       boolean success = impl.sell(i); 
       
            } 
            catch (Exception e){System.out.println(e);}
                           }
    } catch (Exception e){System.out.println(e); System.exit(1);}
        
         }
	else if (choice==3) //view highest bidder
        {
        try {
			
			System.out.println("Enter Item Name: ");
			Scanner scanning = new Scanner(System.in);
			
       		String it = scanning.nextLine();
       		
         	String message = impl.view_high_bidder(it);
         	
         	System.out.println(message);  

            } catch (Exception e){System.out.println(e);}
         }
         
        
	 else if (choice==4) // view auction status
        {
       DauctionApp.DauctionPackage.AuctionStatus status = new DauctionApp.DauctionPackage.AuctionStatus(); 
      status = impl.view_auction_status();
      String message="User with id: " +status.user+ "\nfor Item: " +status.item+ " with bid Price: " +status.currentPrice+ "\n is the highest bidder";
      System.out.println (message); 
        }
	else if (choice==5) // To quit
        {
         System.exit(1); 
         }	
  	}
    
    } catch (Exception e) {
        System.out.println("ERROR : " + e) ;
        e.printStackTrace(System.out);
    }
  }
}



