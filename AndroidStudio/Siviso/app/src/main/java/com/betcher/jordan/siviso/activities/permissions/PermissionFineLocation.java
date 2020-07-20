package com.betcher.jordan.siviso.activities.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.betcher.jordan.siviso.Defaults;

public class PermissionFineLocation implements Permission
{
	UiOfPermissions uiOfPermissions;
	
	public PermissionFineLocation(UiOfPermissions uiOfPermissions)
	{
		this.uiOfPermissions = uiOfPermissions;
	}
	
	public void initUI(Activity activity)
	{
		if(isGranted(activity))
		{
			uiOfPermissions.permissionAccepted();
		}
		else
		{
			uiOfPermissions.permissionNotAccepted();
		}
	}
	
	public void run(Activity activity)
	{
		if(isGranted(activity))
		{
			//If the permission is already granted why is this still active
			uiOfPermissions.permissionAccepted();
		}
		else
		{
			askForPermission(activity);
			if(isGranted(activity))
			{
				uiOfPermissions.permissionAccepted();
			}
		}
		
	}
	
	private void askForPermission(Activity activity)
	{
		ActivityCompat.requestPermissions(activity, new String[]
		                                  {Manifest.permission.ACCESS_FINE_LOCATION},
		                                  Defaults.REQUEST_LOCATION_PERMISSION
		                                 );
	}
	
	public boolean isGranted(Context context)
	{
		boolean granted_fineLocation = ActivityCompat
		                               .checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
		                               PackageManager.PERMISSION_GRANTED;
		return granted_fineLocation;
	}
	
	public void refreshUI(Activity activity)
	{
		initUI(activity);
	}
}
