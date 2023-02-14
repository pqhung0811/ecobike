package entities.transaction;

import java.util.Date;

import entities.card.Card;

public class Transaction {
	private int id;
	private String message;
	private Card card;
	private Date createDate;
	private double money;
}
