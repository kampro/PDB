package com.kamilprokop.pdb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import android.content.Context;

public class PersonsDB
{
	private static PersonsDB mInstance;
	//private Context mAppContext;
	private PersonsDatabaseHelper mPersonsDBHelper;
	
	protected PersonsDB(Context context)
	{
		//mAppContext = appContext;
		mPersonsDBHelper = new PersonsDatabaseHelper(context);
	}
	
	public static PersonsDB getInstance(Context context)
	{
		if(mInstance == null)
			mInstance = new PersonsDB(context.getApplicationContext());
		
		return mInstance;
	}
	
	public PersonsDatabaseHelper.PersonCursor queryPersons()
	{
		return mPersonsDBHelper.queryPersons();
	}
}
