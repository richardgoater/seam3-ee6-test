<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" session="false" />
	<jsp:directive.page import="java.util.*,com.chilternit.model.Customer" />
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
	<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>Upload Customers</title>
	<link href="${contextRoot}/bootstrap/css/bootstrap.min.css"
		rel="stylesheet" type="text/css" />
	<style>
	.help-inline {
		padding-left: 10px;
		padding-bottom: 10px;
	}
	</style>
	<script src="http://code.jquery.com/jquery-latest.js"> <!-- --> </script>
	<script src="${contextRoot}/bootstrap/js/bootstrap.js"
		type="text/javascript"> <!-- --> </script>
	</head>
<body>
	<div class="well">
		<h1>Submit Customer XML</h1>
		<div class="control-group">
			<label>Data:</label><textarea id="data" cols="10" rows="5"> <!--  --> </textarea>
		</div>
		<label />
		<button class="btn" onclick="submit()">Upload</button>
	</div>
	<script>
       	function submit() {
       		var xml = $('textarea#data').val();
       		$.ajax({
       			contentType: 'application/xml',
       			type: 'POST',
       			data: xml       			
       		});
       	}
	</script>
</body>
</html>
</jsp:root>