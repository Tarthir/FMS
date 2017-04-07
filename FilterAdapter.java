package com.tylerbrady34gmail.familyclient.FilterRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tylerbrady34gmail.familyclient.Models.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tyler on 4/3/2017.
 * Our adapter for Filter
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterViewHolder> {
    private ArrayList<FilterRows> mFilters;
    public FilterAdapter(Context context, ArrayList<FilterRows> filter) {
        super();
        mFilters = filter;
    }

    @Override
    public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new FilterViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(FilterViewHolder holder, int position) {
        holder.bind(mFilters.get(position));
    }

    @Override
    public int getItemCount() {
        return mFilters.size();
    }
}
