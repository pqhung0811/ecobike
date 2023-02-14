package entities.card;

public interface PayStrategy {

 // tru tien khi return
  public boolean pay(double paymentAmount);
  public boolean pay(int deposit, double total);
}
