package com.android.NoteBook.notepal.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.NoteBook.notepal.model.Affair;
import com.android.NoteBook.notepal.model.Exam;
import com.android.NoteBook.notepal.model.Homework;
import com.android.NoteBook.notepal.model.Note;

import java.util.List;

public class DataSource
{
    private SQLiteDatabase sqLiteDB;
    private DBHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new DBHelper(context);
        sqLiteDB = dbHelper.getWritableDatabase();
    }

    public void getAll(List<Note> data)     //从数据库获取数据
    {
        getNotes(data);
        getExams(data);
        getHomework(data);
        getAffair(data);
    }

    public void getNotes(List<Note> data)
    {
        //获取Note类型的记录
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM " + DBSchema.NoteTable.TABLE_NAME,
                null);
        if (cursor.moveToFirst()) {
            do {
                Note newNote = new Note(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_THEME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_DATE)));
                data.add(newNote);
            } while (cursor.moveToNext());
        }
    }

    public void getExams(List<Note> data)
    {
        //获取Exam类型的记录
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM " + DBSchema.ExamTable.TABLE_NAME,
                null);
        if (cursor.moveToFirst()) {
            do {
                Note newNote = new Exam(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_SUBJECT)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_TIME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_PLACE)));
                data.add(newNote);
            } while (cursor.moveToNext());
        }
    }

    public void getHomework(List<Note> data)
    {
        //获取Homework类型的记录
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM " + DBSchema.HomeworkTable.TABLE_NAME,
                null);
        if (cursor.moveToFirst()) {
            do {
                Note newNote = new Homework(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_SUBJECT)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_CREATEDATE)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_DEADLINE)));
                data.add(newNote);
            } while (cursor.moveToNext());
        }
    }

    public void getAffair(List<Note> data)
    {
        //获取Affair类型的记录
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM " + DBSchema.AffairTable.TABLE_NAME,
                null);
        if (cursor.moveToFirst()) {
            do {
                Note newNote = new Affair(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_THEME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_TIME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_PLACE)));
                data.add(newNote);
            } while (cursor.moveToNext());
        }
    }

    public void getAll(List<Note> data,String s)     //带参数的获取数据方法，搜索功能使用
    {
        getNotes(data,s);
        getExams(data,s);
        getHomework(data,s);
        getAffair(data,s);
    }

    public void getNotes(List<Note> data,String s)
    {
        //获取Note类型的记录
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM " +
                        DBSchema.NoteTable.TABLE_NAME + " WHERE " +
                        DBSchema.NoteTable.COLUMN_CONTENT + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.NoteTable.COLUMN_THEME + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.NoteTable.COLUMN_DATE + " like " + "'%" + s + "%'"
                ,null);
        if (cursor.moveToFirst())
        {
            do{
                Note newNote = new Note(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_THEME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.NoteTable.COLUMN_DATE)));
                data.add(newNote);
            }while(cursor.moveToNext());
        }
    }

    public void getExams(List<Note> data,String s)
    {
        //获取Exam类型的记录
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM " +
                        DBSchema.ExamTable.TABLE_NAME + " WHERE " +
                        DBSchema.ExamTable.COLUMN_SUBJECT + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.ExamTable.COLUMN_DESCRIPTION + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.ExamTable.COLUMN_DATE + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.ExamTable.COLUMN_TIME + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.ExamTable.COLUMN_PLACE + " like " + "'%" + s + "%'"
                ,null);
        if (cursor.moveToFirst())
        {
            do{
                Note newNote = new Exam(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_SUBJECT)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_TIME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.ExamTable.COLUMN_PLACE)));
                data.add(newNote);
            }while(cursor.moveToNext());
        }
    }

    public void getHomework(List<Note> data,String s)
    {
        //获取Homework类型的记录
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM " +
                        DBSchema.HomeworkTable.TABLE_NAME + " WHERE " +
                        DBSchema.HomeworkTable.COLUMN_SUBJECT + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.HomeworkTable.COLUMN_DESCRIPTION + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.HomeworkTable.COLUMN_CREATEDATE + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.HomeworkTable.COLUMN_DEADLINE + " like " + "'%" + s + "%'"
                ,null);
        if (cursor.moveToFirst())
        {
            do{
                Note newNote = new Homework(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_SUBJECT)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_CREATEDATE)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.HomeworkTable.COLUMN_DEADLINE)));
                data.add(newNote);
            }while(cursor.moveToNext());
        }
    }

    public void getAffair(List<Note> data,String s)
    {
        //获取Affair类型的记录
        Cursor cursor = sqLiteDB.rawQuery("SELECT * FROM " +
                        DBSchema.AffairTable.TABLE_NAME + " WHERE " +
                        DBSchema.AffairTable.COLUMN_THEME + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.AffairTable.COLUMN_DESCRIPTION + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.AffairTable.COLUMN_DATE + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.AffairTable.COLUMN_TIME + " like " + "'%" + s + "%'" + " OR " +
                        DBSchema.AffairTable.COLUMN_PLACE + " like " + "'%" + s + "%'"
                ,null);
        if (cursor.moveToFirst())
        {
            do{
                Note newNote = new Affair(
                        cursor.getLong(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_THEME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_TIME)),
                        cursor.getString(cursor.getColumnIndex(DBSchema.AffairTable.COLUMN_PLACE)));
                data.add(newNote);
            }while(cursor.moveToNext());
        }
    }

    //添加Note类型的记录
    public void insertNote(List<Note> data,String theme, String content, String date)
    {
        long id = System.currentTimeMillis();           //获取系统当前时间作为记录id
        //插入新记录到数据库
        sqLiteDB.execSQL("INSERT INTO " + DBSchema.NoteTable.TABLE_NAME + "(" +
                DBSchema.NoteTable.COLUMN_ID + "," +
                DBSchema.NoteTable.COLUMN_THEME + "," +
                DBSchema.NoteTable.COLUMN_CONTENT + "," +
                DBSchema.NoteTable.COLUMN_DATE +
                ") " +
                "VALUES(" + "'" +
                id + "'," + "'" +
                theme + "'," + "'" +
                content + "'," + "'" +
                date + "'" +
                ")");
        if (data.size() == 0)
            data.add(new Note(id,theme,content,date));
        else
        {
            for (int i = 0, len = data.size(); i < len; i++)    //将新记录插入到数据集中的合适位置
            {
                if (date.compareTo(data.get(i).getDate()) < 0)
                    continue;
                data.add(i,new Note(id,theme,content,date));
                return;
            }
            data.add(data.size(),new Note(id,theme,content,date));
        }
    }

    //添加Exam类型的记录
    public void insertExam(List<Note> data, String subject, String description, String date,
                           String time, String place)
    {
        long id = System.currentTimeMillis();
        sqLiteDB.execSQL("INSERT INTO " + DBSchema.ExamTable.TABLE_NAME + "(" +
                DBSchema.ExamTable.COLUMN_ID + "," +
                DBSchema.ExamTable.COLUMN_SUBJECT + "," +
                DBSchema.ExamTable.COLUMN_DESCRIPTION + "," +
                DBSchema.ExamTable.COLUMN_DATE + "," +
                DBSchema.ExamTable.COLUMN_TIME + "," +
                DBSchema.ExamTable.COLUMN_PLACE +
                ") " +
                "VALUES(" + "'" +
                id + "'," + "'" +
                subject + "'," + "'" +
                description + "'," + "'" +
                date + "'," + "'" +
                time + "'," + "'" +
                place + "'" +
                ")");
        if (data.size() == 0)
            data.add(new Exam(id,subject,description,date,time,place));
        else
        {
            for (int i = 0, len = data.size(); i < len; i++)    //将新记录插入到数据集中的合适位置
            {
                if (date.compareTo(data.get(i).getDate()) < 0)
                    continue;
                data.add(i,new Exam(id,subject,description,date,time,place));
                return;
            }
            data.add(data.size(),new Exam(id,subject,description,date,time,place));
        }
    }

    //添加Homework类型的记录
    public void insertHomework(List<Note> data, String subject, String description,
                               String createDate, String deadline)
    {
        long id = System.currentTimeMillis();
        sqLiteDB.execSQL("INSERT INTO " + DBSchema.HomeworkTable.TABLE_NAME + "(" +
                DBSchema.HomeworkTable.COLUMN_ID + "," +
                DBSchema.HomeworkTable.COLUMN_SUBJECT + "," +
                DBSchema.HomeworkTable.COLUMN_DESCRIPTION + "," +
                DBSchema.HomeworkTable.COLUMN_CREATEDATE + "," +
                DBSchema.HomeworkTable.COLUMN_DEADLINE +
                ") " +
                "VALUES(" + "'" +
                id + "'," + "'" +
                subject + "'," + "'" +
                description + "'," + "'" +
                createDate + "'," + "'" +
                deadline + "'" +
                ")");
        if (data.size() == 0)
            data.add(new Homework(id,subject,description,createDate,deadline));
        else
        {
            for (int i = 0, len = data.size(); i < len; i++)    //将新记录插入到数据集中的合适位置
            {
                if (deadline.compareTo(data.get(i).getDate()) < 0)
                    continue;
                data.add(i,new Homework(id,subject,description,createDate,deadline));
                return;
            }
            data.add(data.size(),new Homework(id,subject,description,createDate,deadline));
        }
    }

    //添加Affair类型的记录
    public void insertAffair(List<Note> data, String theme, String description, String date,
                           String time, String place)
    {
        long id = System.currentTimeMillis();
        sqLiteDB.execSQL("INSERT INTO " + DBSchema.AffairTable.TABLE_NAME + "(" +
                DBSchema.AffairTable.COLUMN_ID + "," +
                DBSchema.AffairTable.COLUMN_THEME + "," +
                DBSchema.AffairTable.COLUMN_DESCRIPTION + "," +
                DBSchema.AffairTable.COLUMN_DATE + "," +
                DBSchema.AffairTable.COLUMN_TIME + "," +
                DBSchema.AffairTable.COLUMN_PLACE +
                ") " +
                "VALUES(" + "'" +
                id + "'," + "'" +
                theme + "'," + "'" +
                description + "'," + "'" +
                date + "'," + "'" +
                time + "'," + "'" +
                place + "'" +
                ")");
        if (data.size() == 0)
            data.add(new Affair(id,theme,description,date,time,place));
        else
        {
            for (int i = 0, len = data.size(); i < len; i++)    //将新记录插入到数据集中的合适位置
            {
                if (date.compareTo(data.get(i).getDate()) < 0)
                    continue;
                data.add(i,new Affair(id,theme,description,date,time,place));
                return;
            }
            data.add(data.size(),new Affair(id,theme,description,date,time,place));
        }
    }

    //删除记录（四种类型通用）
    public void delete(List<Note> data,int position)
    {
        int type = data.get(position).getType();
        long id = data.get(position).getId();
        switch (type)
        {
            case Note.TYPE_NOTE:
                sqLiteDB.execSQL("DELETE FROM " + DBSchema.NoteTable.TABLE_NAME + " WHERE " +
                        DBSchema.NoteTable.COLUMN_ID + "=" + id);
                data.remove(position);
                break;
            case Note.TYPE_EXAM:
                sqLiteDB.execSQL("DELETE FROM " + DBSchema.ExamTable.TABLE_NAME + " WHERE " +
                        DBSchema.ExamTable.COLUMN_ID + "=" + id);
                data.remove(position);
                break;
            case Note.TYPE_HOMEWORK:
                sqLiteDB.execSQL("DELETE FROM " + DBSchema.HomeworkTable.TABLE_NAME + " WHERE " +
                        DBSchema.HomeworkTable.COLUMN_ID + "=" + id);
                data.remove(position);
                break;
            case Note.TYPE_AFFAIR:
                sqLiteDB.execSQL("DELETE FROM " + DBSchema.AffairTable.TABLE_NAME + " WHERE " +
                        DBSchema.AffairTable.COLUMN_ID + "=" + id);
                data.remove(position);
                break;
        }
    }

    //删除所有记录
    public void deleteAll(List<Note> data)
    {
        sqLiteDB.execSQL("DELETE FROM " + DBSchema.NoteTable.TABLE_NAME);
        sqLiteDB.execSQL("DELETE FROM " + DBSchema.ExamTable.TABLE_NAME);
        sqLiteDB.execSQL("DELETE FROM " + DBSchema.HomeworkTable.TABLE_NAME);
        sqLiteDB.execSQL("DELETE FROM " + DBSchema.AffairTable.TABLE_NAME);
        data.clear();
    }
}
