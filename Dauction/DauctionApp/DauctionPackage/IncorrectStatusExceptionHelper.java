package DauctionApp.DauctionPackage;


/**
* DauctionApp/DauctionPackage/IncorrectStatusExceptionHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Dauction.idl
* Tuesday, April 5, 2016 2:04:37 PM CDT
*/

abstract public class IncorrectStatusExceptionHelper
{
  private static String  _id = "IDL:DauctionApp/Dauction/IncorrectStatusException:1.0";

  public static void insert (org.omg.CORBA.Any a, DauctionApp.DauctionPackage.IncorrectStatusException that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static DauctionApp.DauctionPackage.IncorrectStatusException extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [1];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "description",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (DauctionApp.DauctionPackage.IncorrectStatusExceptionHelper.id (), "IncorrectStatusException", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static DauctionApp.DauctionPackage.IncorrectStatusException read (org.omg.CORBA.portable.InputStream istream)
  {
    DauctionApp.DauctionPackage.IncorrectStatusException value = new DauctionApp.DauctionPackage.IncorrectStatusException ();
    // read and discard the repository ID
    istream.read_string ();
    value.description = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, DauctionApp.DauctionPackage.IncorrectStatusException value)
  {
    // write the repository ID
    ostream.write_string (id ());
    ostream.write_string (value.description);
  }

}
