package com.betcher.jordan.siviso.activities.activity_modifySiviso;

import android.content.Context;
import android.content.Intent;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.database.SivisoData;

public class IntentBuilder_EditActivity
{
	final Class<Activity_Edit> INTENT_CLASS = Activity_Edit.class;
	
	Context context;
	Intent intent;
	
	public IntentBuilder_EditActivity(Context context)
	{
		this.context = context;
		intent = new Intent(context, INTENT_CLASS);
	}
	
	public void putExtraSivisoData(SivisoData sivisoData)
	{
		intent.putExtra(Activity_Edit.EXTRA_NAME_ID, sivisoData.id());
		intent.putExtra(Activity_Edit.EXTRA_NAME_NAME, sivisoData.name());
		intent.putExtra(Activity_Edit.EXTRA_NAME_SIVISO, sivisoData.siviso().name());
		intent.putExtra(Activity_Edit.EXTRA_NAME_LATITUDE, sivisoData.latitude());
		intent.putExtra(Activity_Edit.EXTRA_NAME_LONGITUDE, sivisoData.longitude());
	}
	
	public void runIntent()
	{
		context.startActivity(intent);
	}
}
