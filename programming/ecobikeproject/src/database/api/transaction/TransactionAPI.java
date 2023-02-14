package database.api.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;

import database.connection.Context;
import entities.rent.Rent;


public class TransactionAPI implements TransactionInterface {
	  @Override
	  public void saveTransaction(int orID, String msg, double money,String card) {
	    try {
	      Date date = Calendar.getInstance().getTime();  
	      DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	      String strDate = dateFormat.format(date);  
	      Connection con = Context.getConnection();
	      //GET ID
	      PreparedStatement ps = con.prepareStatement(
	          "INSERT INTO transaction(RentID,message,money,card,createdate) VALUES (?,?,?,?,?); ");
	      ps.setString(1, Integer.toString(orID));
	      ps.setString(2, msg);
	      ps.setString(3, Double.toString(money));
	      ps.setString(4,card);
	      ps.setString(5, strDate);
	      ps.executeUpdate();
	      con.close();
	    } catch (Exception e) {
	      System.out.println(e);

	    }


	  }

	  //updateRent and bike status
	  @Override
	  public void updateReturn(int orId, int bikeId, double totMoney, String timeFinish,String returnI) {
	    try {
	      Connection con = Context.getConnection();
	      //GET ID
	      PreparedStatement ps = con.prepareStatement("SELECT insertReturn(?,?,?,?,?)");
	      ps.setString(1, Integer.toString(orId));
	      ps.setString(2, Integer.toString(bikeId));
	      ps.setString(3, Double.toString(totMoney));
	      ps.setString(4, timeFinish);
	      ps.setString(5, returnI);
	      ps.execute();
	      con.close();
	    } catch (Exception e) {
	      System.out.println(e);

	    }
	  }



	public void saveTransacToDB(Rent rent) {

		try {
			// update bike rent /save transact to "order table"
			Connection con = Context.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT insertRent(?,?);"); // function to insert and update
																						// bike Table

			ps.setString(1, rent.getTimeCreate());
			ps.setString(2, Integer.toString(rent.getBike().getId()));
			ps.execute();

			// get store id as input update store dock when rent
			PreparedStatement ps1 = con.prepareStatement("SELECT updateStationRent(?)");
			ps1.setString(1, Integer.toString(rent.getBike().getId()));
			ps1.execute();
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
	}
}
