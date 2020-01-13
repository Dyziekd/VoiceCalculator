package com.example.voicecalculator.History;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.voicecalculator.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryAdapter extends CursorAdapter
{
    // adapter constructor
    public HistoryAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, 0);
    }

    // inflate layout from xml to new row
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    // fill new row with data
    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        // create new row resources
        TextView dateTextView = view.findViewById(R.id.list__date);
        TextView timeTextView = view.findViewById(R.id.list__time);
        TextView expressionTextView = view.findViewById(R.id.list__expression);
        TextView resultTextView = view.findViewById(R.id.list__result);

        // get new row data
        String fullDate = cursor.getString(cursor.getColumnIndexOrThrow(HistoryDatabaseHelper.DATE_FIELD_NAME));
        String date = formatDate(fullDate, new SimpleDateFormat("dd.MM.yyyy"));
        String time = formatDate(fullDate, new SimpleDateFormat("kk:mm"));
        String expression = cursor.getString(cursor.getColumnIndexOrThrow(HistoryDatabaseHelper.EXPRESSION_FIELD_NAME)) + "=";
        String result = cursor.getString(cursor.getColumnIndexOrThrow(HistoryDatabaseHelper.RESULT_FIELD_NAME));

        // set new row data
        dateTextView.setText(date);
        timeTextView.setText(time);
        expressionTextView.setText(expression);
        resultTextView.setText(result);
    }

    // convert date to passed format (from yyyy-MM-dd kk:mm)
    private String formatDate(String date, SimpleDateFormat newDateFormat)
    {
        try
        {
            // create old date format
            SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");

            // get date in old format
            Date date1 = oldDateFormat.parse(date);

            // parse date to string in new format
            return newDateFormat.format(date1);
        }
        catch (ParseException e)
        {
            Log.e("Date parsing error","An error occurred when parsing date");
            e.printStackTrace();
        }

        return "error";
    }

}
