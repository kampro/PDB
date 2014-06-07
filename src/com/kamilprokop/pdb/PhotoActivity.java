package com.kamilprokop.pdb;

import android.app.Fragment;

public class PhotoActivity extends OneFragmentActivity
{
	@Override
	protected Fragment createFragment()
	{
		return new PhotoFragment();
	}
}
