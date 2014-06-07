package com.kamilprokop.pdb;

import java.util.Calendar;

public class PESEL
{
	private String mPesel;
	
	public PESEL(String pesel)
	{
		if(pesel == null)
			mPesel = "";
		else
			mPesel = pesel;
	}
	
	public boolean isCorrect()
	{
		if(mPesel.length() != 11)
			return false;
		
		byte n0 = (byte)Character.getNumericValue(mPesel.charAt(0));
		byte n1 = (byte)Character.getNumericValue(mPesel.charAt(1));
		byte n2 = (byte)Character.getNumericValue(mPesel.charAt(2));
		byte n3 = (byte)Character.getNumericValue(mPesel.charAt(3));
		byte n4 = (byte)Character.getNumericValue(mPesel.charAt(4));
		byte n5 = (byte)Character.getNumericValue(mPesel.charAt(5));
		byte n6 = (byte)Character.getNumericValue(mPesel.charAt(6));
		byte n7 = (byte)Character.getNumericValue(mPesel.charAt(7));
		byte n8 = (byte)Character.getNumericValue(mPesel.charAt(8));
		byte n9 = (byte)Character.getNumericValue(mPesel.charAt(9));
		byte n10 = (byte)Character.getNumericValue(mPesel.charAt(10));
		
		int control = n0
				+ 3 * n1
				+ 7 * n2
				+ 9 * n3
				+ n4
				+ 3 * n5
				+ 7 * n6
				+ 9 * n7
				+ n8
				+ 3 * n9;
		
		int rest = control / 10;
		rest = control - (rest * 10);
		
		if(rest != 0)
			rest = 10 - rest;
		
		if(rest == n10)
			return true;
		else
			return false;
	}
	
	public boolean isFemale()
	{
		if(mPesel.length() != 11)
			return false;
		
		if(Character.getNumericValue(mPesel.charAt(9)) % 2 == 0)
			return true;
		else
			return false;
	}
	
	public Calendar getCalendar()
	{
		if(!isCorrect())
			return null;
		
		String year1 = "19";
		String year2 = mPesel.substring(0, 2);
		int month1 = Character.getNumericValue(mPesel.charAt(2));
		int minus = 0;
		
		if(month1 < 0)
			return null;
		
		if(month1 >= 2 && month1 <= 3)
		{
			year1 = "20";
			minus = 20;
		}
		else if(month1 >= 4 && month1 <= 5)
		{
			year1 = "21";
			minus = 40;
		}
		else if(month1 >= 6 && month1 <= 7)
		{
			year1 = "22";
			minus = 60;
		}
		else if(month1 >= 8 && month1 <= 9)
		{
			year1 = "18";
			minus = 80;
		}
		
		int year = Integer.parseInt(year1 + year2);
		int month = Integer.parseInt(mPesel.substring(2, 4)) - minus;
		int day = Integer.parseInt(mPesel.substring(4, 6));
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		
		return calendar;
	}
}
