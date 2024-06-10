package RailwayReservationSystem;

import java.util.*;

public class Main {
	
	static void bookTicket(Passenger p) {
		
		//Booking object
		Booking booker = new Booking();
		
		if((p.berthPreference.equals("L") && Booking.availableLowerBerths > 0) || (p.berthPreference.equals("M") && Booking.availableMiddleBerths > 0) || (p.berthPreference.equals("U") && Booking.availableUpperBerths > 0)) {
			System.out.println("Your preferred berth is available");
			if(p.berthPreference.equals("L")) {
				System.out.println("Lower Berth Given");
				booker.bookTicket(p,Booking.lowerBerthPositions.get(0),"L");
				Booking.lowerBerthPositions.remove(0);
				Booking.availableLowerBerths--;
			}
			else if(p.berthPreference.equals("M")) {
				System.out.println("Middle Berth Given");
				booker.bookTicket(p, Booking.middleBerthPositions.get(0), "M");
				Booking.middleBerthPositions.remove(0);
				Booking.availableMiddleBerths--;
			}
			else if(p.berthPreference.equals("U")) {
				System.out.println("Upper Berth Given");
				booker.bookTicket(p,Booking.upperBerthPositions.get(0), "U");
				Booking.upperBerthPositions.remove(0);
				Booking.availableUpperBerths--;
			}
		}
		else if(Booking.availableLowerBerths > 0) {
			System.out.println("Lower Berth Given");
			booker.bookTicket(p,Booking.lowerBerthPositions.get(0),"L");
			Booking.lowerBerthPositions.remove(0);
			Booking.availableLowerBerths--;
		}
		else if(Booking.availableMiddleBerths > 0) {
			System.out.println("Middle Berth Given");
			booker.bookTicket(p, Booking.middleBerthPositions.get(0), "M");
			Booking.middleBerthPositions.remove(0);
			Booking.availableMiddleBerths--;
		}
		else if(Booking.availableUpperBerths > 0) {
			System.out.println("Upper Berth Given");
			booker.bookTicket(p,Booking.upperBerthPositions.get(0), "U");
			Booking.upperBerthPositions.remove(0);
			Booking.availableUpperBerths--;
		}
		else if(Booking.availableRacTickets > 0) {
			System.out.println("RAC is Given");
			booker.addToRac(p,Booking.racPositions.get(0),"RAC");
		}
		else {
			System.out.println("Waiting List is Given");
			booker.addToWaitingList(p,Booking.waitingListPositions.get(0),"WL");
		}
	}
	
	static void cancelTicket(int passengerId) {
		
		//Booking object
		Booking booker = new Booking();
				
		if(!Booking.passengerMap.containsKey(passengerId)) {
			System.out.println("Unknown passenger Id");
		}
		else {
			booker.cancelTicket(passengerId);
		}
	}

	public static void main(String[] args) {
		boolean bool = true;
		while(bool) {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println(" 1 -> Book Tickets /n 2 -> Cancel Tickets /n 3 -> Print Available Tickets /n 4 -> Print All Bookings");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
			case 1 : System.out.println("Chosen number is "+choice);
					 if(Booking.availableWaitingList == 0) {
						 System.out.println("No Tickets Available");
						 continue;
					 }
					 System.out.println("Enter your name here : ");
					 String passengerName = scanner.nextLine();
					 System.out.println("Enter your Berth Preference here : ");
					 String berthPreference = scanner.nextLine();
					 
					 //Create a Passenger object
					 Passenger p = new Passenger(passengerName,berthPreference);
					 bookTicket(p);
					 break;
			case 2 : System.out.println("Chosen number is "+choice);
					 System.out.println("Enter your passenger id here : ");
					 int passengerId = scanner.nextInt();
					 cancelTicket(passengerId);
			 		 break;
			case 3 : System.out.println("Chosen number is "+choice);
					 Booking.printAvailableTickets();
			 		 break;
			case 4 : System.out.println("Chosen number is "+choice);
					 Booking.printAllBookings();
			 		 break;
			}
			
		}

	}

}
