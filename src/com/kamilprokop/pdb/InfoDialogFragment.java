package com.kamilprokop.pdb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class InfoDialogFragment extends DialogFragment
{
	private String mMessage;
	private String mTitle;
	
	public void setMessage(String message)
	{
		mMessage = message;
	}
	
	public String getMessage()
	{
		return mMessage;
	}
	
	public void setTitle(String title)
	{
		mTitle = title;
	}
	
	public String getTitle()
	{
		return mTitle;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		if(mTitle == null)
			mTitle = "";
		
		if(mMessage == null)
			mMessage = "";
		
		return new AlertDialog.Builder(getActivity())
			.setTitle(mTitle)
			.setMessage(mMessage)
			.setPositiveButton(android.R.string.ok, null)
			.create();
	}
}
