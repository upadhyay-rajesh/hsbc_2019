<%@page import="com.mail.entity.Country"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mail.dao.MailDAO"%>
<%@page import="com.mail.dao.MailDAOInterface"%>
<%
	MailDAOInterface mm=new MailDAO();
	ArrayList<Country> cc=mm.getCountryList();
	String cc1 = "<select id=cou1 onchange=loadState() ><option>select</option>";
	for(Country c:cc){
	cc1=cc1+"<option>"+c.getCountryName()+"</option>";
	}
	cc1=cc1+"</select>";
	out.println(cc1);
%>


		