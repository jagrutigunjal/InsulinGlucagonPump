package Model;

import java.util.Observable;

public class InsulinReservoir extends Observable {
	private static InsulinReservoir insulinReservoir = null;
	final String STATUS_LOW = "LOW";
	final String STATUS_FULL = "FULL";
	final String STATUS_EMPTY = "EMPTY";
	final String STATUS_OK = "OK";
	
	public final static int tank_capacity = 100; //units
	private int available_amount = tank_capacity  ;
	AudioPlayer02 audioplayer02 = new AudioPlayer02();
	
	public InsulinReservoir(){
		
	}
	public static InsulinReservoir getInstance() {
		if (insulinReservoir != null) {
			return insulinReservoir;
		} else {
			insulinReservoir = new InsulinReservoir();
			return insulinReservoir;
		}
	}
	public void refill() {
		available_amount = tank_capacity; 
		
	}

	public int getInsulinAmount(int amount) {
		available_amount = available_amount-amount;
		/*if(available_amount<20){
			String song="C:\\Users\\RAKA\\Desktop\\scs\\beep-09.wav";
		    audioplayer02.playAudio(song);*/
		//}
		return amount;
	}

	public int getAvailable(){
		return available_amount;
	}

	public void setInsulin(int amount) {
		available_amount = amount;
		//setChanged();
		//notifyObservers(InsulinReservoir);	
	}
	



}
