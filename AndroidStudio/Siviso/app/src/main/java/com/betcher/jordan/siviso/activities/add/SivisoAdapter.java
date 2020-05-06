package com.betcher.jordan.siviso.activities.add;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.database.SivisoData;

import java.util.ArrayList;
import java.util.List;

public class SivisoAdapter extends RecyclerView.Adapter<SivisoAdapter.SivisoHolder>
{
	private static final String TAG = "SivisoAdapter";
	
	private List<SivisoData> sivisoDatas = new ArrayList<>();
	private SivisoData selectedSiviso = null;
	
	private View selectedView = null;
	private int previousViewColor = 0;
	private int highlightColor = Color.LTGRAY;
	
	@NonNull
	@Override
	public SivisoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View itemView = LayoutInflater.from(parent.getContext())
		                              .inflate(R.layout.list_item_siviso_data, parent, false);
		return new SivisoHolder(itemView);
	}
	
	@Override
	public void onBindViewHolder(@NonNull SivisoHolder holder, int position)
	{
		SivisoData currentSivisoData = sivisoDatas.get(position);
		holder.setName(currentSivisoData.getName());
		holder.setSiviso(currentSivisoData.getSiviso());
	}
	
	@Override
	public int getItemCount()
	{
		Log.d(TAG, "getItemCount: " + sivisoDatas.size());
		return sivisoDatas.size();
	}
	
	public void setSivisoDatas(List<SivisoData> sivisoDatas)
	{
		this.sivisoDatas = sivisoDatas;
		notifyDataSetChanged();
		Log.d(TAG, "setSivisoDatas: " + sivisoDatas.size());
	}
	
	public SivisoData getSelectedSiviso()
	{
		return selectedSiviso;
	}
	
	public boolean getIsSivisoSelected()
	{
		if (selectedSiviso != null && sivisoDatas.contains(selectedSiviso))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	class SivisoHolder extends RecyclerView.ViewHolder
	{
		private TextView textViewName;
		private Spinner spinnerSiviso;
		SivisoHolder sivisoHolder;
		
		public SivisoHolder(View itemView)
		{
			super(itemView);
			textViewName = itemView.findViewById(R.id.textViewHoldName);
			spinnerSiviso = itemView.findViewById(R.id.spinnerHoldSiviso);
			sivisoHolder = this;
			
			itemView.setOnClickListener(new SivisoItemClickListener());
		}
		
		public void setName(String name)
		{
			textViewName.setText(name);
		}
		
		public void setSiviso(String siviso)
		{
			ArrayAdapter arrayAdapter = (ArrayAdapter) spinnerSiviso.getAdapter();
			int arrayPositionOfSiviso = arrayAdapter.getPosition(siviso);
			spinnerSiviso.setSelection(arrayPositionOfSiviso);
		}
		
		private class SivisoItemClickListener implements View.OnClickListener
		{
			@Override
			public void onClick(View view)
			{
				int position = getAdapterPosition();
				if (position != RecyclerView.NO_POSITION)
				{
					if(selectedView != null)
					{
						selectedView.setBackgroundColor(previousViewColor);
					}
					
					selectedSiviso = sivisoDatas.get(position);
					selectedView = view;
					previousViewColor = ((ColorDrawable)view.getBackground()).getColor();
					view.setBackgroundColor(highlightColor);
				}
			}
		}
	}
}
