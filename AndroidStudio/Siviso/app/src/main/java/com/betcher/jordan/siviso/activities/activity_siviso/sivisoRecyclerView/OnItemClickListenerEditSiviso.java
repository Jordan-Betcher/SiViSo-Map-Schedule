package com.betcher.jordan.siviso.activities.activity_siviso.sivisoRecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.Preferences_Siviso;
import com.betcher.jordan.siviso.database.AndroidViewModel_Siviso;
import com.betcher.jordan.siviso.database.TableRow_Siviso;
import com.betcher.jordan.siviso.siviso.SivisoRingmode;
import com.google.android.gms.maps.model.LatLng;

class OnItemClickListenerEditSiviso
		implements AdapterView.OnItemSelectedListener
{
	private static final String TAG = "OnItemClickListenerEdit";
	TableRow_Siviso currentSivisoData;
	private Context context;
	private AndroidViewModel_Siviso sivisoModel;
	private Spinner spinnerSiviso;
	
	
	public OnItemClickListenerEditSiviso(Context context, AndroidViewModel_Siviso sivisoModel,
	                                     Spinner spinnerSiviso)
	{
		this.context = context;
		this.sivisoModel = sivisoModel;
		this.spinnerSiviso = spinnerSiviso;
	}
	
	public void setSivisoData(
	TableRow_Siviso currentSivisoData)
	{
		this.currentSivisoData = currentSivisoData;
	}
	
	private void editSivisoData()
	{
		
		
		String name = currentSivisoData.name();
		String sivisoName = spinnerSiviso.getSelectedItem().toString();
		SivisoRingmode sivisoRingmode = SivisoRingmode
		.siviso(sivisoName);
		
		if(name == Defaults.DEFAULT_SIVISO_NAME)
		{
			Preferences_Siviso.saveDefaultSiviso(context,
			                                     sivisoRingmode);
		}
		else
		{
			LatLng latLng = currentSivisoData.latLng();
			
			TableRow_Siviso sivisoData = new TableRow_Siviso(name, sivisoRingmode
			.name(), latLng.latitude, latLng.longitude);
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
