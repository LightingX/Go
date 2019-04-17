package goModel;

public class ChessBoard {

	private static final int BOARD_ROAD = 15;
	private Chess[][] chesses;

	private static volatile ChessBoard theChessBoard;

	public static ChessBoard getChessBoard() {
		synchronized (ChessBoard.class) {
			if (theChessBoard == null)
				theChessBoard = new ChessBoard();
		}
		return theChessBoard;
	}

	private ChessBoard() {
		this.chesses = new Chess[BOARD_ROAD][BOARD_ROAD];
	}

	public void addChess(BoardPoint point, Chess chess) {
		chesses[point.getX()][point.getY()] = chess;
	}

	public void removeChess(BoardPoint point) {
		chesses[point.getX()][point.getY()] = null;
	}

	public Chess getChess(BoardPoint point) {
		return chesses[point.getX()][point.getY()];
	}

	public Chess[][] getChesses() {
		return chesses;
	}
	
	/*
	 * This method use the last step provided by players,
	 * and judge if the player wins.
	 */
	public boolean isWin(BoardPoint point, Chess chess) {
		int[][] walker = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
		int directionFlag = 0;
		int count = 0;
		for (int[] curWalker : walker) {
			if (directionFlag % 2 == 0)
				count = 0;
			for (int i = point.getX(), j = point.getY(); i < BOARD_ROAD 
					&& i >= 0 && j < BOARD_ROAD && j >= 0 && chesses[i][j] != null
					&& chesses[i][j].equals(chess); i += curWalker[0], j += curWalker[1])
				count++;
			directionFlag++;
			if (count - 1 >= 5)
				return true;
		}
		return false;
	}
	
	public void clearBoard() {
		chesses = new Chess[BOARD_ROAD][BOARD_ROAD];
	}

//	// temp as follow.
//
//	public void print() {
//		int i, j;
//		for (i = 0; i < BOARD_ROAD; i++) {
//			for (j = 0; j < BOARD_ROAD; j++) {
//				if (chesses[i][j] != null)
//					System.out.print(chesses[i][j].toString());
//				else
//					System.out.print(" ");
//			}
//			System.out.println();
//		}
//	}
//
//	public static void main(String[] args) {
//		ChessBoard board = new ChessBoard();
//		board.addChess(10, 10, new Chess(ChessColor.BLACK));
//		board.addChess(11, 11, new Chess(ChessColor.BLACK));
//		board.addChess(12, 12, new Chess(ChessColor.BLACK));
//		board.addChess(13, 13, new Chess(ChessColor.BLACK));
//		board.addChess(14, 14, new Chess(ChessColor.BLACK));
//		board.print();
//		System.out.println(board.isWin(12, 12, new Chess(ChessColor.BLACK)));
//	}
}
