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

<link rel="stylesheet" href="css/element.css">

<script>
	var queryInfo = ${mainQueryInfo};

	$(function(){
		drawing_QueryInfo("visual_panel", queryInfo);
	})
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VQLweb (Ver.20150412)</title>
</head>

<body>

	<div class="page-header text-center">
	  <h1>VQLweb<small> (ver.${version})</small></h1>
	</div>
	
	<span>
		${queryString}
	</span>
	
	//
	
	<span>
		${mainQueryInfo}
	</span>
	
	<div id="visual_panel" style="position: relative;">
		
	</div>

</body>
</html>


