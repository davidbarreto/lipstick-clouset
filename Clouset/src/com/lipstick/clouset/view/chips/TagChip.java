package com.lipstick.clouset.view.chips;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

public class TagChip implements Chip {
	
	private CharSequence mDisplayText;
	private CharSequence mOriginalText;

	private ChipAttributes mChipAttrs;
	private double mWidth;
	
	public TagChip(CharSequence displayText, ChipAttributes chipAttrs) {
		this(null, displayText, chipAttrs);
	}
	
	public TagChip(CharSequence originalText, CharSequence displayText, ChipAttributes chipAttrs) {
		this.mChipAttrs = chipAttrs;
		setOriginalText(originalText);
		setDisplayText(displayText);
	}
	
	private float getTextYOffset(String text, TextPaint paint, int height) {
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		int textHeight = bounds.bottom - bounds.top;
		return height - ((height - textHeight) / 2) - (int) paint.descent();
	}
	
	public Bitmap getChipBitmap(ChipBackgroundType type) {
		
		int height = (int) mChipAttrs.getChipHeight();
		int width = (int) mWidth;
		TextPaint textPaint = mChipAttrs.getTextPaint();
		
		Bitmap tmpBitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(tmpBitmap);
		Drawable background = null;
		
		switch (type) {
			case NORMAL:
				background = mChipAttrs.getChipBackgroundDrawable();
				break;
			case INVALID:
				background = mChipAttrs.getInvalidChipBackgroundDrawable();
				break;
			case SELECTED:
				background = mChipAttrs.getChipBackgroundPressedDrawable();
				break;
			case DELETE:
				background = mChipAttrs.getChipDeleteDrawable();
				break;
		}
		
		background.setBounds(0, 0, width, height);
		background.draw(canvas);

		canvas.drawText(
				mDisplayText,
				0,
				mDisplayText.length(),
				mChipAttrs.getChipPadding(),
				getTextYOffset((String) mDisplayText, textPaint, height),
				textPaint);
		
		return tmpBitmap;
	}
	
	@Override
	public CharSequence getOriginalText() {
		
		if (mOriginalText != null) {
			return mOriginalText;
		}
		return mDisplayText;
	}
	
	@Override
	public void setOriginalText(CharSequence originalText) {
		this.mOriginalText = originalText;
	}
	
	@Override
	public CharSequence getDisplayText() {
		return mDisplayText;
	}
	
	@Override
	public void setDisplayText(CharSequence displayText) {
		
		mDisplayText = displayText;
		// Calculate the minimum width that accommodate the text
		mWidth = Math.floor(mChipAttrs.getTextPaint().measureText(mDisplayText, 0,
				mDisplayText.length()) + (mChipAttrs.getChipPadding() * 2));
	}
	
	@Override
	public String toString() {
		return mOriginalText != null ? mOriginalText.toString() : mDisplayText.toString();
	}
}