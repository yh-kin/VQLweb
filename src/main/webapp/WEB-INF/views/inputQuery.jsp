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

<!-- Sample Defualt Query String -->
<script src="sampleQuery.js"></script>

<script>
	$(function(){
		$("#queryString").val(_sampleQuery);
	})
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VQLweb (Ver.${version})</title>
</head>

<body>
	<div class="page-header text-center">
	  <h1>VQLweb<small> (ver.${version})</small></h1>
	</div>
	
	<!-- Query Input Textarea -->
	<form action="main" method="POST" class="form-horizontal" role="form">
		<div class="form-group">
			<div class="col-sm-8 col-md-offset-2">
				<textarea id="queryString" name="queryString" class="form-control" rows="15" placeholder="Input your SQL To Visualize"></textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-1 col-md-offset-9">
				<button type="submit" class="btn btn-default btn-success btn-block">Go!</button>
			</div>
		</div>
	</form>
</body>
</html>