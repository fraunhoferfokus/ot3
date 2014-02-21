package UserPackage;

import org.eclipse.jface.text.ITypedRegion;

public class OutlineTupel implements ITypedRegion {
	
	public String bildname = "peng";
	public String aufschrift;
	public int laenge;
	public int abstand;
	public Object[] kinder;
	
	public OutlineTupel(String name, String bild, int offs, int len, Object[] children, boolean hk) {
		
		bildname = bild;
		aufschrift = name;
		laenge = len;
		abstand = offs;
		kinder = children;
		
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return laenge;
	}

	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return abstand;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return aufschrift;
	}

}
