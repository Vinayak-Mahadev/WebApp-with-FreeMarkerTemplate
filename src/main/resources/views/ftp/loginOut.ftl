<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="${style}.css">
<title>Welcome</title>
</head>
<body>
	
	<h2>${user}</h2>
	
	<form action="${formAction.name}">
	<h1>${msg}...!</h1>
		<input type="text" name="username" placeholder="Username"
			required="required" /> <input type="password" name="password"
			placeholder="Password" required="required" />
		<button type="submit" class="btn btn-primary btn-block btn-large">Let
			me in.</button>
	</form>
</body>
</html>
