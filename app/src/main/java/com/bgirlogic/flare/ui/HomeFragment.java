package com.bgirlogic.flare.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bgirlogic.flare.MuseAsyncTask;
import com.bgirlogic.flare.R;
import com.bgirlogic.flare.common.Utils;
import com.bgirlogic.flare.data.models.Response1;
import com.bgirlogic.flare.data.sql.MuseContract;

import java.util.concurrent.ExecutionException;

/**
 * Created by kimsuh on 3/26/16.
 */
public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks,
        MuseAsyncTask.OnTaskCompleted {

    private View mView;
    private RecyclerView mRecyclerView;
    private JobViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Cursor mCursor;
    private ContentResolver mContentResolver;
    private static final int MUSE_LOADER = 0;
    private Response1 mResponse;
    private boolean onTaskCompleted, onLoadFinished;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        //no args
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mcursor", "onCreate");

        mContentResolver = getContext().getContentResolver();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("mcursor", "onCreateView");

        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.job_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Utils.isConnectedToInternet()) {
            try {
                mResponse = new MuseAsyncTask(getContext(), this, MainActivity.getZipCode()).execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "INTERNET LOST", Toast.LENGTH_LONG).show();
        }
        getActivity().getSupportLoaderManager().initLoader(MUSE_LOADER, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public android.support.v4.content.Loader onCreateLoader(int id, Bundle args) {
        Log.d("mcursor", "onCreateLoader");

        Uri uri = MuseContract.CONTENT_URI;
        return new CursorLoader(getContext(), uri, MuseContract.getProjection(), null, null, null);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader loader, Object data) {
        Log.d("mcursor", "onLoadFinished + " + onTaskCompleted);

        mCursor = mContentResolver.query(
                MuseContract.CONTENT_URI,
                MuseContract.getProjection(),
                null,
                null,
                null);

        mAdapter = new JobViewAdapter(mCursor);
        mAdapter.setCursor(mCursor);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
//        Log.d("mcursor", "mcursor adapter set " + mCursor.getCount());
        onLoadFinished = true;
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {
        Log.d("mcursor", "onLoaderReset");
    }

    @Override
    public void onTaskCompleted(Response1 results) {
        Log.d("mcursor", "onTaskCompleted");

//        if (results.getResults().size() > 0) {
//            ContentValues values = new ContentValues();
//            for (int i = 0; i < results.getResults().size(); i++) {
//                values.put(MuseContract.JobEntry.COLUMN_JOB_NAME,
//                        results.getResults().get(i).getName());
//                values.put(MuseContract.JobEntry.COLUMN_COMPANY_NAME,
//                        results.getResults().get(i).getCompany().getShort_name());
//                getContext().getContentResolver().insert(MuseContract.CONTENT_URI, values);
//            }
//        }
        onTaskCompleted = true;

        if (onLoadFinished) {
//            mCursor = mContentResolver.query(
//                    MuseContract.CONTENT_URI,
//                    MuseContract.getProjection(),
//                    null,
//                    null,
//                    null);
//            mAdapter.setCursor(mCursor);
//
//            mAdapter.notifyDataSetChanged();
//            Uri uri = MuseContract.CONTENT_URI;
//            onLoadFinished(new CursorLoader(getContext(), uri, MuseContract.getProjection(), null, null, null),
//                    mCursor);
        }
    }
}
