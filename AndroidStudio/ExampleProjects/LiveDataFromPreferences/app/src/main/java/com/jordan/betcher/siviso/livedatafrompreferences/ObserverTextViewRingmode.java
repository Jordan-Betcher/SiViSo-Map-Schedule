package com.jordan.betcher.siviso.livedatafrompreferences;

import android.widget.TextView;

import androidx.lifecycle.Observer;

public class ObserverTextViewRingmode
implements Observer<Home>
{
	TextView textView;
	
	public ObserverTextViewRingmode(TextView textView)
	{
		this.textView = textView;
	}
	
	@Override
	public void onChanged(Home home)
	{
		textView.setText(home.ringmode().toString());
	}
}
