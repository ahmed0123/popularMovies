package com.demo.mody.popularmovies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mahmoud El-Metainy on 09-Jan-16.
 */
public class DBHelper extends SQLiteOpenHelper {

    // Database name
    public static final String DATABASE_NAME = "popular_movies.db";
    // Database version
    public static final int DATABASE_VERSION = 1;
    // Tables names
    public static final String TABLE_FAVORITES = "movies";
    // Users columns
    public static final String COL_FAVORITES_MOVIE_ID = "movieID";
    public static final String COL_FAVORITES_POSTER_URL = "movieURL";
    // Create Favorites table statement
    private static final String CREATE_TABLE_FAVORITES = " CREATE TABLE " + TABLE_FAVORITES + "(" + COL_FAVORITES_MOVIE_ID + " INTEGER PRIMARY KEY," + COL_FAVORITES_POSTER_URL + " TEXT )";

    private Context context;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Creating database tables
        db.execSQL(CREATE_TABLE_FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);

        // Create tables again
        onCreate(db);
    }
}
