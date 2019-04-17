package goModel;

import java.util.*;

public class Recorder {
	
	ArrayList<Record> recordList;
	
	public Recorder() {
		recordList = new ArrayList<Record>();
	}
	
	public void addRecord(Time time, int turn, ChessColor operator, BoardPoint operationPoint) {
		recordList.add(new Record(time, turn, operator, operationPoint));
	}
	
	public void removeTopIRecord(int i) {
		recordList.remove(recordList.size() - i);
	}
	
	public void removeTopRecord() {
		this.removeTopIRecord(1);
	}
	
	public ArrayList<Record> getRecords() {
		return recordList;
	}
	
	public Record getTopIRecord(int i) {
		return recordList.get(recordList.size() - i);
	}
	
	public Record getTopRecord() {
		return getTopIRecord(1);
	}
	
	public Iterator getRecordIterator() {
		return recordList.iterator();
	}
	
	public void clear() {
		recordList.clear();
	}
}
