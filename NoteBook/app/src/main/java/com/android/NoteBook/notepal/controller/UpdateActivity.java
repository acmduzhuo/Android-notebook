package com.android.NoteBook.notepal.controller;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.NoteBook.notepal.R;
import com.android.NoteBook.notepal.model.Note;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Created by John on 2018/1/20.
 */

public class UpdateActivity extends AppCompatActivity
{
    private String theme;
    private String content;
    private String subject;
    private String description;
    private String date;
    private String time;
    private String place;
    private String deadline;
    private int position;
    private int type;           //记录选中的记录的类型

    @Nullable
    @BindView(R.id.themeText_updateNote)
    EditText themeText_Note;

    @Nullable
    @BindView(R.id.contentText_updateNote)
    EditText contentText_Note;

    @Nullable
    @BindView(R.id.okButton_updateNote)
    Button okButton_Note;

    @Nullable
    @BindView(R.id.cancelButton_updateNote)
    Button cancelButton_Note;

    @Optional
    @OnClick(R.id.cancelButton_updateNote)
    public void cancelButtonNoteClick(View view)       //点击取消按钮，直接返回
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Optional
    @OnClick(R.id.okButton_updateNote)
    public void okButtonNoteClick(View view)        //点击确定按钮，检测是否更改，更改则回传数据
    {
        String newTheme = themeText_Note.getEditableText().toString();
        String newContent = contentText_Note.getEditableText().toString();
        if (newTheme.length() == 0)             //主题为空时给出提示
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示：");
            builder.setMessage("为确保记录完善，请输入您的主题");
            builder.setPositiveButton("好的",null);
            builder.show();
            return;
        }
        Intent intent = new Intent();
        if (newTheme.equals(theme) && newContent.equals(content))   //检测是否更改
            intent.putExtra("isChanged",false);
        else
        {                                                       //回传更改后的数据
            intent.putExtra("isChanged",true);
            intent.putExtra("type",type);
            intent.putExtra("theme",newTheme);
            intent.putExtra("content",newContent);
            intent.putExtra("position",position);
        }
        setResult(RESULT_OK,intent);
        finish();
    }

//*************************************************************************************
//  查看更改Exam所用的布局内容

    @Nullable
    @BindView(R.id.subjectText_updateExam)
    EditText subjectText_Exam;

    @Nullable
    @BindView(R.id.dateText_updateExam)
    TextView dateText_Exam;

    @Nullable
    @BindView(R.id.timeText_updateExam)
    TextView timeText_Exam;

    @Nullable
    @BindView(R.id.placeText_updateExam)
    EditText placeText_Exam;

    @Nullable
    @BindView(R.id.descriptionText_updateExam)
    EditText descriptionText_Exam;

    @Nullable
    @BindView(R.id.okButton_updateExam)
    Button okButton_Exam;

    @Nullable
    @BindView(R.id.cancelButton_updateExam)
    Button cancelButton_Exam;

    @Optional
    @OnClick(R.id.dateText_updateExam)
    public void setDate_Exam()                   //点击dateText设置日期
    {
        date = dateText_Exam.getText().toString();
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7)) - 1;  //传给DatePicker的月份从0开始
        int day = Integer.parseInt(date.substring(8,10));
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                null,year,month,day);
        //为了解决安卓4.4版本没有取消的问题，采用手动设置按钮事件
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = datePickerDialog.getDatePicker();
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int dayOfMonth = datePicker.getDayOfMonth();
                        String date = year + ".";
                        if (month < 9)
                            date += "0" + (month + 1);
                        else
                            date += (month + 1);
                        date += ".";
                        if (dayOfMonth < 10)
                            date += "0" + dayOfMonth;
                        else
                            date += dayOfMonth;
                        dateText_Exam.setText(date);
                    }
                });
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        datePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.timeText_updateExam)
    public void setTime_Exam()               //点击timeText设置时间
    {
        time = timeText_Exam.getText().toString();
        int hour;
        int minute;
        if (time.equals("点击设置时间"))          //如果之前没有设置时间
        {
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
        }
        else            //之前已经设置过时间，按格式获取
        {
            try {
                hour = Integer.parseInt(time.substring(0, 2));  //小时的长度可能1位或2位
                minute = Integer.parseInt(time.substring(3, 5));
            } catch(NumberFormatException e){
                hour = Integer.parseInt(time.substring(0,1));
                minute = Integer.parseInt(time.substring(2,4));
            }
        }
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":";
                if (minute < 10)
                    time += "0" + minute;
                else
                    time += minute;
                timeText_Exam.setText(time);
            }
        },hour,minute,true);
        timePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.cancelButton_updateExam)
    public void cancelButtonExamClick(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Optional
    @OnClick(R.id.okButton_updateExam)
    public void okButtonExamClick()
    {
        String newSubject = subjectText_Exam.getEditableText().toString();
        String newDescription = descriptionText_Exam.getEditableText().toString();
        String newDate = dateText_Exam.getText().toString();
        String newTime = timeText_Exam.getText().toString();
        String newPlace = placeText_Exam.getText().toString();
        if (newSubject.length() == 0)             //主题为空时给出提示
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("为确保记录完善，请输入您的科目");
            builder.setPositiveButton("好的",null);
            builder.show();
            return;
        }
        Intent intent = new Intent();
        //检测是否更改
        if (newTime.equals("点击设置时间"))
            newTime = "";
        if (newSubject.equals(subject) && newDescription.equals(description) &&
                newDate.equals(date) && newTime.equals(time) && newPlace.equals(place))
            intent.putExtra("isChanged",false);
        else
        {                                                       //回传更改后的数据
            intent.putExtra("isChanged",true);
            intent.putExtra("type",type);
            intent.putExtra("subject",newSubject);
            intent.putExtra("description",newDescription);
            intent.putExtra("date",newDate);
            intent.putExtra("time",newTime);
            intent.putExtra("place",newPlace);
            intent.putExtra("position",position);
        }
        setResult(RESULT_OK,intent);
        finish();
    }

