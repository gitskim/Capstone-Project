package com.bgirlogic.flare.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bgirlogic.flare.MuseAsyncTask;
import com.bgirlogic.flare.R;
import com.bgirlogic.flare.data.models.Response1;

import java.util.concurrent.ExecutionException;

/**
 * Created by kimsuh on 3/26/16.
 */
public class HomeFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Response1 mResponse;

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
        try {
            mResponse = new MuseAsyncTask(getContext()).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.job_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        //TODO: do it in onResume
        if (mResponse != null) {
            mAdapter = new JobViewAdapter(mResponse);
        }
        mRecyclerView.setAdapter(mAdapter);
        return mView;
    }
}
