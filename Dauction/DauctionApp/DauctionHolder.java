package DauctionApp;

/**
* DauctionApp/DauctionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Dauction.idl
* Tuesday, April 5, 2016 2:04:37 PM CDT
*/

public final class DauctionHolder implements org.omg.CORBA.portable.Streamable
{
  public DauctionApp.Dauction value = null;

  public DauctionHolder ()
  {
  }

  public DauctionHolder (DauctionApp.Dauction initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = DauctionApp.DauctionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    DauctionApp.DauctionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return DauctionApp.DauctionHelper.type ();
  }

}
