package com.bgirlogic.flare.data.sql;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by kimsuh on 5/23/16.
 */
public class MuseProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    //code for the UriMatcher
    private static final int JOB = 100;
    private static final int JOB_WITH_ID = 200;

    private MuseDBHelper mOpenHelper;

    private static UriMatcher buildUriMatcher() {
        //Build a UriMatcher by adding a specific code to return based on a match
        //it's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MuseContract.CONTENT_AUTHORITY;

        //add a code for each type of URI you want.
        matcher.addURI(authority, MuseContract.JobEntry.TABLE_NAME, JOB);
        matcher.addURI(authority, MuseContract.JobEntry.TABLE_NAME + "/#", JOB_WITH_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MuseDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            //all movies selected
            case JOB: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MuseContract.JobEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                return retCursor;
            }

            //individual job based on id selected
            case JOB_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        MuseContract.JobEntry.TABLE_NAME,
                        projection,
                        MuseContract.JobEntry._ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                return retCursor;
            }
            default:
                //be default, a bad uri is assumed
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case JOB: {
                return MuseContract.CONTENT_DIR_TYPE;
            }
            case JOB_WITH_ID: {
                return MuseContract.CONTENT_ITEM_TYPE;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;
        switch (sUriMatcher.match(uri)) {
            case JOB: {
                long _id = db.insert(MuseContract.JobEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    // when the id exists, the uri is built with the method from the contract.
                    returnUri = MuseContract.buildJobUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into: " + uri);
                }

                break;
            }

            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int numDeleted;
        switch (match) {
            case JOB:
                numDeleted = db.delete(MuseContract.JobEntry.TABLE_NAME,
                        selection, selectionArgs);
                //reset _ID
                db.execSQL("DELETE FROM SQLITE SEQUENCE WHERE NAME = '"
                        + MuseContract.JobEntry.TABLE_NAME + "'");
                break;
            case JOB_WITH_ID:
                numDeleted = db.delete(MuseContract.JobEntry.TABLE_NAME,
                        MuseContract.JobEntry._ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return numDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int numUpdated = 0;

        if (values == null) {
            throw new IllegalArgumentException("Cannot have null content values");
        }

        switch (sUriMatcher.match(uri)) {
            case JOB: {
                numUpdated = db.update(MuseContract.JobEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            }
            case JOB_WITH_ID: {
                numUpdated = db.update(MuseContract.JobEntry.TABLE_NAME,
                        values,
                        MuseContract.JobEntry._ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }

        if (numUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numUpdated;
    }
}
