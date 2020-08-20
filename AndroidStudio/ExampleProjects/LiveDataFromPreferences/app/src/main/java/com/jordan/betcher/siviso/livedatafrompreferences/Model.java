package com.jordan.betcher.siviso.livedatafrompreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Model
{
	MutableLiveData<Home> homeMutableLiveData;
	Home home = new Home(Defaults.HomeRingmode);
	
	public Model()
	{
		homeMutableLiveData = createHomeMutableLiveData();
		homeMutableLiveData.setValue(home);
	}
	
	protected MutableLiveData<Home> createHomeMutableLiveData()
	{
		return new MutableLiveData<Home>();
	}
	
	public LiveData<Home> homeLiveData()
	{
		return homeMutableLiveData;
	}
	
	public void updateHomeRingmode(Ringmode ringmode)
	{
		home.ringmode = ringmode;
		homeMutableLiveData.setValue(home); //Can't  be tested, but needed for update
	}
}
