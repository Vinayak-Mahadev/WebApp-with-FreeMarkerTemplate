package model.org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dom.pojo.org.Department;
import dom.pojo.org.Employee;
import dom.pojo.org.Organization;
import dom.pojo.org.User;
import model.dbConnection.DBConnection;
import msg.Messages;

/**<pre>Pojo classes will be created in this class
 * </br>check the pojo classes
 * </pre>
 * 
 * @see Employee
 * @see Department
 */
public class OrgDetail {

	private Connection connection;


	public OrgDetail(User user) {

		DBConnection.GetDB_Class().properties.setProperty("DB_USERNAME", user.getUsername());
		DBConnection.GetDB_Class().properties.setProperty("DB_PASSWORD", user.getPassword());
		connection = DBConnection.GetDB_Class().getDBConnection();
	}


	private ResultSet resultSet;
	private PreparedStatement pstmt;

	private List<Employee>   empList;
	private List<Department> deptList;
	private List<Organization> orgList;


	public Messages readEmployee_By_ID(int empId) throws Exception {
		Messages msg = null;

		System.out.println("Read Employee_By_ID operation going on...");
		String query = "SELECT * FROM EMPLOYEES WHERE EMP_ID = "+empId;
		resultSet = connection.createStatement().executeQuery(query);
		empList = new LinkedList<>();
		if(resultSet.next()) {
			empList = new LinkedList<>();
			Employee e = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
			empList.add(e);
			System.out.println(e);
			msg = new Messages();
			msg.setSuccessmsg("This is current Employee for this emp id");
			return msg;
		}
		if(empList.isEmpty()){
			empList = new LinkedList<>();
			msg = new Messages();
			msg.setErrormsg("please give different emp_id and try again");
			System.out.println("please give different emp_id and try again");
			return msg;
		}else{
			return msg;
		}

	}


