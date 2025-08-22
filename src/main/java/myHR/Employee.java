package myHR;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long empId;
	private String empFname;
	private String empLname;
	@Enumerated(EnumType.STRING)
	private ClearenceLevel clearanceLevel;
	
	public Employee() {
		super();
	}
	
	public Employee(String empFname, String empLname, ClearenceLevel clearanceLevel) {
		super();
		this.empFname = empFname;
		this.empLname = empLname;
		this.clearanceLevel = clearanceLevel;
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
		this.empFname = empFname;
	}

	public String getEmpLname() {
		return empLname;
	}

	public void setEmpLname(String empLname) {
		this.empLname = empLname;
	}

	public ClearenceLevel getClearanceLevel() {
		return clearanceLevel;
	}

	public void setClearanceLevel(ClearenceLevel clearanceLevel) {
		this.clearanceLevel = clearanceLevel;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empFname=" + empFname + ", empLname=" + empLname + ", clearanceLevel="
				+ clearanceLevel + "]";
	}
	
	
}
