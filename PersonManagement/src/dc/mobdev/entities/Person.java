package dc.mobdev.entities;

public class Person {
	// Properties
	private int id;
	private String name;
	private int yearOfBirth;
	private String gender;
	private String interested;
	private String description;

	// Constructor
	public Person() {

	}
	// Overload constructor method
	public Person(int id, String name, int yearOfBirth, String gender,
			String interested, String description) {
		this.id = id;
		this.name = name;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;
		this.interested = interested;
		this.description = description;
	}
	
	public Person(String name, int yearOfBirth, String gender,
			String interested, String description) {
		this.name = name;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;
		this.interested = interested;
		this.description = description;
	}

	// Getter and Setter
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public int getYearOfBirth() {
		return this.yearOfBirth;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}

	public void setInterested(String interested) {
		this.interested = interested;
	}

	public String getInterested() {
		return this.interested;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		String personInfo = "id :" + this.id + "\n";
		personInfo += "name :" + this.name + "\n";
		personInfo += "Year :" + this.yearOfBirth + "\n";
		personInfo += "gender :" + this.gender + "\n";
		personInfo += "interested :" +this.interested + "\n";
		personInfo += "description :" +this.description;
		return personInfo;
	}

	public String toXML() {
		String personInfoXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		personInfoXml += "\n<person>";
		personInfoXml += "\n\t<name>" + this.name + "</name>";
		personInfoXml += "\n\t<yearofbirth>" + this.yearOfBirth
				+ "</yearofbirth>";
		personInfoXml += "\n\t<gender>" + this.gender + "</gender>";
		personInfoXml += "\n\t<interested>" + this.interested + "</interested>";
		personInfoXml += "\n\t<description>" + this.description
				+ "</description>";
		personInfoXml += "\n</person>";
		return personInfoXml;
	}
}
