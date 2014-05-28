package com.lipstick.clouset.view.chips.listeners;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;

public class ChipsTextWatcher implements TextWatcher {
	
	private final String TAG = "ChipsTextWatcher";

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		Log.d(TAG, "onTextChanged");
	}

	@Override
	public void afterTextChanged(Editable s) {
		Log.d(TAG, "afterTextChanged");

		//If the content was deleted, erase all the chips
		if (TextUtils.isEmpty(s)) {
			//TODO remove the chips
			return;
		}
	}
	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
}
