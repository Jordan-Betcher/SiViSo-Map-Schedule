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
import com.betcher.jordan.siviso.database.AndroidViewModel_Siviso;
import com.betcher.jordan.siviso.database.TableRow_Siviso;
import com.betcher.jordan.siviso.siviso.SivisoRingmode;
import com.betcher.jordan.siviso.siviso.SpinnerAdapter_Siviso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_Siviso
		extends RecyclerView.Adapter<RecyclerViewAdapter_Siviso.SivisoHolder>
{
	private static final String TAG = "SVSRecyclerViewAdapter";
	
	private List<TableRow_Siviso> sivisoDatas = new ArrayList<>();
	
	private ArrayList<OnBindViewListener> onBindViewListeners = new ArrayList<>();
	private AndroidViewModel_Siviso sivisoModel;
	private Context context;
	
	public RecyclerViewAdapter_Siviso(Context context, AndroidViewModel_Siviso sivisoModel)
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
		TableRow_Siviso currentSivisoData = sivisoDatas.get(position);
		holder.setSivisoData(currentSivisoData);
		
		callAllOnBindViewListeners(currentSivisoData, holder.itemView);
	}
	
	private void callAllOnBindViewListeners(
	TableRow_Siviso currentSivisoData, View itemView)
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
	
	public void setSivisoDatas(List<TableRow_Siviso> sivisoDatas)
	{
		this.sivisoDatas = sivisoDatas;
		notifyDataSetChanged();
		Log.d(TAG, "setSivisoDatas: " + sivisoDatas.size());
	}
	
	public TableRow_Siviso getItem(int itemPosition)
	{
		return sivisoDatas.get(itemPosition);
	}
	
	public boolean containItem(
	TableRow_Siviso selectedSiviso)
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
	
	public int getPosition(TableRow_Siviso selectedSiviso)
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
		
		public SivisoHolder(View itemView, Context context, AndroidViewModel_Siviso sivisoModel)
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
		
		private void setSiviso(SivisoRingmode sivisoRingmode)
		{
			spinnerSiviso.setSelection(
			sivisoRingmode.index());
		}
		
		public void setSivisoData(
		TableRow_Siviso currentSivisoData)
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
		
		void OnBindView(TableRow_Siviso currentSivisoData, View itemView);
	}
}


