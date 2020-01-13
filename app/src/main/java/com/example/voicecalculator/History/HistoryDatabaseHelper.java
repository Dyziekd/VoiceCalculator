package com.example.voicecalculator.History;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDatabaseHelper extends SQLiteOpenHelper
{
    // database info
    public static final int DB_VERSION = 1;
    public  static final String DB_NAME = "CalculationsHistory.db";

    // database structure
    public static final String HISTORY_TABLE_NAME = "History";
    public static final String ID_FIELD_NAME = "_id";
    public static final String DATE_FIELD_NAME = "date";
    public static final String EXPRESSION_FIELD_NAME = "expression";
    public static final String RESULT_FIELD_NAME = "result";

    // database scripts
    private static final String CREATE_DB_SCRIPT = "CREATE TABLE " + HISTORY_TABLE_NAME + "( " +
                                                    ID_FIELD_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    DATE_FIELD_NAME + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                                                    EXPRESSION_FIELD_NAME + " TEXT, " +
                                                    RESULT_FIELD_NAME + " TEXT);";
    public static final String CLEAR_DB_SCRIPT = "DELETE FROM " + HISTORY_TABLE_NAME + ";";
    public static final String GET_HISTORY_QUERY = "SELECT * FROM " + HISTORY_TABLE_NAME + " ORDER BY " + DATE_FIELD_NAME + " DESC";

    // database constructor
    public HistoryDatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // create database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_DB_SCRIPT);
    }

    // upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
    }
}
