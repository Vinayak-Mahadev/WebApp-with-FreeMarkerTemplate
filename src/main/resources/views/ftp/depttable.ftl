<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${style}.css">
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<section>
	<div class = "tbl-header">
	<table>
		<thead>
			<tr>
			<#list fields as field>
				<th>${field}</th>
			</#list>
			</tr>
		</thead>
		
		</table>
		</div>
		<div class = "tbl-content">
		<table cellpadding="0" cellspacing="0" border="0">
		<tbody>
			<#list departments as department>
			<tr>
				<td>${department.dept_id}</td>
				<td>${department.dept_name}</td>
			</tr>
			</#list>
				<tr>
				<td><button onClick = "check()">Message</button></td>
			<td><form action = "${home}"><button type="submit" class="btn btn-primary btn-block btn-large">Go home</button>
				</form></td></tr>
		</tbody>
	</table>
</div>	

	<script>	
		function check(){
		alert("${msg}");
		}
	</script>

</section>
</body>
</html>