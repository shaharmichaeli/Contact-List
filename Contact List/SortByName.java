package matala2;

import java.util.Comparator;

public class SortByName implements Comparator<Contact> {

	@Override
	public int compare(Contact c1, Contact c2) {
		int res = 0;
		res = c1.getLastname().compareTo(c2.getLastname());
		if (res != 0) {
			return res;
		}
		return c1.getFirstname().compareTo(c2.getFirstname());
	}

}
