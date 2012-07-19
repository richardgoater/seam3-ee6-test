<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" 
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8" 
		pageEncoding="UTF-8" session="false"/>
	<jsp:directive.page import="java.util.*,com.chilternit.model.Customer"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
	<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Customers</title>
		<link href="${contextRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script src="http://code.jquery.com/jquery-latest.js"> <!-- --> </script>
		<script src="${contextRoot}/bootstrap/js/bootstrap.js" type="text/javascript"> <!-- --> </script>
	</head>
	<body>
		<div id="container" style="width: 1000px; margin: 0 auto; padding-top: 19px">
			<div class="row">
				<div class="span6">
					 <form method="POST" class="well" id="customerForm">
					 	<h1>Add Customer</h1>
					 	<fieldset>
					 		<div class="control-group">
					 			<label class="control-label">Name:</label>
					 			<div class="controls"><input type="text" name="name" /></div>
					 		</div>
				            <div class="control-group">
				            	<label class="control-label">Address:</label>
				            	<div class="controls"><input class="input-large" type="text" name="address" /></div>
				            </div>
				            <label /><button class="btn btn-success" type="submit">Add</button>
				            <script>
				            	<![CDATA[ 
				            	$('#customerForm').submit( function() {
				            		var formData = $('#customerForm').serialize();
				            		$.post('customers', formData, function(customer) {
				            			$('tbody').append('<tr style="display: none"><td>' + customer.name + '</td><td>' + customer.address + '</td>' + 
						            			"<td><button class=\"btn btn-danger\" onclick=\"remove(" + customer.id 
						            					+ ", $(this).parents('tr'))\">Delete</button></td></tr>");
				            			$('tr:last').fadeIn('1000');
				            		});
				            		return false;
				            	});
				            	]]>
				            </script>
			            </fieldset>
			            <c:if test="${not empty model.errors}">
			            	<script>
				            	function addFormError(fieldName, message) {
				            		var controlGroupNode = $('div.controls:has(input[name='+fieldName+'])');
				    				controlGroupNode.parent().addClass('error');
				    				controlGroupNode.append('<span class="help-block">' + message + '</span>');
				            	}
				            	<c:forEach var="error" items="${model.errors}">
				            		addFormError('${error.propertyPath}', '${error.message}');
			            		</c:forEach>
				            </script>
			            </c:if>
	        		</form>
	        	</div>
	        </div>
	        <div class="row">
		        <table class="table table-striped table-bordered">
		        	<thead>
		        		<tr><th>Name</th><th>Address</th><th></th></tr>
		        	</thead>
		        	<tbody>
		        		<c:forEach var="customer" items="${model.customers}">
		            		<tr><td>${customer.name}</td><td>${customer.address}</td>
		            			<td><button class="btn btn-danger" onclick="remove(${customer.id}, $(this).parents('tr'))">Delete</button></td></tr>
		            	</c:forEach>	
		        	</tbody>
		        </table>
	        </div>
        </div>
        <script>
	       	function remove(id, row) {
				$.ajax({
					url: 'customers/' + id,
					type: 'DELETE',
					success: function() {
						$(row).hide(1000, function() {
							$(this).remove();
						});
					}
				});
        	}
        </script>
	</body>
</html>
</jsp:root>