package com.kamilprokop.pdb;

import android.app.Fragment;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

public class PhotoFragment extends Fragment
{
	private Camera camera;
	private SurfaceView surfaceView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if(NavUtils.getParentActivityName(getActivity()) != null)
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		
		View v = inflater.inflate(R.layout.fragment_photo, container, false);
		
		this.surfaceView = (SurfaceView)v.findViewById(R.id.surface_view_photo);
		SurfaceHolder holder = this.surfaceView.getHolder();
		//holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); -- DEPRECATED
		holder.addCallback(new SurfaceHolder.Callback()
		{
			@Override
			public void surfaceCreated(SurfaceHolder holder)
			{
				try
				{
					if(camera != null)
						camera.setPreviewDisplay(holder);
				}
				catch(Exception ex)
				{}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
			{
				if(camera == null)
					return;
				
				try
				{
					camera.startPreview();
				}
				catch(Exception ex)
				{
					camera.release();
					camera = null;
				}
			}
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder)
			{
				if(camera != null)
					camera.stopPreview();
			}
		});
		
		/*Button getDataBtn = (Button)v.findViewById(R.id.button_get_birth_date);
		getDataBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Toast.makeText(getActivity().getApplicationContext(), "Bêdzie pobieranie", Toast.LENGTH_SHORT)
					.show();			
			}
		});*/
		
		return v;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		this.camera = Camera.open(0);
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		
		if(this.camera != null)
		{
			this.camera.release();
			this.camera = null;
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case android.R.id.home:
				if (NavUtils.getParentActivityName(getActivity()) != null)
					NavUtils.navigateUpFromSameTask(getActivity());
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
