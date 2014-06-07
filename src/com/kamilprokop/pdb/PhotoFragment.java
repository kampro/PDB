package com.kamilprokop.pdb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class PhotoFragment extends Fragment
{
	public static final String EXTRA_PHOTO_FILENAME = "com.kamilprokop.photofragment.photofilename";
	private static final int CAMERA_ID = 0;
	
	private Camera mCamera;
	private SurfaceView mSurfaceView;
	
	private Camera.PictureCallback cameraSavePictureCallback = new Camera.PictureCallback()
	{
		@Override
		public void onPictureTaken(byte[] data, Camera camera)
		{
			String filename = "test.jpg";
			OutputStream os = null;
			
			try
			{
				Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
				Matrix matrix = new Matrix();
				matrix.postRotate(ImageUtils.getCameraRotation(CAMERA_ID));
				
				if(ImageUtils.isFrontCamera(CAMERA_ID))
					matrix.postScale(-1, 1);
				
				bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
				
				//os = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
				//os.write(data);
				//Environment.getExternalStorageDirectory()
				File file = new File(getActivity().getExternalFilesDir(null), filename);
				os = new FileOutputStream(file);
				bmp.compress(CompressFormat.JPEG, 90, os);
			}
			catch(Exception ex)
			{}
			finally
			{
				try
				{
					if(os != null)
						os.close();
				}
				catch(Exception ex)
				{}
			}
			
			Intent i = new Intent();
			i.putExtra(EXTRA_PHOTO_FILENAME, filename);
			getActivity().setResult(Activity.RESULT_OK, i);
			
			getActivity().finish();
		}
	};
	
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
		
		mSurfaceView = (SurfaceView)v.findViewById(R.id.surface_view_photo);
		
		SurfaceHolder holder = mSurfaceView.getHolder();		
		//holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); -- DEPRECATED
		holder.addCallback(new SurfaceHolder.Callback()
		{
			@SuppressWarnings("deprecation")
			@Override
			public void surfaceCreated(SurfaceHolder holder)
			{
				try
				{
					if(mCamera != null)
					{
						mCamera.setPreviewDisplay(holder);
						
						LayoutParams layParams = mSurfaceView.getLayoutParams();
						layParams.width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
						layParams.height = layParams.width / 3 * 4;
						
						mSurfaceView.setLayoutParams(layParams);
					}
				}
				catch(Exception ex)
				{}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
			{
				if(mCamera == null)
					return;
				
				Camera.Parameters params = mCamera.getParameters();
				Camera.Size size = findBestCameraSize(params.getSupportedPreviewSizes(), width, height);
				params.setPreviewSize(size.width, size.height);
				mCamera.setParameters(params);
				mCamera.setDisplayOrientation(ImageUtils.getCameraPreviewRotation(getActivity(), CAMERA_ID));
				
				try
				{
					mCamera.startPreview();
				}
				catch(Exception ex)
				{
					mCamera.release();
					mCamera = null;
				}
			}
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder)
			{
				if(mCamera != null)
					mCamera.stopPreview();
			}
		});
		
		Button takeBtn = (Button)v.findViewById(R.id.button_take_photo);
		takeBtn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				if(mCamera != null)
					mCamera.takePicture(null, null, cameraSavePictureCallback);
			}
		});
		
		return v;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		mCamera = Camera.open(0);
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		
		if(mCamera != null)
		{
			mCamera.release();
			mCamera = null;
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
				
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private Camera.Size findBestCameraSize(List<Camera.Size> sizes, int w, int h)
	{
		Camera.Size bestSize = sizes.get(0);
		int maxWidth = bestSize.width;
		double ratio;
		
		for(Camera.Size s : sizes)
		{
			ratio = (double)s.width / s.height;
			
			if(maxWidth < s.width && (ratio == (double)4 / 3 || ratio == (double)3 / 4))
				bestSize = s;
		}
		
		return bestSize;
	}
}
