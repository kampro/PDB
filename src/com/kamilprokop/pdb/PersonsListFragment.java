package com.kamilprokop.pdb;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PersonsListFragment extends ListFragment
{
	private Callbacks mCallbacks;
	
	public interface Callbacks
	{
		void onPersonClick(Person person);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		ListAdapter listAdapter = new ArrayAdapter<Person>(getActivity(), android.R.layout.simple_list_item_1, PersonsDB.getInstance(getActivity()).getPersons());
		setListAdapter(listAdapter);
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		
		mCallbacks = (Callbacks)activity;
	}
	
	@Override
	public void onDetach()
	{
		super.onDetach();

		mCallbacks = null;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		Person person = (Person)getListAdapter().getItem(position);
		
		mCallbacks.onPersonClick(person);
	}
}