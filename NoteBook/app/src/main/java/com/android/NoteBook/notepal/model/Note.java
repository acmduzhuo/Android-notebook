package com.android.NoteBook.notepal.model;

import android.support.annotation.NonNull;

public class Note implements Comparable<Note>
{
    public static final int TYPE_NOTE = 0;      //四种记录类型定义为公有常量，便于其他类使用
    public static final int TYPE_EXAM = 1;
    public static final int TYPE_HOMEWORK = 2;
    public static final int TYPE_AFFAIR = 3;

    private long id;
    private int type;
    private String theme;
    private String content;
    private String date;

    public long getId() {
        return id;
    }

    public int getType(){
        return type;
    }

    public String getTheme() {
        return theme;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public static int getTypeCount()
    {
        return 4;
    }

    public Note(long id, String theme, String content, String createDate)
    {

        this.id = id;
        this.type = TYPE_NOTE;
        this.theme = theme;
        this.content = content;
        this.date = createDate;
    }

    public Note(long id, int type, String theme, String content, String createDate)
    {

        this.id = id;
        this.type = type;
        this.theme = theme;
        this.content = content;
        this.date = createDate;
    }

    @Override
    public int compareTo(@NonNull Note o)       //按照日期对笔记进行比较（新日期在前）
    {
        if (this instanceof Homework)           //如果是Homework类型则比较deadline
        {
            Homework temp1 = (Homework)this;
            if (o instanceof Homework)
            {
                Homework temp2 = (Homework)o;
                if (temp1.getDeadline().compareTo(temp2.getDeadline()) > 0)
                    return -1;
                else if (temp1.getDeadline().compareTo(temp2.getDeadline()) < 0)
                    return  1;
                else
                    return 0;
            }
            else
            {
                if (temp1.getDeadline().compareTo(o.getDate()) > 0)
                    return -1;
                else if (temp1.getDeadline().compareTo(o.getDate()) < 0)
                    return  1;
                else
                    return 0;
            }
        }
        else
        {
            if (o instanceof Homework)
            {
                Homework temp = (Homework)o;
                if (this.getDate().compareTo(temp.getDeadline()) > 0)
                    return -1;
                else if (this.getDate().compareTo(temp.getDeadline()) < 0)
                    return  1;
                else
                    return 0;
            }
            else
            {
                if (this.getDate().compareTo(o.getDate()) > 0)
                    return -1;
                else if (this.getDate().compareTo(o.getDate()) < 0)
                    return  1;
                else
                    return 0;
            }
        }
    }
}
