package com.android.NoteBook.notepal.model;

public class Affair extends Note
{
    private String time;
    private String place;

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription(){
        return super.getContent();
    }

    public Affair(long id, String theme, String description, String date, String time, String place)
    {
        super(id,Note.TYPE_AFFAIR,theme,description,date);
        this.time = time;
        this.place = place;
    }
}
