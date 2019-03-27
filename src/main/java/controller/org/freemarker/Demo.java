package controller.org.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dom.pojo.org.Department;
import dom.pojo.org.Employee;
import dom.pojo.org.Organization;
import dom.pojo.org.User;
import freemarker.template.Template;
import model.org.OrgDetail;
import msg.Messages;
import session.id.SessionId;
import view.ftl.TemplateLocal;

/**
 * Servlet implementation class Demo
 */
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Demo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("You are in demo servlet");
		System.out.println(request.getParameter("action"));
		System.out.println("end");
		Writer out = null;
		Messages msg = null;
		Template template;
		HttpSession session;
		Map<String, Object> map ;
		OrgDetail org      = null;
		List<Department> departments = null;
		List<String>	fields = null;
		User user = null;
		String action = null;
		List<Employee>  employees = null;
		List<Organization> organizations = null;
		try {
			session  = request.getSession(false);
			if (SessionId.getSessionId().equals(session.getId())) 
				user  = (User) session.getAttribute("user");
			System.out.println(session.getId());
			System.out.println(user);
			action = request.getParameter("action_name");
			if(user != null){
				map    = new HashMap<>();
				map.put("style", "table");
				map.put("home", "HomeServlet");
				out = response.getWriter();
				System.out.println("Action : "+ action);

				String employeeId     = request.getParameter("employeeId");
				String employeeName   = request.getParameter("employeeName");
				String departmentId   = request.getParameter("departmentId");
				String departmentName = request.getParameter("departmentName");

				System.out.println("\n------Session Paramethers----"+
						"\nEmp id : " + employeeId+
						"\nEmp Name : " + employeeName+
						"\nEmp Dept Id" + departmentId+
						"\nDept id : "+departmentId+
						"\nDept Name : "+departmentName+
						"\n---------------------------------");


				try {
					switch(action) 
					{ 
					case "Read All Department":
					{
						System.out.println("Read All Department"); 
						org         = new OrgDetail(user);
						departments = org.readAllDepartment();
						fields      = Department.getFieldNames();
						template = TemplateLocal.getTemplate("depttable.ftl");
						map.put("fields", fields);
						map.put("departments", departments);
						map.put("msg", "Departments");
						template.process(map, out);
						break; 
					}
					case "Read All Employee": 
					{
						System.out.println("Read All Employee"); 
						org			= new OrgDetail(user);
						employees   = org.readAllEmployee();
						fields      = Employee.getFieldNames();
						template    = TemplateLocal.getTemplate("emptable.ftl");
						map.put("fields", fields);
						map.put("employees", employees);
						map.put("msg", "Employees");

						System.out.println(map);
						template.process(map, out);
						break; 
					}
					case "Read All Organization": 
					{
						System.out.println("Read All Organization"); 
						org			= new OrgDetail(user);
						organizations   = org.viewAll();
						fields      = Organization.getFieldNames();
						template    = TemplateLocal.getTemplate("orgtable.ftl");
						map.put("fields", fields);
						map.put("organizations", organizations);
						map.put("msg", "Organizations");
						template.process(map, out);
						break; 
					}
					case "Find Department By Id": 
					{
						System.out.println("Find Department By Id"); 
						System.out.println("Department id  :  "+departmentId);
						org			= new OrgDetail(user);
						fields      = Department.getFieldNames();
						map.put("fields", fields);
						template    = TemplateLocal.getTemplate("depttable.ftl");
						msg = org.readDepartment_By_ID(new Department(Integer.parseInt(departmentId), null));

						if(msg != null){
							System.out.println("msg is not null");
							if(msg.getSuccessmsg() !=null){
								System.out.println(msg.getSuccessmsg());
								departments = org.getDeptList();
								map.put("msg", msg.getSuccessmsg());
								map.put("departments", departments);

								template.process(map, out);
							}
							else if(msg.getFailmsg() != null){
								System.out.println(msg.getErrormsg());
								departments = org.getDeptList();
								map.put("msg", msg.getFailmsg());

								map.put("departments", departments);

								template.process(map, out);
							}
						}
						else{
							departments = new ArrayList<>();
							map.put("msg", "Please try again later");

							map.put("departments", departments);

							template.process(map, out);

						}

						break; 
					}
					case "Find Employee By Id":{
						System.out.println("Find Employee By Id"); 
						System.out.println("Employee id  :  "+employeeId);

						org			= new OrgDetail(user);
						fields      = Employee.getFieldNames();
						template    = TemplateLocal.getTemplate("emptable.ftl");
						map.put("fields", fields);
						msg = org.readEmployee_By_ID(Integer.parseInt(employeeId));
						if(msg != null){
							if(msg.getSuccessmsg() != null){
								System.out.println(msg.getSuccessmsg());
								employees = org.getEmpList();
								map.put("msg", msg.getSuccessmsg());
								map.put("employees", employees);

								template.process(map, out);
							}
							else if(msg.getErrormsg() != null){
								System.out.println(msg.getErrormsg());
								employees = new ArrayList<>();
								map.put("msg", msg.getErrormsg());

								map.put("employees", employees);

								template.process(map, out);
							}
						}
						else{
							employees = new ArrayList<>();
							map.put("msg", "Please try again");

							map.put("employees", employees);

							template.process(map, out);

						}
						break; 
					}
					case "Find Employee By dept Id":{
						System.out.println("Find Employee By dept Id"); 
						System.out.println("Department id  :  "+departmentId);

						org			= new OrgDetail(user);
						fields      = Employee.getFieldNames();
						template    = TemplateLocal.getTemplate("emptable.ftl");
						map.put("fields", fields);
						employees = org.readEmployee_by_dept_id(new Employee(0, null, Integer.parseInt(departmentId)));
						System.out.println(employees);
						if(! employees.isEmpty()){
							map.put("employees", employees);
							map.put("msg", "List of Employees");
							template.process(map, out);
						}
						else{
							employees = new ArrayList<>();
							map.put("msg", "Department list Empty now for given dept_id");

							map.put("employees", employees);

							template.process(map, out);
						}
						break; 
					}
					case "Add Employee": 
					{

						System.out.println("Add Employee"); 
						System.out.println(Integer.parseInt(employeeId) + " "+ employeeName+" "+ Integer.parseInt(departmentId));
						org			= new OrgDetail(user);
						msg   = org.addEmployee(
								new Employee(Integer.parseInt(employeeId), employeeName, Integer.parseInt(departmentId))
								);
						fields      = Employee.getFieldNames();
						template    = TemplateLocal.getTemplate("emptable.ftl");
						map.put("fields", fields);

						employees = org.getEmpList();
						if(msg != null){
							if(msg.getSuccessmsg()!=null){
								System.out.println(msg.getSuccessmsg());
								map.put("employees", employees);
								map.put("msg", msg.getSuccessmsg());
								template.process(map, out);

							}
							else if(msg.getErrormsg()!=null){
								System.out.println(msg.getErrormsg());
								map.put("employees", employees);
								map.put("msg", msg.getErrormsg());
								template.process(map, out);
							}
						}else{
							employees = new ArrayList<>();
							msg = new  Messages();
							msg.setErrormsg("Please try again later..");
							map.put("employees", employees);
							map.put("msg", msg.getErrormsg());
							template.process(map, out);

						}

						break; 
					}

					case "Update Employee By ID": 
					{

						System.out.println("Update Employee By ID"); 
						System.out.println(employeeId+ " "+ employeeName+" "+ departmentId);
						org			= new OrgDetail(user);
						msg   = org.updateEmployee_By_ID(
								new Employee(Integer.parseInt(employeeId), employeeName, 0)
								);
						fields      = Employee.getFieldNames();
						template    = TemplateLocal.getTemplate("emptable.ftl");
						map.put("fields", fields);

						employees = org.getEmpList();
						if(msg != null){
							if(msg.getSuccessmsg()!=null){
								System.out.println(msg.getSuccessmsg());
								map.put("employees", employees);
								map.put("msg", msg.getSuccessmsg());
								template.process(map, out);

							}
							else if(msg.getErrormsg()!=null){
								System.out.println(msg.getErrormsg());
								map.put("employees", employees);
								map.put("msg", msg.getErrormsg());
								template.process(map, out);
							}
						}else{
							employees = new ArrayList<>();
							msg = new  Messages();
							msg.setErrormsg("Please try again later..");
							map.put("employees", employees);
							map.put("msg", msg.getErrormsg());
							template.process(map, out);

						}

						break; 
					}

					case "Delete Employee By ID": 
					{

						System.out.println("Delete Employee By ID"); 
						System.out.println(employeeId+ " "+ employeeName+" "+ departmentId);
						org			= new OrgDetail(user);
						msg   = org.deleteEmployee_By_ID(
								new Employee(Integer.parseInt(employeeId), employeeName, 0)
								);
						fields      = Employee.getFieldNames();
						template    = TemplateLocal.getTemplate("emptable.ftl");
						map.put("fields", fields);

						employees = org.getEmpList();
						if(msg != null){
							if(msg.getSuccessmsg()!=null){
								System.out.println(msg.getSuccessmsg());
								map.put("employees", employees);
								map.put("msg", msg.getSuccessmsg());
								template.process(map, out);

							}
							else if(msg.getErrormsg()!=null){
								System.out.println(msg.getErrormsg());
								map.put("employees", employees);
								map.put("msg", msg.getErrormsg());
								template.process(map, out);
							}
						}else{
							employees = new ArrayList<>();
							msg = new  Messages();
							msg.setErrormsg("Please try again later..");
							map.put("employees", employees);
							map.put("msg", msg.getErrormsg());
							template.process(map, out);

						}

						break; 
					}

					case "Update Department By ID": 
					{

						System.out.println("Update Department By ID"); 
						System.out.println(departmentId+ " "+ departmentName);
						org			= new OrgDetail(user);
						Department department = new Department(Integer.parseInt(departmentId), departmentName);
						
						msg   = org.updateDepartment_By_ID(
								department
								);
						fields      = Department.getFieldNames();
						template    = TemplateLocal.getTemplate("depttable.ftl");
						map.put("fields", fields);

						departments = org.getDeptList();
						if(msg != null){
							if(msg.getSuccessmsg()!=null){
								departments = new ArrayList<>();
								departments.add(department);
								System.out.println(msg.getSuccessmsg());
								map.put("departments", departments);
								map.put("msg", msg.getSuccessmsg());
								template.process(map, out);

							}
							else if(msg.getErrormsg()!=null){
								departments = org.getDeptList();
								departments = new ArrayList<>();
								System.out.println(msg.getErrormsg());
								map.put("departments", departments);
								map.put("msg", msg.getErrormsg());
								template.process(map, out);
							}
						}else{
							departments = new ArrayList<>();
							msg = new  Messages();
							msg.setErrormsg("Please try again later..");
							map.put("departments", departments);
							map.put("msg", msg.getErrormsg());
							template.process(map, out);

						}

						break; 
					}
					case "Delete Department By ID": 
					{

						System.out.println("Delete Employee By ID"); 
						System.out.println(departmentId+ " "+ departmentName);
						org			= new OrgDetail(user);
						msg   = org.deleteDepartment_By_ID(
								new Department(Integer.parseInt(departmentId), departmentName)
								);
						fields      = Department.getFieldNames();
						template    = TemplateLocal.getTemplate("depttable.ftl");
						map.put("fields", fields);

						departments = org.getDeptList();
						if(msg != null){
							if(msg.getSuccessmsg()!=null){
								System.out.println(msg.getSuccessmsg());
								map.put("departments", departments);
								map.put("msg", msg.getSuccessmsg());
								template.process(map, out);

							}
							else if(msg.getErrormsg()!=null){
								System.out.println(msg.getErrormsg());
								map.put("departments", departments);
								map.put("msg", msg.getErrormsg());
								template.process(map, out);
							}
						}else{
							departments = new ArrayList<>();
							msg = new  Messages();
							msg.setErrormsg("Please try again later..");
							map.put("departments", departments);
							map.put("msg", msg.getErrormsg());
							template.process(map, out);

						}

						break; 
					}
					case "Add Department": 
					{

						System.out.println("Add Department"); 
						System.out.println(departmentId+ " "+ departmentName);
						org			= new OrgDetail(user);
						Department department = new Department(Integer.parseInt(departmentId), departmentName);
						msg   = org.addDepartment(department);
						fields      = Department.getFieldNames();
						template    = TemplateLocal.getTemplate("depttable.ftl");
						map.put("fields", fields);

						departments = org.getDeptList();
						if(msg != null){
							if(msg.getSuccessmsg()!=null){
								departments = new ArrayList<>();
								departments.add(department);
								System.out.println(msg.getSuccessmsg());
								map.put("departments", departments);
								map.put("msg", msg.getSuccessmsg());
								template.process(map, out);

							}
							else if(msg.getErrormsg()!=null){
								departments = org.getDeptList();
								System.out.println(msg.getErrormsg());
								map.put("departments", departments);
								map.put("msg", msg.getErrormsg());
								template.process(map, out);
							}
						}else{
							departments = new ArrayList<>();
							msg = new  Messages();
							msg.setErrormsg("Please try again later..");
							map.put("departments", departments);
							map.put("msg", msg.getErrormsg());
							template.process(map, out);

						}

						break; 
					}

					default:{ 
						try {
							RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
							rd.forward(request,response);

						} catch (Exception e2) {
							response.sendRedirect("index.html");
						}
						break;
					} 

					} 
				}catch (Exception e) {
					e.printStackTrace();
					throw new Exception();
				}

			}else
				throw new Exception();
		} catch (Exception e) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
				rd.forward(request,response);

			} catch (Exception e2) {
				response.sendRedirect("index.html");
			}
		}
	}

}
