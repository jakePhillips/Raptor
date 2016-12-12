package models;

public class User {
	static long		counter =01;
	
	private long 	id;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String occupation;
	
	public User(String firstName, String lastName, int age, String gender, String occupation)
	{
		this.id=counter++;
		this.firstName = firstName;
		this.lastName = lastName;
		if(age < 1 || age > 100)
		{
			throw new IllegalArgumentException();
		}
		this.age = age;
		if(gender.trim().toLowerCase().charAt(0)!='f'||gender.trim().toLowerCase().charAt(0)!='f')
		this.gender = gender.substring(0);
		this.occupation = occupation;
	}
	
	@Override
	public String toString() {
		return "ID: "+id+" First name=" + firstName + ", Last Name=" + lastName + ", Age=" + age + ", Gender=" + gender + ", Occupation=" + occupation + "]";
	}

	public static long getCounter() {
		return counter;
	}

	public static void setCounter(long counter) {
		User.counter = counter;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
}
