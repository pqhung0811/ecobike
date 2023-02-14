package entities.station;

import java.util.ArrayList;

import entities.bike.Bike;
import javafx.scene.image.Image;

public class Station {
	private int id;
	private String stationName;
	private int emptyDock;
	private int availableBike;
	private double area;
	private String address;
	private	int Bike;
	private int ElectricBike;
	private int TwinBike;
	private Image station;
	
	public Station(int id) {
		super();
		this.id = id;
	}
	public int getBike() {
		return Bike;
	}
	public void setBike(int StandardBike) {
		this.Bike = StandardBike;
	}
	public int getElectricBike() {
		return ElectricBike;
	}
	public void setElectricBike(int ElectricBike) {
		this.ElectricBike = ElectricBike;
	}
	public int getTwinBike() {
		return TwinBike;
	}
	public void setTwinBike(int TwinBike) {
		this.TwinBike = TwinBike;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public int getEmptyDock() {
		return emptyDock;
	}
	public void setEmptyDock(int emptyDock) {
		this.emptyDock = emptyDock;
	}
	public int getAvailableBike() {
		return availableBike;
	}
	public void setAvailableBike(int availableBike) {
		this.availableBike = availableBike;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Station() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Station(int id, String stationName, int emptyDock, int availableBike, double area, String address) {
		super();
		this.id = id;
		this.stationName = stationName;
		this.emptyDock = emptyDock;
		this.availableBike = availableBike;
		this.area = area;
		this.address = address;
		this.ElectricBike = 0;
		this.Bike = 0;
		this.TwinBike = 0;
	}
	public Image getStation() {
		return station;
	}
	public void setStation(Image station) {
		this.station = station;
	}
		
	
}
