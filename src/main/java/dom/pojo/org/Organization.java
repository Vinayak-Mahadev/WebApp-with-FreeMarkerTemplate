package dom.pojo.org;

import java.util.ArrayList;
import java.util.List;

public class Organization {

	private int dept_id;
	private int emp_id;
	private String emp_name;
	private String dept_name;



	public Organization(int dept_id, int emp_id, String emp_name, String dept_name) {
		super();
		this.dept_id = dept_id;
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.dept_name = dept_name;
	}
	public static List<String> getFieldNames() {
		List<String> fieldNames;

		fieldNames = new ArrayList<>();
		fieldNames.add("dept_id");
		fieldNames.add("dept_name");
		fieldNames.add("emp_id");
		fieldNames.add("emp_name");
		return fieldNames;
	}
	public int getDept_id() {
		return dept_id;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	@Override
	public String toString() {
		return "Organization :  dept_id = " + dept_id + ", emp_id = " + emp_id + ", emp_name = " + emp_name + ", dept_name = "
				+ dept_name ;
	}



}
