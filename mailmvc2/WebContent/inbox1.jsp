<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

			<h1>MAIL BOX OF ${name }</h1>
			<br>
			<br>
			<form method=post action=GlobalServlet?action=delete><input type=submit value=delete>
			<a href=GlobalServlet?action=logout>logout</a>
			<a href=GlobalServlet?action=inbox1>inbox</a>
			<a href=GlobalServlet?action=compose>compose</a>
			<table border=1>
			<tr>
				<th>
					select
				</th>
				<th>
					subject
				</th>
				<th>
					sender
				</th>
				<th>
					time
				</th>
			</tr>
			<c:forEach items="${cc}" var="res2">
			
				<tr>
					<td><input type=checkbox name=c value="${res2.subject}"></td>
					<td><a href=GlobalServlet?action=mail&m=${res2.subject}>${res2.subject}</a></td>
					<td><c:out value="${res2.sender}"></c:out></td>
					<td><c:out value="${res2.senddate}"></c:out></td>
				</tr>
			</c:forEach>
			
			
			</table></form>
			
			
			
			
			
			
			
			
			
			