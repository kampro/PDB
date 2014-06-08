package com.kamilprokop.pdb;

import android.app.Fragment;

public class PhotoActivity extends FragmentActivity
{
	@Override
	protected Fragment createFragment()
	{
		return new PhotoFragment();
	}
}
