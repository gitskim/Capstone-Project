package com.bgirlogic.flare.data.sql;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by kimsuh on 5/23/16.
 */
public final class MuseContract {

    public MuseContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.bgirlogic.flare.app";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String[] getProjection() {
        return new String[]{MuseContract.JobEntry.COLUMN_JOB_NAME,
                MuseContract.JobEntry.COLUMN_COMPANY_NAME};
    }

    public static abstract class JobEntry implements BaseColumns {
        public static final String TABLE_NAME = "job_table";
        public static final String COLUMN_JOB_NAME = "job_name";
        public static final String COLUMN_COMPANY_NAME = "company_name";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + JobEntry.TABLE_NAME + " (" +
                    JobEntry._ID + " INTEGER PRIMARY KEY," +
                    JobEntry.COLUMN_JOB_NAME + TEXT_TYPE + COMMA_SEP +
                    JobEntry.COLUMN_COMPANY_NAME + TEXT_TYPE +
                    " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + JobEntry.TABLE_NAME;

    // create content uri
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
            .appendPath(JobEntry.TABLE_NAME).build();
    // create cursor of base type directory for multiple entries
    public static final String CONTENT_DIR_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + JobEntry.TABLE_NAME;
    // create cursor of base type item for single entry
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + JobEntry.TABLE_NAME;

    // for building URIs on insertion
    public static Uri buildJobUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }
}
