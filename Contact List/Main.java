package matala2;

import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

// Shahar Michaeli 316535608

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		ContactList list = new ContactList();
		showMenu(s, list);

	}

	private static void showMenu(Scanner s, ContactList list) throws Exception {
		int opt;
		System.out.println("Contact Creator");
		System.out.println("1.Add/Update Contact\n" + "2.Remove contact\n" + "3.Save Contacts To File\n" + "4.Load Contacts From File\n"
				+ "5.Sort Contacts\n" + "6.Search Contact\n" + "7.Print All Contacts");

		do {
			System.out.println("\nChoose your option or any other key to EXIT.\nYour Option:");

			opt = s.nextInt();
			s.nextLine();
			switch (opt) {
				case 1 :
					add(list, s);
					break;
				case 2 :
					remove(list, s);
					break;

				case 3 :
					list.saveToFile();
					break;

				case 4 :
					list.loadFromFile();
					break;

				case 5 :
					sort(list, s);
					System.out.println(list);
					break;

				case 6 :
					search(list, s);
					break;
				case 7 :
					System.out.println(list);
					break;

				default :
					System.out.println("Bye bye");
					break;
			}
		} while (opt == 1 || opt == 2 || opt == 3 || opt == 4 || opt == 5 || opt == 6 || opt == 7);

	}

	private static void search(ContactList list, Scanner s) {
		System.out.println("SEARCH CONTACT:");
		String homephone, mobilephone;
		int index = -1;
		boolean flag = false;

		System.out.println("1.By Name\n2.By Home Phone\n3.By Mobile Phone");
		char opt = s.nextLine().charAt(0);
		switch (opt) {
			case '1' :
				String fname = askFNAME(s);
				String lname = askLNAME(s);
				Collections.sort(list, new SortByName());
				index = Collections.binarySearch(list, new Contact(fname, lname), new SortByName());
				if (index >= 0) {
					System.out.println("Result: " + list.get(index));
				} else {
					System.out.println("Result: Contact not found!");

				}

				break;

			case '2' :
				System.out.println("Enter Home Phone:");
				homephone = s.nextLine();
				flag = Pattern.matches("[0-9]{2}-?[0-9]{7}", homephone);
				if (flag) {
					Collections.sort(list, new SortByHome());
					index = Collections.binarySearch(list, new Contact("", "", homephone, ""), new SortByHome());
				} else {
					System.out.println("REMOVE CONTACT EXCEPTION : Valid home phone number!");
					return;
				}
				break;

			case '3' :
				System.out.println("Enter Mobile Phone:");
				mobilephone = s.nextLine();
				flag = Pattern.matches("[0-9]{3}-?[0-9]{7}", mobilephone);
				if (flag) {
					Collections.sort(list, new SortByMobile());
					index = Collections.binarySearch(list, new Contact("", "", "", mobilephone), new SortByMobile());
				} else {
					System.out.println("REMOVE CONTACT EXCEPTION : Valid mobile phone number!");
					return;
				}
				break;

			default :
				list.printContact(index);
				break;
		}

	}

	private static void sort(ContactList list, Scanner s) {
		System.out.println("SORT LIST CONTACTS:");
		System.out.println("1.By Name\n2.By Home Phone\n3.By Mobile Phone");
		char o = s.nextLine().charAt(0);
		switch (o) {
			case '1' :
				list.setComp(new SortByName());
				break;
			case '2' :
				list.setComp(new SortByHome());
				break;
			case '3' :
				list.setComp(new SortByMobile());
				break;
		}

	}

	private static void remove(ContactList list, Scanner s) {
		String homephone, mobilephone;
		int index = -1;
		String areYouSure;
		boolean flag = false;

		System.out.println("REMOVE CONTACT :");
		System.out.println("1.By Name\n2.By Home Phone\n3.By Mobile Phone");
		char opt = s.nextLine().charAt(0);
		switch (opt) {
			case '1' :
				String fname = askFNAME(s);
				String lname = askLNAME(s);
				Collections.sort(list, new SortByName());
				index = Collections.binarySearch(list, new Contact(fname, lname), new SortByName());

				break;

			case '2' :
				System.out.println("Enter Home Phone:");
				homephone = s.nextLine();
				flag = Pattern.matches("[0-9]{2}-?[0-9]{7}", homephone);
				if (flag) {
					Collections.sort(list, new SortByHome());
					index = Collections.binarySearch(list, new Contact("", "", homephone, ""), new SortByHome());
				} else {
					System.out.println("REMOVE CONTACT EXCEPTION : Valid home phone number!");
					return;
				}
				break;

			case '3' :
				System.out.println("Enter Mobile Phone:");
				mobilephone = s.nextLine();
				flag = Pattern.matches("[0-9]{3}-?[0-9]{7}", mobilephone);
				if (flag) {
					Collections.sort(list, new SortByMobile());
					index = Collections.binarySearch(list, new Contact("", "", "", mobilephone), new SortByMobile());
				} else {
					System.out.println("REMOVE CONTACT EXCEPTION : Valid mobile phone number!");
					return;
				}
				break;

			default :
				break;
		}

		System.out.print("Result : " + list.get(index));
		System.out.println("\nAre you sure? y/n:");
		areYouSure = s.nextLine();
		if (areYouSure.equals("y") || areYouSure.equals("Y")) {
			if (list.remove(index) == null) {
				System.out.println("Result : Contact not found!");
			} else {
				System.out.println("Result : Contact removed!");
			}
		}
		list.sort();

	}

	private static void add(ContactList list, Scanner s) {
		int size = list.size();
		String homephone, mobilephone;
		boolean hC = false, mC = false, flag = false;
		System.out.println("ADD/UPDATE CONTACT:");
		String fname = askFNAME(s);
		String lname = askLNAME(s);
		System.out.println("Enter Home Phone:");
		homephone = s.nextLine();
		hC = Pattern.matches("[0-9]{2}-?[0-9]{7}", homephone);

		System.out.println("Enter Mobile Phone:");
		mobilephone = s.nextLine();
		mC = Pattern.matches("[0-9]{3}-?[0-9]{7}", mobilephone);
		if (hC == false && mC == false) {
			System.out.println("ADD/UPDATE CONTACT EXCEPTION: You must enter at least one valid phone number!");
			return;
		} else if (mC == false) {
			flag = list.add(new Contact(fname, lname, homephone, ""));
		} else if (hC == false) {
			flag = list.add(new Contact(fname, lname, "", mobilephone));
		} else {
			flag = list.add(new Contact(fname, lname, homephone, mobilephone));
		}

		if (flag) {
			if (size == list.size()) {
				System.out.println("Result : Contact updated!");
			} else {
				System.out.println("Result : Contact added!");
			}
		}

	}

	private static String askLNAME(Scanner s) {
		String n;
		System.out.println("Enter Last Name:");
		n = s.nextLine();
		while (n.length() < 3) {
			System.out.println("ADD/UPDATE CONTACT EXCEPTION: Last name can not be less then 3 letters!\nEnter again:");
			n = s.nextLine();
		}
		return n;
	}

	private static String askFNAME(Scanner s) {
		String n;
		System.out.println("Enter First Name:");
		n = s.nextLine();
		while (n.length() < 3) {
			System.out.println("ADD/UPDATE CONTACT EXCEPTION: First name can not be less then 3 letters!\nEnter again:");
			n = s.nextLine();
		}
		return n;

	}
}