package database.api.bike;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import entities.bike.Bike;
import entities.bike.BikeFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import database.connection.Context;

public class BikeAPI implements BikeInterface {
	

	/* get all bike by Store name */
	@Override
	public ObservableList<Bike> getListFromDB(String station) {
		ObservableList<Bike> bikeList = FXCollections.observableArrayList();

		try {
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT bike.id,bike.bike_name,type,bike.licence_plate,bike.status,bike.cost,bike.stationID FROM bike INNER JOIN station ON bike.stationID = station.id where station.stationName = ?");
			ps.setString(1, station);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// BikeType bike = getObject(rs.getString(3));
				Bike bike = BikeFactory.getBike(rs.getString(3));
				bike.setId(Integer.valueOf(rs.getString(1)));
				bike.setBike_name(rs.getString(2));
				bike.setType(rs.getString(3));
				bike.setLicence_plate(rs.getString(4));
				bike.setStatus((rs.getString(5)));
				bike.setCost(Integer.parseInt(rs.getString(6)));
				bikeList.add(bike);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return bikeList;
	}

	/* get bike by name */
	
	@Override
	public Bike getBikeFromDB(String bikeName) {

		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM bike where bike.bike_name = ?");
			ps.setString(1, bikeName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Bike bike = BikeFactory.getBike(rs.getString(3));
			bike.setId(Integer.valueOf(rs.getString(1)));
			bike.setBike_name(rs.getString(2));
			bike.setType(rs.getString(3));
			bike.setLicence_plate(rs.getString(8));
			bike.setCost(Integer.valueOf(rs.getString(5)));
			bike.setBarcode(rs.getString(6));
			
			File file = new File("view/images/" + bike.getBike_name() + ".jpg");
			Image image = new Image(file.toURI().toString());
			bike.setUrlImage(image);
			con.close();
			return bike;
		} catch (Exception e) {
			System.out.println(e);

		}
		return null;
	}
	public String getBikeType(String id) {
		String type = null;
		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT type FROM bike where id = ?"); // nen select col thay
																									// vi select het
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
			type = rs.getString(1);}
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);

		}
		return type;
	}
	/* get bike by Id */
	public Bike getBikeById(String id) {
		Bike bike;
		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM bike where id = ?"); // nen select col thay
																									// vi select het
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			bike = BikeFactory.getBike(rs.getString(3)); // type
			bike.setId(Integer.valueOf(id));
			bike.setBike_name(rs.getString(2));
			bike.setType(rs.getString(3));
			bike.setLicence_plate(rs.getString(8));
			bike.setCost(Integer.valueOf(rs.getString(5)));
			File file = new File("src/view/images/" + bike.getBike_name() + ".jpg");
			Image image = new Image(file.toURI().toString());
			bike.setUrlImage(image);
			con.close();
			return bike;
		} catch (Exception e) {
			System.out.println(e);

		}
		return null;
	}

	@Override
	public ObservableList<Bike> getAllBike() {
		ObservableList<Bike> bikeList = FXCollections.observableArrayList();

		try {
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  * FROM bike"); // nen select col thay vi select het

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Bike bike = BikeFactory.getBike(rs.getString(4)); // type
				bike.setId(Integer.valueOf(rs.getString(1)));
				bike.setStationId(Integer.valueOf(rs.getString(2)));
				bike.setBike_name(rs.getString(3));
				bike.setType(rs.getString(4));
				bike.setLicence_plate(rs.getString(6));
				bike.setCost(Integer.valueOf(rs.getString(9)));
				bike.setStatus(rs.getString(10));
				bikeList.add(bike);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return bikeList;
	}

	public boolean checkBikeRent(String bikeId) {

		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT status FROM bike where bike_name = ?");
			ps.setString(1, bikeId);

			ResultSet rs = ps.executeQuery();

			rs.next();
			if (rs.getString(1).equals("Rent")) {
				con.close();
				return true;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	@Override
	public Integer getIdByName(String name) {
		int id = 0;
		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT id FROM bike where bike_name = ?"); // nen select col thay
																									// vi select het
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
			id = Integer.valueOf(rs.getString(1));}
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);

		}
		return id;
	}

}
