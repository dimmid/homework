package entities;

import exceptions.ParseException;
import interfaces.XMLPrintable;

public class Family implements XMLPrintable {

	private String name;
	private String born;
	private Address address;
	private Phone phone;

	public Family(String metadata) {
		if (metadata == null || !metadata.startsWith("F|")) {
			throw new ParseException("Malformed input data. Can't find the item \"F|\" to create a family");
		}
		// Remove trailing and leading white spaces
		metadata = metadata.trim();

		// Get born
		int cutIndex = metadata.lastIndexOf("|") + 1;
		born = metadata.substring(cutIndex, metadata.length()).trim();

		// Reset metadata
		metadata = metadata.substring(0, cutIndex - 1);

		// Get name
		cutIndex = metadata.lastIndexOf("|");
		name = metadata.substring(cutIndex + 1, metadata.length()).trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBorn() {
		return born;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	@Override
	public String printXml() {

		String content = "\n  <family>";

		if (name != null && !name.isEmpty()) {
			content += "\n    <name>" + name + "</name>";
		}
		if (born != null && !born.isEmpty()) {
			content += "\n    <born>" + born + "</born>";
		}

		if (address != null) {
			content += address.printXml();
		}

		if (phone != null) {
			content += phone.printXml();
		}

		content += "\n  </family>";

		return content;
	}

	public Phone getPhone() {
		return phone;
	}
}
