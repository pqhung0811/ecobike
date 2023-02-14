package test.bike.blackbox;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import entities.bike.Bike;
import entities.bike.BikeFactory;


public class BikeCostTest {

	private int time;
	private Bike bike;

	@Test
	void testBikeCost1() {
		time = 70;
		bike = BikeFactory.getBike("Bike");
		assertEquals(19000, bike.calTotalCost(time));
	}

	@Test
	void testBikeCost2() {
		time = 30;
		bike = BikeFactory.getBike("Bike");
		assertEquals(10000, bike.calTotalCost(time));
	}

	@Test
	void testEBikeCost1() {
		time = 70;
		bike = BikeFactory.getBike("ElectricBike");
		assertEquals(28500, bike.calTotalCost(time));

	}

	@Test
	void testEBikeCost2() {
		time = 30;
		bike = BikeFactory.getBike("ElectricBike");
		assertEquals(15000, bike.calTotalCost(time));
	}

	@Test
	void testTwinBikeCost1() {
		time = 70;
		bike = BikeFactory.getBike("TwinBike");
		assertEquals(28500, bike.calTotalCost(time));
	}

	@Test
	void testTwinBikeCost2() {
		time = 30;
		bike = BikeFactory.getBike("TwinBike");
		assertEquals(15000, bike.calTotalCost(time));
	}

	@Test
	void testCost() {
		time = 0;
		bike = BikeFactory.getBike("TwinBike");
		assertEquals(0, bike.calTotalCost(time));
	}
}
