package com.kamilprokop.pdb;

import android.app.Fragment;

public class PersonActivity extends FragmentActivity implements PersonFragment.Callbacks
{
	@Override
	protected Fragment createFragment()
	{
		Person person = (Person)getIntent().getSerializableExtra(PersonFragment.EXTRA_PERSON);
		
		return PersonFragment.getInstance(person);
	}
	
	public void onPersonSaved()
	{
		finish();
	}
}
