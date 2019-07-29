package com.mail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail.dao.MailDAO;
import com.mail.dao.MailDAOInterface;
import com.mail.entity.Login;
import com.mail.entity.MailBox;
import com.mail.entity.MailUser;

public class GlobalServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a=request.getParameter("action");
		
		PrintWriter out=response.getWriter();
		if(a.equals("register")){
			String accnum=request.getParameter("a");
			String accnum1=request.getParameter("a1");
			String accnum2=request.getParameter("a2");
			String accnum3=request.getParameter("a3");
			
			MailUser mm=new MailUser();
			mm.setUsername(accnum);
			mm.setPassword(accnum1);
			mm.setEmail(accnum2);
			mm.setAddress(accnum3);
			
			MailDAOInterface md=new MailDAO();
			String userid=md.register(mm);
			
			request.setAttribute("name",accnum );
			request.setAttribute("pass",accnum1 );
			request.setAttribute("id",userid );
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/registerSuccess.jsp");
			rd.forward(request, response);
			
		}
		if(a.equals("login")){
			String accnum=request.getParameter("a");
			String accnum1=request.getParameter("a1");

			Login ll=new Login();
			ll.setId(accnum);
			ll.setPassword(accnum1);
			
			MailDAOInterface md=new MailDAO();
			boolean b=md.login(ll);
			if(b){
				HttpSession sc=request.getSession(true);
				sc.setAttribute("id",accnum); 
				
				int count=md.mailCount(ll);
				request.setAttribute("name", accnum);
				request.setAttribute("cc", count);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/loginSuccess.jsp");
				rd.forward(request, response);
			}
			else{
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/loginFail.jsp");
				rd.forward(request, response);
			}
			
			
		}
		if(a.equals("inbox1")){
			HttpSession sc=request.getSession(true);
			Object p=sc.getAttribute("id");
		
			String accnum=p.toString();
			MailBox mm=new MailBox();
			mm.setReceiver(accnum);
			
			MailDAOInterface md=new MailDAO();
			ArrayList<MailBox> aa=md.mail(mm);
			
			request.setAttribute("name", accnum);
			request.setAttribute("cc", aa);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/inbox1.jsp");
			rd.forward(request, response);
		}
		
		if(a.equals("mail")){
			HttpSession sc=request.getSession(true);
			Object p=sc.getAttribute("id");
			String accnum=p.toString();

			String mail1=request.getParameter("m");
			MailBox mm=new MailBox();
			mm.setReceiver(accnum);
			mm.setSubject(mail1);
			
			MailDAOInterface md=new MailDAO();
			MailBox mm1=md.mail1(mm);
			
			request.setAttribute("name", accnum);
			request.setAttribute("cc", mm1);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/mail.jsp");
			rd.forward(request, response);
			
		}
		if(a.equals("reply")){
			String accnum1=request.getParameter("to");
			String accnum2=request.getParameter("subject");
			String accnum3=request.getParameter("message");
			
			HttpSession sc=request.getSession(true);
			Object p=sc.getAttribute("id");
			String accnum=p.toString();
			
			request.setAttribute("name", accnum);
			request.setAttribute("name1", accnum1);
			request.setAttribute("name2", accnum2);
			request.setAttribute("name3", accnum3);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/reply.jsp");
			rd.forward(request, response);
			
		}
		if(a.equals("logout")){
			HttpSession hs=request.getSession(true);
			hs.setAttribute("id","");
			hs.invalidate();
			
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/logout.jsp");
			rd.forward(request, response);

		}
		if(a.equals("compose")){
			HttpSession sc=request.getSession(true);
			Object p=sc.getAttribute("id");
			
			String accnum=p.toString();
			
			request.setAttribute("name", accnum);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/compose.jsp");
			rd.forward(request, response);
			
		}
		if(a.equals("send")){
			String accnum=request.getParameter("to");
			String accnum1=request.getParameter("from");
			String accnum2=request.getParameter("subject");
			String accnum3=request.getParameter("message");
			String sysdate=new java.util.Date().toString();
			
			MailBox mm=new MailBox();
			mm.setSender(accnum1);
			mm.setReceiver(accnum);
			mm.setSubject(accnum2);
			mm.setMessage(accnum3);
			mm.setSenddate(sysdate);
			
			MailDAOInterface md=new MailDAO();
			int i=md.sendmail(mm);
			
			if(i>0){
				request.setAttribute("name", accnum);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/sendmail.jsp");
				rd.forward(request, response);
			}
			
		}
		if(a.equals("delete")){
			int ii=0;
			String aa[]=request.getParameterValues("c");
			MailDAOInterface md=new MailDAO();
			for(int i=0;i<aa.length;i++){
				ii=md.deleteMail(aa[i]);
			}
			if(ii>0){
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/GlobalServlet?action=inbox1");
				rd.forward(request, response);
			}
		}
		
		
	}

}














