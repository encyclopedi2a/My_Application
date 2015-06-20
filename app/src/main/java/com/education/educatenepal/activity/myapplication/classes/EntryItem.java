package com.education.educatenepal.activity.myapplication.classes;


import com.education.educatenepal.activity.myapplication.interfaces.Item;

public class EntryItem implements Item {
	 public final String title;
	 
	 public EntryItem(String title) {
	  this.title = title;
	 }
	  
	 @Override
	 public boolean isSection() {
	  return false;
	 }
	 
}
