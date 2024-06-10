package RailwayReservationSystem;

import java.util.*;

public class Booking {
	
	static int availableLowerBerths  = 1;
	static int availableMiddleBerths = 1;
	static int availableUpperBerths  = 1;
	static int availableRacTickets   = 1;
	static int availableWaitingList  = 1;
	
	static Queue<Integer> waitingListQueue    = new LinkedList<Integer>();
	static Queue<Integer> racListQueue        = new LinkedList<Integer>();
	static List<Integer> bookedTicketList     = new ArrayList<Integer>();
	
	static List<Integer> lowerBerthPositions  = new ArrayList<Integer>(Arrays.asList(1));
	static List<Integer> middleBerthPositions = new ArrayList<Integer>(Arrays.asList(1));
	static List<Integer> upperBerthPositions  = new ArrayList<Integer>(Arrays.asList(1));
	static List<Integer> racPositions         = new ArrayList<Integer>(Arrays.asList(1));
	static List<Integer> waitingListPositions = new ArrayList<Integer>(Arrays.asList(1));
	
	static Map<Integer,Passenger> passengerMap= new HashMap<Integer,Passenger>();
	
	public void bookTicket(Passenger p,int berthInfo, String allottedBerth) {
		p.number = berthInfo;
		p.allotted = allottedBerth;
		
		passengerMap.put(p.passengerId, p);
		bookedTicketList.add(p.passengerId);
	}
	public void addToRac(Passenger p, Integer racInfo, String allottedRac) {
		p.number = racInfo;
		p.allotted = allottedRac;
		
		passengerMap.put(p.passengerId, p);
		racListQueue.add(p.passengerId);
		
		racPositions.remove(0);
		availableRacTickets--;
		
		System.out.println("Added to RAC Successfully!");
	}
	public void addToWaitingList(Passenger p, Integer waitingListInfo, String allottedWaitingList) {
		p.number = waitingListInfo;
		p.allotted = allottedWaitingList;
		
		passengerMap.put(p.passengerId, p);
		waitingListQueue.add(p.passengerId);
		
		waitingListPositions.remove(0);
		availableWaitingList--;
		
		System.out.println("Added to Waiting List Successfully!");
	}
	public void cancelTicket(int passengerId) {
		
		//Cancel ticket
		Passenger p = passengerMap.get(passengerId);
		bookedTicketList.remove(Integer.valueOf(passengerId));
		passengerMap.remove(Integer.valueOf(passengerId));
		System.out.println("Cancelled Ticket Successfully");
		
		//Free up space in lower,middle,upper berths
		if(p.allotted.equals("L")) {
			lowerBerthPositions.add(p.number);
			availableLowerBerths++;
		}
		else if(p.allotted.equals("M")) {
			middleBerthPositions.add(p.number);
			availableMiddleBerths++;
		}
		else if(p.allotted.equals("U")) {
			upperBerthPositions.add(p.number);
			availableUpperBerths++;
		}
		
		//Check if there is a RAC tickets
		if(racListQueue.size() > 0) {
			Passenger passengerFromRac = passengerMap.get(racListQueue.poll());
			//bookedTicketList.add(passengerFromRac.number);
			racPositions.add(passengerFromRac.number);
			racListQueue.remove(Integer.valueOf(passengerFromRac.passengerId));
			availableRacTickets++;
			if(waitingListQueue.size() > 0){
				Passenger passengerFromWL = passengerMap.get(waitingListQueue.poll());
				waitingListPositions.add(passengerFromWL.number);
				waitingListQueue.remove(Integer.valueOf(passengerFromWL.passengerId));
				
				//Admit into RAC
				passengerFromWL.number = racPositions.get(0);
				passengerFromWL.allotted = "RAC";
				racPositions.remove(0);
				racListQueue.add(passengerFromWL.passengerId);
				
				availableRacTickets--;
				availableWaitingList++;
			}
			Main.bookTicket(passengerFromRac);
		}
	}
	static public void printAvailableTickets() {
		System.out.println(
				  "Available Lower Berths - "+availableLowerBerths+
				"\nAvailable Middle Berths - "+availableMiddleBerths+
				"\nAvailable Upper Berths - "+availableUpperBerths+
				"\nAvailable RAC Tickets - "+availableRacTickets+
				"\nAvailable Waiting Lists - "+availableWaitingList);
	}
	static public void printAllBookings() {
		if(passengerMap.size()==0) {
			System.out.println("No Bookings done");
		}
		else {
			for(Passenger p : passengerMap.values()) {
				System.out.println(
						  "passenger Id - "+p.passengerId+
						"\nPassenger Name - "+p.passengerName+
						"\nSeat number - "+p.number+p.allotted);
			}
		}
	}
}
