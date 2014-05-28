package com.lipstick.clouset.view.chips;

import android.graphics.Bitmap;


public interface Chip {

	public CharSequence getOriginalText();
	public void setOriginalText(CharSequence originalText);
	
	public CharSequence getDisplayText();
	public void setDisplayText(CharSequence displayText);
	
	public Bitmap getChipBitmap(ChipBackgroundType type);
	
	enum ChipBackgroundType {
		NORMAL,
		INVALID,
		SELECTED,
		DELETE
	}

}


