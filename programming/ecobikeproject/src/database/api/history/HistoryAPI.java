package database.api.history;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.connection.Context;
import entities.history.ItemHistory;

public class HistoryAPI implements HistoryInterface {
	private ArrayList<ItemHistory> history;

	/* Get rent history by id */
	public ArrayList<ItemHistory> getRentHistory() {

		try {
			history = new ArrayList<>();
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con.prepareStatement("SELECT b.bike_name,r.timeCreate,t.money FROM transaction t	INNER JOIN rent r ON t.RentID = r.id	INNER JOIN bike b	ON r.bikeId = b.id	where mod(t.id,2) = 1 "); // lay cai dat coc
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemHistory or = new ItemHistory();
				or.setBikeName(rs.getString(1));
				or.setTime(rs.getString(2));
				or.setMoney(Float.valueOf(rs.getString(3)));
				history.add(or);
			}

		} catch (Exception e) {
			System.out.println(e);

		}

		return history;
	}

	/* Get return history by id */
	public ArrayList<ItemHistory> getReturnHistory() {
		// lay so tien return
		try {
			history = new ArrayList<>();
			Connection con = Context.getConnection();
			// GET ID
			PreparedStatement ps = con.prepareStatement("	SELECT b.bike_name,r.timeFinish,t.money FROM transaction t INNER JOIN rent r ON t.RentID = r.id INNER JOIN bike b ON r.bikeId = b.id where mod(t.id,2) = 0");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ItemHistory or = new ItemHistory();
				or.setBikeName(rs.getString(1));
				or.setTime(rs.getString(2));
				or.setMoney(Float.valueOf(rs.getString(3)));
				history.add(or);
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return history;

	}
}
