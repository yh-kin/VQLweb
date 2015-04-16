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

<link rel="stylesheet" href="css/bootstrap.vertical-tabs.css">

<!-- User Defined -->
<link rel="stylesheet" href="css/element.css">

<script src="js/QueryPainter.js"></script>
<script src="js/SelectPainter.js"></script>
<script src="js/FromPainter.js"></script>

<script>
	var queryInfo = ${mainQueryInfo};

	$(function(){
		var queryPainter = new _QueryPainter("visual_panel");
		queryPainter.paint(queryInfo);
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
		${queryString}
	</div>
	
	<!--
	<div id="visual_panel" class="container col-md-8" style="position: relative; background-color: red"></div>
	-->
	<div class="col-md-9">
		<div class="col-md-2"> <!-- required for floating -->
			<!-- Nav tabs -->
			<ul class="nav nav-tabs tabs-left">
				<li class="active"><a href="#home" data-toggle="tab">Main Query</a></li>
				<li><a href="#profile" data-toggle="tab">01_SubQuery</a></li>
				<li><a href="#messages" data-toggle="tab">02_SubQuery</a></li>
				<li><a href="#settings" data-toggle="tab">03_SubQuery</a></li>
			</ul>
		</div>
	
		<div id="visual_panel" class="col-md-10"></div> 
	</div>
	
	<div class="col-md-3" style="background-color: orange;">
		<div id="query_tree" class="container" style="background-color: blue;"></div>
		<div id="query_detail" class="container" style="background-color: green;"></div>
	</div>
	
</body>
</html>


