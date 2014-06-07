package com.kamilprokop.pdb;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PersonsListFragment extends ListFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		ListAdapter listAdapter = new ArrayAdapter<Person>(getActivity(), android.R.layout.simple_list_item_1, PersonsDB.getInstance(getActivity()).getPersons());
		setListAdapter(listAdapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		Person person = (Person)getListAdapter().getItem(position);
		
		Intent i = new Intent(getActivity(), PersonActivity.class);
		i.putExtra(PersonFragment.EXTRA_PERSON, person);
		startActivity(i);
	}
}