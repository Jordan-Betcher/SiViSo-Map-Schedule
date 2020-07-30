package com.betcher.jordan.siviso.activities.activity_permission;

import android.app.Activity;

interface Permission
{
	void initUI(Activity activity);
	void run(Activity activity);
	void refreshUI(Activity activity);
}
