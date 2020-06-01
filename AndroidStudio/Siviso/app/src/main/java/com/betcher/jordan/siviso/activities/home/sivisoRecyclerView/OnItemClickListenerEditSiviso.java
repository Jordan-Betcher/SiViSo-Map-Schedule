package com.betcher.jordan.siviso.activities.home.sivisoRecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.google.android.gms.maps.model.LatLng;

class OnItemClickListenerEditSiviso
		implements AdapterView.OnItemSelectedListener
{
	private static final String TAG = "OnItemClickListenerEdit";
	SivisoData currentSivisoData;
	private SivisoModel sivisoModel;
	private Spinner spinnerSiviso;
	
	
	public OnItemClickListenerEditSiviso(SivisoModel sivisoModel,
	                                     Spinner spinnerSiviso)
	{
		this.sivisoModel = sivisoModel;
		this.spinnerSiviso = spinnerSiviso;
	}
	
	public void setSivisoData(SivisoData currentSivisoData)
	{
		this.currentSivisoData = currentSivisoData;
	}
	
	private void editSivisoData()
	{
		String name = currentSivisoData.getName();
		String siviso = spinnerSiviso.getSelectedItem().toString();
		LatLng latLng = currentSivisoData.getLatLng();
		
		SivisoData sivisoData = new SivisoData(name, siviso, latLng.latitude, latLng.longitude);
		sivisoData.setId(currentSivisoData.getId());
		
		sivisoModel.update(sivisoData);
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
