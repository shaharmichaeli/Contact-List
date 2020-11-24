package matala2;

import java.util.Iterator;

public class ContactIterator implements Iterator<Contact> {
	private ContactList list;
	private int currIndex;

	public ContactIterator(ContactList list) {
		super();
		this.list = list;
		this.currIndex = 0;
	}

	@Override
	public boolean hasNext() {
		if (list.get(currIndex) != null && currIndex < list.size()) {
			return true;
		}
		return false;
	}

	@Override
	public Contact next() {
		if (hasNext()) {
			currIndex++;
			return list.get(currIndex - 1);
		}
		return null;
	}

	public void remove() {
		if (currIndex < list.size() && list.get(currIndex) != null) {
			list.remove(currIndex);
			currIndex--;
		}
	}

}
