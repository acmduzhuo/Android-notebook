package com.android.NoteBook.notepal.database;

/**
 * Created by John on 2018/1/13.
 */

public class DBSchema
{
    public static final String DB_NAME = "mynotes.db";   //数据库名
    public static final int DB_VERSION = 5;             //数据库版本号

    public static class NoteTable           //对应数据库中的notes表
    {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_THEME = "theme";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_DATE = "date";

        public static final String CREATE_NOTETABLE = "CREATE TABLE IF NOT EXISTS "
                + NoteTable.TABLE_NAME + "("
                + NoteTable.COLUMN_ID + " LONG PRIMARY KEY,"
                + NoteTable.COLUMN_THEME + " VARCHAR(50),"
                + NoteTable.COLUMN_CONTENT + " TEXT,"
                + NoteTable.COLUMN_DATE + " CHAR(10));";

        public static final String DROP_NOTETABLE = "DROP TABLE IF EXISTS "
                + NoteTable.TABLE_NAME + ";";
    }

    public static class ExamTable           //对应数据库中的exams表
    {
        public static final String TABLE_NAME = "exams";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_SUBJECT = "subject";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_PLACE = "place";

        public static final String CREATE_EXAMTABLE = "CREATE TABLE IF NOT EXISTS "
                + ExamTable.TABLE_NAME + "("
                + ExamTable.COLUMN_ID + " LONG PRIMARY KEY,"
                + ExamTable.COLUMN_SUBJECT + " VARCHAR(50),"
                + ExamTable.COLUMN_DESCRIPTION + " TEXT,"
                + ExamTable.COLUMN_DATE + " CHAR(10),"
                + ExamTable.COLUMN_TIME + " CHAR(5),"
                + ExamTable.COLUMN_PLACE + " VARCHAR(50));";

        public static final String DROP_EXAMTABLE = "DROP TABLE IF EXISTS "
                + ExamTable.TABLE_NAME + ";";
    }

    public static class HomeworkTable
    {
        public static final String TABLE_NAME = "homework";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_SUBJECT = "subject";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_CREATEDATE = "createDate";
        public static final String COLUMN_DEADLINE = "deadline";

        public static final String CREATE_HOMEWORKTABLE = "CREATE TABLE IF NOT EXISTS "
                + HomeworkTable.TABLE_NAME + "("
                + HomeworkTable.COLUMN_ID + " LONG PRIMARY KEY,"
                + HomeworkTable.COLUMN_SUBJECT + " VARCHAR(50),"
                + HomeworkTable.COLUMN_DESCRIPTION + " TEXT,"
                + HomeworkTable.COLUMN_CREATEDATE + " CHAR(10),"
                + HomeworkTable.COLUMN_DEADLINE + " CHAR(10));";

        public static final String DROP_HOMEWORKTABLE = "DROP TABLE IF EXISTS "
                + HomeworkTable.TABLE_NAME + ";";
    }

    public static class AffairTable
    {
        public static final String TABLE_NAME = "affair";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_THEME = "theme";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_PLACE = "place";

        public static final String CREATE_AFFAIRTABLE = "CREATE TABLE IF NOT EXISTS "
                + AffairTable.TABLE_NAME + "("
                + AffairTable.COLUMN_ID + " LONG PRIMARY KEY,"
                + AffairTable.COLUMN_THEME + " VARCHAR(50),"
                + AffairTable.COLUMN_DESCRIPTION + " TEXT,"
                + AffairTable.COLUMN_DATE + " CHAR(10),"
                + AffairTable.COLUMN_TIME + " CHAR(5),"
                + AffairTable.COLUMN_PLACE + " VARCHAR(50));";

        public  static final String DROP_AFFAIRTABLE = "DROP TABLE IF EXISTS "
                + AffairTable.TABLE_NAME + ";";
    }
}
