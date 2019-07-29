<h1>MAIL BOX OF ${name }</h1>
			<br>
			<br>
			<a href=GlobalServlet?action=delete>delete</a>
			<a href=GlobalServlet?action=logout>logout</a>
			<a href=GlobalServlet?action=inbox1>inbox</a>
			<a href=GlobalServlet?action=compose>compose</a>

			<table border=1>
					
			<tr>
					<td>message</td>
					<td><textarea rows=3 column=5 name=c >${cc.message }</textarea></td>
									
				</tr>
			
			</table>
			<a href=GlobalServlet?action=reply&to=${cc.sender}&subject=${cc.subject}&message=${cc.message}>reply</a>
			
			
			