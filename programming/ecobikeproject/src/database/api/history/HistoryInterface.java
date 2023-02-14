package database.api.history;

import java.util.ArrayList;

import entities.history.ItemHistory;

public interface HistoryInterface {
	public ArrayList<ItemHistory> getRentHistory();
	  public ArrayList<ItemHistory> getReturnHistory();
}
