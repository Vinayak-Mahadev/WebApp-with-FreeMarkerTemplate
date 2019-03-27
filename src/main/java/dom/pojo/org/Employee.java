package dom.pojo.org;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private int emp_id;
	private String emp_name;
	private int dept_id;

	public Employee(int emp_id, String emp_name, int dept_id) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.dept_id = dept_id;
	}
	public static List<String> getFieldNames() {
		List<String> fieldNames;
		
		fieldNames = new ArrayList<>();
		fieldNames.add("emp_id");
		fieldNames.add("emp_name");
		fieldNames.add("dept_id");
		return fieldNames;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	@Override
	public String toString() {
		return "emp_id =  " + emp_id + ", emp_name =  " + emp_name + ", dept_id =  " + dept_id;
	}	

}
