package com.lipstick.clouset.view.chips.listeners;

import com.lipstick.clouset.view.chips.ChipsMultiAutoCompleteTextView;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * Defines the component behavior when an item from the tags tips list is clicked
 * It have to create a new 'chip' and put it in the 'ChipsMultiAutoCompleteTextView'
 * 
 * @author dbarreto
 * @see ChipsMultiAutoCompleteTextView
 */
public class ChipsOnItemClickListener implements OnItemClickListener {
	
	private final String TAG = "ChipsClickListener";
	private ChipsMultiAutoCompleteTextView chipsView;
	
	public ChipsOnItemClickListener(ChipsMultiAutoCompleteTextView chipsView) {
		this.chipsView = chipsView;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.d(TAG, "onItemClick");
		
		TextView v = (TextView) view;
		Log.d(TAG, "Text: " + v.getText());
		Log.d(TAG, "Original text: " + chipsView.getText());
		
		CharSequence selectedText = v.getText();
		chipsView.createChip(selectedText);
	}
}
