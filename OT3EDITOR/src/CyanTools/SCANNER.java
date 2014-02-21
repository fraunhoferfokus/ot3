package CyanTools;

import java.io.StringReader;

import Generated.YyTokens;
import Generated.Yylex;
import Gently.GrammarLib;
import Interface.*;

import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class SCANNER implements ITokenScanner {
	Yylex lexer;
	int rangeoffset = -1;
	
	int TOKENOFFSET;
    int TOKENLENGTH;

    // ------------------------------------------------------

	public SCANNER() { // used in Umbra.Configuration / PresentationReconceiler
	}

	
	@Override()
	public IToken nextToken() {
	   //System.out.println("+++ Scanner nextToken called");

		int val = 0;
		IToken res;
		try {
			val = lexer.yylex(); // !!! yylex !!!
			

			Gently.JavaNode lexvalnode = Gently.LexerState.LEXERSTATE_yylval;
			int offset = lexvalnode.getCoordinate().getOffset();
			int length = lexvalnode.getCoordinate().getLength();

			TOKENOFFSET = rangeoffset + offset;
			TOKENLENGTH = length;

			res = ConvertedToken(val);
		} catch (Exception e) {
			TOKENOFFSET = rangeoffset;
			TOKENLENGTH = 0;

			res = Token.EOF;
		}

		return res;
	}

	@Override()
	public int getTokenLength() {
	   //System.out.println("+++ Scanner getTokenLength called");
		return TOKENLENGTH;
	}

	@Override()
	public int getTokenOffset() {
	   //System.out.println("+++ Scanner getTokenOffset called");
		return TOKENOFFSET;
	}

	@Override()
	public void setRange(IDocument document, int offset, int length) {
	   //System.out.println("+++ Scanner setRange called");
		rangeoffset = offset;
		lexer = CreateScanner(document, offset, length);
	}
	
	// --------------------------------------------------------------------------

	private IToken ConvertedToken(int val) {
		if (val == lexer.YYEOF) {
			return Token.EOF;
		}
		if (val == GrammarLib.WhitespaceToken) {
			return EDITOR.CM_for_Scanner.TokenTab[12];
		}

		int colorcode = YyTokens.GetColor(val);
		return EDITOR.CM_for_Scanner.TokenTab[colorcode];

	}

	private static Yylex CreateScanner(IDocument document, int offset,
			int length) {
		try {
			String dirtyRegion = document.get(offset, length);
			java.io.Reader rdr = new StringReader(dirtyRegion);
			Yylex lexer = GrammarLib.CyanScanner(rdr, offset);
			return lexer;
		} catch (BadLocationException e) {
			System.out.println("exception in create scanner");
			return null;
		}
	}
}