//*************************************************************************************
//  查看更改Homework所用的布局

    @Nullable
    @BindView(R.id.subjectText_updateHomework)
    EditText subjectText_Homework;

    @Nullable
    @BindView(R.id.descriptionText_updateHomework)
    EditText descriptionText_Homework;

    @Nullable
    @BindView(R.id.deadlineText_updateHomework)
    TextView deadlineText_Homework;

    @Nullable
    @BindView(R.id.okButton_updateHomework)
    Button okButton_Homework;

    @Nullable
    @BindView(R.id.cancelButton_updateHomework)
    Button cancelButton_Homework;

    @Optional
    @OnClick(R.id.deadlineText_updateHomework)
    public void setDeadline_Homework()                   //点击deadlineText设置日期
    {
        int year;
        int month;
        int day;
        deadline = deadlineText_Homework.getText().toString();
        if (deadline.equals("点击设置deadline"))    //如果还没设置日期
        {
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);   //月从1开始
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }
        else
        {
            year = Integer.parseInt(deadline.substring(0,4));
            month = Integer.parseInt(deadline.substring(5,7)) - 1;  //传给DatePicker的月份从0开始
            day = Integer.parseInt(deadline.substring(8,10));
        }
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                null,year,month,day);
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = datePickerDialog.getDatePicker();
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int dayOfMonth = datePicker.getDayOfMonth();
                        String date = year + ".";
                        if (month < 9)
                            date += "0" + (month + 1);
                        else
                            date += (month + 1);
                        date += ".";
                        if (dayOfMonth < 10)
                            date += "0" + dayOfMonth;
                        else
                            date += dayOfMonth;
                        deadlineText_Homework.setText(date);
                    }
                });
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        datePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.cancelButton_updateHomework)
    public void cancelButtonHomeworkClick()
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Optional
    @OnClick(R.id.okButton_updateHomework)
    public void okButtonHomeworkClick()
    {
        String newSubject = subjectText_Homework.getEditableText().toString();
        String newDescription = descriptionText_Homework.getEditableText().toString();
        String newDeadline = deadlineText_Homework.getText().toString();
        if (newSubject.length() == 0)             //主题为空时给出提示
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示：");
            builder.setMessage("为确保记录完善，请输入您的科目");
            builder.setPositiveButton("好的",null);
            builder.show();
            return;
        }
        Intent intent = new Intent();
        //检测是否更改
        if (newDeadline.equals("点击设置deadline"))
            newDeadline = "";
        if (newSubject.equals(subject) && newDescription.equals(description) &&
                newDeadline.equals(deadline))
            intent.putExtra("isChanged",false);
        else
        {                                                       //回传更改后的数据
            intent.putExtra("isChanged",true);
            intent.putExtra("type",type);
            intent.putExtra("subject",newSubject);
            intent.putExtra("description",newDescription);
            intent.putExtra("deadline",newDeadline);
            intent.putExtra("position",position);
        }
        setResult(RESULT_OK,intent);
        finish();
    }

