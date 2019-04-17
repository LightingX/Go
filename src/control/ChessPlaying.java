package control;

import java.awt.*;
import java.awt.event.MouseEvent;
import goModel.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.*;

public class ChessPlaying implements Observer {

	private ChessBoard theChessBoard = ChessBoard.getChessBoard();
	private Recorder recorder;
	private MainFrame frame;
	private int turn;
	private ResponsibilityTimer timer;
	private PlayingStatus status;
	
	public ChessPlaying() {
		status = PlayingStatus.PRE_PLAYING;
		timer = new ResponsibilityTimer();
		timer.setPlaying(this);
		recorder = new Recorder();
		turn = 0;
	}
	
	public void setFrame(MainFrame frame) {
		this.frame = frame;
		timer.setControlView(frame.getControl());
	}
	
	public int getTurn() {
		return turn;
	}
	
	public PlayingStatus getStatus() {
		return status;
	}
	
	public ChessColor whoChessing() {
		return (turn % 2 == 0)? ChessColor.WHITE : ChessColor.BLACK;
	}
	
	public boolean canDown(BoardPoint point) {
		return (theChessBoard.getChess(point) == null);
	}
	
	public boolean canBack(BoardPoint point) {
		return !canDown(point);
	}
	
	public Time getBlackTime() {
		return timer.getTime(ChessColor.BLACK);
	}
	
	public Time getWhiteTime() {
		return timer.getTime(ChessColor.WHITE);
	}
	
	public void opening() {
		turn = 1;
		timer.start();
		status = PlayingStatus.PLAYING;
		frame.getControl().update();
	}
	
	public void chessDown(BoardPoint point){
		if(!status.equals(PlayingStatus.PLAYING))
			return;
		if(!this.canDown(point))
			return;
		ChessColor curOperator = whoChessing();
		Chess chess = new Chess(curOperator);
		theChessBoard.addChess(point, chess);
		Time time = timer.getCurRunningClockTime();
		recorder.addRecord(time, turn, curOperator, point);
		timer.switchClock();
		frame.getChessBoard().repaint();
		turn++;
		if(theChessBoard.isWin(point, chess))
			gameOver(curOperator);
	}
	
	public void back(ChessColor operator) {
		Record lastStep = recorder.getTopRecord();
		BoardPoint lastPoint = lastStep.getOperationPoint();
		Record last2Step = recorder.getTopIRecord(2);
		BoardPoint last2Point = last2Step.getOperationPoint();
		if(canBack(lastPoint)) {
			theChessBoard.removeChess(lastPoint);
			recorder.removeTopRecord();
			turn--;
		}
		if(operator.equals(whoChessing()) && canBack(last2Point)) {
			theChessBoard.removeChess(last2Point);
			recorder.removeTopRecord();
			turn--;
		}
		frame.getChessBoard().repaint();
		frame.getControl().update();
	}
	
	public void gamePause() {
		timer.pauseTiming();
		status = PlayingStatus.PAUSE;
		frame.getControl().update();
	}
	
	public void gameContinue() {
		timer.continueTiming();
		status = PlayingStatus.PLAYING;
		frame.getControl().update();
	}
	
	public void giveUp(ChessColor operator) {
		gameOver(operator);
	}
	
	public void aDraw(ChessColor operator) {
		gameOver(null);
	}
	
	public void gameOver(ChessColor winner) {
		timer.pauseTiming();
		status = PlayingStatus.END;
		JOptionPane.showMessageDialog(null, (winner == null ? "????" :(winner.toString() + "???????")), "???????", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void restart() {
		theChessBoard.clearBoard();
		recorder.clear();
		timer = new ResponsibilityTimer();
		frame.controlReset();
		timer.setControlView(frame.getControl());
		timer.setPlaying(this);
		turn = 0;
		status = PlayingStatus.PRE_PLAYING;
		frame.getChessBoard().repaint();
		frame.getControl().update();
	}
	
	@Override
	public void update() {
		if(this.getBlackTime().toSecond() == 0)
			gameOver(ChessColor.BLACK);
		else if(this.getWhiteTime().toSecond() == 0)
			gameOver(ChessColor.WHITE);
	}
	
}