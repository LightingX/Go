package goModel;

import java.awt.Color;

public enum ChessColor {
	BLACK, WHITE;

	public int toInteger() {
		switch (this) {
		case BLACK:
			return 1;
		case WHITE:
			return -1;
		}
		return 0;
	}

	public Color toColor() {
		switch (this) {
		case WHITE:
			return Color.WHITE;
		case BLACK:
			return Color.BLACK;
		}
		return new Color(255, 255, 255, 0);
	}
	
	public String toString() {
		switch(this) {
		case BLACK:
			return "ºÚ";
		case WHITE:
			return "°×";
		}
		return null;
	}
	
	public ChessColor toOpposite() {
		if(this.equals(BLACK))
			return WHITE;
		else if(this.equals(WHITE))
			return BLACK;
		else return null;
	}
}
