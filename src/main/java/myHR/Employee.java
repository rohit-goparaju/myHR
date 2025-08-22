package myHR;

public class Employee {
	
	private long empId;
	private String empFname;
	private String empLname;
	private ClearenceLevel clearanceLevel;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
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
