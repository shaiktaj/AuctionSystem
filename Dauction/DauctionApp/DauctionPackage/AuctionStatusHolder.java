package DauctionApp.DauctionPackage;

/**
* DauctionApp/DauctionPackage/AuctionStatusHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Dauction.idl
* Tuesday, April 5, 2016 2:04:37 PM CDT
*/

public final class AuctionStatusHolder implements org.omg.CORBA.portable.Streamable
{
  public DauctionApp.DauctionPackage.AuctionStatus value = null;

  public AuctionStatusHolder ()
  {
  }

  public AuctionStatusHolder (DauctionApp.DauctionPackage.AuctionStatus initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = DauctionApp.DauctionPackage.AuctionStatusHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    DauctionApp.DauctionPackage.AuctionStatusHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return DauctionApp.DauctionPackage.AuctionStatusHelper.type ();
  }

}
