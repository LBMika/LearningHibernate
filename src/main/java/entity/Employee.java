package entity;

import javax.persistence.*;

/**
 * A representation of an employee from Touloulou
 */
@Entity
@Table(name="employee")
public class Employee {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private int age;
	private String roleTitle;
	private String phoneNumber;
	private String address;
	
	/**
	 * Default constructor
	 */
	public Employee() {
		super();
		this.id = -1;
		this.age = -1;
	}
	
	/**
	 * Full constructor
	 * @param id DB id
	 * @param firstname First name
	 * @param lastname Last name
	 * @param email Email
	 * @param age Age
	 * @param roleTitle Title of the employee position
	 * @param phoneNumber Phone number
	 * @param address Current address
	 */
	public Employee(long id, String firstname, String lastname, String email, int age, String roleTitle,
			String phoneNumber, String address) {
		super();
		this.id = id;
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setEmail(email);
		this.setAge(age);
		this.setRoleTitle(roleTitle);
		this.setPhoneNumber(phoneNumber);
		this.setAddress(address);
	}

	/**
	 * Check if all attributes are initialize with valid value (except id)
	 * @return True if valid for creation
	 */
	public boolean isValidForCreation() {
		boolean result;
		
		if (this.firstname==null || this.firstname.isBlank() || this.lastname==null || this.lastname.isBlank() ||
				this.email==null || this.email.isBlank() || this.age==-1 || this.roleTitle== null || this.roleTitle.isBlank() ||
				this.phoneNumber==null || this.phoneNumber.isBlank() || this.address==null || this.address.isBlank())
			result = false;
		else
			result = true;
		
		return result;
	}
	
	/**
	 * Copy into the current instance the correctly initialized attributes from the parameter 'employee' 
	 * @param employee An instance of Employee
	 */
	public void init(Employee employee) {
		if (employee.firstname!=null && !employee.firstname.isBlank()) this.firstname = employee.firstname;
		if (employee.lastname!=null && !employee.lastname.isBlank()) this.lastname = employee.lastname;
		if (employee.email!=null && !employee.email.isBlank()) this.email = employee.email;
		if (employee.roleTitle!=null && !employee.roleTitle.isBlank()) this.roleTitle = employee.roleTitle;
		if (employee.age!=-1) this.age = employee.age;
		if (employee.phoneNumber!=null && !employee.phoneNumber.isBlank()) this.phoneNumber = employee.phoneNumber;;
		if (employee.address!=null && !employee.address.isBlank()) this.address = employee.address;
	}
	
	// Getters/Setters block
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		// TODO check if >0
		this.age = age;
	}
	
	public String getRoleTitle() {
		return roleTitle;
	}
	
	public void setRoleTitle(String roleTitle) {
		this.roleTitle = roleTitle;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", age=" + age + ", roleTitle=" + roleTitle + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ "]";
	}
}
