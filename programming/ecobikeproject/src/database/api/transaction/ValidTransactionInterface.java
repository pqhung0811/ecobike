package database.api.transaction;

public interface ValidTransactionInterface {
	public boolean checkTransactFinish();                // check finish pay
	public boolean checkSameCard(String cardNum, String orderId);  // card same with previous pay
	public boolean checkCardUsed(String cardNum, String orderId);  // card used or not
}
