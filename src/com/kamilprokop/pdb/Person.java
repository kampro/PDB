package com.kamilprokop.pdb;

import java.util.Date;
import java.util.UUID;

public class Person
{
	public enum Sex
	{
		MALE,
		FEMALE
	}
	
	private UUID mUuid;
	private String mName;
	private String mSurname;
	private int mPesel;
	private String mStreet;
	private String mHouseNo;
	private String mApartmentNo;
	private String mPostalCode;
	private String mCity;
	private boolean mInsurance;
	private Date mBirthDate;
	private Sex mSex;
	private String mOtherDocumentID;
	
	public Person()
	{
		mUuid = UUID.randomUUID();
	}
	
	public String getName()
	{
		return mName;
	}
	
	public void setName(String name)
	{
		mName = name;
	}
	
	public String getSurname()
	{
		return mSurname;
	}
	
	public void setSurname(String surname)
	{
		mSurname = surname;
	}
	
	public int getPESEL()
	{
		return mPesel;
	}
	
	public void setPESEL(int pesel)
	{
		mPesel = pesel;
	}
	
	public String getStreet()
	{
		return mStreet;
	}
	
	public void setStreet(String street)
	{
		mStreet = street;
	}
	
	public String getHouseNo()
	{
		return mHouseNo;
	}
	
	public void setHouseNo(String houseNo)
	{
		mHouseNo = houseNo;
	}
	
	public String getApartmentNo()
	{
		return mApartmentNo;
	}
	
	public void setApartmentNo(String apartmentNo)
	{
		mApartmentNo = apartmentNo;
	}
	
	public String getPostalCode()
	{
		return mPostalCode;
	}
	
	public void setPostalCode(String postalCode)
	{
		mPostalCode = postalCode;
	}
	
	public String getCity()
	{
		return mCity;
	}
	
	public void setCity(String city)
	{
		mCity = city;
	}
	
	public boolean isInsurance()
	{
		return mInsurance;
	}
	
	public void setInsurance(boolean insurance)
	{
		mInsurance = insurance;
	}
	
	public Date getBirthDate()
	{
		return mBirthDate;
	}
	
	public void setBirthDate(Date birthDate)
	{
		mBirthDate = birthDate;
	}
	
	public Sex getSex()
	{
		return mSex;
	}
	
	public void setSex(Sex sex)
	{
		mSex = sex;
	}
	
	public String getOtherDocumentID()
	{
		return mOtherDocumentID;
	}
	
	public void setOtherDocumentID(String otherDocumentID)
	{
		mOtherDocumentID = otherDocumentID;
	}
	
	public UUID getUUID()
	{
		return mUuid;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder(mName);
		sb.append(" ");
		sb.append(mSurname);
		
		return sb.toString();
	}
}
