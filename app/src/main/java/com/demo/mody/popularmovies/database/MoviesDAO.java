package com.demo.mody.popularmovies.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.demo.mody.popularmovies.models.DiscoverMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahmoud El-Metainy on 09-Jan-16.
 */
public class MoviesDAO {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public MoviesDAO(Context context) {

        dbHelper = new DBHelper(context);
    }

    /**
     * Adds new movie to the user's favorite movies table
     *
     * @param discoverMovie object
     */
    public void addMovie(DiscoverMovie discoverMovie) {

        database = dbHelper.getWritableDatabase();

        ContentValues value = new ContentValues();

        // Inserting Row
        value.put(DBHelper.COL_FAVORITES_MOVIE_ID, discoverMovie.getMovieID());
        value.put(DBHelper.COL_FAVORITES_POSTER_URL, discoverMovie.getPosterPath());
        database.insert(DBHelper.TABLE_FAVORITES, null, value);

        // Closing database connection
        database.close();
    }

    /**
     * Gets a list containing all user's favorite movies
     *
     * @return List of DiscoverMovies
     */
    public List<DiscoverMovie> getAllMovies() {

        database = dbHelper.getReadableDatabase();

        List<DiscoverMovie> discoverMovieList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_FAVORITES, null);

        // Checking for data and looping through all rows and adding to a list
        if (cursor != null && cursor.moveToFirst()) {

            do {
                DiscoverMovie discoverMovie = new DiscoverMovie();

                discoverMovie.setMovieID(cursor.getInt(cursor.getColumnIndex(DBHelper.COL_FAVORITES_MOVIE_ID)));
                discoverMovie.setPosterPath(cursor.getString(cursor.getColumnIndex(DBHelper.COL_FAVORITES_POSTER_URL)));

                discoverMovieList.add(discoverMovie);

            } while (cursor.moveToNext());
        }

        // Closing database connection
        database.close();

        return discoverMovieList;
    }

    /**
     * Checks if this particular movie already exist in the favorites
     *
     * @param movieID The id of the selected movie
     * @return True if the movie already exist, false otherwise
     */
    public boolean isAdded(int movieID) {

        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT " + DBHelper.COL_FAVORITES_MOVIE_ID + " FROM " + DBHelper.TABLE_FAVORITES + " WHERE " + DBHelper.COL_FAVORITES_MOVIE_ID + " = " + movieID, null);

        if (cursor.getCount() == 0) {
            return false;
        }

        return true;
    }

    /**
     * Deletes a certain movie from user's favorite movie collection
     *
     * @param movieID The unique id for the selected movie
     */
    public void deleteMovie(int movieID) {

        database = dbHelper.getWritableDatabase();

        database.delete(DBHelper.TABLE_FAVORITES, DBHelper.COL_FAVORITES_MOVIE_ID + " = ?", new String[]{String.valueOf(movieID)});

        database.close();
    }
}
