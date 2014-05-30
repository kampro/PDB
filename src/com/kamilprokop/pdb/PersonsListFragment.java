package com.kamilprokop.pdb;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class PersonsListFragment extends ListFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		ListAdapter listAdapter = new ArrayAdapter<Person>(getActivity(), android.R.layout.simple_list_item_1, PersonsDB.getInstance(getActivity()).getPersons());
		setListAdapter(listAdapter);
	}
}