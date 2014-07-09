package com.kamilprokop.pdb;

import android.app.Activity;
import android.hardware.Camera;
import android.view.Surface;

public abstract class ImageUtils
{
	public static int getCameraRotation(int cameraId)
	{
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		Camera.getCameraInfo(cameraId, cameraInfo);

		return cameraInfo.orientation;
	}

	public static boolean isFrontCamera(int cameraId)
	{
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		Camera.getCameraInfo(cameraId, cameraInfo);

		if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
			return true;
		else
			return false;
	}

	public static int getCameraPreviewRotation(Activity activity, int cameraId)
	{
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		Camera.getCameraInfo(cameraId, cameraInfo);
		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int deg = 0;

		switch (rotation)
		{
		case Surface.ROTATION_0:
			deg = 0;
			break;

		case Surface.ROTATION_90:
			deg = 90;
			break;

		case Surface.ROTATION_180:
			deg = 180;
			break;

		case Surface.ROTATION_270:
			deg = 270;
			break;
		}

		int result;

		if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
		{
			result = (cameraInfo.orientation + deg) % 360;
			result = (360 - result) % 360; // compensate the mirror
		}
		else
			result = (cameraInfo.orientation - deg + 360) % 360;

		return result;
	}
}
