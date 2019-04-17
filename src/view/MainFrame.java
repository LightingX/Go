package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import control.ChessPlaying;

public class MainFrame extends JFrame {
	
	ChessBoardPanel chessBoard;
	ControlPanel control;
	ChessPlaying playing;
	
	public MainFrame(ChessPlaying playing) throws HeadlessException {
		this.playing = playing;
		this.setLayout(new BorderLayout());
		this.chessBoard = new ChessBoardPanel(playing);
		this.control = new ControlPanel(playing);
		this.add(BorderLayout.CENTER, chessBoard);
		this.add(BorderLayout.WEST, control);
		this.setTitle("BangBangÎå×ÓÆå");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 850, 800);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public ChessBoardPanel getChessBoard() {
		return chessBoard;
	}
	
	public ControlPanel getControl() {
		return control;
	}
	
	public void controlReset() {
		this.add(BorderLayout.WEST, control);
	}
	
	public void ChessBoardReset() {
		this.chessBoard = new ChessBoardPanel(playing);
	}
}
