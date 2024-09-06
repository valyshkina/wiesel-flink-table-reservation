package com.example.testaufgabe_reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.testaufgabe_reservation.model.Restaurant;
import com.example.testaufgabe_reservation.model.Table;
import com.example.testaufgabe_reservation.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TestaufgabeReservationApplicationTests {

	@Autowired
	private ReservationService reservationService;

	@BeforeEach
	public void setUp() {
		reservationService.resetAllReservations(new Restaurant("Wiesel-Flink-Food Hamburg", reservationService.getTables(new Restaurant("Wiesel-Flink-Food Hamburg", List.of()))));
	}

	@Test
	public void testGetRestaurants() {
		List<Restaurant> restaurants = reservationService.getRestaurants();
		assertNotNull(restaurants);
		assertFalse(restaurants.isEmpty());
	}

	@Test
	public void testGetTables() {
		Restaurant restaurant = reservationService.findRestaurantByName("Wiesel-Flink-Food Hamburg");
		List<Table> tables = reservationService.getTables(restaurant);
		assertNotNull(tables);
		assertFalse(tables.isEmpty());
	}

	@Test
	public void testIsTableAvailable() {
		Restaurant restaurant = reservationService.findRestaurantByName("Wiesel-Flink-Food Hamburg");
		boolean isAvailable = reservationService.isTableAvailable(restaurant, 4);
		assertTrue(isAvailable);
	}

	@Test
	public void testReserveTable() {
		Restaurant restaurant = reservationService.findRestaurantByName("Wiesel-Flink-Food Hamburg");
		int reservationNumber = reservationService.reserve(restaurant, 4, "Max Mustermann");
		assertTrue(reservationNumber > 0);
	}

	@Test
	public void testCancelReservation() {
		Restaurant restaurant = reservationService.findRestaurantByName("Wiesel-Flink-Food Hamburg");
		int reservationNumber = reservationService.reserve(restaurant, 4, "Max Mustermann");
		assertTrue(reservationNumber > 0, "Die Reservierung sollte erfolgreich sein");

		boolean canceled = reservationService.cancelReservation(restaurant, "Max Mustermann", reservationNumber);
		assertTrue(canceled);
	}

	@Test
	public void testResetAllReservations() {
		Restaurant restaurant = reservationService.findRestaurantByName("Wiesel-Flink-Food Hamburg");
		reservationService.resetAllReservations(restaurant);

		List<Table> tables = reservationService.getTables(restaurant);
		for (Table table : tables) {
			assertEquals("0", table.getReservedBy());
			assertFalse(table.isReserved());
		}
	}

	@Test
	public void testNegativeCapacity() {
		Restaurant restaurant = reservationService.findRestaurantByName("Wiesel-Flink-Food Hamburg");
		int negativeCapacity = -12;
		String reservedBy = "Marius der Schleifenprogrammierer";
		int result = reservationService.reserve(restaurant, negativeCapacity, reservedBy);
		assertEquals(0, result);
	}

	@Test
	public void testTooHighCapacity() {
		Restaurant restaurant = reservationService.findRestaurantByName("Wiesel-Flink-Food Hamburg");
		int negativeCapacity = 40000;
		String reservedBy = "Marius der Schleifenprogrammierer";
		int result = reservationService.reserve(restaurant, negativeCapacity, reservedBy);
		assertEquals(0, result);
	}




}
