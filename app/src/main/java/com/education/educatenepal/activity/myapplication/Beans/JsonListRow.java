package com.education.educatenepal.activity.myapplication.Beans;

/**
 * Created by gokarna on 7/5/15.
 */
public class JsonListRow {
    private String collegeName;
    public JsonListRow(String collegeName){
        this.collegeName=collegeName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
