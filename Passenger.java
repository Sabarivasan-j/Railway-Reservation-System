package RailwayReservationSystem;

public class Passenger {
	static int id = 0;
	int passengerId;
	String passengerName;
	String berthPreference;
	
	int number;
	String allotted;
	
	public Passenger(String passengerName, String berthPreference ){
		this.berthPreference = berthPreference;
		this.passengerName   = passengerName;
		this.passengerId = id++;
	}
}
