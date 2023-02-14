package database.api.bike;

import java.util.List;

import entities.bike.Bike;
import javafx.collections.ObservableList;

public interface BikeInterface {
	  public ObservableList<Bike> getListFromDB(String Station);
	  public ObservableList<Bike> getAllBike();
	  public Bike getBikeFromDB(String bikeName);
	  public Bike getBikeById(String id);
	  public String getBikeType(String id);
	  public Integer getIdByName(String name);
	  public boolean checkBikeRent(String bikeId);
}
