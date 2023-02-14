package entities.bike;

public class BikeFactory {
	  public static Bike getBike(String type) {
		    switch (type) {
		      case "electricbike":
		        return new ElectricBike();

		      case "bike":
		        return new StandardBike();
                    
		      case "twinbike":
		        return new TwinBike();
		       default:
		    	   return null;
                
		    }
	   }
}
