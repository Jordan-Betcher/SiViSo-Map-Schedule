package com.jordan.betcher.siviso.livedatafrompreferences;

public class Home
{
	Ringmode ringmode;
	
	public Home(Ringmode ringmode){this.ringmode = ringmode;}
	
	public Ringmode ringmode()
	{
		return ringmode;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this == o){ return true; }
		if(!(o instanceof Home)){ return false; }
		Home home = (Home) o;
		return ringmode == home.ringmode;
	}
}
