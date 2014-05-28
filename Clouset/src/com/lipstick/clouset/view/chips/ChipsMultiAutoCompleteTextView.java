package com.lipstick.clouset.view.chips;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import com.lipstick.clouset.view.chips.listeners.ChipsOnItemClickListener;
import com.lipstick.clouset.view.chips.listeners.ChipsTextWatcher;

/**
 * Class to shows a chip when user click on a tag tip
 * like Gmail does in the 'to' addresses field.
 * 
 * A chip is a box that presents the tip chose by the user.
 * It can be erased by clicking in the 'x' in the right side of the box
 * 
 * Based on 
 * @link http://www.kpbird.com/2013/02/android-chips-edittext-token-edittext.html
 * and
 * @link https://android.googlesource.com/platform/frameworks/ex/+/refs/heads/master/chips/src/com/android/ex/chips
 * 
 * @author dbarreto
 *
 */
public class ChipsMultiAutoCompleteTextView extends MultiAutoCompleteTextView {
	
	private final String TAG = "ChipsMultiAutoCompleteTextView";
	
	//List of chips from this view
	private List<Chip> mChips;
	
	private ChipAttributes mChipAttributes;
	
	//Default text watcher for chips
	private ChipsTextWatcher mTextWatcher;
	
	//Default item click listener for chips
	//called when an item from tips list is clicked
	private ChipsOnItemClickListener mItemClickListener;
	
	//Default adapter for chips
	//it will give us the suggestions for tags
	//TODO change to an appropriated adapter!! This adapter will bring data from the cloud when necessary
	private ArrayAdapter<String> mAdapter;

	public ChipsMultiAutoCompleteTextView(Context context) {
		super(context);
		init(context, null);
	}
	
	public ChipsMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}
	
	public ChipsMultiAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}
	
	//Called in object construction time
	private void init(Context context, AttributeSet attrs) {
		Log.d(TAG, "init");
		
		mChipAttributes = new ChipAttributes(context, attrs, getPaint());
		
		//Listen to changes in the text inside this component
		mTextWatcher = new ChipsTextWatcher();
		addTextChangedListener(mTextWatcher);
		
		//Listen to clicks in the tips list presented
		mItemClickListener = new ChipsOnItemClickListener(this);
		setOnItemClickListener(mItemClickListener);
		
		//Get the data (tag tips) from either local data base or the cloud
		//TODO change to the approprieated adapter
		mAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, TAGS);
		setAdapter(mAdapter);
		
		//Sets the separator character to the tags.
		//TODO verify if this tokenizer is appropriated!
		setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		
		//Initialize the chips list
		mChips = new ArrayList<Chip>();
	}

	//TODO remove after create the appropeiated adapter
	private static final String[] TAGS = new String[] {
		"shoes", "dress", "pants", "shirt"
	};
	
	/**
	 * Create a new chip with the specified text
	 * @param text
	 */
	public void createChip(CharSequence text) {
		
		//Calculate the display text. If the text is bigger than the available space,
		//the displayed text will be truncated at end, and added a ellipsis character
		CharSequence displayText = TextUtils.ellipsize(text, getPaint(), calculateAvailableWidth(),
		                TextUtils.TruncateAt.END);
		
		Chip chip = new TagChip(
				text, //The tip text chosen
				displayText, //The text modified
				mChipAttributes
			);
		
		mChips.add(chip);
		Log.i(TAG, "Total chip list: " + mChips.toString());
		
		Bitmap bitmap = chip.getChipBitmap(Chip.ChipBackgroundType.SELECTED);
        Drawable result = new BitmapDrawable(getResources(), bitmap);
        result.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
        
        int start = getText().toString().indexOf(displayText.toString());
        DrawableChip drawableChip = new DrawableChip(result, chip);
        getText().setSpan(drawableChip, start, start + displayText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		Log.i(TAG, "onTouchEvent");
		if (!isFocused()) {
			return super.onTouchEvent(event);	
		}
		
		Log.i(TAG, "focused!!");
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Log.i(TAG, "x=" + event.getX() + ", y=" + event.getY());
		}
		
		return false;
	}
	
    /**
     * Get the max amount of space a chip can take up. The formula takes into
     * account the width of the EditTextView, any view padding, and padding
     * that will be added to the chip.
     */
	private float calculateAvailableWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight() - (mChipAttributes.getChipPadding() * 2);
    }
}
