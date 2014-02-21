package UserPackage;

import java.util.ArrayList;

// import YySupportPackage.JavaLib;
// import YySupportPackage.JavaNode;
// import YySupportPackage.JavaStringValue;
import Gently.*;

public class UserCode {

	public static String[][] XCA_UserData(int offset) {
		int len = 3;
		String[][] res = new String[3][];
		for (int i = 0; i < len; i++) {
			res[i] = new String[4];
			res[i][0] = "didel";
			res[i][1] = "" + offset;
			res[i][2] = "0";
			res[i][3] = "" + (offset + 7);
		}
		return res;

	}

	public static String XTH_UserData(JavaNode n, int offset) {
		int from = n.getCoordinate().getOffset();
		int len = n.getCoordinate().getLength();
		int upto = from + len - 1;
		String res = null;

		if (len == -1)
			return null;
		
		/*
		 if (from <= offset && offset <= upto) {
			
			res = "[" + offset + n.Text();

			for (JavaNode ch : n.Child1) {
				String ans = XTH_UserData(ch, offset);
				if (ans != null) {
					res = res + "\n" + ans;
					break;
				}
			}
		}
		*/
			
	    res = "/XTH_UserData/";
			
		return res;

	}

	public static Object[] XOL_UserData(JavaNode parseresult) {
		Generated.YyProcs.OUTLINE(parseresult);
		JavaNode Outline = JavaLib.yyoutvalue1;

		Object[] result = BuildOutlineTree(Outline);
		return result;
	}

	private static Object[] BuildOutlineTree(JavaNode EntryList) {
		//System.out.println("Outline: BuildOutlineTree");
		ArrayList<OutlineTupel> result = new ArrayList<OutlineTupel>();
		String Tag = EntryList.getType();

		while (Tag.equals("SeqOf-OutlineEntry")) {

			JavaNode Head = EntryList.Child1[0];

			JavaNode Tail = EntryList.Child1[1];

			// next
			EntryList = Tail;
			Tag = EntryList.getType();

			// process current

			// a0 name
			JavaNode a0 = Head.Child1[0];
			JavaStringValue sv = (JavaStringValue) a0;
			String str = sv.Str;

			// a1 bild
			JavaNode a1 = Head.Child1[1];
			JavaStringValue sv1 = (JavaStringValue) a1;
			String bild = sv1.Str;

			// a2 node
			JavaNode a2 = Head.Child1[2];
			int offs = a2.getCoordinate().getOffset();
			int len = a2.getCoordinate().getLength();

			// a3
			JavaNode a3 = Head.Child1[3];
			Object[] result3 = BuildOutlineTree(a3);

			OutlineTupel arg0 = new OutlineTupel(str, bild, offs, len, result3, false);
			result.add(arg0);
		}

		return result.toArray();
	}

}
