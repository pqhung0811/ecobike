package database.api.transaction;

import entities.rent.Rent;

public interface TransactionInterface {
	  public void saveTransaction(int orID, String msg, double money,String card); //
	  public void saveTransacToDB(Rent rent);
	  public void updateReturn(int orId, int bikeId, double totMoney, String timeFinish,String returnI);
}
