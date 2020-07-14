package com.android.NoteBook.notepal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.NoteBook.notepal.R;
import com.android.NoteBook.notepal.model.Affair;
import com.android.NoteBook.notepal.model.Exam;
import com.android.NoteBook.notepal.model.Homework;
import com.android.NoteBook.notepal.model.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by John on 2018/1/13.
 */

public class DataAdapter extends BaseAdapter
{
    private Context context;

    private List<Note> data;

    public DataAdapter(Context context,List<Note> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolderNote viewHolderNote = null;
        ViewHolderExam viewHolderExam = null;
        ViewHolderHomework viewHolderHomework = null;
        ViewHolderAffair viewHolderAffair = null;
        int type = getItemViewType(position);
        Note dataItem = (Note)getItem(position);
        if (convertView == null)
        {
            switch (type)   //根据不同的type来inflate不同的layout，然后设置不同的tag
            {
                case Note.TYPE_NOTE:
                    convertView = View.inflate(context,R.layout.listview_note,null);
                    viewHolderNote = new ViewHolderNote(convertView);
                    convertView.setTag(viewHolderNote);
                    break;
                case Note.TYPE_EXAM:
                    convertView = View.inflate(context,R.layout.listview_exam,null);
                    viewHolderExam = new ViewHolderExam(convertView);
                    convertView.setTag(viewHolderExam);
                    break;
                case Note.TYPE_HOMEWORK:
                    convertView = View.inflate(context,R.layout.listview_homework,null);
                    viewHolderHomework = new ViewHolderHomework(convertView);
                    convertView.setTag(viewHolderHomework);
                    break;
                case Note.TYPE_AFFAIR:
                    convertView = View.inflate(context,R.layout.listview_affair,null);
                    viewHolderAffair = new ViewHolderAffair(convertView);
                    convertView.setTag(viewHolderAffair);
                    break;
            }
        }
        else        //根据不同的type来获得tag
        {
            switch (type)
            {
                case Note.TYPE_NOTE:
                    viewHolderNote = (ViewHolderNote)convertView.getTag();
                    break;
                case Note.TYPE_EXAM:
                    viewHolderExam = (ViewHolderExam)convertView.getTag();
                    break;
                case Note.TYPE_HOMEWORK:
                    viewHolderHomework = (ViewHolderHomework)convertView.getTag();
                    break;
                case Note.TYPE_AFFAIR:
                    viewHolderAffair = (ViewHolderAffair)convertView.getTag();
                    break;
            }
        }

        switch (type)       //根据不同的type设置数据
        {
            case Note.TYPE_NOTE:
                viewHolderNote.themeText.setText(dataItem.getTheme());
                viewHolderNote.contentText.setText(dataItem.getContent());
                viewHolderNote.dateText.setText(dataItem.getDate());
                break;
            case Note.TYPE_EXAM:
                Exam exam = (Exam) dataItem;
                viewHolderExam.subjectText.setText(exam.getSubject());
                viewHolderExam.placeText.setText(exam.getPlace());
                viewHolderExam.dateText.setText(exam.getDate());
                viewHolderExam.timeText.setText(exam.getTime());
                break;
            case Note.TYPE_HOMEWORK:
                Homework homework = (Homework)dataItem;
                viewHolderHomework.subjectText.setText(homework.getSubject());
                viewHolderHomework.descriptionText.setText(homework.getDescription());
                viewHolderHomework.dateText.setText(homework.getCreateDate());
                viewHolderHomework.deadlineText.setText(homework.getDeadline());
                break;
            case Note.TYPE_AFFAIR:
                Affair affair = (Affair)dataItem;
                viewHolderAffair.themeText.setText(affair.getTheme());
                viewHolderAffair.placeText.setText(affair.getPlace());
                viewHolderAffair.dateText.setText(affair.getDate());
                viewHolderAffair.timeText.setText(affair.getTime());
                break;
        }
        return convertView;
    }

    class ViewHolderNote	//Note类型的内部类ViewHolder，用于优化加载性能
    {
        @BindView(R.id.themeText_listviewNote)
        TextView themeText;
        @BindView(R.id.contentText_listviewNote)
        TextView contentText;
        @BindView(R.id.dateText_listviewNote)
        TextView dateText;
        public ViewHolderNote(View view)        //使用ButterKnife
        {
            ButterKnife.bind(this,view);
        }
    }

    class ViewHolderExam      //Exam类的ViewHolder
    {
        @BindView(R.id.subjectText_listviewExam)
        TextView subjectText;
        @BindView(R.id.placeText_listviewExam)
        TextView placeText;
        @BindView(R.id.timeText_listviewExam)
        TextView timeText;
        @BindView(R.id.dateText_listviewExam)
        TextView dateText;
        public ViewHolderExam(View view)
        {
            ButterKnife.bind(this,view);
        }
    }

    class ViewHolderHomework    //Homework类的ViewHolder
    {
        @BindView(R.id.subjectText_listviewHomework)
        TextView subjectText;
        @BindView(R.id.descriptionText_listviewHomework)
        TextView descriptionText;
        @BindView(R.id.dateText_listviewHomework)
        TextView dateText;
        @BindView(R.id.deadlineText_listviewHomework)
        TextView deadlineText;
        public ViewHolderHomework(View view)
        {
            ButterKnife.bind(this,view);
        }
    }

    class ViewHolderAffair      //Affair类的ViewHolder
    {
        @BindView(R.id.themeText_listviewAffair)
        TextView themeText;
        @BindView(R.id.placeText_listviewAffair)
        TextView placeText;
        @BindView(R.id.dateText_listviewAffair)
        TextView dateText;
        @BindView(R.id.timeText_listviewAffair)
        TextView timeText;
        public ViewHolderAffair(View view)
        {
            ButterKnife.bind(this,view);
        }
    }
}