<center><form method=post action=GlobalServlet?action=send>
			<h1>MAIL BOX OF ${name }</h1>
			<br>
			<br>
			<a href=logout>logout</a>
			<a href=inbox1>inbox</a>
			<a href=compose>compose</a>
			<table border=.6>
			<tr>
					<td>TO</td>
					<td><input type=text name=to value=${name1 }></td>
			</tr>
			<tr>
					<td>FROM</td>
					<td><input type=text name=from value=${name }></td>
			</tr>
			<tr>
					<td>SUBJECT</td>
					<td><input type=text name=subject value=RE:${name2 }></td>
			</tr>
			<tr>
					<td></td>

					<td><textarea rows=3 column=5 name=message>@@&&:${name3 }</textarea></td>
			</tr>
			<tr>
					<td><input type=submit value=send ></td>
			</tr>
					
			
			</table>
			</form>
			</center>