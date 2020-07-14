package com.android.NoteBook.notepal.model;

public class Exam extends Note
{
    private String time;
    private String place;

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getSubject()
    {
        return super.getTheme();
    }

    public String getDescription()
    {
        return super.getContent();
    }

    public Exam(long id, String subject, String description, String date, String time, String place)
    {
        super(id,Note.TYPE_EXAM,subject,description,date);
        this.time = time;
        this.place = place;
    }
}
