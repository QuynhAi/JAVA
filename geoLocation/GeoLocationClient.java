// Ai Quynh Nguyen
// 11/26/2017
// CSE142
// TA:Daniel Sullivan
// Assisgnment #8
//
// This program is about writing code that constructs many GeoLocation
// Get latitudes and longtitudes then get the distance between locations

public class GeoLocationClient{
   public static void main(String[] args){
      
      // Use object to store the latitudes and longitudes for the three locations
      GeoLocation theStash = new GeoLocation(34.988889, -106.614444);
      GeoLocation studioABQ = new GeoLocation(34.989978, -106.614357);
      GeoLocation buildingFBI = new GeoLocation( 35.131281, -106.61263);
      
      // From oject, print out the latitudes and longitudes for each location
      System.out.println("the stash is at " + theStash.toString());
      System.out.println("ABQ studio is at " + studioABQ.toString());
      System.out.println("FBI building is at " + buildingFBI.toString());
      
      // From object, calculating the distance between two locations
      System.out.println("distance in miles between:");
      System.out.println("    stash/studio = " + theStash.distanceFrom(studioABQ)) ;
      System.out.println("    stash/fbi    = " + theStash.distanceFrom(buildingFBI)) ;
      
   }
}