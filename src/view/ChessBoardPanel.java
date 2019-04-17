package view;

import goModel.*;
import control.*;
import javax.swing.*;
import javax.swing.plaf.synth.SynthSeparatorUI;

import java.awt.*;
import java.awt.event.*;

public class ChessBoardPanel extends JPanel {
	
	private static final int BOARD_ROAD = 15;
	private static final int PANEL_SIZE = 710;
	private static final int BORDER_DISTANCE = 40;
	private static final int CHESS_RADIUS = 20;
	private static final int cellSize = (PANEL_SIZE - 2*BORDER_DISTANCE) / (BOARD_ROAD-1);
	private ChessBoard theChessBoard = ChessBoard.getChessBoard();
	private ChessPlaying playing;
	
	public ChessBoardPanel(ChessPlaying playing) {
		super();
		this.playing = playing;
		this.setSize(PANEL_SIZE, PANEL_SIZE);
		this.setBackground(new Color(240,230,140));
		this.addListener();
	}
	
	public void addListener() {
		this.addMouseListener(new MouseAdapter() {
//			public void mouseEntered(MouseEvent e) {
//				
//			}
			public void mouseReleased(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				if(e.getButton() == MouseEvent.BUTTON1) {
					double chessArea = cellSize / 2.0;
					int x = ((int) (mouseX - BORDER_DISTANCE + chessArea)) / cellSize;
					int y = ((int) (mouseY - BORDER_DISTANCE + chessArea)) / cellSize;
					x = Math.max(0, x);
					x = Math.min(BOARD_ROAD-1, x);
					y = Math.max(0, y);
					y = Math.min(BOARD_ROAD-1, y);
					playing.chessDown(new BoardPoint(x, y));
				}
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.paintChessBoard(g);
		this.paintChess(g);
	}
	
	public void paintChess(Graphics g) {
		final int CHESS_DIAMETER = CHESS_RADIUS * 2;
		for(int i = 0 ; i < BOARD_ROAD; i++) 
			for(int j = 0; j < BOARD_ROAD; j++)  {
				BoardPoint point = new BoardPoint(i, j);
				if(theChessBoard.getChess(point) == null)
					continue;
				g.setColor(theChessBoard.getChess(point).getColor().toColor());
				g.fillOval(BORDER_DISTANCE + i * cellSize - CHESS_RADIUS, 
						BORDER_DISTANCE + j * cellSize - CHESS_RADIUS, CHESS_DIAMETER, CHESS_DIAMETER);
			}
	}
	
	public void paintChessBoard(Graphics g) {
		for(int i = 0; i < BOARD_ROAD; i++)
			g.drawLine(BORDER_DISTANCE, BORDER_DISTANCE + i * cellSize, 
					BORDER_DISTANCE + cellSize * (BOARD_ROAD - 1), BORDER_DISTANCE + i * cellSize);
		for(int j = 0;j < BOARD_ROAD; j++)
			g.drawLine(BORDER_DISTANCE + j * cellSize, BORDER_DISTANCE, 
					BORDER_DISTANCE + j * cellSize, BORDER_DISTANCE + cellSize * (BOARD_ROAD - 1));
	}
}