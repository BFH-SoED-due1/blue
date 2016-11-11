package ch.bfh.blue.srs;

public class Space {
	private String name;
	private boolean booked = false;
	
	public Space(String name){
		this.name=name;
	}
	
	// returns if room is booked
	public boolean isBooked() {	
		return this.booked;
	}

	public void booking() {
		this.booked = true;
	}
}
