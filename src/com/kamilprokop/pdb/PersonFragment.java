package com.kamilprokop.pdb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class PersonFragment extends Fragment
{
	private static final int REQUEST_PHOTO = 0;
	private static final String DIALOG_INFO = "dialog info";
	
	private ImageView mImgView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		final View v = inflater.inflate(R.layout.fragment_person, container, false);
		
		Button getDataBtn = (Button)v.findViewById(R.id.button_get_data);
		getDataBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v2)
			{
				EditText etPesel = (EditText)v.findViewById(R.id.edit_text_persons_id);
				String peselStr = etPesel.getText().toString();
				
				PESEL pesel = new PESEL(peselStr);
				Calendar cal = pesel.getCalendar();
				
				if(cal == null)
				{
					FragmentManager fm = getActivity().getFragmentManager();
					InfoDialogFragment dialog = new InfoDialogFragment();
					dialog.setMessage(getActivity().getResources().getString(R.string.incorrect_persons_id));
					dialog.setTitle(getActivity().getResources().getString(R.string.data_error));
					dialog.show(fm, DIALOG_INFO);
				}
				else
				{
					EditText etDate = (EditText)v.findViewById(R.id.edit_text_birth_date);
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
					etDate.setText(df.format(cal.getTime()));
					
					RadioButton rbSex;
					
					if(pesel.isFemale())
						rbSex = (RadioButton)v.findViewById(R.id.radio_female);
					else
						rbSex = (RadioButton)v.findViewById(R.id.radio_male);
					
					rbSex.setChecked(true);
				}
			}
		});
		
		ImageButton imgBtn = (ImageButton)v.findViewById(R.id.image_button_photo);
		imgBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(getActivity(), PhotoActivity.class);
				
				startActivityForResult(i, REQUEST_PHOTO);
			}
		});
		
		mImgView = (ImageView)v.findViewById(R.id.image_view_person);
		
		return v;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(resultCode != Activity.RESULT_OK)
			return;
		
		if(requestCode == REQUEST_PHOTO)
		{
			String filename = data.getStringExtra(PhotoFragment.EXTRA_PHOTO_FILENAME);
			String filePath = getActivity().getExternalFilesDir(null) + filename; // getActivity().getFilesDir() -- path for app internal storage
			
			if(filename != null)
			{
				mImgView.setImageBitmap(BitmapFactory.decodeFile(filePath));
				Toast.makeText(getActivity(), R.string.photo_taken, Toast.LENGTH_SHORT).show();
			}
		}
	}
}
