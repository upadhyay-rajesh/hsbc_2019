package com.mail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mail.entity.Country;
import com.mail.entity.Login;
import com.mail.entity.MailBox;
import com.mail.entity.MailUser;
import com.mail.entity.State;
import com.mail.helper.DataBaseConnection;

public class MailDAO implements MailDAOInterface{

	@Override
	public String register(MailUser mm) {
		String userid=null;
		try{
		Connection conn=DataBaseConnection.getConnection();	
		String sql="insert into register(username,password,address,email) values(?,?,?,?)";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1,mm.getUsername());
		stmt.setString(2,mm.getPassword());
		stmt.setString(3,mm.getEmail());
		stmt.setString(4,mm.getAddress());
		int res1=stmt.executeUpdate();

		String sql1="select * from register";
		Statement stmt1=conn.createStatement();
		ResultSet res2=stmt1.executeQuery(sql1);
		int j=0;
		while(res2.next())
		{
			j=res2.getInt("id");
		}
		
		userid=j+mm.getUsername();

		String sql2="insert into login values(?,?)";
		PreparedStatement stmt2=conn.prepareStatement(sql2);
		stmt2.setString(1, userid);
		stmt2.setString(2, mm.getPassword());
		int res3=stmt2.executeUpdate();
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return userid;
	}

	@Override
	public boolean login(Login ll) {
		boolean bb=false;
		try{
		Connection conn=DataBaseConnection.getConnection();	
		String sql="select * from login where id=? and password=?";
		PreparedStatement stmt2=conn.prepareStatement(sql);
		stmt2.setString(1, ll.getId());
		stmt2.setString(2,ll.getPassword());
		ResultSet res1=stmt2.executeQuery();
		if(res1.next()){
			bb=true;
		}
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return bb;
	}

	@Override
	public int sendmail(MailBox mm) {
		int i=0;
		try{
		Connection conn=DataBaseConnection.getConnection();

		String sql="insert into mailbox values(?,?,?,?,?)";
		
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1,mm.getSender());
		stmt.setString(2,mm.getReceiver());
		stmt.setString(3,mm.getSubject());
		stmt.setString(4,mm.getSenddate());
		stmt.setString(5, mm.getMessage());
		i=stmt.executeUpdate();
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return i;
	}

	@Override
	public ArrayList<MailBox> mail(MailBox mm) {
		ArrayList<MailBox> mm1=new ArrayList<MailBox>();
		try{
		Connection conn=DataBaseConnection.getConnection();
		System.out.println(mm.getReceiver());
		String sql1="select * from mailbox where receiver=?";
		PreparedStatement stmt2=conn.prepareStatement(sql1);
		stmt2.setString(1, mm.getReceiver());
		ResultSet res2=stmt2.executeQuery();
		while(res2.next()){
			MailBox m1=new MailBox();
			m1.setSender(res2.getString(1));
			m1.setReceiver(res2.getString(2));
			m1.setSubject(res2.getString("subject"));
			m1.setSenddate(res2.getString(4));
			m1.setMessage(res2.getString(5));
			
			mm1.add(m1);
		}
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return mm1;
	}

	@Override
	public int mailCount(Login ll) {
		int count=0;
		try{
			Connection conn=DataBaseConnection.getConnection();    
		    String sql1="select * from mailbox where receiver=?";
				PreparedStatement stmt1=conn.prepareStatement(sql1);
				stmt1.setString(1, ll.getId());
				ResultSet res2=stmt1.executeQuery();
				
				while(res2.next())
				{
					count++;
				}

		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return count;
	}

	@Override
	public MailBox mail1(MailBox mm) {
		MailBox m1=null;
		try{
			Connection conn=DataBaseConnection.getConnection();
			System.out.println(mm.getReceiver()+"  "+mm.getSubject());
			String sql1="select * from mailbox where receiver=? and subject=?";
			PreparedStatement stmt2=conn.prepareStatement(sql1);
			stmt2.setString(1, mm.getReceiver());
			stmt2.setString(2, mm.getSubject());
			ResultSet res2=stmt2.executeQuery();
			if(res2.next()){
				m1=new MailBox();
				m1.setSender(res2.getString(1));
				m1.setReceiver(res2.getString(2));
				m1.setSubject(res2.getString(3));
				m1.setSenddate(res2.getString(4));
				m1.setMessage(res2.getString(5));
				
				
			}
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		return m1;
	}

	@Override
	public int deleteMail(String string) {
		int i=0;
		try{
		Connection conn=DataBaseConnection.getConnection();

		String sql="delete from mailbox where subject=?";
		
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1,string);
		i=stmt.executeUpdate();
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return i;
	}

	@Override
	public ArrayList<Country> getCountryList() {
		ArrayList<Country> mm1=new ArrayList<Country>();
		try{
		Connection conn=DataBaseConnection.getConnection();
		
		String sql1="select * from country";
		PreparedStatement stmt2=conn.prepareStatement(sql1);
		
		ResultSet res2=stmt2.executeQuery();
		while(res2.next()){
			Country m1=new Country();
			m1.setCountryId(res2.getInt(1));
			m1.setCountryName(res2.getString(2));
			m1.setCountryPopulation(res2.getLong(3));
			mm1.add(m1);
		}
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return mm1;
	}
	
	@Override
	public ArrayList<State> getStateList(Country z) {
		ArrayList<State> mm1=new ArrayList<State>();
		try{
		Connection conn=DataBaseConnection.getConnection();
		
		String sql1="select s.stateId,s.stateName,s.statePopulation from country c,state s where c.countryName=? and c.countryId=s.countryId";
		PreparedStatement stmt2=conn.prepareStatement(sql1);
		stmt2.setString(1,z.getCountryName());
		ResultSet res2=stmt2.executeQuery();
		while(res2.next()){
			State m1=new State();
			m1.setStateId(res2.getInt(1));
			m1.setStateName(res2.getString(2));
			m1.setStatePopulation(res2.getLong(3));
			mm1.add(m1);
		}
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return mm1;
	}
	
	@Override
	public ArrayList<Country> getCityList() {
		ArrayList<Country> mm1=new ArrayList<Country>();
		try{
		Connection conn=DataBaseConnection.getConnection();
		
		String sql1="select * from country";
		PreparedStatement stmt2=conn.prepareStatement(sql1);
		
		ResultSet res2=stmt2.executeQuery();
		while(res2.next()){
			Country m1=new Country();
			m1.setCountryId(res2.getInt(1));
			m1.setCountryName(res2.getString(2));
			m1.setCountryPopulation(res2.getLong(3));
			mm1.add(m1);
		}
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return mm1;
	}

}

















