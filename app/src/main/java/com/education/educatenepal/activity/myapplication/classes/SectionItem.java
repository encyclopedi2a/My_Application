package com.education.educatenepal.activity.myapplication.classes;


import com.education.educatenepal.activity.myapplication.interfaces.Item;

public class SectionItem implements Item {
	 public final String title;
	  
	 public SectionItem(String title) {
	  this.title = title;
	 }
	
	 @Override
	 public boolean isSection() {
	  return true;
	 }
	 
}
