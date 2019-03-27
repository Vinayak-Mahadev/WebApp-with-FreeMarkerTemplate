package test;

import java.util.Scanner;

public class Launch {
	private static ConnectPostgreSQL cps;
	private static Scanner scan = new Scanner(System.in); 

	public  void main() throws Exception { 
		intro();

		if(scan.nextInt()==1) {
			validate();
			try {
				start();
			} catch (Exception e) {
				System.out.println("Given deatils are not present in database "
						+ "\n IF you want try again press 1 :");
				if(scan.nextInt()==1) {
					ConnectPostgreSQL.setUrl("jdbc:postgresql://localhost:5432/testdb");
					validate();
					start();
				}
			}
		}else
			start();

	}

	private static void intro() {
		System.out.println("Conform table like");

		System.out.println("Table : 1"
				+ "\nEMPLOYEES"
				+ "\n  emp_id(numeric) , emp_name(name), dept_id(numeric)");
		System.out.println("Table : 2"
				+ "\nDEPARTMENT"
				+ "\n  dept_id(numeric), dept_name(name)");


		System.out.println("-----------------or------------\ncopy this.."
				+ "\n"
				+ "create table employees(emp_id numeric, emp_name name , dept_id numeric);\r\n" + 
				"create table department(dept_id numeric, dept_name name);");
		System.out.println("\nIf you have table in database go for head\n");
		System.out.println("DEFAULT URL : "
				+ "\njdbc:postgresql://localhost:5432/testdb\n"
				+ "USERNAME : postgres"
				+ "\nPASSWORD : postgres\n"
				+ "IF YOU WANT TO CHANGE PRESS 1 :");

	}


	private static void validate() throws Exception {
		try {
			System.out.println("If you want change URL press 1");
			if(scan.nextInt()==1) {
				System.out.println("Enter url : ");

				ConnectPostgreSQL.setUrl(scan.next());
			}

			System.out.println("Enter Username : ");
			ConnectPostgreSQL.setUn(scan.next());
			System.out.println("Enter Password : ");
			ConnectPostgreSQL.setPw(scan.next());
		} catch (Exception e) {
			System.out.println("Check url,un,pw");
		}
	}

	private static void start() throws  Exception{
		cps = ConnectPostgreSQL.getConnection();
		boolean status=true; int choice =0; while (status) {
			System.out.
			println("\n1. ADD EMPLOYEE\t2. READ EMPLOYEE BY ID\n3. READ ALL EMPLOYEES\t"
					+ "4. UPDATE EMPLOYEE BY ID\n5. DELETE EMPLOYEE BY ID" +
					"\t6. ADD Department\n7. READ Department BY ID\t8. READ ALL Department\n" +
					"9. UPDATE Department BY ID\t10. DELETE Department BY ID" +
					"\n11. Natural join for emp and dept\t12. Read employee by dept_id" +
					"\n13. Exit"); 
			System.out.print("Enter the key   :    "); 
			choice =	scan.nextInt(); 
			switch(choice) { 
			case 1:  cps.addEmployee(); break; 
			case 2:  cps.readEmployee_By_ID(); break; 
			case 3:  cps.readAllEmployee(); break; 
			case 4:  cps.updateEmployee_By_ID(); break; 
			case 5:  cps.deleteEmployee_By_ID();break; 
			case 6:  cps.addDepartment();; break; 
			case 7:  cps.readDepartment_By_ID(); break; 
			case 8:  cps.readAllDepartment(); break;
			case 9:  cps.updateDepartment_By_ID(); break; 
			case 10: cps.deleteDepartment_By_ID(); break; 
			case 11: cps.viewAll(); break; 
			case 12: cps.readEmployee_by_dept_id(); break; 
			case 13: System.out.println("Thank You"); status=false; break;
			default:
				System.out.println("Please try again...."); break; 
			}
		} scan.close(); }


}
