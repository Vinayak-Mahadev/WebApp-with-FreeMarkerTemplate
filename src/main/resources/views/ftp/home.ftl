<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${style}.css">
<meta charset="ISO-8859-1">
<title>Welcome</title>

<script type = "text/javascript">
         <!--
			function mycolorFunction() {
  				document.getElementById("demo").style.color = "green";
			}
			
		function operationCRUD(btn_id){
  				document.getElementsByName("action_name")[0].value = btn_id;
  				
  				if(btn_id === ("Read All Department")){
						document.getElementsByName("action_name")[0].value = btn_id;
		     			document.getElementById("myForm").submit();
			 		
				}
				if(btn_id === ("Read All Employee")){
						document.getElementsByName("action_name")[0].value = btn_id;
		     			document.getElementById("myForm").submit();
			 		
				}
				if(btn_id === ("Read All Organization")){
						document.getElementsByName("action_name")[0].value = btn_id;
		     			document.getElementById("myForm").submit();
			 		
				}
				if(btn_id=== ("Add Employee")){
						var empid   = 0;
						var empName = "";
						var deptId  = 0;
						var flag    = true;
						empid   = empid + Number(document.getElementById("employeeId").value);
						empName = empName + document.getElementById("employeeName").value;
						deptId  = deptId + Number(document.getElementById("departmentId").value);
						alert("You entered :  "+empid+"  "+empName+"  "+deptId)
						if((empid===0) ){
							alert("Please provide valid Empid");
							flag  = false;
						}
						if(empName.trim() === ""){
							alert("Please provide valid EmpName");
							flag  = false;		
						}
						if(deptId === 0){
							alert("Please provide employee DeptId");
						}
						if(flag){
							document.getElementById("myForm").submit();
						}
				}
				
				if(btn_id === ("Update Employee By ID")){
						var empid   = 0;
						var empName = "";
						var flag    = true;
						empid   = empid + Number(document.getElementById("employeeId").value);
						empName = empName + document.getElementById("employeeName").value;
						alert("You entered :  "+empid+"  "+empName)
						if((empid===0) ){
							alert("Please provide valid Empid");
							flag  = false;
						}
						if(empName.trim() === ""){
							alert("Please provide valid EmpName");
							flag  = false;		
						}
						if(flag){
							document.getElementById("myForm").submit();
						}
				}
				if(btn_id === ("Find Employee By Id")){
						var empid   = 0;
						var flag    = true;
						empid   = empid + Number(document.getElementById("employeeId").value);
						alert("You entered :  "+empid);
						if((empid===0) ){
							alert("Please provide valid Empid");
							flag  = false;
						}
						if(flag){
							document.getElementById("myForm").submit();
						}
				}
				if(btn_id === ("Find Employee By dept Id")){
						var deptid   = 0;
						var flag    = true;
						deptid   = deptid + Number(document.getElementById("departmentId").value);
						alert("You entered :  "+deptid);
						if((deptid===0) ){
							alert("Please provide valid dept id");
							flag  = false;
						}
						if(flag){
							document.getElementById("myForm").submit();
						}
				}
				if(btn_id === ("Find Department By Id")){
						var deptid   = 0;
						var flag    = true;
						deptid   = deptid + Number(document.getElementById("departmentId").value);
						alert("You entered :  "+deptid);
						if((deptid===0) ){
							alert("Please provide valid dept id");
							flag  = false;
						}
						if(flag){
							document.getElementById("myForm").submit();
						}
				}
				
				
				if(btn_id === ("Delete Employee By ID")){
						var empid   = 0;
						var flag    = true;
						empid   = empid + Number(document.getElementById("employeeId").value);
						alert("You entered :  "+empid)
						if((empid===0) ){
							alert("Please provide valid Empid");
							flag  = false;
						}
						if(flag){
							document.getElementById("myForm").submit();
						}
				}
				
				if(btn_id === ("Update Department By ID")){
						var deptid   = 0;
						var deptName = "";
						var flag    = true;
						deptid   = deptid + Number(document.getElementById("departmentId").value);
						deptName = deptName + document.getElementById("department_Name").value;
						alert("You entered :  "+deptid+"  "+ document.getElementById("department_Name").value)
						if((deptid===0) ){
							alert("Please provide valid dept id");
							flag  = false;
						}
						if(deptName.trim() === ""){
							alert("Please provide valid dept Name");
							flag  = false;		
						}
						if(flag){
							document.getElementById("myForm").submit();
						}
				}
				
				if(btn_id === ("Delete Department By ID")){
						var deptid   = 0;
						var flag    = true;
						alert("You entered :  "+document.getElementById("departmentId").value);
						deptid   = deptid + Number(document.getElementById("departmentId").value);
						if((deptid===0) ){
							alert("Please provide valid dept id");
							flag  = false;
						}
						
						if(flag){
							document.getElementById("myForm").submit();
						}
				}
				
				if(btn_id === ("Add Department")){
						var deptid   = 0;
						var deptName = "";
						var flag    = true;
						deptid   = deptid + Number(document.getElementById("departmentId").value);
						deptName = deptName + document.getElementById("department_Name").value;
						alert("You entered :  "+deptid+"  "+deptName)
						if((deptid===0) ){
							alert("Please provide valid dept id");
							flag  = false;
						}
						if(deptName.trim() === ""){
							alert("Please provide valid dept Name");
							flag  = false;		
						}
						if(flag){
							document.getElementById("myForm").submit();
						}
				}
				
				
				
  			}
		
		
		
		
			 //-->
</script>


</head>
<body>

	<h1>${user}</h1>
	
	<h2 id="demo" onclick="mycolorFunction()">${msg}...!</h2></br></br></br></br>

	<div class="tbl-content">
				<form id = "myForm" action="Demo">
    <table cellpadding="0" cellspacing="0" border="0">
     
	
				<thead><tr><td><h1>List of Operation</h1></td></tr></thead>
				<tr><td>   <INPUT name = "action_name" type="hidden" value="null" />   
				<div name ="emp_frame" class="frame" style="display:non"></td></tr>
  <tr><td>
        <input type="number"  id = "employeeId"   name= "employeeId" placeholder = "Employee Id"  class="calculator-input" onkeypress="return event.charCode >= 48 && event.charCode <= 57"   />
        <input type="text"  id = "employeeName" name= "employeeName" placeholder = "Employee Name" /></td></tr>
  <tr><td>
        <input type="number" id = "departmentId"  name = "departmentId" placeholder = "Department Id" class="calculator-input" onkeypress="return event.charCode >= 48 && event.charCode <= 57"    />
 	    <input type="text"  id = "department_Name" name = "departmentName" placeholder = "Department Name" />
  </div>
   </td></tr>	
		<#list forms as form>
  			<tr>
  			  <td> 	
  			  		<button type="button" id = "${form.value}" onClick = "${form.onclick}">${form.value} </button>
  			  </td> 
  			</tr> 
  		</#list>
			</form>    
		<tr><td><form action = "LogOutServlet"><button type = "submit">logout</button> </form></td></tr>
	</table> 
	</div> 

      
      
      
      
      
</body>
</html>