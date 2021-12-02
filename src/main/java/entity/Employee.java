package entity;

import javax.persistence.*;

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
	
	public Employee() {
		super();
		this.id = -1;
		this.age = -1;
	}
	
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
	

	public void init(Employee employee) {
		if (employee.firstname!=null && !employee.firstname.isBlank()) this.firstname = employee.firstname;
		if (employee.lastname!=null && !employee.lastname.isBlank()) this.lastname = employee.lastname;
		if (employee.email!=null && !employee.email.isBlank()) this.email = employee.email;
		if (employee.roleTitle!=null && !employee.roleTitle.isBlank()) this.roleTitle = employee.roleTitle;
		if (employee.age!=-1) this.age = employee.age;
		if (employee.phoneNumber!=null && !employee.phoneNumber.isBlank()) this.phoneNumber = employee.phoneNumber;;
		if (employee.address!=null && !employee.address.isBlank()) this.address = employee.address;
	}
	
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
