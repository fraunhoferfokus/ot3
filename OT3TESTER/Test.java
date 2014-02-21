import java.util.LinkedList;

class Test {
   public String FileName; // TTCN-3: module name
	public String TestId; // = Id of Test
	public LinkedList<Section> Sections = new LinkedList<Section>();

	public Section GetSection(String id) {
		for (Section S : Sections) {
			if (S.Title.equals(id)) {
				return S;
			}
		}
		return null;
	}
}
