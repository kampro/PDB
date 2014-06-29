package com.kamilprokop.pdb;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.widget.Toast;

public class PersonsListActivity extends FragmentActivity implements PersonsListFragment.Callbacks, PersonFragment.Callbacks
{
	@Override
	protected int getLayout()
	{
		return R.layout.activity_masterdetail;
	}
	
	@Override
	protected Fragment createFragment()
	{
		return new PersonsListFragment();
	}
	
	public void onPersonSaved()
	{
		FragmentManager fm = getFragmentManager();
		PersonsListFragment f = (PersonsListFragment)fm.findFragmentById(R.id.frame_layout_master);
		((PersonsListFragment.PersonsListAdapter)f.getListAdapter()).notifyDataSetChanged();
		
		Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
	}
	
	public void onAddPersonClick()
	{
		if(findViewById(R.id.frame_layout_detail) == null)
		{
			Intent i = new Intent(this, PersonActivity.class);
			startActivity(i);
		}
		else
		{
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			
			Fragment fOld = fm.findFragmentById(R.id.frame_layout_detail);
			Fragment fNew = PersonFragment.getInstance(null);
			
			if(fOld != null)
				ft.remove(fOld);
			
			ft.add(R.id.frame_layout_detail, fNew)
				.commit();
		}
	}
	
	public void onPersonClick(Person person)
	{
		if(findViewById(R.id.frame_layout_detail) == null)
		{
			Intent i = new Intent(this, PersonActivity.class);
			i.putExtra(PersonFragment.EXTRA_PERSON, person);
			startActivity(i);
		}
		else
		{
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			
			Fragment fOld = fm.findFragmentById(R.id.frame_layout_detail);
			Fragment fNew = PersonFragment.getInstance(person);
			
			if(fOld != null)
				ft.remove(fOld);
			
			ft.add(R.id.frame_layout_detail, fNew)
				.commit();
		}
	}
}
