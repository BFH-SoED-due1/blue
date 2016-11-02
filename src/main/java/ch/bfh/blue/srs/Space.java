package ch.bfh.blue.srs;

public class Space {
	private String name;
	private boolean booked = false;
	
	public Space(String name){
		this.name=name;
	}

	public boolean isBooked() {	
		return this.booked;
	}
	
	
}
