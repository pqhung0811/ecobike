package database.api.station;

import java.util.List;

import entities.station.Station;
import javafx.collections.ObservableList;

public interface StationInterface {
	public ObservableList<Station> getAllStations();
	public ObservableList<String> getStoreAvai();
	public String getStoreId(String bikeName);
	public void updateStoreReturn(int bikeId, int storeId);
}
