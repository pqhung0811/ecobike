package entities.bike;

import entities.station.Station;
import javafx.scene.image.Image;

public abstract class Bike {
    private int id;
    private String bike_name;
    private String type;
    private String barcode;
    private int cost;
    private String licence_plate;
    private Image urlImage;
    private String status;
	private int stationId;
    
	public Bike() {
		super();
	}

	public Bike(int id, String bike_name, String type, String barcode, int cost, Image urlImage) {
		super();
		this.id = id;
		this.bike_name = bike_name;
		this.type = type;
		this.barcode = barcode;
		this.cost = cost;
		this.urlImage = urlImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBike_name() {
		return bike_name;
	}

	public void setBike_name(String bike_name) {
		this.bike_name = bike_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getLicence_plate() {
		return licence_plate;
	}

	public void setLicence_plate(String licence_plate) {
		this.licence_plate = licence_plate;
	}

	public Image getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(Image urlImage) {
		this.urlImage = urlImage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public abstract int getdeposit();
	
	public double calTotalCost(int time) {
		 double total=0;
		    if (time>0 && time <=30) {
		       total = 10000;
		    } else if(time>30){
		      double part =  Math.ceil((time - 30)/15.0);
	             
		      total =  (10000 + part * 3000);
		   }
		   return total;
	}
}
