package com.betcher.jordan.examplesqlitesavewithroom.actions;

import android.widget.TextView;

import java.util.Random;

public class CreateDataForTextLatitudeAndTextLongitude
{
	public static void run(TextView textLatitude, TextView textLongitude)
	{
		Random random = new Random();
		textLatitude.setText(random.nextDouble() + "");
		textLongitude.setText(random.nextDouble() + "");
	}
}
