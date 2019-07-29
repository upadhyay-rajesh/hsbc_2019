<%@page import="com.mail.entity.State"%>
<%@page import="com.mail.entity.Country"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mail.dao.MailDAO"%>
<%@page import="com.mail.dao.MailDAOInterface"%>
<%
  String ss=request.getParameter("cname");
  Country z=new Country();
  z.setCountryName(ss);
	MailDAOInterface mm=new MailDAO();
	ArrayList<State> cc=mm.getStateList(z);
		
	String cc1 = "<select id=cou1 onchange=loadCity() ><option>select</option>";
	for(State c:cc){
	cc1=cc1+"<option>"+c.getStateName()+"</option>";
	}
	cc1=cc1+"</select>";
	out.println(cc1);
%>


