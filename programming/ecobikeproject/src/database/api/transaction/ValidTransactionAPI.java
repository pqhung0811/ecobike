package database.api.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.connection.Context;

public class ValidTransactionAPI implements ValidTransactionInterface {
	@Override
	public boolean checkTransactFinish() {
		// TODO Auto-generated method stub\
		boolean flag = true;
		 try {
		      Connection connection = Context.getConnection();
		      //GET ID
		      PreparedStatement ps = connection.prepareStatement(
		          "SELECT id FROM rent WHERE timefinish is NULL ");
//		      ps.setString(1, Integer.toString(custId));
		      ResultSet rs = ps.executeQuery();
		      if(rs.next()) {
		    	  flag = false;
		      } 
		      connection.close();
		    } catch (Exception e) {
		      System.out.println(e);

		    }
		return flag;
	}



	@Override
	public boolean checkSameCard(String cardNum, String orderId) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		try {

			Connection con = Context.getConnection();
			// check card have used
			PreparedStatement ps = con.prepareStatement("SELECT distinct(card) from transaction where RentID = ?");
			ps.setString(1, orderId);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == true) {
				if (rs.getString(1).equals(cardNum)) {
					flag = true;
				} else
					flag = false;
			} else
				flag = true;
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return flag;
	}

	@Override
	public boolean checkCardUsed(String cardNum, String orderId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			// update bike rent /save transact to "order table"
			Connection con = Context.getConnection();

			// check card have used
			PreparedStatement ps = con
					.prepareStatement("SELECT count(id) from transaction where card = ? AND RentID != ?;");
			ps.setString(1, cardNum);
			ps.setString(2, orderId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int num = Integer.valueOf(rs.getString(1));
				if (num % 2 == 0) {
					flag = false;
				} else
					flag = true;
			} else
				flag = false;
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
		return flag;
	}

}
