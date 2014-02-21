package Interface;

import java.util.HashMap;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorManager {

	static HashMap<RGB, Color> fColorTable = new HashMap<RGB, Color>(10);

	private Color getColor(RGB rgb) {
		Color color = (Color) fColorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}
		return color;
	}

	public IToken[] TokenTab = new IToken[] {
	      
			// 0 black
			new Token(new TextAttribute(getColor(new RGB(0, 0, 0)))),
			// 1 red
			new Token(new TextAttribute(getColor(new RGB(255, 0, 0)))),
			// 2 green
			new Token(new TextAttribute(getColor(new RGB(0, 255, 0)))),
			// 3 blue
			new Token(new TextAttribute(getColor(new RGB(0, 0, 255)))),
			// 4 deepskyblue
			new Token(new TextAttribute(getColor(new RGB(0, 191, 255)))),
			// 5 lime
			new Token(new TextAttribute(getColor(new RGB(0, 255, 0)))),
			// 6 midnightblue
			new Token(new TextAttribute(getColor(new RGB(25, 25, 112)))),
			// 7 aqua
			new Token(new TextAttribute(getColor(new RGB(0, 255, 255)))),
			// 8 mediumseagreen
			new Token(new TextAttribute(getColor(new RGB(60, 179, 113)))),
			// 9 indigo
			new Token(new TextAttribute(getColor(new RGB(75, 0, 130)))),
			// 10 lightslategray
			new Token(new TextAttribute(getColor(new RGB(119, 136, 153)))),
			// 11 darkred
			new Token(new TextAttribute(getColor(new RGB(139, 0, 0)))),
			// 12 lightgreen
			new Token(new TextAttribute(getColor(new RGB(144, 238, 144)))),
			// 13 brown
			new Token(new TextAttribute(getColor(new RGB(165, 42, 42)))),
			// 14 darkgoldenrod
			new Token(new TextAttribute(getColor(new RGB(184, 134, 11)))),
			// 15 rosybrown
			new Token(new TextAttribute(getColor(new RGB(188, 143, 143)))),
			// 16 darkkhaki
			new Token(new TextAttribute(getColor(new RGB(189, 183, 107)))),
			// 17 silver
			new Token(new TextAttribute(getColor(new RGB(192, 192, 192)))),
			// 18 salmon
			new Token(new TextAttribute(getColor(new RGB(250, 128, 114)))),
			// 19 magenta
			new Token(new TextAttribute(getColor(new RGB(255, 0, 255)))),
			// 20 tomato
			new Token(new TextAttribute(getColor(new RGB(255, 99, 71)))),
			// 21 orange
			new Token(new TextAttribute(getColor(new RGB(255, 165, 0)))),
			// 22 yellow
			new Token(new TextAttribute(getColor(new RGB(255, 255, 0)))),
			// 23 crimson
			new Token(new TextAttribute(getColor(new RGB(220, 20, 60))))
			
			};

}
