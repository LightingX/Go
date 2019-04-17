package goModel;

import view.*;

public class ResponsibilityTimer extends Thread {
	
	private Clock blackClock;
	private Clock whiteClock;
	private Clock curRunningClock;
	private Thread blackThread;
	private Thread whiteThread;
	
	private boolean running = true;
	
	private Observer playing;
	private Observer controlView;
	
	public void setControlView(Observer controlView) {
		this.controlView = controlView;
	}
	
	public void setPlaying(Observer playing) {
		this.playing = playing;
	}
	
	public ResponsibilityTimer() {
		blackClock = new Time();
		whiteClock = new Time();
		blackThread = new Thread(blackClock);
		whiteThread = new Thread(whiteClock);
		blackThread.start();
		whiteThread.start();
	}
	
	public ResponsibilityTimer(int hour, int minute, int second) {
		blackClock = new Time(hour, minute, second);
		whiteClock = new Time(hour, minute, second);
		blackThread = new Thread(blackClock);
		whiteThread = new Thread(whiteClock);
		blackThread.start();
		whiteThread.start();
	}
	
	@Override
	public void run() {
		blackClock.switchRunning();
		curRunningClock = blackClock;
		while(getTime(ChessColor.BLACK).toSecond() != 0 && getTime(ChessColor.WHITE).toSecond() != 0) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(controlView != null)
				notifyControlView();
			if(!running)
				break;
		}
		notifyPlaying();
	}
	
	public void switchClock() {
		blackClock.switchRunning();
		whiteClock.switchRunning();
		curRunningClock = (curRunningClock.equals(blackClock))? whiteClock : blackClock;
	}
	
	public Time getCurRunningClockTime() {
		return curRunningClock.getTime();
	}
	
	public void pauseTiming() {
		blackClock.switchRunning(false);
		whiteClock.switchRunning(false);
	}
	
	public void continueTiming() {
		curRunningClock.switchRunning();
	}
	
	public Time getTime(ChessColor color) {
		if(color.equals(ChessColor.BLACK))
			return blackClock.getTime();
		else if(color.equals(ChessColor.WHITE))
			return whiteClock.getTime();
		return null;
	}
	
	public void notifyControlView() {
		controlView.update();
	}
	
	public void notifyPlaying() {
		playing.update();
	}
	
	public void stopTiming() {
		this.running = false;
	}
}
