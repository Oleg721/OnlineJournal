<%@page import="dao.impl.MarkDaoImpl"%>
<%@page import="dao.MarkDao"%>
<%@page import="bom.Mark"%>
<%@page import="bom.Lesson"%>
<%@page import="dao.impl.LessonDaoImpl"%>
<%@page import="dao.LessonDao"%>
<%@page import="bom.Pupil"%>
<%@page import="dao.impl.PupilDaoImpl"%>
<%@page import="dao.PupilDao"%>
<%@page import="bom.Form"%>
<%@page import="dao.impl.FormDaoImpl"%>
<%@page import="dao.FormDao"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!FormDao formDao = new FormDaoImpl();
	PupilDao pupilDao = new PupilDaoImpl();
	LessonDao lessonDao = new LessonDaoImpl();
	MarkDao markDao = new MarkDaoImpl();%>
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
				<select name="formID">
					<%
						List<Form> formList = formDao.getAllForms();

						Integer classID = Integer.valueOf(request.getParameter("formID"));

						String className = "";

						for (Form form : formList) {
							if (classID == form.getId()) {
								className = form.getName();
					%>
					<option value="<%=form.getId()%>" selected>
						<%="&nbsp&nbsp&nbsp" + className + "&nbsp&nbsp&nbsp"%>
					</option>
					<%
						} else {
					%>
					<option value="<%=form.getId()%>"><%="&nbsp&nbsp&nbsp" + form.getName() + "&nbsp&nbsp&nbsp"%>
					</option>
					<%
						}
						}
					%>
				</select>
			</p>
			<p>
				<input id="navButton" type="submit" value="Choose">
			</p>
		</form>
		<form action="AddForm" method="get">
			<input type="hidden" name="formID" value=" <%=classID%>">
			<h2>Add class</h2>
			<p>
				<Input type="text" maxlength="4" size="4" name="addingform">
		</p>
				<input id="navButton" type="submit" value="Apply">
		
		</form>
	</div>
		<div id="add">
		<div id="addPanel">
			<form action="AddPupil" method="GET">
				<input type="hidden" name="formID" value=" <%=classID%>">
				<input type="text" name="name" placeholder="Name" maxlength="20"> 
				<input type="text" name="sername" placeholder="Sername" maxlength="20">
				<button type="submit" >
				<div>Add pupil</div>
				</button>
			</form>
		</div>
				<div id="addLesson">
			<form action="AddLesson" method="GET">
				<input type="hidden" name="formID" value=" <%=classID%>">
				<input type="date" name="date" placeholder="yyyy-mm-dd" > 
				<input type="text" name="theme" placeholder="Enter theme" maxlength="200">
				<button type="submit" >
				<div>Add lesson</div>
				</button>
			</form>
		</div>
		</div>
	<div id="table">

		<form action="SaveMark" method="get">
			<input type="hidden" name="formID" value=" <%=classID%>">
			<table cellpadding="1" cellspacing="1">
				<tr>
					<td id="classname"><%=className%></td>
					<%
						List<Lesson> lessonByClass = lessonDao.getLessonsByClass(classID);
						
						for (Lesson lesson : lessonByClass) {
					%>
					<td id="lessonsdate"><%=lesson.getLessonDate().toString().substring(0,5)%>
					<br><%=lesson.getLessonDate().toString().substring(5)%>
					</td>
					<%
						}
					%>
				</tr>
				<%
					for (Pupil pupil : pupilDao.getPupilByForm(classID)) {
						List<Mark> pupilMark = markDao.getMarksByPupil(pupil.getId());
						Integer pupilID = pupil.getId();
				%>
				<tr>
					<td id="pupilCell"><nobr><%=pupil.getName() + " " + pupil.getSurname()%></nobr></td>
					<%
						for (Lesson lesson : lessonByClass) {
								Integer lessonID = lesson.getId();
					%>
					<td><Input id="mark" type="text" maxlength="1" size="1"
						onchange="name='markValue';
								  document.getElementById('<%=pupilID + "p" + lessonID%>').name='pupilID';
								  document.getElementById('<%=pupilID + "l" + lessonID%>').name='lessonID';
								  document.getElementById('<%=pupilID + "m" + lessonID%>').name='oldMark'"
						<%Integer mark = markDao.markOfPupilByLesson(pupilMark, lesson.getId());
					String MarkAndAbsence = "";
					if (mark == 0) {
						MarkAndAbsence = "N";
					} else {
						MarkAndAbsence = mark != -1 ? mark.toString() : "";
					}%>
						value="<%=MarkAndAbsence%>"> <input
						id="<%=pupilID + "p" + lessonID%>" type="hidden"
						value=" <%=pupilID%>"> <input
						id="<%=pupilID + "l" + lessonID%>" type="hidden"
						value=" <%=lessonID%>"><input
						id="<%=pupilID + "m" + lessonID%>" type="hidden"
						value=" <%=MarkAndAbsence%>"></td>
					<%
						}
					%>
				</tr>

				<%
					}
				%>

			</table>
			<input id="saveButton" type="submit" value="Save">
		</form>
					<ul>
				<%
					for (Lesson lesson : lessonByClass) {
				%>

				<li><%=lesson.getLessonDate() + " - " + lesson.getTheme()%></li>

				<%
					}
				%>
			</ul>
	</div>
</body>
</html>