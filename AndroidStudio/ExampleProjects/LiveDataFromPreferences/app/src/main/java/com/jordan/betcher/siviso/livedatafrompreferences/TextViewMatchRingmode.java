package com.jordan.betcher.siviso.livedatafrompreferences;

import android.widget.TextView;

import androidx.lifecycle.Observer;

public class TextViewMatchRingmode
implements Observer<Home>
{
	TextView textView;
	
	public TextViewMatchRingmode(TextView textView)
	{
		this.textView = textView;
	}
	
	@Override
	public void onChanged(Home home)
	{
		textView.setText(home.ringmode().toString());
	}
}
