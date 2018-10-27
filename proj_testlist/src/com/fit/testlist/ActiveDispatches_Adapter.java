package com.fit.testlist;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ActiveDispatches_Adapter extends ArrayAdapter<String> {

	
	private ArrayList<String> items;
	Context context;

	/*
	 * ActiveDispatchesAdapter Constructor
	 */
	public ActiveDispatches_Adapter(Context context, int textViewResourceId,ArrayList<String> items) {
		super(context,textViewResourceId,items);
		this.items = items;
		this.context = context;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return super.getView(position, convertView, parent);
	}
}
