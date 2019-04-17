package goModel;

import java.util.*;

import view.ControlPanel;

public class Time implements Clock {
	
	private static final int MAX_TIME = 18000;
	
	private int hour = 0;
	private int minute = 10;
	private int second = 0;
	
	private boolean running = false;
	
	public Time() {
		
	}
	
	public Time(int totalTime) {
		if(totalTime > MAX_TIME)
			return;
		hour = totalTime / 3600;
		totalTime %= 3600;
		minute = totalTime / 60;
		second = totalTime % 60;
	}
	
	public Time(int hour, int minute, int second) {
		if(hour * 3600 + minute * 60 + second < MAX_TIME &&
				hour <= 5 && minute < 60 && second < 60) {
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}
	}
	
	@Override
	public Time getTime() {
		return new Time(hour, minute, second);
	}
	
	public int toSecond() {
		return hour * 3600 + minute * 60 + second;
	}
	
	@Override
	public String toString() {
		String minute = "" + this.minute;
		String second = "" + this.second;
		if(this.minute < 10)
			minute = "0" + this.minute;
		if(this.second < 10)
			second = "0" + this.second;
		return hour + ":" + minute + ":" + second;
	}
	
	@Override
	public void run() {
		while(hour >= 0) {
			while(minute >= 0) {
				while(second >= 0) {
					try {
						Thread.sleep(1000);
						if(!running)
							continue;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					second--;
				}
				minute--;
				second = 59;
			}
			hour--;
			second = minute = 59;
		}
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public void switchRunning() {
		running = !running;
	}
	
	public void switchRunning(boolean running) {
		this.running = running;
	}
}
