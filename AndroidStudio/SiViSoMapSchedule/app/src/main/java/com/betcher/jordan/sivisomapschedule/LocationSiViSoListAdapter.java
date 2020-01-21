package com.betcher.jordan.sivisomapschedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

class LocationSiViSoListAdapter extends ArrayAdapter<ListItemLocationSiViSo> {

    private Context context;
    private int layout_list_item;

    public LocationSiViSoListAdapter(Context context, int layout_list_item, ArrayList<ListItemLocationSiViSo> listLocationSiViSo) {
        super(context, layout_list_item, listLocationSiViSo);
        this.context = context;
        this.layout_list_item = layout_list_item;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String location = getItem(position).getLocation();
        String siviso = getItem(position).getSiviso();

        ListItemLocationSiViSo item = new ListItemLocationSiViSo(location, siviso);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout_list_item, parent, false);

        TextView textViewLocation = (TextView) convertView.findViewById(R.id.location);
        TextView textViewSiViSo = (TextView) convertView.findViewById(R.id.siviso);

        textViewLocation.setText(location);
        textViewSiViSo.setText(siviso);

        return convertView;
    }
}
