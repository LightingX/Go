package goModel;

public class Chess {
	
	private ChessColor color;
	
	public ChessColor getColor() {
		return color;
	}
	
	public Chess(ChessColor color) {
		this.color = color;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Chess)
			if(((Chess) obj).color.equals(this.color))
				return true;
		return false;
	}
	
//	//temp as follow.
//	
//	public String toString() {
//		switch(color) {
//			case BLACK : return "��";
//			case WHITE : return "��";
//			default : return " ";
//		}
//	}
}
