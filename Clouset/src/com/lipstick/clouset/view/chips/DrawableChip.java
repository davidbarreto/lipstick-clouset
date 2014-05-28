package com.lipstick.clouset.view.chips;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

public class DrawableChip extends ImageSpan {

	private Chip mChip;
	
	public DrawableChip(final Drawable drawable, Chip chip) {
		super(drawable, DynamicDrawableSpan.ALIGN_BOTTOM);
		this.mChip = chip;
	}
	
	public void draw(final Canvas canvas) {
		getDrawable().draw(canvas);
	}
	
	public Chip getChip() {
		return mChip;
	}
}
