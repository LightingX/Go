package goModel;


public class BoardPoint {
	int x, y;
	
	public BoardPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public BoardPoint getPoint() {
		return new BoardPoint(x, y);
	}
}
