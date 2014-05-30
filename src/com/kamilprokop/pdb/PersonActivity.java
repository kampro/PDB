package com.kamilprokop.pdb;

import android.app.Fragment;

public class PersonActivity extends OneFragmentActivity
{
	@Override
	protected Fragment createFragment()
	{
		return new PersonFragment();
	}
}
