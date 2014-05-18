package com.lipstick.clouset.fragments;

import android.app.Fragment;

public class FragmentsFactory {

	private static FragmentsFactory obj;
	private Fragment[] array;
	
	private FragmentsFactory(){
		array = new Fragment[] {
			new MyClosetFragment(),
			new FriendsClosetFragment(),
			new LendmeFragment(),
			new LetmeSeeFragment(),
			new ChatFragment(),
			new CalendarFragment()
		};
	}
	
	public static FragmentsFactory getInstance() {
	
		if (obj == null) {
			obj = new FragmentsFactory();
		}
		return obj;
	}
	
	public Fragment[] getFragments() {
		return array;
	}
}
