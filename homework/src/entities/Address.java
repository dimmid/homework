package entities;

import exceptions.ParseException;
import interfaces.XMLPrintable;

public class Address implements XMLPrintable {

	private String street;
	private String city;
	private String zip;

	public Address(String metadata) {
		if (metadata == null || !metadata.startsWith("A|")) {
			throw new ParseException("Malformed input data. Can't find the item \"A|\" to create an address");
		}
		// Remove trailing and leading white spaces
		metadata = metadata.trim();

		// Get zip
		int cutIndex = metadata.lastIndexOf("|") + 1;
		zip = metadata.substring(cutIndex, metadata.length()).trim();

		// Reset metadata
		metadata = metadata.substring(0, cutIndex - 1);

		// Get city
		cutIndex = metadata.lastIndexOf("|");
		city = metadata.substring(cutIndex + 1, metadata.length()).trim();

		// Reset metadata
		metadata = metadata.substring(0, cutIndex);

		// Get street
		cutIndex = metadata.lastIndexOf("|");
		street = metadata.substring(cutIndex + 1, metadata.length()).trim();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String printXml() {

		String content = "\n    <address>";

		if (street != null && !street.isEmpty()) {
			content += "\n      <street>" + street + "</street>";
		}
		if (city != null && !city.isEmpty()) {
			content += "\n      <city>" + city + "</city>";
		}
		if (zip != null && !zip.isEmpty()) {
			content += "\n      <zip>" + zip + "</zip>";
		}

		content += "\n    </address>";

		return content;
	}

}
