package model;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
public class Student {
	
	private int studentId; 
	private String firstName;
	private String affix; //dutch: tussenvoegsel
	private String lastName;
	private String address;
	private String postalCode;
	private String city;
	
	public Student(int studentId, String firstName, String affix, String lastName, String address, String postalCode, String city) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.affix = affix;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAffix() {
		return affix;
	}

	public void setAffix(String affix) {
		this.affix = affix;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String role) {
		this.city = role;
	}
	
	public String toString() {
		return "[" + studentId + ", " + firstName + ", " + affix + ", "+ lastName + ", " + address + ", " + postalCode  + ", " + city + "]" ;
		
	}
}
