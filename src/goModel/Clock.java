package goModel;

public interface Clock extends Runnable {
	
	public Time getTime();
	public boolean isRunning();
	public void switchRunning();
	public void switchRunning(boolean running);
}
