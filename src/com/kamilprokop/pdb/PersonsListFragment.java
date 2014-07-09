package com.kamilprokop.pdb;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class PersonsListFragment extends ListFragment
{
	private PersonsDatabaseHelper.PersonCursor mCursor;
	private Callbacks mCallbacks;

	public interface Callbacks
	{
		void onPersonClick(Person person);

		void onAddPersonClick();
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);

		mCursor = PersonsDB.getInstance(getActivity()).queryPersons();
		setListAdapter(new PersonsListAdapter(getActivity(), mCursor));
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.persons_list_fragment, menu);
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
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.menu_item_add_person:
			mCallbacks.onAddPersonClick();

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		Person person = (Person)getListAdapter().getItem(position);

		mCallbacks.onPersonClick(person);
	}

	public void refresh()
	{
		mCursor.requery();
		((PersonsListAdapter)getListAdapter()).notifyDataSetChanged();
	}

	public class PersonsListAdapter extends CursorAdapter
	{
		private PersonsDatabaseHelper.PersonCursor mCursor;

		public PersonsListAdapter(Context context,
				PersonsDatabaseHelper.PersonCursor cursor)
		{
			super(context, cursor, 0);

			mCursor = cursor;
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent)
		{
			LayoutInflater inflater = (LayoutInflater)context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			return inflater.inflate(R.layout.list_item_person, parent, false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor)
		{
			Person p = mCursor.getPerson();

			TextView tv = (TextView)view.findViewById(R.id.li_name);
			tv.setText(String.format("%s %s", p.getSurname(), p.getName()));

			tv = (TextView)view.findViewById(R.id.li_birth_date);
			tv.setText(String.format("%1$tY-%1$tm-%1$td", p.getBirthDate()));
		}
	}
}