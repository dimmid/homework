package entities;

import exceptions.ParseException;
import interfaces.XMLPrintable;

public class Phone implements XMLPrintable {

	private String mobile;
	private String home;

	public Phone(String metadata) {
		if (metadata == null || !metadata.startsWith("T|")) {
			throw new ParseException("Malformed input data. Can't find the item \"T|\" to create phone");
		}
		// Remove trailing and leading white spaces
		metadata = metadata.trim();

		// Get home
		int cutIndex = metadata.lastIndexOf("|") + 1;
		home = metadata.substring(cutIndex, metadata.length()).trim();

		// Reset metadata
		metadata = metadata.substring(0, cutIndex - 1);

		// Get mobile
		cutIndex = metadata.lastIndexOf("|");
		mobile = metadata.substring(cutIndex + 1, metadata.length()).trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	@Override
	public String printXml() {

		String content = "\n    <phone>";

		if (mobile != null && !mobile.isEmpty()) {
			content += "\n      <mobile>" + mobile + "</mobile>";
		}
		if (home != null && !home.isEmpty()) {
			content += "\n      <home>" + home + "</home>";
		}

		content += "\n    </phone>";

		return content;
	}

}
