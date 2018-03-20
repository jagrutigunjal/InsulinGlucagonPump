package Model;
import java.util.Observable;


public class Battery extends Observable {

	public final static int BATTERY_CAPACITY = 100;
	private int available = BATTERY_CAPACITY;
	public final static String STATUS_LOW = "LOW";
	public final static String STATUS_FULL = "FULL";
	public final static String STATUS_EMPTY = "EMPTY";
	public final static String STATUS_OK = "OK";
	
	private static Battery battery = null;
	AudioPlayer02 audioplayer02= new AudioPlayer02();

	public Battery() {

	}

	public static Battery getInstance() {
		if (battery != null) {
			return battery;
		} else {
			battery = new Battery();
			return battery;
		}
	}

	/**
	 * 
	 * @return status of the battery in string
	 */
	public String checkStatus() {
		if (available < 0.08 * BATTERY_CAPACITY) {
			return STATUS_EMPTY;
		} else if (available < 0.2 * BATTERY_CAPACITY && available > 0.08 * BATTERY_CAPACITY) {
			return STATUS_LOW;
		} else if (available == BATTERY_CAPACITY) {
			return STATUS_FULL;
		}
		return STATUS_OK;
	}

	public int getPowerAmout(int amt) {
		available = available - amt;
		return amt;
	}

	public void setPower(int amt) {
		available = amt;
		
	}

	public int getAvailable() {
		/*setChanged();
		notifyObservers(this);*/
		if(available<20){
	//		String song="C:\\Users\\RAKA\\Desktop\\scs\\beep-09.wav";
//		    audioplayer02.playAudio(song);
		}
		return available;
	}

}
