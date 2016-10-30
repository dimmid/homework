package entities;

import java.util.ArrayList;
import java.util.List;

import interfaces.XMLPrintable;

public class People implements XMLPrintable {

	private List<Person> persons;

	public People() {
		persons = new ArrayList<Person>();
	}

	public List<Person> getPersons() {
		return persons;
	}

	@Override
	public String printXml() {

		String content = "\n<people>";

		for (Person person : persons) {
			content += person.printXml();
		}

		content += "\n</people>";

		return content;
	}
}
