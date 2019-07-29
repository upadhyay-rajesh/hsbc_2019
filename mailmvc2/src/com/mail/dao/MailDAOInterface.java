package com.mail.dao;

import java.util.ArrayList;

import com.mail.entity.Country;
import com.mail.entity.Login;
import com.mail.entity.MailBox;
import com.mail.entity.MailUser;
import com.mail.entity.State;

public interface MailDAOInterface {
	public String register(MailUser mm);
	public boolean login(Login ll);
	public int sendmail(MailBox mm);
	public ArrayList<MailBox> mail(MailBox mm);
	public int mailCount(Login ll);
	public MailBox mail1(MailBox mm);
	public int deleteMail(String string);
	public ArrayList<Country> getCountryList();
	ArrayList<Country> getCityList();
	ArrayList<State> getStateList(Country z);
}
