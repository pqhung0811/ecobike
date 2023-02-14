package entities.rent;

import java.time.LocalDateTime;
import java.util.Date;

import database.api.bike.BikeAPI;
import database.api.bike.BikeInterface;
import entities.bike.Bike;
import utils.DateUtils;
import java.time.Duration;

public class Rent {
	private int id=0;
	private String timeCreate;
	private String timeFinish;
	private double total;
	private Bike bike;
	private String returnId;
	private int time;
	
	public Rent(int id, String timeCreate, Bike bike) {
		super();
		this.id = id;
		this.timeCreate = timeCreate;
		this.bike = bike;
	}
	public int getTime() {
		return time;
	}
	public void setTime(long l) {
		this.time = (int) l;
	}
	public Rent() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}
	public String getTimeFinish() {
		return timeFinish;
	}
	public void setTimeFinish(String timeFinish) {
		this.timeFinish = timeFinish;
	}
	public double getTotal() {
		return this.bike.calTotalCost(time);
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Bike getBike() {
		return bike;
	}
	public void setBike(Bike bike) {
		this.bike = bike;
	}
	public Rent(int id, double deposit, String timeCreate, String timeFinish, double total, Bike bike) {
		super();
		this.id = id;
		this.timeCreate = timeCreate;
		this.timeFinish = timeFinish;
		this.total = total;
		this.bike = bike;
	}
	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}
	public String getTotalTime(LocalDateTime tim) {
//		System.out.println(timeCreate);
		LocalDateTime dateTime = LocalDateTime.parse(timeCreate, DateUtils.format);
		Duration duration = Duration.between(dateTime, tim);
		this.time = (int) duration.toSeconds();
		return DateUtils.date(getTime());
	}
}
