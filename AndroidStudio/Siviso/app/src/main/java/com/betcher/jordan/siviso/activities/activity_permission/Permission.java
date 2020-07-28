package com.betcher.jordan.siviso.activities.activity_permission;

import android.app.Activity;
import android.content.Context;

interface Permission
{
	void initUI(Activity activity);
	void run(Activity activity);
	boolean isGranted(Context context);
	void refreshUI(Activity activity);
}
