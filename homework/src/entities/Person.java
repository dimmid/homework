package entities;

import java.util.ArrayList;
import java.util.List;

import exceptions.ParseException;
import interfaces.XMLPrintable;

public class Person implements XMLPrintable {

	private String firstname;
	private String lastname;
	private Address address;
	private List<Family> families;
	private Phone phone;

	public Person(String metadata) {
		if (metadata == null || !metadata.startsWith("P|")) {
			throw new ParseException("Malformed input data. Can't find the item \"P|\" to create a Person");
		}

		setFamilies(new ArrayList<Family>());

		// Remove trailing and leading white spaces
		metadata = metadata.trim();

		// Get lastname
		int cutIndex = metadata.lastIndexOf("|") + 1;
		lastname = metadata.substring(cutIndex, metadata.length()).trim();

		// Reset metadata
		metadata = metadata.substring(0, cutIndex - 1);

		// Get lastname
		cutIndex = metadata.lastIndexOf("|");
		firstname = metadata.substring(cutIndex + 1, metadata.length()).trim();
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public List<Family> getFamilies() {
		return families;
	}

	public void setFamilies(List<Family> families) {
		this.families = families;
	}

	@Override
	public String printXml() {

		String content = "\n  <person>";

		if (firstname != null && !firstname.isEmpty()) {
			content += "\n    <firstname>" + firstname + "</firstname>";
		}
		if (lastname != null && !lastname.isEmpty()) {
			content += "\n    <lastname>" + lastname + "</lastname>";
		}

		if (address != null) {
			content += address.printXml();
		}

		if (phone != null) {
			content += phone.printXml();
		}

		for (Family family : families) {
			content += family.printXml();
		}
		content += "\n  <person>";

		return content;
	}

}
