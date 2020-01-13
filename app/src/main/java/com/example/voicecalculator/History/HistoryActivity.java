package com.example.voicecalculator.History;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.voicecalculator.R;

public class HistoryActivity extends AppCompatActivity
{
    private ListView historyList;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initResources();
    }

    // init resources
    private void initResources()
    {
        // create history list
        historyList = findViewById(R.id.history__list_view);

        // load calculations history from database
        Cursor historyCursor = loadHistory();

        // create and set adapter
        historyAdapter = new HistoryAdapter(this, historyCursor);
        historyList.setAdapter(historyAdapter);

    }

    // return calculations history from database
    private Cursor loadHistory()
    {
        // open database
        HistoryDatabaseHelper dbHelper = new HistoryDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // create cursor
        Cursor cursor = db.rawQuery(HistoryDatabaseHelper.GET_HISTORY_QUERY, null);

        // return cursor
        return cursor;
    }

    // clear history (on button click)
    public void clearHistory(View view)
    {
        // open database
        HistoryDatabaseHelper dbHelper = new HistoryDatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // clear history
        db.execSQL(HistoryDatabaseHelper.CLEAR_DB_SCRIPT);

        // reload cursor and notify adapter about data change
        Cursor historyCursor = loadHistory();
        historyAdapter.changeCursor(historyCursor);
        historyAdapter.notifyDataSetChanged();

        // close database
        dbHelper.close();
        db.close();
    }
}
