package myHR;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 
 */
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empId;
	private String empFname;
	private String empLname;
	@Enumerated(EnumType.STRING)
	private ClearenceLevel clearanceLevel;
	@Column(unique = true, nullable = false)
	private String empEmail;
	@Column(nullable = false)
	private String empPassword;
	
	public Employee() {
		super();
	}
	

	public Employee(String empFname, String empLname, ClearenceLevel clearanceLevel, String empEmail,
			String empPassword) {
		super();
		this.setEmpFname(empFname);
		this.setEmpLname(empLname);
		this.setClearanceLevel(clearanceLevel);
		this.setEmpEmail(empEmail);
		this.setEmpPassword(empPassword);
	}


	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname.toLowerCase();
	}

	public String getEmpLname() {
		return empLname;
	}

	public void setEmpLname(String empLname) {
		this.empLname = empLname.toLowerCase();
	}

	public ClearenceLevel getClearanceLevel() {
		return clearanceLevel;
	}

	public void setClearanceLevel(ClearenceLevel clearanceLevel) {
		this.clearanceLevel = clearanceLevel;
	}
	
	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	
	
	public String getEmpPassword() {
		return empPassword;
	}


	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empFname=" + empFname + ", empLname=" + empLname + ", clearanceLevel="
				+ clearanceLevel + ", empEmail=" + empEmail + "]";
	}

}