	public List<Employee> readAllEmployee() throws Exception {
		empList = new LinkedList<>();
		try {
			System.out.println("READ ALL OPERATION........\n");
			String s = "SELECT * FROM EMPLOYEES ORDER BY emp_id";
			resultSet = connection.prepareStatement(s).executeQuery();
			while(resultSet.next()) {
				empList.add(new Employee(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3)));
			}
			int i =0;
			for(Employee e : empList) {
				i++;
				System.out.println(i +". "+ e);
			}
			if(empList.size() == 0) {
				System.out.println("Employee List is empty now\n");
			}
		} catch (Exception e) {
			return new LinkedList<Employee>();
		}
		return empList;
	}



	public Messages addEmployee(Employee employee) throws Exception {
		Messages msg = null;
		boolean status = false;
		String dept_check = "select * from department where dept_id = "+employee.getDept_id();
		resultSet = connection.createStatement().executeQuery(dept_check);
		if(resultSet.next()) {
			status = true; // department present
		}
		else{
			status = false; // department not  present
		}
		if(!status){ //If status = true "department present"
			empList = new ArrayList<>();
			System.out.println("Department Id is not there now."
					+ "Try to create department first");
			msg = new Messages();
			msg.setErrormsg("Department Id is not there now."
					+ "Try to create department first");
			return msg;
		}

		String emp_check = "select * from EMPLOYEES where emp_id = "+employee.getEmp_id();
		resultSet = connection.createStatement().executeQuery(emp_check);
		if(resultSet.next()) {
			status = true;
			empList = new ArrayList<>();
			empList.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
		}else{
			status = false;
		}
		if(status){
			System.out.println("Given Emp_id is Already is there in database Please provide different Emp_id and try again");
			msg = new Messages();
			msg.setErrormsg("Given Emp_id is Already is there in database Please provide different Emp_id and try again");
			return msg;
		}else{
			try {
				System.out.print("Enter EMPLOYEE_NAME: "+employee.getEmp_name());
				System.out.print("Enter Department ID : "+employee.getDept_id());
				String sql = "INSERT INTO EMPLOYEES VALUES(?,?,?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, employee.getEmp_id());
				pstmt.setString(2, employee.getEmp_name());
				pstmt.setInt(3, employee.getDept_id());
				int i = pstmt.executeUpdate();
				if( i==0) {
					msg = new Messages();
					empList = new ArrayList<>();
					msg.setErrormsg("please give different emp_id and try again");
					System.out.println("please give different emp_id and try again");
					return msg;
				}
				else {
					empList = new ArrayList<>();
					empList.add(employee);
					System.out.println("One Employee added to table");
					msg = new Messages();
					msg.setSuccessmsg("One Employee added to table");
					return msg;
				}

			}catch(Exception exe) {
				empList = new  ArrayList<Employee>();
				System.out.println("Some problem add employee in Database");
				msg = new Messages();
				msg.setErrormsg("Some problem add employee in Database");
				return msg;

			}

		}		

	}

	public Messages deleteEmployee_By_ID(Employee employee) throws Exception {
		Messages msg = null;

		System.out.println("DELETE operation....\nEnter EMPLOYEE_ID:\n");


		pstmt=connection.prepareStatement("SELECT * FROM EMPLOYEES WHERE EMP_ID = ?");
		pstmt.setInt(1, employee.getEmp_id());
		resultSet = pstmt.executeQuery();
		if(resultSet.next()){
			employee.setEmp_id(resultSet.getInt(1));
			employee.setEmp_name(resultSet.getString(2));
			employee.setDept_id(resultSet.getInt(3));
			pstmt=connection.prepareStatement("DELETE FROM EMPLOYEES WHERE EMP_ID = ?");
			pstmt.setInt(1, employee.getEmp_id());
			pstmt.executeUpdate();
			empList = new ArrayList<>();
			empList.add(employee);
			msg = new Messages();
			msg.setSuccessmsg("Employee :"+employee.getEmp_id()+" deleted from  table");
			System.out.println("Employee :"+employee.getEmp_id()+" deleted from  table");
			return msg;
		}
		else {
			empList = new ArrayList<>();
			System.out.println("Gievn emp_id not present in Database or empty table.. \n"
					+ "please give different emp_id or try again \n");
			msg = new Messages();
			msg.setErrormsg("Gievn emp_id not present in Database or empty table.. \n"
					+ "please give different emp_id or try again \n");
			return msg;
		}


	}


	public Messages updateEmployee_By_ID(Employee employee) throws Exception {
		Messages msg = null;
		System.out.println("Update operation....\nEnter EMPLOYEE_ID:\n");
		System.out.println("Enter the Emp_Name :  ");
		pstmt=connection.prepareStatement("UPDATE EMPLOYEES SET EMP_NAME = ? where EMP_ID = ?");
		pstmt.setString(1, employee.getEmp_name());
		pstmt.setInt(2, employee.getEmp_id());
		int i =pstmt.executeUpdate();
		if(i==0) {
			empList = new ArrayList<>();
			System.out.println("\nGievn emp_id not present in Database or list empty"
					+ ". please give different emp_id or try again\n");
			msg = new Messages();
			msg.setErrormsg("Gievn emp_id not present in Database or list empty"
					+ ". Please give different emp_id or try again");
			return msg;
		}
		else {
			readEmployee_By_ID(employee.getEmp_id());
			int dept = empList.get(0).getDept_id();
			employee.setDept_id(dept);
			System.out.println("Employee  Updated from  table\n");
			msg = new Messages();
			msg.setSuccessmsg("Employee :  Updated with new Name as "+employee.getEmp_name());
			empList = new ArrayList<>();
			empList.add(employee);
			return msg;
		}
	}


	public List<Employee> readEmployee_by_dept_id(Employee employee) throws Exception {
		empList = new LinkedList<>();
		System.out.println("Enter the dept_id\n");
		int dept_id = employee.getDept_id();
		String s = "SELECT emp_id, emp_name FROM EMPLOYEES WHERE DEPT_ID = ?";
		pstmt=connection.prepareStatement(s);
		pstmt.setInt(1, dept_id);
		resultSet = pstmt.executeQuery();
		while(resultSet.next()) 
			empList.add(new Employee(resultSet.getInt(1),resultSet.getString(2) , dept_id));


		if(empList.isEmpty()) 
			return new LinkedList<Employee>();
		return empList;
	}

	public List<Department> readAllDepartment() throws Exception {
		deptList = new LinkedList<Department>();
		System.out.println("READ ALL OPERATION........\n");
		String s = "SELECT * FROM Department ORDER BY dept_id";
		resultSet = connection.prepareStatement(s).executeQuery();
		while(resultSet.next()) {
			deptList.add(new Department(resultSet.getInt(1), resultSet.getString(2)));
		}

		if(deptList.isEmpty()) {
			return new LinkedList<Department>();
		}
		return deptList;
	}
	//--------------------------------------------------------------------------

	public Messages readDepartment_By_ID(Department department) throws Exception {
		Messages msg = new Messages();
		System.out.println("Read Department_By_ID operation going on...\nEnter the dept_id :  "+department.getDept_id());
		String query = "SELECT * FROM Department WHERE dept_ID = ?";
		pstmt=connection.prepareStatement(query);
		pstmt.setInt(1, department.getDept_id());
		resultSet = pstmt.executeQuery(); 
		deptList = new ArrayList<>();
		if(resultSet.next()) {
			msg = new Messages();
			int id = resultSet.getInt(1);
			String name= resultSet.getString(2);
			msg.setSuccessmsg("Department ID : "+id+"  Department Name :  "+name);
			deptList.add(new Department(id, name));
			return msg;
		}
		else {
			deptList.clear();
			msg = new Messages();
			msg.setFailmsg("please give different dept_id and try again");
			System.out.println("DB : please give different dept_id and try again\n");
			return msg;
		}
	}

	public Messages addDepartment(Department department) throws Exception {
		Messages msg = null;

		System.out.println("ADD Department operation going on...\nEnter dept_ID :");
		int dept_id = department.getDept_id();
		boolean status = false; 
		String e = "SELECT * FROM Department";
		resultSet = connection.prepareStatement(e).executeQuery();
		String j = null;
		deptList = new ArrayList<>();
		while(resultSet.next()) {
			int i = resultSet.getInt(1);
			j = resultSet.getString(2);
			if(dept_id == i) {
				status = true;
				deptList.add(new Department(i, j));
			}
		}	
		if(status){
			deptList = new ArrayList<>();
			System.out.println("Given dept_id is Already is there in database Whose dept_name is "+j+" \n"
					+ "Please provide different dept_id and try again\n");
			msg = new Messages();
			msg.setErrormsg("Given dept_id is Already is there in database Whose dept_name is "+j+" \n"
					+ "Please provide different dept_id and try again\n");
			return msg;
		}else {
			System.out.println("Enter Department_NAME: ");
			String emp_name1 = department.getDept_name();
			String s = "INSERT INTO Department (dept_ID, dept_NAME) VALUES(?,?);";
			pstmt=connection.prepareStatement(s);
			pstmt.setInt(1, dept_id);
			pstmt.setString(2,emp_name1);
			int i =	pstmt.executeUpdate();			
			if(i==-1) {
				msg = new Messages();
				msg.setErrormsg("please give different dept_id and try again");
				System.out.println("please give different dept_id and try again");
				return msg;
			}
			else {
				msg = new Messages();
				msg.setSuccessmsg(department.getDept_name()+" :   Department added to table");
				System.out.println("One Department added to table");
				return msg;
			}
		}
	}

	public Messages deleteDepartment_By_ID(Department department) throws Exception {

		Messages msg = null;
		boolean status = false;
		System.out.println("DELETE operation....\nEnter dept_ID:\n");
		int total_emps_deleted;
		resultSet = connection.createStatement().executeQuery("select * from department where dept_id = "+department.getDept_id());

		if(resultSet.next()) {
			status = true;
			deptList = new ArrayList<>();  
			department.setDept_id(resultSet.getInt(1));
			department.setDept_name(resultSet.getString(2));
		}
		if (status) {

			connection.prepareStatement("DELETE FROM Department WHERE dept_ID = "+department.getDept_id()).executeUpdate();
			pstmt = connection.prepareStatement("SELECT * FROM EMPLOYEES WHERE dept_id= ?");
			pstmt.setInt(1, department.getDept_id());
			resultSet=pstmt.executeQuery();
			empList = new LinkedList<>();
			while(resultSet.next()) {
				empList.add(new Employee(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3)));
			}

			total_emps_deleted = connection.prepareStatement("DELETE FROM EMPLOYEES WHERE DEPT_ID = "+department.getDept_id()).executeUpdate();

			msg = new Messages();
			msg.setSuccessmsg("Department  deleted from  table "
					+ "and "+total_emps_deleted+"  employees deleted");
			int i =0;
			String emps = "";
			for (Employee emp : empList) {
				emps = emps+ (i+"."+emp.getEmp_id()+" "+emp.getEmp_name());
			}
			msg.setErrormsg(emps);
			emps = null;
			deptList = new ArrayList<>();
			deptList.add(department);
			return msg;
		}else{
			deptList = new ArrayList<>();
			System.out.println("Gievn dept_id not present in Database or empty table.. \n"
					+ "please give different dept_id or try again \n");
			msg = new Messages();
			msg.setErrormsg("Gievn dept_id not present in Database or empty table.. ");
			return msg;
		}


	}

	public Messages updateDepartment_By_ID(Department department) throws Exception {

		Messages msg = null;

		System.out.println("Update operation....\nEnter dept_ID:\n");
		int dept_id = department.getDept_id();
		System.out.println("Enter the dept_Name :  ");
		String dept_name = department.getDept_name();
		pstmt=connection.prepareStatement("UPDATE Department SET dept_NAME = ? where dept_ID = ?");
		pstmt.setString(1, dept_name);
		pstmt.setInt(2, dept_id);
		int i=pstmt.executeUpdate();
		if(i==0) {
			msg = new Messages();
			msg.setErrormsg("Gievn dept_id not present in Database or list empty"
					+ ".. please give different dept_id or try again.");
			System.out.println("\nGievn dept_id not present in Database or list empty"
					+ "..\n please give different dept_id or try again\n");
			return msg;
		}
		else {
			msg = new Messages();
			msg.setSuccessmsg(department.getDept_id()+" :  Department  Updated new name as   "+department.getDept_name()+" in the  table...");
			System.out.println("Department  Updated from  table\n");
			return msg;
		}
	}

	//-------------------------------------------------------------
	public List<Organization> viewAll()throws Exception{
		Organization org = null;
		orgList = new LinkedList<Organization>();
		resultSet = connection.prepareStatement("select * from employees natural join department").executeQuery();
		while(resultSet.next()) {
			org = new Organization(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4));
			orgList.add(org);
		}
		return orgList;
	}



	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	public List<Department> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}


	public List<Organization> getOrgList() {
		return orgList;
	}


	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
	}

}
