import DauctionApp.*; 
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.io.*; 
import java.util.*;
public class DauctionClient {

  public static void main(String args[]) {
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

      int choice=0;
    
  	while(choice!=4) // while not quit
  	{
	// Menu Items list
      System.out.println("\t.......Client Options Menu.........\n");
      System.out.println("\t1. View Auction Status\n"); 
      System.out.println("\t2. bid\n"); 
      System.out.println("\t3. view bid Status\n"); 
      System.out.println("\t4. Quit\n"); 
           
           Scanner c=new Scanner(System.in);
           
           System.out.println("Welcome to the auction system:");
           
           System.out.println("Please choose an option 1, 2, 3 or 4 : ");
        choice = c.nextInt();
  

	 if (choice==1)  // To view Auction status
        {
	DauctionApp.DauctionPackage.AuctionStatus status = 
   		new DauctionApp.DauctionPackage.AuctionStatus(); 
      status = impl.view_auction_status();
      String message="User with id: " +status.user+ "\n for Item: " +status.item+
           " with Price: " +status.currentPrice+ "\n is the highest bidder\n";
      System.out.println (message); 
        }
	
       else if (choice==2)  // To bid
       {
        try {
        
        Scanner a=new Scanner(System.in);
				System.out.println("Enter your ID:");
				String userId = a.nextLine();
                System.out.println("Enter your bid Price :");
                String strOffer = a.nextLine();
	float offer = Float.parseFloat(strOffer); 
      boolean bid_now = impl.bid(userId, offer);
      if (bid_now==true) System.out.println("Bid is placed Successfully! \n");
      else System.out.println("Unsuccessful bid. Please choose a higher bid Price! \n");
           }catch (Exception e){System.out.println(e);}
       }

      else if (choice==3) // To view client's bid status
       {
        try {
        
        Scanner e = new Scanner(System.in);
      System.out.println("Enter your ID:");
		String id = e.nextLine();	 
      String msg = impl.view_bid_status(id);
      System.out.println(msg + "\n");
          
          }catch (Exception e){System.out.println(e);}
       }

  	}//end of while
    
    } catch (Exception e) {
        System.out.println("ERROR : " + e) ;
        e.printStackTrace(System.out);
    }
  }
}
