package com.kamilprokop.pdb;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class PersonFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_persons_file, container, false);
		
		/*Button getDataBtn = (Button)v.findViewById(R.id.button_get_birth_date);
		getDataBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(getActivity(), PhotoActivity.class);
				
				startActivity(i);
			}
		});*/
		ImageButton imgBtn = (ImageButton)v.findViewById(R.id.image_button_photo);
		imgBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(getActivity(), PhotoActivity.class);
				
				startActivity(i);
			}
		});
		
		return v;
	}
}
