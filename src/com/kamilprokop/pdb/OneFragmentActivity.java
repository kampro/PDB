package com.kamilprokop.pdb;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public abstract class OneFragmentActivity extends Activity
{
	protected abstract Fragment createFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_fragment_container_1);
		
		FragmentManager fm = getFragmentManager();
		Fragment f = fm.findFragmentById(R.id.frame_layout_container_1);
		
		if(f == null)
		{
			f = createFragment();
			
			fm.beginTransaction()
				.add(R.id.frame_layout_container_1, f)
				.commit();
		}
	}
}
