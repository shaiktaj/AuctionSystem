# AuctionSystem
AuctionSystem using Java Corba



The objective of this machine problem is to use CORBA to build a Distributed Auction Service, which allows the buying and
selling of individual items, using an English auction protocol (increasing price, current price visible to all parties). Optionally, other auction protocols could be supported, such as Dutch (the public price is decreased until someone bids) or some variant of sealed-bid (each interested buyer submits one bid, all bids are considered at once, prices are not public, and the bidder making the highest bid would win the object being sold for either the highest price–a first-price auction–or the second-highest–a second price.

I have created 3 Programs – 1 for AuctionServer, 1 for AuctionSeller and the other 1 for
AuctionClient.

The Server file implements the methods of the remote interface and the mechanism to start
the ORB and wait for invocation from a remote client.

It consists of 2 classes 1 is the servant and the other is the server. The servant is the
implementation of the idl file interface. Each instance is implemented by the class instance. The
servant is the subclass of the POA file which is generated by the idlj compiler. The servant
methods are just the methods in java with additional code to deal with ORB,marshalling and
arguments.

The server class has the main () method which is related to the CORBA server.

The Seller program is used by the user who ever wants to sell an item. The methods like
offer_item , view_high_bidder, and sell are only accessed by the seller.

The seller has various options like
System.out.println("\t display...\n");
System.out.println("\t1. place an item \n");
System.out.println("\t2. sell an item\n");
System.out.println("\t3. highest bidder \n");
System.out.println("\t4. Auction Status\n");
System.out.println("\t5. Quit\n");

When each option is selected it call for the method in the server program’s implementation
class. Then the computation is done and the server returns the data to the seller which is
displayed as output.

The seller chooses one of the option and waits for the highest bid, once the desired amount is
placed the seller sells the item.

The Client program is used the user who ever wants to buy an item. The Client has the following
options

1. Client Options menu...
2. View auction status 
3. Bid
4. View bid status 
5. Quit


The bidder can place a bid by choosing the bid option and check for the highest bidder. The
client can also check if he is the highest bidder or not by choosing the view bid status.
Similar to the seller the client call the methods in the server and those methods return the data
which is displayed as the output.
