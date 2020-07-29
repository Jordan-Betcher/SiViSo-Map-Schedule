package com.betcher.jordan.siviso.activities.activity_siviso.sivisoRecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.activities.activity_siviso.onItemClickListener.OnItemClickListener;
import com.betcher.jordan.siviso.database.SivisoData;
import com.betcher.jordan.siviso.database.SivisoModel;
import com.betcher.jordan.siviso.siviso.Siviso;
import com.betcher.jordan.siviso.siviso.SpinnerAdapter_Siviso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_Siviso
		extends RecyclerView.Adapter<RecyclerViewAdapter_Siviso.SivisoHolder>
{
	private static final String TAG = "SVSRecyclerViewAdapter";
	
	private List<SivisoData> sivisoDatas = new ArrayList<>();
	
	private ArrayList<OnBindViewListener> onBindViewListeners = new ArrayList<>();
	private SivisoModel sivisoModel;
	private Context context;
	
	public RecyclerViewAdapter_Siviso(Context context, SivisoModel sivisoModel)
	{
		this.context = context;
		this.sivisoModel = sivisoModel;
	}
	
	@NonNull
	@Override
	public SivisoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View itemView = LayoutInflater.from(parent.getContext())
		                              .inflate(R.layout.list_item_siviso_data, parent, false);
		return new SivisoHolder(itemView, context, sivisoModel);
	}
	
	@Override
	public void onBindViewHolder(@NonNull SivisoHolder holder, int position)
	{
		SivisoData currentSivisoData = sivisoDatas.get(position);
		holder.setSivisoData(currentSivisoData);
		
		callAllOnBindViewListeners(currentSivisoData, holder.itemView);
	}
	
	private void callAllOnBindViewListeners(SivisoData currentSivisoData, View itemView)
	{
		for (OnBindViewListener listener : onBindViewListeners
		     )
		{
			listener.OnBindView(currentSivisoData, itemView);
		}
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
	
	public SivisoData getItem(int itemPosition)
	{
		return sivisoDatas.get(itemPosition);
	}
	
	public boolean containItem(SivisoData selectedSiviso)
	{
		if(sivisoDatas.contains(selectedSiviso))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getPosition(SivisoData selectedSiviso)
	{
		return sivisoDatas.indexOf(selectedSiviso);
	}
	
	public void addOnBindViewListener(OnBindViewListener listener)
	{
		onBindViewListeners.add(listener);
	}
	
	public class SivisoHolder extends RecyclerView.ViewHolder
	{
		private TextView textViewName;
		private Spinner spinnerSiviso;
		SivisoHolder sivisoHolder;
		
		OnItemClickListenerEditSiviso listener;
		
		public SivisoHolder(View itemView, Context context, SivisoModel sivisoModel)
		{
			super(itemView);
			textViewName = itemView.findViewById(R.id.textViewHoldName);
			spinnerSiviso = itemView.findViewById(R.id.spinnerHoldSiviso);
			sivisoHolder = this;
			
			listener = new OnItemClickListenerEditSiviso(context, sivisoModel, spinnerSiviso);
			spinnerSiviso.setOnItemSelectedListener(listener);
			spinnerSiviso.setAdapter(new SpinnerAdapter_Siviso(context));
			
			itemView.setOnClickListener(new View.OnClickListener(){
				
				@Override
				public void onClick(View view)
				{
					callAllOnClickListeners(view);
				}
				
				public void callAllOnClickListeners(View view)
				{
					int position = getAdapterPosition();
					
					for (OnItemClickListener onItemClickListener: onItemClickListeners)
					{
						onItemClickListener.onItemClick(view, position);
					}
				}
			});
		}
		
		private void setName(String name)
		{
			textViewName.setText(name);
		}
		
		private void setSiviso(Siviso siviso)
		{
			spinnerSiviso.setSelection(
			siviso.index());
		}
		
		public void setSivisoData(SivisoData currentSivisoData)
		{
			setName(currentSivisoData.name());
			setSiviso(currentSivisoData.siviso());
			listener.setSivisoData(currentSivisoData);
		}
	}
	
	ArrayList<OnItemClickListener> onItemClickListeners = new ArrayList<>();
	
	public void addOnItemClickedListener(OnItemClickListener onItemClickListener)
	{
		onItemClickListeners.add(onItemClickListener);
	}
	
	public interface OnBindViewListener
	{
		
		void OnBindView(SivisoData currentSivisoData, View itemView);
	}
}


