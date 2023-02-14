package database.api.rent;

import java.util.List;

import entities.rent.Rent;
import entities.rent.RentInfo;
import entities.station.Station;
import javafx.collections.ObservableList;

public interface RentInterface {

  public Rent getRent();  // get order detail by cust id
  public int getRentId(int bikeId); // get orderid by cust id
  public int getBikeId(int id);
  public ObservableList<RentInfo> getAllRents(); 
  public Rent getRentbyID(int id);
}
