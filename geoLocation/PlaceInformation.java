// Ai Quynh Nguyen
// 11/26/2017
// CSE142
// TA:Daniel Sullivan
// Assisgnment #8
//
// This program stores information about a place
// Included name, address, tag, latitude and longitude
// Return those information and then get distance between places
public class PlaceInformation{
   
   private double latitude;
   private double longitude;
   private String theName;
   private String theAddress;
   private String theTag;
   private GeoLocation theLocation;
   
   // this object take in String name, address and tag as paramaters
   // use GeoLocation object to take in the latitude and longitude
   // store those values from method inside values in class by using this.
   public PlaceInformation(String name, String address, String tag,
   double latitude, double longitude){
      theName = name;
      theAddress = address;
      theTag = tag;
      this.latitude = latitude;
      this.longitude = longitude;
      theLocation = new GeoLocation(latitude, longitude);
   }
   
   // Returns the name of the place
   public String getName(){
      return theName;
   }
   
   // Returns the address of the place
   public String getAddress(){
      return theAddress;
   }
   
   // Returns the tag of the place
   public String getTag(){
      return theTag;
   }
   
   // Returns the name, then latitude and longitude
   public String toString(){
      return theName + "(latitude: " + latitude + ", longitude: " + longitude + ")";
   }
   
   // return the location of the place
   public GeoLocation getLocation(){
      return theLocation;
   }
   
   // Use distanceFrom method in GeoLocation claa
   // Returns the distance from that location to other locations(London, Kane)
   public double distanceFrom(GeoLocation spot){
      return theLocation.distanceFrom(spot);
   }
}