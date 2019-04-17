package view;

import java.awt.*;
import java.awt.event.*;
import goModel.*;

import javax.swing.*;
import control.ChessPlaying;

public class ControlPanel extends Panel implements Observer {
	
	JLabel whiteTime;
	JLabel blackTime;
	JLabel curChessing;
	JLabel nowHand;
	JButton playingController;
	JButton aDrawButton;
	JButton backButton;
	JButton giveUpButton;
	JButton exitButton;
	
	ChessPlaying playing;
	
	public ControlPanel(ChessPlaying playing) {
		this.setLayout(new GridLayout(9,1));
		this.playing = playing;
		whiteTime = new JLabel();
		whiteTime.setHorizontalAlignment(SwingConstants.CENTER);
		blackTime = new JLabel();
		blackTime.setHorizontalAlignment(SwingConstants.CENTER);
		curChessing = new JLabel();
		curChessing.setHorizontalAlignment(SwingConstants.CENTER);
		nowHand = new JLabel();
		nowHand.setHorizontalAlignment(SwingConstants.CENTER);
		playingController = new JButton();
		this.update();
		backButton = new JButton("悔棋");
		aDrawButton = new JButton("和棋");
		giveUpButton = new JButton("认输");
		exitButton = new JButton("退出");
		this.add(nowHand);
		this.add(blackTime);
		this.add(whiteTime);
		this.add(curChessing);
		this.add(playingController);
		this.add(backButton);
		this.add(aDrawButton);
		this.add(giveUpButton);
		this.add(exitButton);
		this.setVisible(true);
		this.addListener();
	}
	
	public void addListener() {
		playingController.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(playing.getStatus()) {
				case PRE_PLAYING : 
					playing.opening();
					return;
				case PAUSE :
					playing.gameContinue();
					return;
				case PLAYING :
					playing.gamePause();
					return;
				case END :
					playing.restart();
				}
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playing.getStatus() != PlayingStatus.PLAYING && playing.getStatus() != PlayingStatus.PAUSE) {
					JOptionPane.showMessageDialog(null, "游戏尚未开始！", "不存在的", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
				if(JOptionPane.showConfirmDialog(null, playing.whoChessing().toOpposite() + "方请求悔棋，对方是否同意？","悔棋",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					playing.back(playing.whoChessing());
					JOptionPane.showMessageDialog(null, "悔棋成功！", "悔棋", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "对方拒绝悔棋！", "悔棋", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		aDrawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playing.getStatus() != PlayingStatus.PLAYING && playing.getStatus() != PlayingStatus.PAUSE) {
					JOptionPane.showMessageDialog(null, "游戏尚未开始！", "现在还没到谈判的时间", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(JOptionPane.showConfirmDialog(null, playing.whoChessing() + "方请求和棋，对方是否同意？","和棋",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					playing.aDraw(playing.whoChessing());
				else
					JOptionPane.showMessageDialog(null, "对方拒绝和棋！", "和棋", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		giveUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playing.getStatus() != PlayingStatus.PLAYING && playing.getStatus() != PlayingStatus.PAUSE) {
					JOptionPane.showMessageDialog(null, "游戏尚未开始！", "还没开始就认输了？", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(JOptionPane.showConfirmDialog(null, playing.whoChessing() + "方确认认输？","认输",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					playing.giveUp(playing.whoChessing().toOpposite());
				else
					JOptionPane.showMessageDialog(null, "我不认输！", "要苍天知道", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确认退出？","退出游戏",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		
	}
	
	@Override
	public void update() {
		whiteTime.setText("黑棋用时：" + playing.getBlackTime());
		blackTime.setText("白棋用时：" + playing.getWhiteTime());
		curChessing.setText("当前行棋：" + ((playing.getTurn() == 0)? "无" : playing.whoChessing().toString()));
		nowHand.setText("第" + playing.getTurn() + "手");
		playingController.setText(playing.getStatus().nextOperation());
	}
}
