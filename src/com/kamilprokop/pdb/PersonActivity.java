package com.kamilprokop.pdb;

import android.app.Fragment;

public class PersonActivity extends FragmentActivity
{
	@Override
	protected Fragment createFragment()
	{
		Person person = (Person)getIntent().getSerializableExtra(PersonFragment.EXTRA_PERSON);
		
		return PersonFragment.getInstance(person);
	}
}
