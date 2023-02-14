package entities.card;

public class PayByCard implements PayStrategy{

  // update balance
  private Card card;

  public PayByCard(Card card) {
    this.card = card;
  }

  public boolean pay(double paymentAmount) {
    // TODO Auto-generated method stub
    return card.debit(paymentAmount);
  }

   // true thanh cong
  public boolean pay(int deposit, double total) {
    if(card.debit((int)total)) {
    card.credit(deposit);
    return true;
   }
     return false;
}
  }