//*************************************************************************************
//  查看更改Affair类型记录所用布局

    @Nullable
    @BindView(R.id.themeText_updateAffair)
    EditText themeText_Affair;

    @Nullable
    @BindView(R.id.dateText_updateAffair)
    TextView dateText_Affair;

    @Nullable
    @BindView(R.id.timeText_updateAffair)
    TextView timeText_Affair;

    @Nullable
    @BindView(R.id.placeText_updateAffair)
    EditText placeText_Affair;

    @Nullable
    @BindView(R.id.descriptionText_updateAffair)
    EditText descriptionText_Affair;

    @Nullable
    @BindView(R.id.okButton_updateAffair)
    Button okButton_Affair;

    @Nullable
    @BindView(R.id.cancelButton_updateAffair)
    Button cancelButton_Affair;

    @Optional
    @OnClick(R.id.dateText_updateAffair)
    public void setDate_Affair()                   //点击dateText设置日期
    {
        date = dateText_Affair.getText().toString();
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7)) - 1;  //传给DatePicker的月份从0开始
        int day = Integer.parseInt(date.substring(8,10));
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                null,year,month,day);
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = datePickerDialog.getDatePicker();
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int dayOfMonth = datePicker.getDayOfMonth();
                        String date = year + ".";
                        if (month < 9)
                            date += "0" + (month + 1);
                        else
                            date += (month + 1);
                        date += ".";
                        if (dayOfMonth < 10)
                            date += "0" + dayOfMonth;
                        else
                            date += dayOfMonth;
                        dateText_Affair.setText(date);
                    }
                });
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        datePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.timeText_updateAffair)
    public void setTime_Affair()               //点击timeText设置时间
    {
        time = timeText_Affair.getText().toString();
        int hour;
        int minute;
        if (time.equals("点击设置时间"))          //如果之前没有设置时间
        {
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
        }
        else            //之前已经设置过时间，按格式获取
        {
            try {
                hour = Integer.parseInt(time.substring(0, 2));  //小时的长度可能1位或2位
                minute = Integer.parseInt(time.substring(3, 5));
            } catch(NumberFormatException e){
                hour = Integer.parseInt(time.substring(0,1));
                minute = Integer.parseInt(time.substring(2,4));
            }

        }
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":";
                if (minute < 10)
                    time += "0" + minute;
                else
                    time += minute;
                timeText_Affair.setText(time);
            }
        },hour,minute,true);
        timePickerDialog.show();
    }

    @Optional
    @OnClick(R.id.cancelButton_updateAffair)
    public void cancelButtonAffairClick(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Optional
    @OnClick(R.id.okButton_updateAffair)
    public void OkButtonAffairClick()
    {
        String newTheme = themeText_Affair.getEditableText().toString();
        String newDescription = descriptionText_Affair.getEditableText().toString();
        String newDate = dateText_Affair.getText().toString();
        String newTime = timeText_Affair.getText().toString();
        String newPlace = placeText_Affair.getText().toString();
        if (newTheme.length() == 0)             //主题为空时给出提示
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示：");
            builder.setMessage("为确保记录完善，请输入您的内容");
            builder.setPositiveButton("好的",null);
            builder.show();
            return;
        }
        Intent intent = new Intent();
        //检测是否更改
        if (newTime.equals("点击设置时间"))
            newTime = "";
        if (newTheme.equals(theme) && newDescription.equals(description) &&
                newDate.equals(date) && newTime.equals(time) && newPlace.equals(place))
            intent.putExtra("isChanged",false);
        else
        {                                                       //回传更改后的数据
            intent.putExtra("isChanged",true);
            intent.putExtra("type",type);
            intent.putExtra("theme",newTheme);
            intent.putExtra("description",newDescription);
            intent.putExtra("date",newDate);
            intent.putExtra("time",newTime);
            intent.putExtra("place",newPlace);
            intent.putExtra("position",position);
        }
        setResult(RESULT_OK,intent);
        finish();
    }

//*************************************************************************************
//  onCreate
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);   //用于回传供删除使用
        type = getIntent().getIntExtra("type",-1);
        switch(type)                //根据记录类型加载布局和内容
        {
            case Note.TYPE_NOTE:
                setContentView(R.layout.update_note);
                ButterKnife.bind(this);
                theme = intent.getStringExtra("theme");
                content = intent.getStringExtra("content");
                themeText_Note.setText(theme);
                contentText_Note.setText(content);
                break;
            case Note.TYPE_EXAM:
                setContentView(R.layout.update_exam);
                ButterKnife.bind(this);
                subject = intent.getStringExtra("subject");
                description = intent.getStringExtra("description");
                date = intent.getStringExtra("date");
                time = intent.getStringExtra("time");
                place = intent.getStringExtra("place");
                subjectText_Exam.setText(subject);
                dateText_Exam.setText(date);
                if (time.length() == 0)
                    timeText_Exam.setText("点击设置时间");
                else
                    timeText_Exam.setText(time);
                placeText_Exam.setText(place);
                descriptionText_Exam.setText(description);
                break;
            case Note.TYPE_HOMEWORK:
                setContentView(R.layout.update_homework);
                ButterKnife.bind(this);
                subject = intent.getStringExtra("subject");
                description = intent.getStringExtra("description");
                deadline = intent.getStringExtra("deadline");
                subjectText_Homework.setText(subject);
                descriptionText_Homework.setText(description);
                if (deadline.length() == 0)
                    deadlineText_Homework.setText("点击设置deadline");
                else
                    deadlineText_Homework.setText(deadline);
                break;
            case Note.TYPE_AFFAIR:
                setContentView(R.layout.update_affair);
                ButterKnife.bind(this);
                theme = intent.getStringExtra("theme");
                description = intent.getStringExtra("description");
                date = intent.getStringExtra("date");
                time = intent.getStringExtra("time");
                place = intent.getStringExtra("place");
                themeText_Affair.setText(theme);
                descriptionText_Affair.setText(description);
                dateText_Affair.setText(date);
                if (time.length() == 0)
                    timeText_Affair.setText("点击设置时间");
                else
                    timeText_Affair.setText(time);
                placeText_Affair.setText(place);
                break;
        }
    }
}
