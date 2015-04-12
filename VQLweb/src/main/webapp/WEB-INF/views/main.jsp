<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="js/jquery-2.1.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script src="js/drawing.js"></script>

<script>
	$(function(){
		drawing_sampelCircle("myCanvas");
	})
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VQLweb (Ver.20150412)</title>
</head>

<body>

	<div class="page-header text-center">
	  <h1>VQLweb<small> (ver.${version})</small></h1>
	</div>
	
	<div>
		<canvas id="myCanvas" width="578" height="200"></canvas>
	</div>

</body>
</html>


