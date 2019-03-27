package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import dom.pojo.org.Employee;
import properties.PropertiesForDatabase;


public class ConnectPostgreSQL {
	private static ConnectPostgreSQL cps;
	private static  String url =   "jdbc:postgresql://localhost:5432/testdb";
	private static String  un =   "postgres";
	private  static String  pw =   "enh@123";
	private final String  driver =   "org.postgresql.Driver";
	private Scanner scan = new Scanner(System.in);
	private List<Employee> list = new LinkedList<Employee>();
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pstmt;

	private   ConnectPostgreSQL() throws Exception  {
		Properties properties = PropertiesForDatabase.getProperties().getDBProp();
		url = properties.getProperty("DB_URL")+toString();
		un  = properties.getProperty("DB_USERNAME");
		pw  = properties.getProperty("DB_PASSWORD");
		
		
		Class.forName(driver);
		System.out.println("Driver loaded");
		con = DriverManager.getConnection(url, un,pw);
		System.out.println("db connected");
	}
	public synchronized  static ConnectPostgreSQL getConnection() throws Exception {
		if (cps == null) {
			cps = new ConnectPostgreSQL();
		}
		return cps;
	}
	//----------------------------------------------------------------------------------
	public void readEmployee_By_ID() throws Exception {
		System.out.println("Read Employee_By_ID operation going on...\nEnter the Emp_id :  ");
		String s = "SELECT * FROM EMPLOYEES WHERE EMP_ID= ?";
		pstmt = con.prepareStatement(s);pstmt.setInt(1, scan.nextInt());
		rs = pstmt.executeQuery();
		if(!rs.next()) {
			System.out.println("please give different emp_id and try again\n");
		}
		else {
			System.out.println("Employee ID : "+rs.getInt(1)+"  Employee Name :  "+rs.getString(2)+"Dept_id  : "+rs.getInt(3)+"\n");
		}
	}
	public void readAllEmployee() throws Exception {
		list.removeAll(list);
		System.out.println("READ ALL OPERATION........\n");
		String s = "SELECT * FROM EMPLOYEES";
		rs = con.prepareStatement(s).executeQuery();
		while(rs.next()) {
			list.add(new Employee(rs.getInt(1),rs.getString(2),rs.getInt(3)));
		}int i =0;
		for(Employee e : list) {
			i++;
			System.out.println(i +". "+ e);
		}
		if(i==0) {
			System.out.println("Employee List is empty now\n");
		}
	}
	public void addEmployee() throws Exception {
		boolean statu = false;
		String dept = "select * from department";
		rs = con.createStatement().executeQuery(dept);
		if(rs.next()) {
			statu = true;
		}
		if(statu){
			System.out.print("ADD EMPLOYEE operation going on...\nEnter EMPLOYEE_ID :");
			int emp_id = scan.nextInt();
			boolean status = false; 
			String e = "SELECT * FROM EMPLOYEES";
			rs = con.createStatement().executeQuery(e);
			String emp_name = "";
			int deptId =0;
			while(rs.next()) {
				int i = rs.getInt(1);
				String j = rs.getString(2);
				int k = rs.getInt(3);
				if(emp_id == i) {
					status = true;
					emp_name = emp_name+j;
					deptId = deptId+k;

				}
			}	
			if(status){
				System.out.println("\nGiven Emp_id is Already is there in database Whose Emp_name and dept Id is \n"
						+ ""+emp_name+"   "+deptId
						+ "Please provide different Emp_id and try again\n");
			}else {
				try {

					System.out.print("Enter EMPLOYEE_NAME: ");
					String emp_name1 = scan.next();
					System.out.print("Enter Department ID : ");
					int dept_id = scan.nextInt();
					String s = "INSERT INTO EMPLOYEES (EMP_ID, EMP_NAME,DEPT_ID) VALUES("+emp_id+",'"+emp_name1+"',"+dept_id+");";
					int i =con.createStatement().executeUpdate(s);			
					if(i==-1) {
						System.out.println("please give different emp_id and try again");
					}
					else {
						System.out.println("One Employee added to table");
					}

				}catch(Exception exe) {
					System.out.println("Given dept id not present now try again");
				}
			}

		}
		else {
			System.out.println("Department is empty now......");
		}
	}
	public void deleteEmployee_By_ID() throws Exception {
		System.out.println("DELETE operation....\nEnter EMPLOYEE_ID:\n");
		pstmt=con.prepareStatement("DELETE FROM EMPLOYEES WHERE EMP_ID = ?");
		pstmt.setInt(1, scan.nextInt());
		int i =pstmt.executeUpdate();
		if(i==0) {
			System.out.println("Gievn emp_id not present in Database or empty table.. \n"
					+ "please give different emp_id or try again \n");
		}
		else {
			System.out.println("Employee  deleted from  table");
		}
	}
	public void updateEmployee_By_ID() throws Exception {
		System.out.println("Update operation....\nEnter EMPLOYEE_ID:\n");
		int emp_id = scan.nextInt();
		System.out.println("Enter the Emp_Name :  ");
		String emp_name = scan.next();
		pstmt=con.prepareStatement("UPDATE EMPLOYEES SET EMP_NAME = ? where EMP_ID = ?");
		pstmt.setString(1, emp_name);
		pstmt.setInt(2, emp_id);
		int i =pstmt.executeUpdate();
		if(i==0) {
			System.out.println("\nGievn emp_id not present in Database or list empty"
					+ "..\n please give different emp_id or try again\n");
		}
		else {
			System.out.println("Employee  Updated from  table\n");
		}
	}
	public void readEmployee_by_dept_id() throws Exception {
		System.out.println("Enter the dept_id\n");
		int dept_id = scan.nextInt();
		String s = "SELECT emp_id, emp_name FROM EMPLOYEES WHERE DEPT_ID=?";
		pstmt=con.prepareStatement(s);
		pstmt.setInt(1, dept_id);
		rs = pstmt.executeQuery();
		int i =0;
		while(rs.next()) {
			i++;
			System.out.println("emp_id : "+rs.getInt(1)+"   emp_name : "+rs.getString(2));
		}if(i==0) {
			System.out.println("List empty");
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------
	public void readDepartment_By_ID() throws Exception {
		System.out.println("Read Department_By_ID operation going on...\nEnter the dept_id :  ");
		String s = "SELECT * FROM Department WHERE dept_ID= ?";
		pstmt=con.prepareStatement(s);
		pstmt.setInt(1, scan.nextInt());
		rs = pstmt.executeQuery(); 
		if(!rs.next()) {
			System.out.println("please give different dept_id and try again\n");
		}
		else {
			System.out.println("Department ID : "+rs.getInt(1)+"  Department Name :  "+rs.getString(2)+"\n");
		}
	}
	public void readAllDepartment() throws Exception {
		System.out.println("READ ALL OPERATION........\n");
		String s = "SELECT * FROM Department";
		rs = con.prepareStatement(s).executeQuery();
		int i = 0;
		while(rs.next()) {
			i++;
			System.out.println("Dept id  :  "+rs.getInt(1)+"  Dept_Name  "+rs.getString(2));
		}

		if(i==0) {
			System.out.println("Department List is empty now\n");
		}
	}
	public void addDepartment() throws Exception {
		System.out.println("ADD Department operation going on...\nEnter dept_ID :");
		int dept_id = scan.nextInt();
		boolean status = false; 
		String e = "SELECT * FROM Department";
		rs = con.prepareStatement(e).executeQuery();
		String dept_name = "";
		while(rs.next()) {
			int i = rs.getInt(1);
			String j = rs.getString(2);
			if(dept_id == i) {
				status = true;
				dept_name = dept_name+j;
			}
		}	
		if(status){
			System.out.println("Given dept_id is Already is there in database Whose dept_name is "+dept_name+" \n"
					+ "Please provide different dept_id and try again\n");
		}else {
			System.out.println("Enter Department_NAME: ");
			String emp_name1 = scan.next();
			String s = "INSERT INTO Department (dept_ID, dept_NAME) VALUES(?,?);";
			pstmt=con.prepareStatement(s);
			pstmt.setInt(1, dept_id);
			pstmt.setString(2,emp_name1);
			int i =	pstmt.executeUpdate();			
			if(i==-1) {
				System.out.println("please give different dept_id and try again");
			}
			else {
				System.out.println("One Department added to table");
			}
		}
	}
	public void deleteDepartment_By_ID() throws Exception {



		System.out.println("DELETE operation....\nEnter dept_ID:\n");
		int dept_id = scan.nextInt();
		pstmt = con.prepareStatement("SELECT * FROM EMPLOYEES WHERE dept_id= ?");
		pstmt.setInt(1, dept_id);
		rs=pstmt.executeQuery();
		int status =0;
		while(rs.next()) {
			status++;
			System.out.println("emp_id "+rs.getInt(1)+"  emp_name  "+rs.getString(2));
			rs.getInt(3);
		}
		System.out.println("Employees are going to delete......"
				+ "\nIf you want delete PRESS "+ status);
		if(status==scan.nextInt()) {
			int total_emps_deleted=con.prepareStatement("DELETE FROM EMPLOYEES WHERE DEPT_ID = "+dept_id).executeUpdate();
			int i =con.prepareStatement("DELETE FROM Department WHERE dept_ID = "+dept_id).executeUpdate();
			if(i==0) {
				System.out.println("Gievn dept_id not present in Database or empty table.. \n"
						+ "please give different dept_id or try again \n");
			}
			else {
				System.out.println("Department  deleted from  table\n"
						+ "and "+total_emps_deleted+"  employees deleted");
			}
		}else {
		}

	}
	public void updateDepartment_By_ID() throws Exception {
		System.out.println("Update operation....\nEnter dept_ID:\n");
		int dept_id = scan.nextInt();
		System.out.println("Enter the dept_Name :  ");
		String dept_name = scan.next();
		pstmt=con.prepareStatement("UPDATE Department SET dept_NAME = ? where dept_ID = ?");
		pstmt.setString(1, dept_name);
		pstmt.setInt(2, dept_id);
		int i=pstmt.executeUpdate();
		if(i==0) {
			System.out.println("\nGievn dept_id not present in Database or list empty"
					+ "..\n please give different dept_id or try again\n");
		}
		else {
			System.out.println("Department  Updated from  table\n");
		}
	}
	//-----------------------------------------------------------------------------------------------------------------------------
	public void viewAll()throws Exception{
		rs = con.prepareStatement("select * from employees natural join department").executeQuery();
		int i=0;
		while(rs.next()) {
			System.out.println("dept_id : "+rs.getInt(1)+"  emp_id : "+rs.getInt(2)+"  emp_name : "+rs.getString(3)+"  dept_name : "+rs.getString(4));
			i++;
		}
		if(i==0) {
			System.out.println("Empty now");
		}
	}
	
	public static  void setUrl(String url) {
		ConnectPostgreSQL.url = url;
	}
	public static  void setUn(String un) {
		ConnectPostgreSQL.un = un;
	}
	public static void setPw(String pw) {
		ConnectPostgreSQL.pw = pw;
	}
	
	
}