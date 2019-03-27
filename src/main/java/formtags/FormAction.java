package formtags;

import java.util.LinkedList;
import java.util.List;

public class FormAction {
	
	private String onclick;
	private String action;
	private String type;
	private String name;
	private String value;
	private String style;
	
	
	
	public final String getAction() {
		return action;
	}
	public final String getType() {
		return type;
	}
	public final String getName() {
		return name;
	}
	public final String getValue() {
		return value;
	}
	public final void setAction(String servletname) {
		this.action = servletname;
	}
	public final void setType(String type) {
		this.type = type;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final void setValue(String value) {
		this.value = value;
	}
	private static List<FormAction> listOfHomeFormActions;
	public static  List<FormAction> getListOfHomeFormActions() {
	
		if(listOfHomeFormActions == null){
			listOfHomeFormActions = new LinkedList<>();
			
			String formName = "Demo";
			String action   = "action";
			
			FormAction readAllDepartment;
			FormAction readAllEmployee;
			FormAction readAllOrganization;
			FormAction findDepartment_by_id;
			FormAction findEmployee_by_id;
			FormAction findEmployee_by_dept_id;
			FormAction addEmployee;
			FormAction updateEmployee;
			FormAction deleteEmployee;
			FormAction updateDepartment;
			FormAction deleteDepartment;
			FormAction addDepartment;
			
			addEmployee = new FormAction();
			addEmployee.setOnclick("operationCRUD(this.id)");
			addEmployee.setName(action);
			addEmployee.setType("hidden");
			addEmployee.setValue("Add Employee");
			addEmployee.setAction(formName);
			addEmployee.setStyle("");
			listOfHomeFormActions.add(addEmployee);
			
			
			addDepartment = new FormAction();
			addDepartment.setOnclick("operationCRUD(this.id)");
			addDepartment.setName(action);
			addDepartment.setType("hidden");
			addDepartment.setValue("Add Department");
			addDepartment.setAction(formName);
			addDepartment.setStyle("");
			listOfHomeFormActions.add(addDepartment);
			
			
			readAllDepartment = new FormAction();
			readAllDepartment.setOnclick("operationCRUD(this.id)");
			readAllDepartment.setName(action);
			readAllDepartment.setType("hidden");
			readAllDepartment.setValue("Read All Department");
			readAllDepartment.setAction(formName);
			readAllDepartment.setStyle("none");
			listOfHomeFormActions.add(readAllDepartment);
			
			readAllEmployee = new FormAction();
			readAllEmployee.setOnclick("operationCRUD(this.id)");
			readAllEmployee.setName(action);
			readAllEmployee.setType("hidden");
			readAllEmployee.setValue("Read All Employee");
			readAllEmployee.setAction(formName);
			readAllEmployee.setStyle("none");
			listOfHomeFormActions.add(readAllEmployee);
			
			readAllOrganization = new FormAction();
			readAllOrganization.setOnclick("operationCRUD(this.id)");
			readAllOrganization.setName(action);
			readAllOrganization.setType("hidden");
			readAllOrganization.setValue("Read All Organization");
			readAllOrganization.setAction(formName);
			readAllOrganization.setStyle("none");
			listOfHomeFormActions.add(readAllOrganization);
			
			
			
			findDepartment_by_id = new FormAction();
			findDepartment_by_id.setOnclick("operationCRUD(this.id)");
			findDepartment_by_id.setName(action);
			findDepartment_by_id.setType("text");
			findDepartment_by_id.setValue("Find Department By Id");
			findDepartment_by_id.setAction(formName);
			findDepartment_by_id.setStyle("none");
			listOfHomeFormActions.add(findDepartment_by_id);
			
			findEmployee_by_id = new FormAction();
			findEmployee_by_id.setOnclick("operationCRUD(this.id)");
			findEmployee_by_id.setName(action);
			findEmployee_by_id.setType("text");
			findEmployee_by_id.setValue("Find Employee By Id");
			findEmployee_by_id.setAction(formName);
			findEmployee_by_id.setStyle("none");
			listOfHomeFormActions.add(findEmployee_by_id);
			
			findEmployee_by_dept_id = new FormAction();
			findEmployee_by_dept_id.setOnclick("operationCRUD(this.id)");
			findEmployee_by_dept_id.setName(action);
			findEmployee_by_dept_id.setType("text");
			findEmployee_by_dept_id.setValue("Find Employee By dept Id");
			findEmployee_by_dept_id.setAction(formName);
			findEmployee_by_dept_id.setStyle("none");
			listOfHomeFormActions.add(findEmployee_by_dept_id);
			
			
			updateEmployee = new FormAction();
			updateEmployee.setOnclick("operationCRUD(this.id)");
			updateEmployee.setName(action);
			updateEmployee.setType("hidden");
			updateEmployee.setValue("Update Employee By ID");
			updateEmployee.setAction(formName);
			updateEmployee.setStyle("");
			listOfHomeFormActions.add(updateEmployee);
			
			updateDepartment = new FormAction();
			updateDepartment.setOnclick("operationCRUD(this.id)");
			updateDepartment.setName(action);
			updateDepartment.setType("hidden");
			updateDepartment.setValue("Update Department By ID");
			updateDepartment.setAction(formName);
			updateDepartment.setStyle("");
			listOfHomeFormActions.add(updateDepartment);
			
			deleteEmployee = new FormAction();
			deleteEmployee.setOnclick("operationCRUD(this.id)");
			deleteEmployee.setName(action);
			deleteEmployee.setType("hidden");
			deleteEmployee.setValue("Delete Employee By ID");
			deleteEmployee.setAction(formName);
			deleteEmployee.setStyle("");
			listOfHomeFormActions.add(deleteEmployee);
			
			
			deleteDepartment = new FormAction();
			deleteDepartment.setOnclick("operationCRUD(this.id)");
			deleteDepartment.setName(action);
			deleteDepartment.setType("hidden");
			deleteDepartment.setValue("Delete Department By ID");
			deleteDepartment.setAction(formName);
			deleteDepartment.setStyle("");
			listOfHomeFormActions.add(deleteDepartment);
			
			
			
		}
		
		
		return listOfHomeFormActions;
	}
	
	public static List<String> homeFormNames() {
		return null;
	}
	@Override
	public String toString() {
		return "FormAction [servletname=" + action + ", type=" + type + ", name=" + name + ", value=" + value
				+ "]";
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
