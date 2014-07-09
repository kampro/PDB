package com.kamilprokop.pdb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonsDatabaseHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "persons.sqlite";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_PERSONS = "persons";

	private static final String COLUMN_PERSONS_NAME = "name";
	private static final String COLUMN_PERSONS_SURNAME = "surname";
	private static final String COLUMN_PERSONS_PESEL = "pesel";
	private static final String COLUMN_PERSONS_STREET = "street";
	private static final String COLUMN_PERSONS_HOUSE_NO = "house_no";
	private static final String COLUMN_PERSONS_APARTMENT_NO = "apartment_no";
	private static final String COLUMN_PERSONS_POSTAL_CODE = "postal_code";
	private static final String COLUMN_PERSONS_CITY = "city";
	private static final String COLUMN_PERSONS_INSURANCE = "insurance";
	private static final String COLUMN_PERSONS_BIRTH_DATE = "birth_date";
	private static final String COLUMN_PERSONS_SEX = "sex";
	private static final String COLUMN_PERSONS_OTHER_DOCUMENT_ID = "other_document_id";

	public PersonsDatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("create table persons (_id integer primary key autoincrement,"
				+ "name varchar(160),"
				+ "surname varchar(160),"
				+ "pesel varchar(50),"
				+ "street varchar(160),"
				+ "house_no varchar(50),"
				+ "apartment_no varchar(50),"
				+ "postal_code varchar(50),"
				+ "city varchar(160),"
				+ "insurance integer,"
				+ "birth_date varchar(50),"
				+ "sex integer," + "other_document_id varchar(160))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}

	public long insertPerson(Person p)
	{
		p.setSurname("p");
		p.setPESEL("90042412853");
		p.setStreet("a");
		p.setHouseNo("5");
		p.setApartmentNo("5");
		p.setPostalCode("45");
		p.setCity("j");
		p.setInsurance(true);
		p.setBirthDate(new GregorianCalendar());
		p.setSex(Person.Sex.MALE);
		p.setOtherDocumentID("0");

		int insurance;

		if (p.isInsurance())
			insurance = 1;
		else
			insurance = 0;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		String birthDate = df.format(p.getBirthDate().getTime());

		int sex;

		if (p.getSex() == Person.Sex.MALE)
			sex = 1;
		else
			sex = 2;

		ContentValues cv = new ContentValues();
		cv.put(COLUMN_PERSONS_NAME, p.getName());
		cv.put(COLUMN_PERSONS_SURNAME, p.getSurname());
		cv.put(COLUMN_PERSONS_PESEL, p.getPESEL());
		cv.put(COLUMN_PERSONS_STREET, p.getStreet());
		cv.put(COLUMN_PERSONS_HOUSE_NO, p.getHouseNo());
		cv.put(COLUMN_PERSONS_APARTMENT_NO, p.getApartmentNo());
		cv.put(COLUMN_PERSONS_POSTAL_CODE, p.getPostalCode());
		cv.put(COLUMN_PERSONS_CITY, p.getCity());
		cv.put(COLUMN_PERSONS_INSURANCE, insurance);
		cv.put(COLUMN_PERSONS_BIRTH_DATE, birthDate);
		cv.put(COLUMN_PERSONS_SEX, sex);
		cv.put(COLUMN_PERSONS_OTHER_DOCUMENT_ID, p.getOtherDocumentID());

		return getWritableDatabase().insert(TABLE_PERSONS, null, cv);
	}

	public PersonCursor queryPersons()
	{
		Cursor c = getReadableDatabase().query(TABLE_PERSONS, null, null, null,
				null, null, COLUMN_PERSONS_SURNAME + " asc");

		return new PersonCursor(c);
	}

	public static class PersonCursor extends CursorWrapper
	{
		public PersonCursor(Cursor c)
		{
			super(c);
		}

		public Person getPerson()
		{
			if (isBeforeFirst() || isAfterLast())
				return null;

			boolean insurance;

			if (getInt(getColumnIndex(COLUMN_PERSONS_INSURANCE)) == 1)
				insurance = true;
			else
				insurance = false;

			Calendar cal;
			String[] date = getString(getColumnIndex(COLUMN_PERSONS_BIRTH_DATE))
					.split("-");

			if (date.length == 3)
			{
				cal = new GregorianCalendar();
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
				cal.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);
				cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
			}
			else
				cal = null;

			Person.Sex sex;

			if (getInt(getColumnIndex(COLUMN_PERSONS_SEX)) == 1)
				sex = Person.Sex.MALE;
			else
				sex = Person.Sex.FEMALE;

			Person p = new Person();
			p.setName(getString(getColumnIndex(COLUMN_PERSONS_NAME)));
			p.setSurname(getString(getColumnIndex(COLUMN_PERSONS_SURNAME)));
			p.setPESEL(getString(getColumnIndex(COLUMN_PERSONS_PESEL)));
			p.setStreet(getString(getColumnIndex(COLUMN_PERSONS_STREET)));
			p.setHouseNo(getString(getColumnIndex(COLUMN_PERSONS_HOUSE_NO)));
			p.setApartmentNo(getString(getColumnIndex(COLUMN_PERSONS_APARTMENT_NO)));
			p.setPostalCode(getString(getColumnIndex(COLUMN_PERSONS_POSTAL_CODE)));
			p.setCity(getString(getColumnIndex(COLUMN_PERSONS_CITY)));
			p.setInsurance(insurance);
			p.setBirthDate(cal);
			p.setSex(sex);
			p.setOtherDocumentID(getString(getColumnIndex(COLUMN_PERSONS_OTHER_DOCUMENT_ID)));

			return p;
		}
	}
}
