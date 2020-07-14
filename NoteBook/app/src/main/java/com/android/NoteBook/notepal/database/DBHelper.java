package com.android.NoteBook.notepal.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.NoteBook.notepal.model.Affair;
import com.android.NoteBook.notepal.model.Exam;
import com.android.NoteBook.notepal.model.Homework;
import com.android.NoteBook.notepal.model.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by John on 2018/1/13.
 */

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(Context context) {
        super(context, DBSchema.DB_NAME, null, DBSchema.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DBSchema.NoteTable.CREATE_NOTETABLE);
        db.execSQL(DBSchema.ExamTable.CREATE_EXAMTABLE);
        db.execSQL(DBSchema.HomeworkTable.CREATE_HOMEWORKTABLE);
        db.execSQL(DBSchema.AffairTable.CREATE_AFFAIRTABLE);

        //插入引导用记录
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);   //月从1开始
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = year + ".";
        if (month < 9)
            date += "0" + (month + 1);
        else
            date += (month + 1);
        date += ".";
        if (day < 10)
            date += "0" + day;
        else
            date += day;
        db.execSQL("INSERT INTO " + DBSchema.NoteTable.TABLE_NAME + "(" +
                DBSchema.NoteTable.COLUMN_ID + "," +
                DBSchema.NoteTable.COLUMN_THEME + "," +
                DBSchema.NoteTable.COLUMN_CONTENT + "," +
                DBSchema.NoteTable.COLUMN_DATE +
                ") " +
                "VALUES(" + "'" +
                0 + "'," + "'" +
                "欢迎使用" + "'," + "'" +
                "长按我删除" + "'," + "'" +
                date + "'" +
                ")");
        db.execSQL("INSERT INTO " + DBSchema.NoteTable.TABLE_NAME + "(" +
                DBSchema.NoteTable.COLUMN_ID + "," +
                DBSchema.NoteTable.COLUMN_THEME + "," +
                DBSchema.NoteTable.COLUMN_CONTENT + "," +
                DBSchema.NoteTable.COLUMN_DATE +
                ") " +
                "VALUES(" + "'" +
                1 + "'," + "'" +
                "查看和更改" + "'," + "'" +
                "点击我查看详情" + "'," + "'" +
                date + "'" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        List<Note> data = new ArrayList<>();
        //获取原有Note类型的记录
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBSchema.NoteTable.TABLE_NAME,
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

        //删除旧表，创建新表
        db.execSQL(DBSchema.NoteTable.DROP_NOTETABLE);
        db.execSQL(DBSchema.NoteTable.CREATE_NOTETABLE);

        //插入原有记录到新数据库
        Note tempNote = null;
        for (int i = 0, len = data.size(); i < len; i++)
        {
            tempNote = data.get(i);
            db.execSQL("INSERT INTO " + DBSchema.NoteTable.TABLE_NAME + "(" +
                    DBSchema.NoteTable.COLUMN_ID + "," +
                    DBSchema.NoteTable.COLUMN_THEME + "," +
                    DBSchema.NoteTable.COLUMN_CONTENT + "," +
                    DBSchema.NoteTable.COLUMN_DATE +
                    ") " +
                    "VALUES(" + "'" +
                    tempNote.getId() + "'," + "'" +
                    tempNote.getTheme() + "'," + "'" +
                    tempNote.getContent() + "'," + "'" +
                    tempNote.getDate() + "'" +
                    ")");
        }
        data.clear();      //清空Note类型记录

        //获取原有Exam类型的记录
        cursor = db.rawQuery("SELECT * FROM " + DBSchema.ExamTable.TABLE_NAME,
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

        //删除旧表，创建新表
        db.execSQL(DBSchema.ExamTable.DROP_EXAMTABLE);
        db.execSQL(DBSchema.ExamTable.CREATE_EXAMTABLE);

        //将原有Exam类型的数据插入到新表中
        Exam tempExam = null;
        for (int i = 0, len = data.size(); i < len; i++)
        {
            tempExam = (Exam)data.get(i);
            db.execSQL("INSERT INTO " + DBSchema.ExamTable.TABLE_NAME + "(" +
                    DBSchema.ExamTable.COLUMN_ID + "," +
                    DBSchema.ExamTable.COLUMN_SUBJECT + "," +
                    DBSchema.ExamTable.COLUMN_DESCRIPTION + "," +
                    DBSchema.ExamTable.COLUMN_DATE + "," +
                    DBSchema.ExamTable.COLUMN_TIME + "," +
                    DBSchema.ExamTable.COLUMN_PLACE +
                    ") " +
                    "VALUES(" + "'" +
                    tempExam.getId() + "'," + "'" +
                    tempExam.getSubject() + "'," + "'" +
                    tempExam.getDescription() + "'," + "'" +
                    tempExam.getDate() + "'," + "'" +
                    tempExam.getTime() + "'," + "'" +
                    tempExam.getPlace() + "'" +
                    ")");
        }
        data.clear();       //清空Exam类型记录

        //获取原有Homework类型的记录
        cursor = db.rawQuery("SELECT * FROM " + DBSchema.HomeworkTable.TABLE_NAME,
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

        //删除旧表，创建新表
        db.execSQL(DBSchema.HomeworkTable.DROP_HOMEWORKTABLE);
        db.execSQL(DBSchema.HomeworkTable.CREATE_HOMEWORKTABLE);

        //将原有Homework类型记录插入到新表中
        Homework tempHomework = null;
        for (int i = 0, len = data.size(); i < len; i++)
        {
            tempHomework = (Homework)data.get(i);
            db.execSQL("INSERT INTO " + DBSchema.HomeworkTable.TABLE_NAME + "(" +
                    DBSchema.HomeworkTable.COLUMN_ID + "," +
                    DBSchema.HomeworkTable.COLUMN_SUBJECT + "," +
                    DBSchema.HomeworkTable.COLUMN_DESCRIPTION + "," +
                    DBSchema.HomeworkTable.COLUMN_CREATEDATE + "," +
                    DBSchema.HomeworkTable.COLUMN_DEADLINE +
                    ") " +
                    "VALUES(" + "'" +
                    tempHomework.getId() + "'," + "'" +
                    tempHomework.getSubject() + "'," + "'" +
                    tempHomework.getDescription() + "'," + "'" +
                    tempHomework.getCreateDate() + "'," + "'" +
                    tempHomework.getDeadline() + "'" +
                    ")");
        }
        data.clear();       //清空Homework类型的记录

        //获取原有Affair类型的记录
        cursor = db.rawQuery("SELECT * FROM " + DBSchema.AffairTable.TABLE_NAME,
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

        //删除旧表，创建新表
        db.execSQL(DBSchema.AffairTable.DROP_AFFAIRTABLE);
        db.execSQL(DBSchema.AffairTable.CREATE_AFFAIRTABLE);

        //将原有Affair型记录插入到新表
        Affair tempAffair = null;
        for (int i = 0, len = data.size(); i < len; i++)
        {
            tempAffair = (Affair)data.get(i);
            db.execSQL("INSERT INTO " + DBSchema.AffairTable.TABLE_NAME + "(" +
                    DBSchema.AffairTable.COLUMN_ID + "," +
                    DBSchema.AffairTable.COLUMN_THEME + "," +
                    DBSchema.AffairTable.COLUMN_DESCRIPTION + "," +
                    DBSchema.AffairTable.COLUMN_DATE + "," +
                    DBSchema.AffairTable.COLUMN_TIME + "," +
                    DBSchema.AffairTable.COLUMN_PLACE +
                    ") " +
                    "VALUES(" + "'" +
                    tempAffair.getId() + "'," + "'" +
                    tempAffair.getTheme() + "'," + "'" +
                    tempAffair.getDescription() + "'," + "'" +
                    tempAffair.getDate() + "'," + "'" +
                    tempAffair.getTime() + "'," + "'" +
                    tempAffair.getPlace() + "'" +
                    ")");
        }
    }
}
