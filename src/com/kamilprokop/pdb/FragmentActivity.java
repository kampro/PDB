package com.kamilprokop.pdb;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public abstract class FragmentActivity extends Activity
{
	protected abstract Fragment createFragment();
	
	protected int getLayout()
	{
		return R.layout.activity_fragment_container;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(getLayout());
		
		FragmentManager fm = getFragmentManager();
		Fragment f = fm.findFragmentById(R.id.frame_layout_master);
		
		if(f == null)
		{
			f = createFragment();
			
			fm.beginTransaction()
				.add(R.id.frame_layout_master, f)
				.commit();
		}
	}
}
