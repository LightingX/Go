package goModel;

public class Record {
	private Time time;
	private int turn;
	private ChessColor operator;
	private BoardPoint operationPoint;

	public Record(Time time, int turn, ChessColor operator, BoardPoint operationPoint) {
		this.time = time;
		this.turn = turn;
		this.operator = operator;
		this.operationPoint = operationPoint;
	}

	public Time getTime() {
		return time;
	}

	public int getTurn() {
		return turn;
	}

	public ChessColor getOperator() {
		return operator;
	}

	public BoardPoint getOperationPoint() {
		return operationPoint;
	}
}
