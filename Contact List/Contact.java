package matala2;

import java.io.Serializable;

public class Contact implements Serializable, Comparable<Contact> {

	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String homephone;
	private String phone;

	public Contact(String firstname, String lastname, String homephone, String phone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.homephone = homephone;
		this.phone = phone;
	}

	public Contact(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return this.firstname + " " + this.lastname + ": " + this.homephone + " " + this.phone;
	}

	@Override
	public int compareTo(Contact o) {
		int res = this.getLastname().compareTo(o.getLastname());
		if (res != 0) {
			return res;
		}
		return this.getFirstname().compareTo(o.getFirstname());
	}
}
