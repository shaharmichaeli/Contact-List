package matala2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class ContactList extends LinkedList<Contact> implements Iterable<Contact> {
	private static final long serialVersionUID = 1L;
	private final File FNAME = new File("contacts.obj");
	Comparator<Contact> comp;

	public ContactList() {
		super();
		this.comp = new SortByName();
	}

	public void saveToFile() throws FileNotFoundException, IOException {
		try (ObjectOutputStream objS = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FNAME)))) {
			objS.writeInt(size());
			for (int i = 0; i < size(); i++) {
				objS.writeObject(get(i));
			}
			System.out.println("Contacts saved to file : " + size());
		} catch (FileNotFoundException e) {
			System.out.println("Save File Error : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Save File Error : " + e.getMessage());
		}
	}

	public void loadFromFile() throws FileNotFoundException, IOException {
		int updates = 0, add = 0, size = size();
		boolean isLoaded = false;
		try (ObjectInputStream objL = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FNAME)))) {
			int CAmount = objL.readInt();
			if (CAmount == 0) {
				System.out.println("File is empty!");
				return;
			}
			for (int i = 0; i < CAmount; i++) {
				isLoaded = add((Contact) objL.readObject());
				if (isLoaded) {
					if (size == size()) {
						updates++;
					} else {
						add++;
					}
				}
			}

			System.out.println(CAmount + " Contacts loaded! Added: " + add + " Updated: " + updates);

		} catch (ClassNotFoundException e) {
			System.out.println("Load File Error : " + e.getMessage());
		}
	}

	public boolean add(Contact c) {
		if (c == null) {
			return false;
		}
		Collections.sort(this, new SortByName());
		int index = Collections.binarySearch(this, c, new SortByName());
		if (index >= 0) {
			super.set(index, c);
		} else {
			super.add(Math.abs(index + 1), c);
		}
		sort();
		return true;
	}

	public Contact get(int index) {
		return super.get(index);
	}

	public void sort() {
		Collections.sort(this, comp);
	}

	public int size() {
		return super.size();
	}

	public ContactIterator Iterator() {
		return new ContactIterator(this);  
	}

	public Contact remove(int index) {
		if (index >= 0 && index < size()) {
			Contact c = get(index);
			super.remove(index);
			return c;
		}
		return null;
	}

	public void printContact(int index) {
		if (index >= 0 && index < size()) {
			System.out.println("Result : " + super.get(index));
		}
		System.out.println("Result : Contact not found!");
	}

	public void setComp(Comparator<Contact> comp) {
		this.comp = comp;
		sort();
	}

	@Override
	public String toString() {
		String str = "Contacts:\n";
		String ch = "";
		if (size() == 0) {
			return "Contact List is empty!";
		} else {
			for (int i = 0; i < size(); i++) {
				if (comp instanceof SortByName && !ch.equals(get(i).getLastname().substring(0, 1))) {
					ch = get(i).getLastname().substring(0, 1);
					str += ch.toUpperCase() + ":\n";
				}
				str += get(i).toString() + "\n";
			}
		}
		return str;
	}

}
