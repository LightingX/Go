package goModel;

public enum PlayingStatus {
	PRE_PLAYING,
	PLAYING,
	PAUSE,
	END;
	
	public String nextOperation() {
		switch(this) {
		case PRE_PLAYING:
			return "开始";
		case PLAYING:
			return "暂停";
		case PAUSE:
			return "继续";
		case END:
			return "再来一局";
		}
		return null;
	}
}
