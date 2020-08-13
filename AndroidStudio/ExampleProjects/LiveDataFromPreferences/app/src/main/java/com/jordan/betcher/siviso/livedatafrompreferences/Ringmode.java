package com.jordan.betcher.siviso.livedatafrompreferences;

enum Ringmode
{
	None,
	Silent,
	Vibrate,
	Sound;
	
	public static Ringmode ringmode(int position)
	{
		return Ringmode.values()[position];
	}
	
	
}
