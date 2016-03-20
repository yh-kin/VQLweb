<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="static/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="static/javascript/jquery-2.1.3.min.js"></script>
<script src="static/javascript/bootstrap.min.js"></script>

<link rel="stylesheet" href="static/css/bootstrap.vertical-tabs.css">


<!-- User Defined -->
<link rel="stylesheet/less" href="custom/css/queryInfo.less">
<link rel="stylesheet/less" href="custom/css/selectInfo.less">
<link rel="stylesheet/less" href="custom/css/fromInfo.less">
<link rel="stylesheet/less" href="custom/css/whereInfo.less">
<script src="static/javascript/less.min.js"></script>

<link rel="stylesheet" href="custom/css/element.css">

<script src="custom/javascript/PaintingTechniques.js"></script>
<script src="custom/javascript/QueryPainter.js"></script>
<script src="custom/javascript/SelectPainter.js"></script>
<script src="custom/javascript/FromPainter.js"></script>
<script src="custom/javascript/WherePainter.js"></script>

<script>
	var queryInfo = ${mainQueryInfo};

	$(function(){
		var queryPainter = new _QueryPainter("visual_panel");
		queryPainter.paint(queryInfo);
	})
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VQLweb (Ver.${version})</title>
</head>

<body>

	<div class="page-header text-center">
	  <h1>VQLweb<small> (ver.${version})</small></h1>
	</div>
	
	<div class ="well">
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


