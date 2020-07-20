package com.betcher.jordan.siviso.activities.permissions;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class PermissionNotificationPolicy implements Permission
{
	UiOfPermissions uiOfPermissions;
	
	public PermissionNotificationPolicy(
	UiOfPermissions uiOfPermissions)
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
		if( ! (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))
		{
			uiOfPermissions.remove();
			return;
		}
		
		if(isGranted(activity))
		{
			//If the permission was already granted how did you get here
			//The UI should have been disabled already
			uiOfPermissions.permissionAccepted();
		}
		else
		{
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			{
				askForPermission(activity);
			}
		}
	}
	
	@RequiresApi(api = Build.VERSION_CODES.M)
	private void askForPermission(Activity activity)
	{
		Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
		activity.startActivityForResult(intent, 2);
		
		//UI is refreshed in the "onActivityResult" that get's called in the activity
	}
	
	private boolean isNotificationPolicyInCurrentVersion()
	{
		return (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M);
	}
	
	public boolean isGranted(Context context)
	{
		NotificationManager notificationManager =
		(NotificationManager) context.getSystemService(
		Context.NOTIFICATION_SERVICE);
		
		if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
		{
			return notificationManager.isNotificationPolicyAccessGranted();
		}
		else
		{
			return true;
		}
	}
	
	public void refreshUI(Activity activity)
	{
		initUI(activity);
	}
}
