package database.api.station;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.connection.Context;
import entities.station.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class StationAPI implements StationInterface {
	
	@Override
	public ObservableList<Station> getAllStations() {
		ObservableList<Station> listStations = FXCollections.observableArrayList();
		
		try {

			Connection connect = Context.getConnection();
			// can liet ke so xe dang thue
			PreparedStatement ps = connect.prepareStatement("select s.stationName, s.address, s.area, SUM(CASE WHEN b.type = 'Bike' THEN 1 ELSE 0 END) AS Bike, SUM(CASE WHEN b.type = 'ElectricBike' THEN 1 ELSE 0 END) AS ElectricBike, SUM(CASE WHEN b.type = 'TwinBike' THEN 1 ELSE 0 END) AS TwinBike ,s.emptyDock, s.id from bike b inner join station s on b.stationID = s.id group by b.stationID");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Station station = new Station();
			
				station.setStationName(rs.getString(1));
				station.setAddress(rs.getString(2));
				station.setArea(Double.valueOf(rs.getString(3)));
				station.setBike(Integer.valueOf(rs.getString(4)));
				station.setElectricBike(Integer.valueOf(rs.getString(5)));
				station.setTwinBike(Integer.valueOf(rs.getString(6)));
				station.setEmptyDock(Integer.valueOf(rs.getString(7)));
		
				station.setId(Integer.valueOf(rs.getString(7)));
				File file = new File("src/view/images/" + station.getId() + ".jpeg");
				Image image = new Image(file.toURI().toString());
				station.setStation(image);
				listStations.add(station);
			}
			connect.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return listStations;
	}
	
	@Override
	public ObservableList<String> getStoreAvai() {
		// TODO Auto-generated method stub

		ObservableList<String> storeList = FXCollections.observableArrayList();

		try {

			Connection con = Context.getConnection();
			// can liet ke so xe dang thue
			PreparedStatement ps = con.prepareStatement("SELECT stationName FROM station s where emptyDock != 0");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				storeList.add(rs.getString(1));
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return storeList;
	}

	@Override
	public void updateStoreReturn(int bikeId, int storeId) {
		try {
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con.prepareStatement("SELECT updateReturn(?,?)");
			ps.setString(1, Integer.toString(bikeId));
			ps.setString(2, Integer.toString(storeId));
			ps.execute();
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	@Override
	public String getStoreId(String stationName) {
		String id = null;
		try {
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con.prepareStatement("SELECT id FROM station where stationName = ?");
			ps.setString(1, stationName);
			ResultSet rs = ps.executeQuery();

			rs.next();
			id = rs.getString(1);
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
		return id;
	}

	
}
