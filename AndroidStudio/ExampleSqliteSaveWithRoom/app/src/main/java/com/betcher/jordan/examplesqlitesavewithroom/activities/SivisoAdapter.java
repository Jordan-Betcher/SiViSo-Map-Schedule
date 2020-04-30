package com.betcher.jordan.examplesqlitesavewithroom.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.examplesqlitesavewithroom.R;
import com.betcher.jordan.examplesqlitesavewithroom.database.SivisoData;

import java.util.ArrayList;
import java.util.List;

public class SivisoAdapter  extends RecyclerView.Adapter<SivisoHolder>
{
	private List<SivisoData> sivisoDatas = new ArrayList<>();
	
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
		return sivisoDatas.size();
	}
	
	public void setSivisoDatas(List<SivisoData> sivisoDatas)
	{
		this.sivisoDatas = sivisoDatas;
		notifyDataSetChanged();
	}
}
