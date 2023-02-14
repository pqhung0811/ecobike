package entities.bike;

public class ElectricBike extends Bike {
	private int battery;
	private int time_remain;

	public ElectricBike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getTime_remain() {
		return time_remain;
	}

	public void setTime_remain(int time_remain) {
		this.time_remain = time_remain;
	}
	
	
	@Override
	public int getdeposit() {
		return 700000;
	}
   
}
