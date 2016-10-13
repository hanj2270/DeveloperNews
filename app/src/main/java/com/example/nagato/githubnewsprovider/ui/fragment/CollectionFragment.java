package com.example.nagato.githubnewsprovider.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nagato.githubnewsprovider.adapter.CollectionAdapter;
import com.example.nagato.githubnewsprovider.utils.CommonUtil;

/**
 * Created by nagato hanj
 */
public class CollectionFragment extends StuffBaseFragment {
    private static final String TAG = "CollectionFragment";
    private static final String TYPE = "col_type";

    public static CollectionFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(TYPE, type);

        CollectionFragment fragment = new CollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        super.initData();
        mType = getArguments().getString(TYPE);
    }

    @Override
    protected void loadingMore() {
        return;
    }

    @Override
    protected void fetchLatest() {
        setRefreshLayout(false);
        updateData();
    }

    @Override
    protected RecyclerView.Adapter initAdapter() {
        final CollectionAdapter adapter = new CollectionAdapter(getActivity(), mRealm, mType);
        adapter.setOnItemClickListener(new CollectionAdapter.OnItemClickListener() {
            @Override
            public boolean onItemLongClick(View v, int position) {
                if (mIsLoadingMore || mIsRefreshing)
                    return true;

                getActivity().startActionMode(new StuffFragment.ShareListener(getActivity(), adapter.getStuffAt(position), v));
                return true;
            }

            @Override
            public void onItemClick(View view, int pos) {
                if (mIsLoadingMore || mIsRefreshing)
                    return;

                CommonUtil.openUrl(getActivity(), adapter.getStuffAt(pos).getUrl());
            }
        });
        return adapter;
    }
}

