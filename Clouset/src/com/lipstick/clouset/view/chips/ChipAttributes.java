package com.lipstick.clouset.view.chips;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.lipstick.clouset.R;

public class ChipAttributes {
	
	private Context mContext;
	private AttributeSet mAttrs;
	private TextPaint mTextPaint;
	
	//Resources for displaying chips
    private Drawable mChipBackground = null;
    private Drawable mChipDelete = null;
    private Drawable mInvalidChipBackground = null;
    private Drawable mChipBackgroundPressed = null;
    private float mChipHeight;
    private float mChipFontSize;
    private float mLineSpacingExtra;
    private int mChipPadding;
    private int mAlternatesLayout;
    private TextView mMoreItem;
    private int mMaxLines;
    private int mActionBarHeight;
    
    public ChipAttributes(Context context, AttributeSet attrs, TextPaint textPaint) {
    	this.mContext = context;
    	this.mAttrs = attrs;
    	this.mTextPaint = textPaint;
    	init();
    }
    
	/**
	 * Setup the chip default dimensions, drawables etc
	 */
	private void init() {
		
		TypedArray a = mContext.obtainStyledAttributes(mAttrs,
				R.styleable.RecipientEditTextView, 0, 0);
		
		Resources resources = mContext.getResources();

		mChipBackground = a
				.getDrawable(R.styleable.RecipientEditTextView_chipBackground);
		if (mChipBackground == null) {
			mChipBackground = resources.getDrawable(R.drawable.chip_background);
		}
		mChipBackgroundPressed = a
				.getDrawable(R.styleable.RecipientEditTextView_chipBackgroundPressed);
		if (mChipBackgroundPressed == null) {
			mChipBackgroundPressed = resources
					.getDrawable(R.drawable.chip_background_selected);
		}
		mChipDelete = a
				.getDrawable(R.styleable.RecipientEditTextView_chipDelete);
		if (mChipDelete == null) {
			mChipDelete = resources.getDrawable(R.drawable.chip_delete);
		}
		mChipPadding = a.getDimensionPixelSize(
				R.styleable.RecipientEditTextView_chipPadding, -1);
		if (mChipPadding == -1) {
			mChipPadding = (int) resources.getDimension(R.dimen.chip_padding);
		}
		mAlternatesLayout = a.getResourceId(
				R.styleable.RecipientEditTextView_chipAlternatesLayout, -1);
		if (mAlternatesLayout == -1) {
			mAlternatesLayout = R.layout.chips_alternate_item;
		}

		mMoreItem = (TextView) LayoutInflater.from(mContext).inflate(
				R.layout.more_item, null);

		mChipHeight = a.getDimensionPixelSize(
				R.styleable.RecipientEditTextView_chipHeight, -1);
		if (mChipHeight == -1) {
			mChipHeight = resources.getDimension(R.dimen.chip_height);
		}
		mChipFontSize = a.getDimensionPixelSize(
				R.styleable.RecipientEditTextView_chipFontSize, -1);
		if (mChipFontSize == -1) {
			mChipFontSize = resources.getDimension(R.dimen.chip_text_size);
		}
		mInvalidChipBackground = a
				.getDrawable(R.styleable.RecipientEditTextView_invalidChipBackground);
		if (mInvalidChipBackground == null) {
			mInvalidChipBackground = resources
					.getDrawable(R.drawable.chip_background_invalid);
		}
		mLineSpacingExtra = resources.getDimension(R.dimen.line_spacing_extra);
		mMaxLines = resources.getInteger(R.integer.chips_max_lines);
		TypedValue tv = new TypedValue();
		if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize,
				tv, true)) {
			mActionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					resources.getDisplayMetrics());
		}
		a.recycle();
	}

	public Context getContext() {
		return mContext;
	}

	public AttributeSet getAttrs() {
		return mAttrs;
	}

	public Drawable getChipBackgroundDrawable() {
		return mChipBackground;
	}

	public Drawable getChipDeleteDrawable() {
		return mChipDelete;
	}

	public Drawable getInvalidChipBackgroundDrawable() {
		return mInvalidChipBackground;
	}

	public Drawable getChipBackgroundPressedDrawable() {
		return mChipBackgroundPressed;
	}

	public float getChipHeight() {
		return mChipHeight;
	}

	public float getChipFontSize() {
		return mChipFontSize;
	}

	public float getLineSpacingExtra() {
		return mLineSpacingExtra;
	}

	public int getChipPadding() {
		return mChipPadding;
	}

	public int getAlternatesLayout() {
		return mAlternatesLayout;
	}

	public TextView getMoreItem() {
		return mMoreItem;
	}

	public int getMaxLines() {
		return mMaxLines;
	}

	public int getActionBarHeight() {
		return mActionBarHeight;
	}
	
	public TextPaint getTextPaint() {
		return mTextPaint;
	}
}
