package com.betcher.jordan.siviso.activities.home.methods;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.betcher.jordan.siviso.activities.Home;
import com.betcher.jordan.siviso.activities.Permissions;

public class CheckAndAskPermissions
{
	public static void run(Home home)
	{
		boolean granted_fineLocation = ActivityCompat
		                       .checkSelfPermission(home, Manifest.permission.ACCESS_FINE_LOCATION) ==
		                       PackageManager.PERMISSION_GRANTED;
		
		NotificationManager notificationManager =
		(NotificationManager) home.getSystemService(
		Context.NOTIFICATION_SERVICE);
		
		boolean granted_notificationPolicy =
		Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
		&& notificationManager.isNotificationPolicyAccessGranted();
		
		if(false == granted_fineLocation)
		{
			Intent intent_permissions = new Intent(home, Permissions.class);
			home.startActivity(intent_permissions);
		}
	}
}
