package matala2;

import java.util.Comparator;

public class SortByHome implements Comparator<Contact> {

	@Override
	public int compare(Contact c1, Contact c2) {
		return c1.getHomephone().compareTo(c2.getHomephone());
	}

}
