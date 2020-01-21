package com.betcher.jordan.examplelistlocations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);

        ListItemLocationSiViSo location1 = new ListItemLocationSiViSo("location 1", "silent");
        ListItemLocationSiViSo location2 = new ListItemLocationSiViSo("location 2", "vibrate");
        ListItemLocationSiViSo location3 = new ListItemLocationSiViSo("location 3", "sound");
        ListItemLocationSiViSo location4 = new ListItemLocationSiViSo("location 4", "silent");

        ArrayList<ListItemLocationSiViSo> listLocationSiViSo = new ArrayList<ListItemLocationSiViSo>();
        listLocationSiViSo.add(location1);
        listLocationSiViSo.add(location2);
        listLocationSiViSo.add(location3);
        listLocationSiViSo.add(location4);

        LocationSiViSoListAdapter adapter = new LocationSiViSoListAdapter(this, R.layout.layout_list_item, listLocationSiViSo);
        list.setAdapter(adapter);
    }
}
