package control;

import view.MainFrame;

public class GoBang {
	
	private ChessPlaying playing;
	private MainFrame frame;
	
	public static void main(String[] args) {
		GoBang goBang = new GoBang();
		goBang.playing = new ChessPlaying();
		MainFrame frame = new MainFrame(goBang.playing);
		goBang.playing.setFrame(frame);
	}
}
