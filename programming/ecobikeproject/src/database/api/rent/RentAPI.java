package database.api.rent;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.api.bike.BikeAPI;
import database.api.bike.BikeInterface;
import database.connection.Context;
import entities.bike.Bike;
import entities.bike.BikeFactory;
import entities.rent.Rent;
import entities.rent.RentInfo;
import entities.station.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class RentAPI implements RentInterface {

	// login need bikeId custId deposit timeCreate
	/* Get order by userid  */
	@Override
	public Rent getRent() {
		Rent rent = new Rent();
		BikeInterface bikeInterface = new BikeAPI();
		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT id,timecreate,bikeID FROM rent where timefinish is null ORDER BY id desc limit 1");
//			ps.setString(1, Integer.toString(userId));
			ResultSet rs = ps.executeQuery();

			// exist bike
			if (rs.next()) {
//				if(Integer.valueOf(rs.getString(3))==bikeId) {
					rent.setId(Integer.valueOf(rs.getString(1)));
					rent.setTimeCreate(rs.getString(2));
					Bike bike = bikeInterface.getBikeById(rs.getString(3));
					rent.setBike(bike);
					/*
					BikeType bike = ibike.getBikeById(rs.getString(3));
					File file = new File("src/se/project/image/" + bike.getName() + ".jpeg");
					Image image = new Image(file.toURI().toString());
					bike.setI(image);
					bike.setId(Integer.valueOf(rs.getString(3)));
					order.setBike(bike);  */
//					rent.getBike().setId(Integer.valueOf(rs.getString(3)));
//				}

			} else {
				//order.setBike(null);
				rent = null;
			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return rent;
	}

	/* Get order id by userId  */
	@Override
	public int getRentId(int bikeId) {
		try {
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con
					.prepareStatement("SELECT id FROM rent WHERE bikeId = ? ORDER BY id DESC LIMIT 1 ");
			ps.setString(1, Integer.toString(bikeId));
			ResultSet rs = ps.executeQuery();
			rs.next();
			int ans = Integer.valueOf(rs.getString(1));
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
		return 0;
	}
	
	@Override
	public int getBikeId(int id) {
		try {
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con
					.prepareStatement("SELECT bikeID FROM rent WHERE id = ?");
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			rs.next();
			int ans = Integer.valueOf(rs.getString(1));
			con.close();
			return ans;
		} catch (Exception e) {
			System.out.println(e);

		}
		return 0;
	}
	
//	@Override
//	public List<Rent> getAllRent() {
//	List<Rent> rentList = new ArrayList<Rent>();
//		try {
//			Connection con = Context.getConnection();
//			PreparedStatement ps = con.prepareStatement("SELECT  * FROM rent");
//
//			BikeInterface bikeInterface = new BikeAPI();
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//				Rent rent; // type
//				Bike bike = bikeInterface.getBikeById(null)
//				rent.setId(Integer.valueOf(rs.getString(1)));
//				rent.setDeposit(Double.valueOf(rs.getString(2)));
//				rent.setBike_name(rs.getString(3));
//				bike.setType(rs.getString(4));
//				bike.setLicence_plate(rs.getString(6));
//				bike.setCost(Integer.valueOf(rs.getString(9)));
//				bike.setStatus(rs.getString(10));
//				bikeList.add(bike);
//			}
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//		return rentList;
//	}
	@Override
	public ObservableList<RentInfo> getAllRents() {
		ObservableList<RentInfo> listRents = FXCollections.observableArrayList();
		try {

			Connection connect = Context.getConnection();
			// can liet ke so xe dang thue
			PreparedStatement ps = connect.prepareStatement("SELECT r.id, r.timecreate, b.bike_name, b.type, s.stationName, s.address, r.bikeID FROM rent r INNER JOIN bike b ON r.bikeID = b.id INNER JOIN station s ON b.stationID = s.id WHERE r.timefinish IS NULL");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RentInfo rentInfo = new RentInfo();
			
				rentInfo.setIdInteger(Integer.valueOf(rs.getString(1)));
				rentInfo.setTimecreateString(rs.getString(2));
				rentInfo.setBikenameString(rs.getString(3));
				rentInfo.setTypeString(rs.getString(4));
				rentInfo.setStationnameString(rs.getString(5));
				rentInfo.setAddressString(rs.getString(6));
				
				listRents.add(rentInfo);
			}
			connect.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
		return listRents;
	}
	
	@Override
	public Rent getRentbyID(int id) {
		Rent rent = new Rent();
		BikeInterface bikeInterface = new BikeAPI();
		try {

			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, timecreate, bikeID FROM rent where id = ?");
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();

			// exist bike
			if (rs.next()) {
//				if(Integer.valueOf(rs.getString(3))==bikeId) {
					rent.setId(Integer.valueOf(rs.getString(1)));
					rent.setTimeCreate(rs.getString(2));
					Bike bike = bikeInterface.getBikeById(rs.getString(3));
					rent.setBike(bike);
					/*
					BikeType bike = ibike.getBikeById(rs.getString(3));
					File file = new File("src/se/project/image/" + bike.getName() + ".jpeg");
					Image image = new Image(file.toURI().toString());
					bike.setI(image);
					bike.setId(Integer.valueOf(rs.getString(3)));
					order.setBike(bike);  */
//					rent.getBike().setId(Integer.valueOf(rs.getString(3)));
//				}

			} else {
				//order.setBike(null);
				rent = null;
			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return rent;
	}
}
