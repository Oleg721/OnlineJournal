<%@page import="bom.Form"%>
<%@page import="dao.impl.FormDaoImpl"%>
<%@page import="dao.FormDao"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!FormDao formDao = new FormDaoImpl();%>
<html>
<head>
<title>Journal</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="basics.css" />
</head>
<body>
	<div id="hdr">
		<h1>Journal</h1>
	</div>
	<div id="nav">
		<h2>Class</h2>
		<form action="JournalOfClass.jsp" method="get">
			<p>
				<select name="formID" >
					<%
						List<Form> formList = formDao.getAllForms();

						for (Form form : formList) {
					%>
					<option value="<%=form.getId()%>"><%="&nbsp&nbsp&nbsp" + form.getName() + "&nbsp&nbsp&nbsp"%>
					</option>

					<%
						}
					%>

				</select>
			</p>
			<p>
				<input id="navButton" type="submit" value="Choose">
			</p>
		</form>
		<form action="AddForm" method="get">
		<input type="hidden" name="formID">
			<h2>Add class</h2>
			<p>
				<Input type="text" maxlength="4" size="4" name="addingform" placeholder="Class">
			</p>
				<input id="navButton" type="submit" value="Apply">
			
		</form>
	</div>
	<div id="table">
		<h2>Select the class</h2>
	</div>

</body>
</html>