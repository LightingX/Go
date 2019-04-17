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
		backButton = new JButton("����");
		aDrawButton = new JButton("����");
		giveUpButton = new JButton("����");
		exitButton = new JButton("�˳�");
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
					JOptionPane.showMessageDialog(null, "��Ϸ��δ��ʼ��", "�����ڵ�", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
				if(JOptionPane.showConfirmDialog(null, playing.whoChessing().toOpposite() + "��������壬�Է��Ƿ�ͬ�⣿","����",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					playing.back(playing.whoChessing());
					JOptionPane.showMessageDialog(null, "����ɹ���", "����", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "�Է��ܾ����壡", "����", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		aDrawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playing.getStatus() != PlayingStatus.PLAYING && playing.getStatus() != PlayingStatus.PAUSE) {
					JOptionPane.showMessageDialog(null, "��Ϸ��δ��ʼ��", "���ڻ�û��̸�е�ʱ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(JOptionPane.showConfirmDialog(null, playing.whoChessing() + "��������壬�Է��Ƿ�ͬ�⣿","����",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					playing.aDraw(playing.whoChessing());
				else
					JOptionPane.showMessageDialog(null, "�Է��ܾ����壡", "����", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		giveUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playing.getStatus() != PlayingStatus.PLAYING && playing.getStatus() != PlayingStatus.PAUSE) {
					JOptionPane.showMessageDialog(null, "��Ϸ��δ��ʼ��", "��û��ʼ�������ˣ�", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(JOptionPane.showConfirmDialog(null, playing.whoChessing() + "��ȷ�����䣿","����",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					playing.giveUp(playing.whoChessing().toOpposite());
				else
					JOptionPane.showMessageDialog(null, "�Ҳ����䣡", "Ҫ����֪��", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "ȷ���˳���","�˳���Ϸ",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		
	}
	
	@Override
	public void update() {
		whiteTime.setText("������ʱ��" + playing.getBlackTime());
		blackTime.setText("������ʱ��" + playing.getWhiteTime());
		curChessing.setText("��ǰ���壺" + ((playing.getTurn() == 0)? "��" : playing.whoChessing().toString()));
		nowHand.setText("��" + playing.getTurn() + "��");
		playingController.setText(playing.getStatus().nextOperation());
	}
}
