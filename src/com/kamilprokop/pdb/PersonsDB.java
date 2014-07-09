package com.kamilprokop.pdb;

import android.content.Context;

public class PersonsDB
{
	private static PersonsDB mInstance;
	// private Context mAppContext;
	private PersonsDatabaseHelper mPersonsDBHelper;

	protected PersonsDB(Context context)
	{
		// mAppContext = appContext;
		mPersonsDBHelper = new PersonsDatabaseHelper(context);
	}

	public static PersonsDB getInstance(Context context)
	{
		if (mInstance == null)
			mInstance = new PersonsDB(context.getApplicationContext());

		return mInstance;
	}

	public PersonsDatabaseHelper.PersonCursor queryPersons()
	{
		return mPersonsDBHelper.queryPersons();
	}

	public void insertPerson(Person person)
	{
		if (person.getId() == -1)
			mPersonsDBHelper.insertPerson(person);
	}
}
