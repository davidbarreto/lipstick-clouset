package com.lipstick.clouset.view.chips.listeners;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ChipsOnClickListener implements OnClickListener {

	private static final String TAG = "ChipsOnClickListener"; 
	
	@Override
	public void onClick(View v) {

		Log.d(TAG, "Class: " + v.getClass().getName());
	}

}
