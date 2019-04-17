package goModel;

public enum PlayingStatus {
	PRE_PLAYING,
	PLAYING,
	PAUSE,
	END;
	
	public String nextOperation() {
		switch(this) {
		case PRE_PLAYING:
			return "��ʼ";
		case PLAYING:
			return "��ͣ";
		case PAUSE:
			return "����";
		case END:
			return "����һ��";
		}
		return null;
	}
}
