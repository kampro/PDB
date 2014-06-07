package com.kamilprokop.pdb;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class PersonsDB
{
	private static PersonsDB mInstance;
	//private Context mAppContext;
	private List<Person> mPersons;
	
	protected PersonsDB(Context appContext)
	{
		//mAppContext = appContext;
		
		mPersons = new ArrayList<Person>();
		Person p;
		for(int i = 0; i < 30; i++)
		{
			p = new Person();
			p.setName("Imiê " + i);
			p.setSurname("Nazwisko" + i);
			mPersons.add(p);
		}
	}
	
	public static PersonsDB getInstance(Context context)
	{
		if(mInstance == null)
			mInstance = new PersonsDB(context.getApplicationContext());
		
		return mInstance;
	}
	
	public ArrayList<Person> getPersons()
	{
		return (ArrayList<Person>)mPersons;
	}
}
