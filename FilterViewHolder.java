package com.tylerbrady34gmail.familyclient.FilterRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tylerbrady34gmail.familyclient.R;

/**
 * Created by tyler on 4/3/2017.
 */

public class FilterViewHolder extends RecyclerView.ViewHolder {
    public FilterViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.filter_view, parent, false));
    }
}
