package entities.card;

import java.time.LocalDate;
import java.util.Date;

public class Card {
	private int id;
    private String name;
    private String security_code;
    private String number;
    private int balance=10000000;
    private LocalDate dateExpired;
    private String bank;
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecurity_code() {
		return security_code;
	}
	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public LocalDate getDateExpired() {
		return dateExpired;
	}
	public void setDateExpired(LocalDate dateExpired) {
		this.dateExpired = dateExpired;
	}
	public Card(Integer Id, String Bank, String name, String number, LocalDate dateExpired) {
		super();
		this.id=Id;
		this.bank = Bank;
		this.name = name;
		this.number = number;
		this.dateExpired = dateExpired;
	}  
	
	public void credit(int amount) {
		setBalance(getBalance() + amount);
	}

	public boolean debit(double money) {
		if (money > balance) {
			return false;
		} else {
			balance -= money;
			return true;
		}
	}
}
