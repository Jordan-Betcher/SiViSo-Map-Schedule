package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.google.android.gms.maps.model.LatLng;

class OnItemClickListenerEditSiviso
		implements AdapterView.OnItemSelectedListener
{
	private static final String TAG = "OnItemClickListenerEdit";
	SivisoData currentSivisoData;
	private Context context;
	private SivisoModel sivisoModel;
	private Spinner spinnerSiviso;
	
	
	public OnItemClickListenerEditSiviso(Context context, SivisoModel sivisoModel,
	                                     Spinner spinnerSiviso)
	{
		this.context = context;
		this.sivisoModel = sivisoModel;
		this.spinnerSiviso = spinnerSiviso;
	}
	
	public void setSivisoData(SivisoData currentSivisoData)
	{
		this.currentSivisoData = currentSivisoData;
	}
	
	private void editSivisoData()
	{
		
		
		String name = currentSivisoData.name();
		String siviso = spinnerSiviso.getSelectedItem().toString();
		
		if(name == Defaults.DEFAULT_SIVISO_NAME)
		{
			SharedPreferences prefs = context.getSharedPreferences(Defaults.PREFERENCE_NAME, Context.MODE_PRIVATE);
			prefs.edit().putString(Defaults.PREFERENCE_KEY_DEFAULT_SIVISO, siviso).apply();
		}
		else
		{
			LatLng latLng = currentSivisoData.latLng();
			
			SivisoData sivisoData = new SivisoData(name, siviso, latLng.latitude, latLng.longitude);
			sivisoData.setId(currentSivisoData.id());
			
			sivisoModel.update(sivisoData);
		}
	}
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
	{
		editSivisoData();
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
	
	}
}
