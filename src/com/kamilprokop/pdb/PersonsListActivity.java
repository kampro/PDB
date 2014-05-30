package com.kamilprokop.pdb;

import android.app.Fragment;

public class PersonsListActivity extends OneFragmentActivity
{
	@Override
	protected Fragment createFragment()
	{
		return new PersonsListFragment();
	}
}
