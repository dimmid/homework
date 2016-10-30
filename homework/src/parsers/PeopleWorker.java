package parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entities.Address;
import entities.Family;
import entities.People;
import entities.Person;
import entities.Phone;
import exceptions.ParseException;

public class PeopleWorker {

	public static void main(String args[]) {

		String fileName = "c:/testa/SomeData.txt";

		if (args.length == 1) {
			fileName = args[0];
		}

		PeopleWorker worker = new PeopleWorker();
		worker.parseFile(fileName);
	}

	private void parseFile(String path) {

		try {
			People people = createPeople(path);
			System.out.println(people.printXml());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private People createPeople(String path) throws IOException, ParseException {
		People people = new People();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			while ((createPerson(br.readLine(), br, people)) != null) {
			}
		}
		return people;
	}

	private Person createPerson(String personMetaData, BufferedReader br, People people)
			throws ParseException, IOException {
		if (personMetaData == null) {
			return null;
		}
		Person person = new Person(personMetaData);
		people.getPersons().add(person);

		// Then after that we pick the details of the person
		String line;

		while ((line = br.readLine()) != null) {
			if (line.startsWith("T")) {
				Phone phone = new Phone(line);
				person.setPhone(phone);
			}
			if (line.startsWith("A")) {
				Address address = new Address(line);
				person.setAddress(address);
			}
			if (line.startsWith("F")) {
				createFamily(line, br, people, person);
				// person.getFamilies().add(family);
			}
			// If we get another P| it is a new person
			if (line.startsWith("P")) {
				createPerson(line, br, people);
			}

		}

		return person;
	}

	private Family createFamily(String familyMetadata, BufferedReader br, People people, Person person)
			throws ParseException, IOException {
		// Family is always first
		Family family = new Family(familyMetadata);
		person.getFamilies().add(family);

		// Then after that we pick the details of the family
		String line;
		while ((line = br.readLine()) != null) {
			if (line.startsWith("T")) {
				Phone phone = new Phone(line);
				family.setPhone(phone);
			}
			if (line.startsWith("A")) {
				Address address = new Address(line);
				family.setAddress(address);
			}
			if (line.startsWith("F")) {
				createFamily(line, br, people, person);
			}
			// If we get another P| it is a new person
			if (line.startsWith("P")) {
				createPerson(line, br, people);
			}

		}
		return family;
	}

}
